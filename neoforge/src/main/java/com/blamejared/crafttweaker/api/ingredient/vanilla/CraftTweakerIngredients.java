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
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.ICustomIngredient;
import net.neoforged.neoforge.common.crafting.IngredientType;

import java.util.List;

public class CraftTweakerIngredients {
    
    private static final BiMap<CraftTweakerVanillaIngredientSerializer<?>, IngredientType<? extends ICustomIngredient>> SERIALIZER_CACHE = HashBiMap.create();
    private static final BiMap<CraftTweakerVanillaIngredient, DelegatingCustomIngredient<?>> SINGLETON_CACHE = HashBiMap.create();
    
    public static class Ingredients {
        
        public static ICustomIngredient any() {
            
            return SINGLETON_CACHE.computeIfAbsent(IngredientAny.of(), DelegatingCustomIngredient::new);
        }
        
        public static <T extends IIngredient> ICustomIngredient crafttweaker(T internal) {
            
            return new DelegatingCustomIngredient<>(IngredientCraftTweaker.of(internal));
        }
        
        public static ICustomIngredient iitemstack(IItemStack internal) {
            
            return new DelegatingCustomIngredient<>(IngredientIItemStack.of(internal));
        }
        
        public static ICustomIngredient list(List<Ingredient> children) {
            
            return new DelegatingCustomIngredient<>(IngredientList.of(children));
        }
        
        static <T extends CraftTweakerVanillaIngredient> DelegatingCustomIngredient<T> of(T ingredient) {
            
            if(ingredient.singleton()) {
                return GenericUtil.uncheck(SINGLETON_CACHE.computeIfAbsent(ingredient, DelegatingCustomIngredient::new));
            }
            return new DelegatingCustomIngredient<>(ingredient);
        }
        
    }
    
    public static class Types {
        
        public static final IngredientType<?> ANY = of(IngredientAnySerializer.INSTANCE);
        public static final IngredientType<?> LIST = of(IngredientListSerializer.INSTANCE);
        public static final IngredientType<?> CRAFTTWEAKER = of(IngredientCraftTweakerSerializer.INSTANCE);
        public static final IngredientType<?> IITEMSTACK = of(IngredientIItemStackSerializer.INSTANCE);
        
        static <T extends CraftTweakerVanillaIngredient> IngredientType<DelegatingCustomIngredient<T>> of(CraftTweakerVanillaIngredientSerializer<T> ingredient) {
            
            return GenericUtil.uncheck(SERIALIZER_CACHE.computeIfAbsent(ingredient, DelegatingCustomIngredient::ingredientType));
        }
        
    }
    
}
