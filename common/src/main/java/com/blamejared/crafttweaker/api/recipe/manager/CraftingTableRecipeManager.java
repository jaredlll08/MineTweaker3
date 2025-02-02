package com.blamejared.crafttweaker.api.recipe.manager;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.MirrorAxis;
import com.blamejared.crafttweaker.api.recipe.fun.RecipeFunction1D;
import com.blamejared.crafttweaker.api.recipe.fun.RecipeFunction2D;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.recipe.type.CTShapedRecipe;
import com.blamejared.crafttweaker.api.recipe.type.CTShapelessRecipe;
import com.blamejared.crafttweaker.api.util.RecipeUtil;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeGlobals;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;
import java.util.Map;

/**
 * @docParam this craftingTable
 */
@ZenRegister
@ZenCodeType.Name("crafttweaker.api.recipe.CraftingTableRecipeManager")
@Document("vanilla/api/recipe/manager/CraftingTableRecipeManager")
public class CraftingTableRecipeManager implements IRecipeManager<CraftingRecipe> {
    
    @ZenCodeGlobals.Global("craftingTable")
    public static final CraftingTableRecipeManager INSTANCE = new CraftingTableRecipeManager();
    
    private CraftingTableRecipeManager() {}
    
    @ZenCodeType.Method
    public void addShaped(String recipeName, IItemStack output, IIngredient[][] ingredients, @ZenCodeType.Optional RecipeFunction2D recipeFunction) {
        
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, createHolder(fixRecipeId(recipeName), new CTShapedRecipe(output, ingredients, MirrorAxis.NONE, recipeFunction)), "shaped"));
    }
    
    @ZenCodeType.Method
    public void addShapedPattern(String recipeName, IItemStack output, String[] pattern, Map<String, IIngredient> keys, @ZenCodeType.Optional RecipeFunction2D recipeFunction) {
        
        // TODO right now this requires casting the map nicely, which is not ideal at all, we need to add some rewrites for it
        int height = pattern.length;
        int width = Arrays.stream(pattern).mapToInt(String::length).max().orElse(0);
        
        IIngredient[][] ingredients = RecipeUtil.dissolvePattern(pattern, keys, width, height);
        
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, createHolder(fixRecipeId(recipeName), new CTShapedRecipe(output, ingredients, MirrorAxis.NONE, recipeFunction)), "shaped"));
    }
    
    /**
     * Adds a mirrored shaped recipe to the crafting table.
     *
     * This method lets you provide a {@link MirrorAxis}, which can be used to set which axis the recipe is mirrored on.
     * Use cases are making a recipe only be mirrored vertically or only horizontally.
     *
     * @param recipeName     name of the recipe to add.
     * @param mirrorAxis     The axis that this recipe mirrored on.
     * @param output         output {@link IItemStack}
     * @param ingredients    array of an array of {@link IIngredient} for inputs
     * @param recipeFunction optional {@link RecipeFunction2D} for more advanced conditions
     *
     * @docParam recipeName "recipe_name"
     * @docParam mirrorAxis MirrorAxis.DIAGONAL
     * @docParam output <item:minecraft:dirt>
     * @docParam ingredients [[<item:minecraft:diamond>], [<tag:item:minecraft:wool>]]
     * @docParam recipeFunction (usualOut as IItemStack, inputs as IItemStack[][]) => {if(inputs[0][0].displayName == "totally real diamond block" ){return usualOut;}return <item:minecraft:clay>.setDisplayName("Diamond");}
     */
    @ZenCodeType.Method
    public void addShapedMirrored(String recipeName, MirrorAxis mirrorAxis, IItemStack output, IIngredient[][] ingredients, @ZenCodeType.Optional RecipeFunction2D recipeFunction) {
        
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, createHolder(fixRecipeId(recipeName), new CTShapedRecipe(output, ingredients, mirrorAxis, recipeFunction)), "mirroring shaped"));
    }
    
    @ZenCodeType.Method
    public void addShapeless(String recipeName, IItemStack output, IIngredient[] ingredients, @ZenCodeType.Optional RecipeFunction1D recipeFunction) {
        
        ResourceLocation name = fixRecipeId(recipeName);
        CTShapelessRecipe.checkEmptyIngredient(name, ingredients);
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, createHolder(name, new CTShapelessRecipe(output, ingredients, recipeFunction)), "shapeless"));
    }
    
    @Override
    public RecipeType<CraftingRecipe> getRecipeType() {
        
        return RecipeType.CRAFTING;
    }
    
    
}
