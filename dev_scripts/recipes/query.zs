import crafttweaker.api.recipe.RecipeHolder;
import crafttweaker.api.recipe.type.Recipe;
import crafttweaker.api.world.Container;
import crafttweaker.api.recipe.input.RecipeInput;

val recipe = recipes.getRecipeByName("minecraft:acacia_boat");
if recipe is RecipeHolder<Recipe<RecipeInput>> {
    println(recipe.value.resultItem.commandString);
}