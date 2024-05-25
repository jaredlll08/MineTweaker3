package com.blamejared.crafttweaker.platform.services;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.mixin.common.access.registry.AccessRegistrySynchronization;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface IRegistryHelper {
    
    default Set<ResourceKey<?>> serverOnlyRegistries() {
        
        return AccessRegistrySynchronization.crafttweaker$callOwnedNetworkableRegistries(CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess()).map(RegistryAccess.RegistryEntry::key).collect(Collectors.toSet());
    }
    
    default <T> Registry<T> registryOrThrow(ResourceKey<Registry<T>> registry) {
        
        return CraftTweakerAPI.getAccessibleElementsProvider().registryAccess().registryOrThrow(registry);
    }
    
    default <T> ResourceLocation keyOrThrow(ResourceKey<Registry<T>> registry, T thing) {
        
        return CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess()
                .registryOrThrow(registry)
                .getKey(thing);
    }
    
    default <T> Holder<T> holderOrThrow(ResourceKey<?> registry, T thing) {
        
        return CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess()
                .<T> registryOrThrow(GenericUtil.uncheck(registry))
                .wrapAsHolder(thing);
    }
    
    /**
     * Maybe returns the registry key of the given object if we know about its type.
     */
    default Optional<ResourceLocation> maybeGetRegistryKey(Object object) {
        
        if(object instanceof Item obj) {
            return nonDefaultKey(BuiltInRegistries.ITEM, obj);
        } else if(object instanceof Potion obj) {
            return nonDefaultKey(BuiltInRegistries.POTION, obj);
        } else if(object instanceof EntityType<?> obj) {
            return nonDefaultKey(BuiltInRegistries.ENTITY_TYPE, obj);
        } else if(object instanceof RecipeType<?> obj) {
            return nonDefaultKey(BuiltInRegistries.RECIPE_TYPE, obj);
        } else if(object instanceof RecipeSerializer<?> obj) {
            return nonDefaultKey(BuiltInRegistries.RECIPE_SERIALIZER, obj);
        } else if(object instanceof Attribute obj) {
            return nonDefaultKey(BuiltInRegistries.ATTRIBUTE, obj);
        } else if(object instanceof Fluid obj) {
            return nonDefaultKey(BuiltInRegistries.FLUID, obj);
        } else if(object instanceof Enchantment obj) {
            return nonDefaultKey(BuiltInRegistries.ENCHANTMENT, obj);
        } else if(object instanceof Block obj) {
            return nonDefaultKey(BuiltInRegistries.BLOCK, obj);
        } else if(object instanceof MobEffect obj) {
            return nonDefaultKey(BuiltInRegistries.MOB_EFFECT, obj);
        } else if(object instanceof VillagerProfession obj) {
            return nonDefaultKey(BuiltInRegistries.VILLAGER_PROFESSION, obj);
        } else if(object instanceof Biome obj) {
            return nonDefaultKey(CraftTweakerAPI.getAccessibleElementsProvider()
                    .registryAccess()
                    .registryOrThrow(Registries.BIOME), obj);
        } else if(object instanceof SoundEvent obj) {
            return nonDefaultKey(BuiltInRegistries.SOUND_EVENT, obj);
        }
        
        return Optional.empty();
    }
    
    private <T> Optional<ResourceLocation> nonDefaultKey(Registry<T> registry, T obj) {
        
        ResourceLocation key = registry.getKey(obj);
        if(registry instanceof DefaultedRegistry<T> def) {
            if(def.getDefaultKey().equals(key) && !def.get(key).equals(obj)) {
                return Optional.empty();
            }
        }
        return Optional.ofNullable(key);
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
