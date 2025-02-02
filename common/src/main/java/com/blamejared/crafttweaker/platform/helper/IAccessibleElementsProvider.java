package com.blamejared.crafttweaker.platform.helper;

import com.blamejared.crafttweaker.api.registry.TagAddingRegistryLookup;
import com.blamejared.crafttweaker.mixin.common.access.recipe.AccessRecipeManager;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.function.Function;

// TODO("Better package")
public interface IAccessibleElementsProvider {
    
    RecipeManager recipeManager();
    
    AccessRecipeManager accessibleRecipeManager();
    
    void recipeManager(final RecipeManager manager);
    
    RegistryAccess registryAccess();
    
    default <T> T registryAccess(Function<RegistryAccess, T> func) {
        
        return func.apply(registryAccess());
    }
    
    default <T> HolderLookup.RegistryLookup<T> lookupOrThrow(ResourceKey<? extends Registry<T>> key) {
        
        return registryAccess().lookupOrThrow(key);
    }
    
    boolean hasRegistryAccess();
    
    IAccessibleClientElementsProvider client();
    
    IAccessibleServerElementsProvider server();
    
    TagAddingRegistryLookup tagAddingRegistryLookup();
    
}
