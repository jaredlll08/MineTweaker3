import crafttweaker.api.ingredient.type.IIngredientAny;

var onlyEvenlyNamedItems = IIngredientAny.getInstance().onlyIf("only_even", stack => {
  return stack.registryName.toString().length % 2 == 0;
});

craftingTable.addShapeless("even_diamonds", <item:minecraft:diamond>, [onlyEvenlyNamedItems, onlyEvenlyNamedItems, onlyEvenlyNamedItems]);