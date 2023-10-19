package com.blamejared.crafttweaker.impl.registry.recipe;

import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.recipe.component.IDecomposedRecipe;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandlerRegistry;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.api.util.ItemStackUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class RecipeHandlerRegistry implements IRecipeHandlerRegistry {
    
    private final Map<Class<? extends Recipe<?>>, IRecipeHandler<?>> recipeHandlers = new HashMap<>();
    
    public <T extends Recipe<?>> void register(Class<? extends T> clazz, IRecipeHandler<T> handler) {
        
        this.recipeHandlers.put(clazz, handler);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Recipe<?>> IRecipeHandler<T> getRecipeHandlerFor(final T recipe) {
        
        return (IRecipeHandler<T>) getRecipeHandlerFor(recipe.getClass());
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Recipe<?>> IRecipeHandler<T> getRecipeHandlerFor(Class<T> recipeClass) {
        
        return (IRecipeHandler<T>) getRecipeHandlerForClass(recipeClass).orElse(DefaultRecipeHandler.INSTANCE);
    }
    
    private Optional<IRecipeHandler<?>> getRecipeHandlerForClass(final Class<?> recipeClass) {
        
        final Deque<Class<?>> classes = new ArrayDeque<>();
        classes.offer(recipeClass);
        
        while(!classes.isEmpty()) {
            final Class<?> target = classes.poll();
            
            // Don't check for Recipe: we don't allow any handlers with that superclass
            if(target == Recipe.class) {
                continue;
            }
            
            final IRecipeHandler<?> attempt = this.recipeHandlers.get(target);
            if(attempt != null) {
                return Optional.of(attempt);
            }
            
            if(target.getSuperclass() != null) {
                classes.offer(target.getSuperclass());
            }
            Arrays.stream(target.getInterfaces()).forEach(classes::offer);
        }
        
        return Optional.empty();
    }
    
}
