craftingTable.addJsonRecipe("json_recipe_test",
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "group": "boat",
  "key": {
    "#": {
      "item": "minecraft:diamond"
    }
  },
  "pattern": [
    "# #",
    "###"
  ],
  "result": {
    "count": 1,
    "id": "minecraft:acacia_boat"
  }
}
);

val acacia_boat = craftingTable.getRecipeAsJson("minecraft:acacia_boat");
println(acacia_boat.getAsString());
craftingTable.addJsonRecipe("diamond_boat", acacia_boat.map(data => {
    data["key"]["#"] = <item:minecraft:diamond>;
    return data;
}));