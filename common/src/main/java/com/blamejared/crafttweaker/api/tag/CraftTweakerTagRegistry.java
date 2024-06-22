package com.blamejared.crafttweaker.api.tag;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.tag.manager.ITagManager;
import com.blamejared.crafttweaker.api.tag.manager.TagManagerFactory;
import com.blamejared.crafttweaker.api.tag.manager.type.KnownTagManager;
import com.blamejared.crafttweaker.api.tag.manager.type.UnknownTagManager;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.api.util.InstantiationUtil;
import com.blamejared.crafttweaker.mixin.common.access.tag.AccessTagNetworkSerialization;
import com.blamejared.crafttweaker.platform.Services;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagManager;
import net.minecraft.tags.TagNetworkSerialization;
import org.openzen.zencode.java.ZenCodeGlobals;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@ZenRegister(loaders = {CraftTweakerConstants.DEFAULT_LOADER_NAME, CraftTweakerConstants.TAGS_LOADER_NAME})
@ZenCodeType.Name("crafttweaker.api.tag.TagManager")
public final class CraftTweakerTagRegistry {
    
    private static final Supplier<Set<ResourceLocation>> SERVER_ONLY_FOLDERS = Suppliers.memoize(() -> Services.REGISTRY.serverOnlyRegistries()
            .stream()
            .map(CraftTweakerTagRegistry.INSTANCE::makeTagFolder)
            .map(ResourceLocation::tryParse)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet()));
    
    public static final String GLOBAL_NAME = "tags";
    
    @ZenCodeGlobals.Global(GLOBAL_NAME)
    public static final CraftTweakerTagRegistry INSTANCE = new CraftTweakerTagRegistry();
    
    private CraftTweakerTagRegistry() {}
    
    private final Map<ResourceKey<? extends Registry<?>>, ITagManager<?>> registeredManagers = new HashMap<>();
    private final Set<ResourceKey<? extends Registry<?>>> knownManagers = new HashSet<>();
    private final Set<ResourceKey<? extends Registry<?>>> knownManagersView = Collections.unmodifiableSet(knownManagers);
    
    /**
     * Adds a new {@link ITagManager} to the registry.
     *
     * @param cls The class of the {@link ITagManager} to add.
     *
     * @return The {@link ITagManager} that was added.
     */
    public ITagManager<?> addManager(Class<? extends ITagManager<?>> cls) {
        
        ITagManager<?> manager = InstantiationUtil.getOrCreateInstance(cls);
        Objects.requireNonNull(manager, "Error while creating tag manager from class: '" + cls + "'! Make sure it has a default constructor or a public static INSTANCE field!");
        return addManager(manager);
    }
    
    /**
     * Adds a new {@link ITagManager} to the registry.
     *
     * @param manager The {@link ITagManager} to add.
     *
     * @return The {@link ITagManager} that was added.
     */
    public ITagManager<?> addManager(ITagManager<?> manager) {
        
        registeredManagers.put(manager.resourceKey(), manager);
        if(manager.getClass().equals(KnownTagManager.class)) {
            knownManagers.add(manager.resourceKey());
        }
        return manager;
    }
    
    /**
     * Gets all registered {@link ITagManager}'s
     *
     * @return All registered {@link ITagManager}'s
     */
    public Collection<ITagManager<?>> managers() {
        
        return Collections.unmodifiableCollection(registeredManagers.values());
    }
    
    /**
     * Gets all {@link KnownTagManager}'s
     *
     * @return All registered {@link KnownTagManager}
     */
    public Collection<ResourceKey<? extends Registry<?>>> knownManagers() {
        
        return knownManagersView;
    }
    
    /**
     * Finds a {@link  ITagManager} for the given {@link ResourceKey}
     *
     * @param key The key to find.
     *
     * @return An optional {@link ITagManager} if found, an empty optional otherwise.
     */
    public <T> Optional<ITagManager<?>> findManager(ResourceKey<? extends Registry<T>> key) {
        
        return Optional.ofNullable(registeredManagers.get(key));
    }
    
    /**
     * Finds a {@link  KnownTagManager} for the given {@link ResourceKey}
     *
     * @param key The key to find.
     *
     * @return An optional {@link KnownTagManager} if found, an empty optional otherwise.
     */
    public <T> Optional<KnownTagManager<T>> findKnownManager(ResourceKey<? extends Registry<T>> key) {
        
        return Optional.ofNullable(registeredManagers.get(key))
                .map(it -> it instanceof KnownTagManager ? (KnownTagManager<T>) it : null);
    }
    
    /**
     * Tries to get an {@link ITagManager} from the given tag folder.
     *
     * @param tagFolder The tag folder to get the manager of.
     *
     * @return An optional {@link ITagManager} if found, an empty optional otherwise.
     */
    public <T> Optional<? extends ITagManager<?>> tagManagerFromFolder(ResourceLocation tagFolder) {
        
        return this.registeredManagers.values()
                .stream()
                .filter(iTagManager -> tagFolder
                        .equals(ResourceLocation.tryParse(iTagManager.tagFolder())))
                .findFirst();
    }
    
    public boolean isServerOnly(ResourceLocation tagFolder) {
        
        return SERVER_ONLY_FOLDERS.get().contains(tagFolder);
    }
    
    /**
     * Checks if the given {@link ResourceKey} is a {@link KnownTagManager}
     *
     * @param key The key of the manager to look for.
     *
     * @return True if there is a {@link KnownTagManager} for the given key.
     */
    public boolean isKnownManager(ResourceKey<? extends Registry<?>> key) {
        
        return knownManagers.contains(key);
    }
    
    /**
     * Checks if the given tagFolder corresponds to a {@link KnownTagManager}
     *
     * @param tagFolder The tag folder of the manager to look for.
     *
     * @return True if there is a {@link KnownTagManager} for the given folder.
     */
    public boolean isKnownManager(ResourceLocation tagFolder) {
        
        return tagManagerFromFolder(tagFolder).map(manager -> isKnownManager(manager.resourceKey())).orElse(false);
    }
    
    /**
     * Gets the {@link ITagManager} corresponding to the given {@link ResourceKey}
     *
     * @param key The key to look for.
     *
     * @return The {@link ITagManager} for the given key.
     */
    public <T> ITagManager<?> tagManager(ResourceKey<? extends Registry<T>> key) {
        
        return findManager(key).orElseThrow(() -> new NoSuchElementException("No tag manager found for given key: " + key));
    }
    
    /**
     * Gets the {@link KnownTagManager} corresponding to the given {@link ResourceKey}
     *
     * @param key The key to look for.
     *
     * @return The {@link KnownTagManager} for the given key.
     */
    public <T> KnownTagManager<T> knownTagManager(ResourceKey<? extends Registry<T>> key) {
        
        return findKnownManager(key).orElseThrow(() -> new NoSuchElementException("No known tag manager found for given key: " + key));
    }
    
    /**
     * Gets the {@link ITagManager} corresponding to the given {@link ResourceLocation}
     *
     * @param registryLocation The location to look for.
     *
     * @return The {@link ITagManager} for the given location.
     */
    @ZenCodeType.Method
    public <T extends ITagManager<?>> T tagManager(ResourceLocation registryLocation) {
        
        return (T) findManager(ResourceKey.createRegistryKey(registryLocation)).orElseThrow(() -> new NoSuchElementException("No tag manager found for given location: " + registryLocation));
    }
    
    /**
     * Binds the given {@link TagManager} to the registry.
     *
     * <p>Note: This will clear all registered managers.</p>
     *
     * @param tagManager The {@link TagManager} to bind.
     */
    public void bind(TagManager tagManager) {
        
        this.bind(tagManager.getResult());
    }
    
    /**
     * Binds the given map to the registry.
     *
     * <p>Note: This will clear all registered managers.</p>
     *
     * @param tags The map to bind.
     */
    public void bind(Map<ResourceKey<? extends Registry<?>>, TagNetworkSerialization.NetworkPayload> tags) {
        
        bind(tags, new BindContext());
    }
    
    /**
     * Binds the given map to the registry.
     *
     * <p>Note: This will clear all registered managers.</p>
     *
     * @param tags    The map to bind.
     * @param context The bind context.
     */
    @SuppressWarnings("rawtypes")
    public void bind(Map<ResourceKey<? extends Registry<?>>, TagNetworkSerialization.NetworkPayload> tags, BindContext context) {
        
        List<TagManager.LoadResult<?>> results = new ArrayList<>();
        Set<ResourceKey<?>> knownKeys = new HashSet<>();
        CraftTweakerAPI.getAccessibleElementsProvider().client().runWithRegistryAccess((registryAccess) -> {
            tags.forEach((resourceKey, networkPayload) -> {
                
                knownKeys.add(resourceKey);
                HashMap<Object, Object> resultMap = new HashMap<>();
                
                Registry<Object> registry = registryAccess.registryOrThrow(resourceKey);
                AccessTagNetworkSerialization.crafttweaker$$callDeserializeTagsFromNetwork(GenericUtil.uncheck(resourceKey), registry, networkPayload, (key, holders) -> resultMap.put(key.location(), holders));
                results.add(new TagManager.LoadResult(resourceKey, resultMap));
                
            });
            registryAccess
                    .registries()
                    .forEach(registryEntry -> {
                        if(knownKeys.contains(registryEntry.key())) {
                            return;
                        }
                        results.add(new TagManager.LoadResult<>(registryEntry.key(), new HashMap<>()));
                    });
            
            bind(results, context);
        });
        
    }
    
    /**
     * Binds the given results to the registry.
     *
     * <p>Note: This will clear all registered managers.</p>
     *
     * @param results The results to bind.
     */
    public void bind(List<TagManager.LoadResult<?>> results) {
        
        bind(results, new BindContext());
    }
    
    /**
     * Binds the given results to the registry.
     *
     * <p>Note: This will clear all registered managers.</p>
     *
     * @param results The results to bind.
     * @param context The bind context.
     */
    @SuppressWarnings("rawtypes")
    public void bind(List<TagManager.LoadResult<?>> results, BindContext context) {
        
        this.registeredManagers.clear();
        this.knownManagers.clear();
        for(TagManager.LoadResult loadResult : results) {
            Optional<? extends Class<?>> taggableElement = CraftTweakerAPI.getRegistry()
                    .getTaggableElementFor(loadResult.key());
            
            if(context.registerKnownManagers()) {
                TagManagerFactory taggableElementFactory = CraftTweakerAPI.getRegistry()
                        .getTaggableElementFactory(loadResult.key());
                taggableElement.ifPresentOrElse(it -> this.addManager(taggableElementFactory.apply(loadResult.key(), it))
                                .bind(loadResult),
                        () -> this.addManager(new UnknownTagManager(loadResult.key())).bind(loadResult));
            } else {
                this.addManager(new UnknownTagManager(loadResult.key())).bind(loadResult);
            }
            
        }
    }
    
    public String makeTagFolder(ResourceKey<? extends Registry<?>> key) {
        
        String tagDir = Registries.tagsDirPath(key);
        
        // Modders would *really* need to go out of their way to use a different folder, if they do, that's on them
        if(tagDir.startsWith("tags/")) {
            tagDir = tagDir.substring("tags/".length());
        }
        return tagDir;
    }
    
    public record BindContext(boolean registerKnownManagers) {
        
        public BindContext() {
            
            this(true);
        }
        
    }
    
}
