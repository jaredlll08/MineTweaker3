import crafttweaker.api.recipe.replacement.Replacer;
import crafttweaker.api.ingredient.IIngredient;

Replacer.create()
    .replace<IIngredient>(
        <recipecomponent:crafttweaker:input/ingredients>,
        <item:minecraft:iron_ingot>,
        <item:minecraft:apple>
    )
    .execute();