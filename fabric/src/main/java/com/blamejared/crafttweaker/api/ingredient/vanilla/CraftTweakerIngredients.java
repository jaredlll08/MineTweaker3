package com.blamejared.crafttweaker.api.ingredient.vanilla;


import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.ingredient.vanilla.serializer.CraftTweakerVanillaIngredientSerializer;
import com.blamejared.crafttweaker.api.ingredient.vanilla.serializer.IngredientAnySerializer;
import com.blamejared.crafttweaker.api.ingredient.vanilla.serializer.IngredientCraftTweakerSerializer;
import com.blamejared.crafttweaker.api.ingredient.vanilla.serializer.IngredientIItemStackSerializer;
import com.blamejared.crafttweaker.api.ingredient.vanilla.serializer.IngredientListSerializer;
import com.blamejared.crafttweaker.api.ingredient.vanilla.type.CraftTweakerVanillaIngredient;
import com.blamejared.crafttweaker.api.ingredient.vanilla.type.IngredientAny;
import com.blamejared.crafttweaker.api.ingredient.vanilla.type.IngredientCraftTweaker;
import com.blamejared.crafttweaker.api.ingredient.vanilla.type.IngredientIItemStack;
import com.blamejared.crafttweaker.api.ingredient.vanilla.type.IngredientList;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredient;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredientSerializer;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class CraftTweakerIngredients {
    
    private static final BiMap<CraftTweakerVanillaIngredientSerializer<?>, DelegatingCustomIngredientSerializer<?>> SERIALIZER_CACHE = HashBiMap.create();
    private static final BiMap<CraftTweakerVanillaIngredient, DelegatingCustomIngredient<?>> SINGLETON_CACHE = HashBiMap.create();
    
    public static class Ingredients {
        
        public static CustomIngredient any() {
            
            return SINGLETON_CACHE.computeIfAbsent(IngredientAny.of(), DelegatingCustomIngredient::new);
        }
        
        public static <T extends IIngredient> CustomIngredient crafttweaker(T internal) {
            
            return new DelegatingCustomIngredient<>(IngredientCraftTweaker.of(internal));
        }
        
        public static CustomIngredient iitemstack(IItemStack internal) {
            
            return new DelegatingCustomIngredient<>(IngredientIItemStack.of(internal));
        }
        
        public static CustomIngredient list(List<Ingredient> children) {
            
            return new DelegatingCustomIngredient<>(IngredientList.of(children));
        }
        
        static <T extends CraftTweakerVanillaIngredient> DelegatingCustomIngredient<T> of(T ingredient) {
            
            if(ingredient.singleton()) {
                return GenericUtil.uncheck(SINGLETON_CACHE.computeIfAbsent(ingredient, DelegatingCustomIngredient::new));
            }
            return new DelegatingCustomIngredient<>(ingredient);
        }
        
    }
    
    public static class Serializers {
        
        public static final CustomIngredientSerializer<?> ANY = SERIALIZER_CACHE.computeIfAbsent(IngredientAnySerializer.INSTANCE, DelegatingCustomIngredientSerializer::new);
        public static final CustomIngredientSerializer<?> LIST = SERIALIZER_CACHE.computeIfAbsent(IngredientListSerializer.INSTANCE, DelegatingCustomIngredientSerializer::new);
        public static final CustomIngredientSerializer<?> CRAFTTWEAKER = SERIALIZER_CACHE.computeIfAbsent(IngredientCraftTweakerSerializer.INSTANCE, DelegatingCustomIngredientSerializer::new);
        public static final CustomIngredientSerializer<?> IITEMSTACK = SERIALIZER_CACHE.computeIfAbsent(IngredientIItemStackSerializer.INSTANCE, DelegatingCustomIngredientSerializer::new);
        
        
        static <T extends CraftTweakerVanillaIngredient> CustomIngredientSerializer<DelegatingCustomIngredient<T>> of(CraftTweakerVanillaIngredientSerializer<T> ingredient) {
            
            return GenericUtil.uncheck(SERIALIZER_CACHE.computeIfAbsent(ingredient, DelegatingCustomIngredientSerializer::new));
        }
        
    }
    
}
