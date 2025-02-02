package com.blamejared.crafttweaker.api.action.recipe.generic;

import com.blamejared.crafttweaker.api.util.GenericUtil;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ActionRemoveGenericRecipe extends ActionRemoveGenericRecipeBase {
    
    protected final Predicate<RecipeHolder<Recipe<RecipeInput>>> removePredicate;
    protected Supplier<String> describeFunction;
    
    public ActionRemoveGenericRecipe(Predicate<RecipeHolder<Recipe<RecipeInput>>> removePredicate) {
        
        this.removePredicate = removePredicate;
        this.describeFunction = () -> "Removing all recipes that match a custom condition";
    }
    
    public ActionRemoveGenericRecipe(Predicate<RecipeHolder<Recipe<RecipeInput>>> removePredicate, Supplier<String> describeFunction) {
        
        this.removePredicate = removePredicate;
        this.describeFunction = describeFunction;
    }
    
    @Override
    public String describe() {
        
        return describeFunction.get();
    }
    
    @Override
    protected boolean shouldRemove(RecipeHolder<?> holder) {
        
        return removePredicate.test(GenericUtil.uncheck(holder));
    }
    
}
