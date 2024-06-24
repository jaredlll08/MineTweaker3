package com.blamejared.crafttweaker.platform.services;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.mixin.common.access.registry.AccessRegistrySynchronization;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface IRegistryHelper {
    
    default Set<ResourceKey<? extends Registry<?>>> serverOnlyRegistries() {
        
        return AccessRegistrySynchronization.crafttweaker$callOwnedNetworkableRegistries(CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess()).map(RegistryAccess.RegistryEntry::key).collect(Collectors.toSet());
    }
    
    default <T> T getOrThrow(ResourceKey<Registry<T>> registry, ResourceKey<T> key) {
        
        return registryOrThrow(registry).getOrThrow(key);
    }
    
    default <T> Registry<T> registryOrThrow(ResourceKey<Registry<T>> registry) {
        
        return CraftTweakerAPI.getAccessibleElementsProvider().registryAccess().registryOrThrow(registry);
    }
    
    default <T> ResourceLocation keyOrThrow(ResourceKey<Registry<T>> registry, T thing) {
        
        ResourceLocation key = CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess()
                .registryOrThrow(registry)
                .getKey(thing);
        if(key == null) {
            throw new IllegalStateException("No key found for " + thing + " in " + registry);
        }
        return key;
    }
    
    default <T> ResourceKey<T> resourceKeyOrThrow(ResourceKey<Registry<T>> registry, T thing) {
        
        return CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess()
                .registryOrThrow(registry)
                .getResourceKey(thing)
                .orElseThrow(() -> new IllegalStateException("No key found for " + thing + " in " + registry));
    }
    
    default <T> Optional<ResourceLocation> key(ResourceKey<Registry<T>> registry, T thing) {
        
        return Optional.ofNullable(CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess()
                .registryOrThrow(registry)
                .getKey(thing));
    }
    
    default <T> Holder<T> holderFromLocationOrThrow(ResourceKey<?> registry, ResourceLocation location) {
        Registry<T> reg = registryOrThrow(GenericUtil.uncheck(registry));
        
        return reg.getHolderOrThrow(ResourceKey.create(reg.key(),location));
    }
    
    default <T> Holder<T> holderOrThrow(ResourceKey<?> registry, T thing) {
        
        return this.<T>registryOrThrow(GenericUtil.uncheck(registry)).wrapAsHolder(thing);
    }
    
    default <T> Holder<T> holderOrThrow(ResourceKey<Registry<T>> registry, ResourceLocation location) {
        
        return registryOrThrow(registry).getHolderOrThrow(ResourceKey.create(registry, location));
    }
    
    default Registry<Biome> biomes() {
        
        return CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess()
                .registryOrThrow(Registries.BIOME);
    }
    
    default <T> Holder<T> makeHolder(ResourceKey<?> resourceKey, Either<T, ResourceLocation> objectOrKey) {
        
        Registry<T> objects = CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess()
                .registryOrThrow(GenericUtil.uncheck(resourceKey));
        
        return objectOrKey.map(objects::wrapAsHolder, resourceLocation -> objects.getHolder(resourceLocation)
                .orElseThrow());
    }
    
    default <T> Registry<T> makeRegistry(ResourceKey<Registry<T>> resourceKey) {
        
        return new MappedRegistry<>(resourceKey, Lifecycle.stable());
    }
    
}
