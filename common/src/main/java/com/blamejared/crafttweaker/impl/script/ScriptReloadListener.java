package com.blamejared.crafttweaker.impl.script;

import com.blamejared.crafttweaker.CraftTweakerCommon;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.ingredient.IngredientCacheBuster;
import com.blamejared.crafttweaker.api.tag.CraftTweakerTagRegistry;
import com.blamejared.crafttweaker.api.util.sequence.SequenceManager;
import com.blamejared.crafttweaker.api.villager.CTVillagerTrades;
import com.blamejared.crafttweaker.api.zencode.scriptrun.IScriptRun;
import com.blamejared.crafttweaker.api.zencode.scriptrun.ScriptDiscoveryConfiguration;
import com.blamejared.crafttweaker.api.zencode.scriptrun.ScriptRunConfiguration;
import com.blamejared.crafttweaker.mixin.common.access.recipe.AccessRecipeManager;
import com.blamejared.crafttweaker.mixin.common.access.tag.AccessTagManager;
import com.blamejared.crafttweaker.platform.helper.IAccessibleServerElementsProvider;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.tags.TagManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public final class ScriptReloadListener extends SimplePreparableReloadListener<Void> {
    
    private record RunPreparation(IScriptRun run, Path root, List<Path> scripts) {}
    
    private static final MutableComponent MSG_RELOAD_STARTING = Component.translatable("crafttweaker.reload.start");
    private static final MutableComponent MSG_RELOAD_COMPLETE = Component.translatable("crafttweaker.reload.complete");
    private static final Random RANDOM = ThreadLocalRandom.current();
    
    private final ReloadableServerResources resources;
    private final Consumer<MutableComponent> feedbackConsumer;
    
    public ScriptReloadListener(final ReloadableServerResources managerSupplier, final Consumer<MutableComponent> feedbackConsumer) {
        
        this.resources = managerSupplier;
        this.feedbackConsumer = feedbackConsumer;
    }
    
    
    @Override
    protected Void prepare(ResourceManager resourceManager, ProfilerFiller profiler) {
        
        return null;
    }
    
    @Override
    protected void apply(Void object, ResourceManager resourceManager, ProfilerFiller profiler) {
        
        IngredientCacheBuster.claim();
        SequenceManager.clearSequences();
        CTVillagerTrades.clear();
        IAccessibleServerElementsProvider asep = CraftTweakerAPI.getAccessibleElementsProvider().server();
        asep.resources(this.resources);
        TagManager tagmanager = asep.accessibleResources().crafttweaker$getTagManager();
        asep.registryAccess(((AccessTagManager) tagmanager).crafttweaker$getRegistryAccess());
        CraftTweakerTagRegistry.INSTANCE.bind(tagmanager);
        
        final RecipeManager manager = this.resources.getRecipeManager();
        
        this.feedbackConsumer.accept(MSG_RELOAD_STARTING);
        this.fixRecipeManager(manager);
        final RunPreparation runPreparation = this.prepareRun();
        
        try {
            runPreparation.run().execute();
        } catch(final Throwable e) {
            CraftTweakerCommon.logger().error("Unable to execute script run", e);
            return;
        } finally {
            IngredientCacheBuster.release();
        }
        
        this.storeScriptsInRecipes(manager, runPreparation.root(), runPreparation.scripts());
        
        this.feedbackConsumer.accept(MSG_RELOAD_COMPLETE);
        if(!runPreparation.scripts().isEmpty() && runPreparation.run().specificRunInfo().displayBranding()) {
            
            this.displayPatreonBranding();
        }
    }
    
    private void fixRecipeManager(final RecipeManager manager) {
        
        final AccessRecipeManager accessRecipeManager = (AccessRecipeManager) manager;
        accessRecipeManager.crafttweaker$setByType(HashMultimap.create(accessRecipeManager.crafttweaker$getByType()));
        accessRecipeManager.crafttweaker$setByName(new HashMap<>(accessRecipeManager.crafttweaker$getByName()));
        CraftTweakerAPI.getAccessibleElementsProvider().recipeManager(manager);
    }
    
    private RunPreparation prepareRun() {
        
        class Retainer implements ScriptDiscoveryConfiguration.DiscoveryRetainer {
            
            Path root;
            List<Path> scripts;
            
            @Override
            public void retain(final Path root, final List<Path> discoveryResults) {
                
                this.root = root;
                this.scripts = discoveryResults;
            }
            
        }
        
        final Retainer retainer = new Retainer();
        final ScriptDiscoveryConfiguration discoveryConfiguration = new ScriptDiscoveryConfiguration(
                ScriptDiscoveryConfiguration.SuspiciousNamesBehavior.WARN,
                retainer
        );
        final ScriptRunConfiguration runConfiguration = new ScriptRunConfiguration(
                CraftTweakerConstants.DEFAULT_LOADER_NAME,
                CraftTweakerConstants.RELOAD_LISTENER_SOURCE_ID,
                ScriptRunConfiguration.RunKind.EXECUTE
        );
        
        final IScriptRun run = CraftTweakerAPI.getScriptRunManager()
                .createScriptRun(CraftTweakerAPI.getScriptsDirectory(), discoveryConfiguration, runConfiguration);
        
        return new RunPreparation(run, retainer.root, retainer.scripts);
    }
    
    private void storeScriptsInRecipes(final RecipeManager manager, final Path root, final List<Path> scripts) {
        
        final Multimap<RecipeType<?>, RecipeHolder<?>> recipes = ((AccessRecipeManager) manager).crafttweaker$getByType();
        scripts.stream()
                .map(it -> this.buildScriptRecipe(it, root))
                .forEach(it -> recipes.put(it.getType(), new RecipeHolder<>(it.getId(), it)));
    }
    
    private ScriptRecipe buildScriptRecipe(final Path file, final Path root) {
        
        final String fileName = root.relativize(file).toString().replace('\\', '/');
        return new ScriptRecipe(fileName, this.readContents(file));
    }
    
    private String readContents(final Path file) {
        
        try {
            return String.join("\n", Files.readAllLines(file));
        } catch(final IOException e) {
            CraftTweakerCommon.logger().info("Unable to read script file " + file, e);
            return "";
        }
    }
    
    private void displayPatreonBranding() {
        
        final Collection<String> patronList = CraftTweakerCommon.getPatronList();
        
        if(patronList.isEmpty()) {
            return;
        }
        
        patronList.stream()
                .skip(RANDOM.nextInt(patronList.size()))
                .findFirst()
                .ifPresent(name -> CraftTweakerCommon.logger()
                        .info("This reload was made possible by {} and more! Become a patron at https://patreon.com/jaredlll08?s=crtmod", name));
    }
    
}
