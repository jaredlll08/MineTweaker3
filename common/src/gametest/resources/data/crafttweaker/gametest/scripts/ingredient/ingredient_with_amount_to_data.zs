import crafttweaker.api.util.Many;
import crafttweaker.api.data.IData;
import crafttweaker.api.ingredient.IIngredientWithAmount;

var many = (<tag:item:minecraft:wool> * 2) as IIngredientWithAmount;
storeData(many as IData);
