package com.blamejared.crafttweaker.platform;

import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.fluid.MCFluidStack;
import com.blamejared.crafttweaker.api.fluid.MCFluidStackMutable;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.ingredient.condition.IngredientConditions;
import com.blamejared.crafttweaker.api.ingredient.transformer.IngredientTransformers;
import com.blamejared.crafttweaker.api.ingredient.vanilla.CraftTweakerIngredients;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.item.MCItemStack;
import com.blamejared.crafttweaker.api.item.MCItemStackMutable;
import com.blamejared.crafttweaker.api.loot.LootModifierManager;
import com.blamejared.crafttweaker.api.loot.modifier.ILootModifier;
import com.blamejared.crafttweaker.api.mod.Mod;
import com.blamejared.crafttweaker.api.mod.PlatformMod;
import com.blamejared.crafttweaker.api.recipe.handler.helper.CraftingTableRecipeConflictChecker;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.impl.fluid.SimpleFluidStack;
import com.blamejared.crafttweaker.impl.mod.FabricMod;
import com.blamejared.crafttweaker.mixin.common.access.fabric.AccessFakePlayer;
import com.blamejared.crafttweaker.mixin.common.access.item.AccessBucketItem;
import com.blamejared.crafttweaker.platform.helper.inventory.IInventoryWrapper;
import com.blamejared.crafttweaker.platform.helper.world.inventory.TAInventoryWrapper;
import com.blamejared.crafttweaker.platform.services.IPlatformHelper;
import com.faux.customentitydata.api.CustomDataHelper;
import com.google.common.base.Suppliers;
import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Either;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.PlayerInventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.ModOrigin;
import net.minecraft.Util;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.material.Fluid;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FabricPlatformHelper implements IPlatformHelper {
    
    public final Supplier<List<Mod>> modList = Suppliers.memoize(() -> FabricLoader.getInstance()
            .getAllMods()
            .stream()
            .map(ModContainer::getMetadata)
            .map(metadata -> new Mod(metadata.getId(), metadata.getName(), metadata.getVersion()
                    .getFriendlyString()))
            .toList());
    
    public final Function<String, Optional<Mod>> modFinder = Util.memoize(modid -> modList.get()
            .stream()
            .filter(modObject -> modObject.id().equals(modid))
            .findFirst());
    
    private static final Supplier<Reflections> REFLECTIONS = Suppliers.memoize(FabricPlatformHelper::makeReflections);
    private static final Supplier<MappingResolver> MAPPING_RESOLVER = Suppliers.memoize(() -> FabricLoader.getInstance()
            .getMappingResolver());
    
    private static Reflections makeReflections() {
        
        Collection<URL> urls = ClasspathHelper.forClassLoader();
        // Not a fan of hard coding for a specific thing, but not sure the implications of removing everything that isn't a file.
        // Absolutely hate this but fabric mods gotta be fabric mods, this catches FabricASM and MagicLib
        urls.removeIf(url -> url.getProtocol().contains("magic"));
        return new Reflections(new ConfigurationBuilder().addUrls(urls).setParallel(true));
    }
    
    @Override
    public String getPlatformName() {
        
        return "Fabric";
    }
    
    @Override
    public boolean isModLoaded(String modId) {
        
        return FabricLoader.getInstance().isModLoaded(modId);
    }
    
    @Override
    public boolean isDevelopmentEnvironment() {
        
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
    
    @Override
    public boolean isDataGen() {
        
        // If / When fabric has a way to detect datagen, update this.
        return false;
    }
    
    @Override
    public List<Mod> getMods() {
        
        return modList.get();
    }
    
    @Override
    public Optional<Mod> getMod(String modid) {
        
        return modFinder.apply(modid);
    }
    
    @Override
    public IItemStack createItemStack(ItemStack stack, IngredientConditions conditions, IngredientTransformers transformers) {
        
        return new MCItemStack(stack, conditions, transformers);
    }
    
    @Override
    public IItemStack createItemStackMutable(ItemStack stack, IngredientConditions conditions, IngredientTransformers transformers) {
        
        return new MCItemStackMutable(stack, conditions, transformers);
    }
    
    @Override
    public IFluidStack createFluidStack(Fluid fluid, long amount, DataComponentPatch patch) {
        
        return new MCFluidStack(new SimpleFluidStack(fluid, Math.toIntExact(amount), patch));
    }
    
    @Override
    public IFluidStack createFluidStackMutable(Fluid fluid, long amount, DataComponentPatch patch) {
        
        return new MCFluidStackMutable(new SimpleFluidStack(fluid, Math.toIntExact(amount), patch));
    }
    
    @Override
    public <T> IFluidStack createFluidStack(T stack) {
        
        if(stack instanceof SimpleFluidStack fluidStack) {
            return new MCFluidStack(fluidStack);
        }
        throw new IllegalArgumentException("Unable to create IFluidStack from '" + stack + "'!");
    }
    
    @Override
    public <T> IFluidStack createFluidStackMutable(T stack) {
        
        if(stack instanceof SimpleFluidStack fluidStack) {
            return new MCFluidStackMutable(fluidStack);
        }
        throw new IllegalArgumentException("Unable to create IFluidStack from '" + stack + "'!");
    }
    
    @Override
    public Fluid getBucketContent(BucketItem item) {
        
        return ((AccessBucketItem) item).crafttweaker$getContent();
    }
    
    @Override
    public Path getGameDirectory() {
        
        return FabricLoader.getInstance().getGameDir();
    }
    
    @Override
    public <T extends Annotation> Stream<? extends Class<?>> findClassesWithAnnotation(
            final Class<T> annotationClass,
            final Consumer<PlatformMod> classProviderConsumer,
            final Predicate<Either<T, Map<String, Object>>> annotationFilter
    ) {
        
        final Set<Class<?>> typesAnnotatedWith = REFLECTIONS.get().getTypesAnnotatedWith(annotationClass);
        return typesAnnotatedWith.stream()
                .filter(it -> it.isAnnotationPresent(annotationClass)) // Thank you reflections for giving classes without the annotation, very cool
                .filter(it -> annotationFilter.test(Either.left(it.getAnnotation(annotationClass))))
                .peek(it -> this.getModsForClass(it).forEach(classProviderConsumer));
    }
    
    private List<PlatformMod> getModsForClass(Class<?> clazz) {
        
        final Path classFile;
        try {
            classFile = Paths.get(clazz.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch(final URISyntaxException e) {
            return List.of();
        }
        
        // This doesn't work for the current mod in dev.
        // The origin paths just include build/resources/main, not build/classes/main, but otherwise works great
        return FabricLoader.getInstance()
                .getAllMods()
                .stream()
                .map(modContainer -> Map.entry(modContainer.getOrigin(), modContainer.getMetadata()))
                .filter(pair -> pair.getKey().getKind() == ModOrigin.Kind.PATH)
                .flatMap(pair -> pair.getKey().getPaths().stream().map(path -> Map.entry(path, pair.getValue())))
                .filter(pair -> pair.getKey().equals(classFile))
                .map(pair -> {
                    final ModMetadata metadata = pair.getValue();
                    final Mod mod = new Mod(metadata.getId(), metadata.getName(), metadata.getVersion()
                            .getFriendlyString());
                    return FabricMod.of(mod, pair.getKey());
                })
                .map(GenericUtil::<PlatformMod>uncheck) // Why??
                .toList();
    }
    
    @Override
    public String findMappedMethodName(final Class<?> clazz, final String methodName, final Class<?> returnType, final Class<?>... parameterTypes) {
        
        final String namespace = MAPPING_RESOLVER.get().getCurrentRuntimeNamespace();
        final String owner = clazz.getName();
        final String descriptor = Arrays.stream(parameterTypes)
                .map(Class::descriptorString)
                .collect(Collectors.joining("", "(", ")" + returnType.descriptorString()));
        return MAPPING_RESOLVER.get().mapMethodName(namespace, owner, methodName, descriptor);
    }
    
    @Override
    public String findMappedFieldName(final Class<?> clazz, final String fieldName, final Class<?> fieldType) {
        
        final String namespace = MAPPING_RESOLVER.get().getCurrentRuntimeNamespace();
        final String owner = clazz.getName();
        final String descriptor = fieldType.descriptorString();
        return MAPPING_RESOLVER.get().mapFieldName(namespace, owner, fieldName, descriptor);
    }
    
    @Override
    public IInventoryWrapper getPlayerInventory(Player player) {
        
        return new TAInventoryWrapper(PlayerInventoryStorage.of(player));
    }
    
    @Override
    public Map<ResourceLocation, ILootModifier> getLootModifiersMap() {
        
        return LootModifierManager.INSTANCE.modifiers();
    }
    
    @Override
    public Set<MutableComponent> getFluidsForDump(ItemStack stack, Player player, InteractionHand hand) {
        
        Storage<FluidVariant> storage = FluidStorage.ITEM.find(stack, ContainerItemContext.ofPlayerHand(player, InteractionHand.MAIN_HAND));
        if(storage == null) {
            return Set.of();
        }
        Set<MutableComponent> components = new HashSet<>();
        for(StorageView<FluidVariant> view : storage) {
            if(!view.isResourceBlank()) {
                components.add(Component.literal(BuiltInRegistries.FLUID.getKey(view.getResource()
                        .getFluid()) + " * " + view.getAmount()));
            }
        }
        
        return components;
    }
    
    @Override
    public CompoundTag getCustomData(Entity entity) {
        
        return CustomDataHelper.getCustomData(entity);
    }
    
    @Override
    public CompoundTag getPersistentData(ServerPlayer player) {
        
        return CustomDataHelper.getPersistentData(player);
    }
    
    @Override
    public boolean doesIngredientRequireTesting(Ingredient ingredient) {
        
        return ingredient.requiresTesting();
    }
    
    @Override
    public Ingredient getIngredientAny() {
        
        return CraftTweakerIngredients.Ingredients.any().toVanilla();
    }
    
    @Override
    public Ingredient getIngredientList(List<Ingredient> children) {
        
        return CraftTweakerIngredients.Ingredients.list(children).toVanilla();
    }
    
    @Override
    public Ingredient getCraftTweakerIngredient(IIngredient internal) {
        
        return CraftTweakerIngredients.Ingredients.crafttweaker(internal).toVanilla();
    }
    
    @Override
    public Ingredient getIItemStackIngredient(IItemStack internal) {
        
        return CraftTweakerIngredients.Ingredients.iitemstack(internal).toVanilla();
    }
    
    @Override
    public boolean isCustomIngredient(Ingredient ingredient) {
        
        return ingredient.getCustomIngredient() != null;
    }
    
    @Override
    public Stream<ItemStack> getCustomIngredientItems(Ingredient ingredient) {
        
        return ingredient.getCustomIngredient() == null ? Stream.empty() : ingredient.getCustomIngredient()
                .getMatchingStacks()
                .stream();
    }
    
    @Override
    public Stream<GameProfile> fakePlayers() {
        
        return Stream.concat(Stream.of(AccessFakePlayer.getDEFAULT_PROFILE()), AccessFakePlayer.getFAKE_PLAYER_MAP()
                .values()
                .stream()
                .map(Player::getGameProfile));
    }
    
    @Override
    public boolean isFakePlayer(Player player) {
        
        return player instanceof FakePlayer;
    }
    
    @Override
    public FoodProperties.PossibleEffect createPossibleEffect(MobEffectInstance effect, float probability) {
        
        return new FoodProperties.PossibleEffect(effect, probability);
    }
    
}
