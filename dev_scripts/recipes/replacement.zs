import crafttweaker.api.recipe.replacement.Replacer;
import crafttweaker.api.ingredient.IIngredient;
import crafttweaker.api.recipe.replacement.type.ModsFilteringRule;

// FIX RECIPE REPLACEMENT

Replacer.create().filter(ModsFilteringRule.of("minecraft")).replace<IIngredient>(<recipecomponent:crafttweaker:input/ingredients>, <item:minecraft:iron_ingot>, <item:minecraft:crying_obsidian>).execute();