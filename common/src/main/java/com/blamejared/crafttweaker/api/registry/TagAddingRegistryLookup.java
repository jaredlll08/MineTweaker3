package com.blamejared.crafttweaker.api.registry;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;

import java.util.Optional;
import java.util.stream.Stream;

public class TagAddingRegistryLookup implements HolderLookup.Provider {
    
    private final RegistryAccess registryAccess;
    
    public TagAddingRegistryLookup(RegistryAccess delegate) {
        
        this.registryAccess = delegate;
    }
    
    @Override
    public Stream<ResourceKey<? extends Registry<?>>> listRegistries() {
        
        return this.registryAccess.listRegistries();
    }
    
    @Override
    public <T> Optional<HolderLookup.RegistryLookup<T>> lookup(ResourceKey<? extends Registry<? extends T>> key) {
        
        return this.registryAccess.registry(key).map(Registry::asTagAddingLookup);
    }
    
}