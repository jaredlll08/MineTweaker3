package com.blamejared.crafttweaker.api.action.recipe;

import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import com.blamejared.crafttweaker.api.action.internal.CraftTweakerAction;
import com.blamejared.crafttweaker.api.recipe.RecipeList;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public abstract class ActionRecipeBase<T extends Recipe<?>> extends CraftTweakerAction implements IRuntimeAction {
    
    private final IRecipeManager<T> manager;
    
    public ActionRecipeBase(IRecipeManager<T> manager) {
        
        this.manager = manager;
    }
    
    public IRecipeManager<T> getManager() {
        
        return manager;
    }
    
    /**
     * Gets a {@link RecipeList} for this manager, which helps ensure that changes are applied to all recipe maps that vanilla has.
     *
     * @return A {@link RecipeList} for the recipe manager type.
     */
    public RecipeList<T> getRecipeMutator() {
        
        return this.getManager().getRecipeList();
    }
    
    public RecipeType<T> getRecipeType() {
        
        return this.getManager().getRecipeType();
    }
    
    public ResourceLocation getRecipeTypeName() {
        
        return BuiltInRegistries.RECIPE_TYPE.getKey(getRecipeType());
    }
    
    
}
