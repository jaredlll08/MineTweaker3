package com.blamejared.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientConditionSerializer;
import com.blamejared.crafttweaker.api.ingredient.transformer.IIngredientTransformerSerializer;
import com.blamejared.crafttweaker.mixin.common.access.registry.AccessMappedRegistry;
import com.blamejared.crafttweaker.platform.Services;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;

public class CraftTweakerRegistries {
    
    public static Registry<IIngredientTransformerSerializer<?>> TRANSFORMER_SERIALIZER = Services.REGISTRY.makeRegistry(Keys.TRANSFORMER_SERIALIZER);
    public static Registry<IIngredientConditionSerializer<?>> CONDITIONER_SERIALIZER = Services.REGISTRY.makeRegistry(Keys.CONDITIONER_SERIALIZER);
    
    public static void init() {
        
        if(BuiltInRegistries.REGISTRY instanceof AccessMappedRegistry registry) {
            boolean wasFrozen = registry.crafttweaker$isFrozen();
            registry.crafttweaker$setFrozen(false);
            registerRegistry(TRANSFORMER_SERIALIZER);
            registerRegistry(CONDITIONER_SERIALIZER);
            registry.crafttweaker$setFrozen(wasFrozen);
        } else {
            throw new IllegalStateException("Unable to create new registries! Expected REGISTRY to be mapped, but was: '" + BuiltInRegistries.REGISTRY.getClass() + "'");
        }
    }
    
    @SuppressWarnings("rawtypes")
    private static <T> Registry<T> registerRegistry(Registry<T> mappedReg) {
        
        WritableRegistry registry = (WritableRegistry) BuiltInRegistries.REGISTRY;
        registry.register(mappedReg.key(), mappedReg, RegistrationInfo.BUILT_IN);
        return mappedReg;
    }
    
    public static class Keys {
        
        public static ResourceKey<Registry<IIngredientTransformerSerializer<?>>> TRANSFORMER_SERIALIZER = ResourceKey.createRegistryKey(CraftTweakerConstants.rl("transformer_serializer"));
        public static ResourceKey<Registry<IIngredientConditionSerializer<?>>> CONDITIONER_SERIALIZER = ResourceKey.createRegistryKey(CraftTweakerConstants.rl("condition_serializer"));
        
    }
    
    
}
