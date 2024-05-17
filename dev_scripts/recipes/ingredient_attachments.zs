craftingTable.addShapeless("transforms", <item:minecraft:dirt>, [<item:minecraft:diamond_sword>.transformDamage(1)]);
craftingTable.addShapeless("nested_transforms", <item:minecraft:dirt>, [<item:minecraft:iron_sword>.transformReplace(<item:minecraft:golden_sword>).transformDamage(1)]);
craftingTable.addShapeless("list_transforms", <item:minecraft:dirt>, [(<item:minecraft:iron_shovel> | <item:minecraft:golden_shovel>).transformDamage(1)]);

craftingTable.addShapeless("conditions", <item:minecraft:dirt>, [<item:minecraft:stone_sword>.anyDamage()]);
craftingTable.addShapeless("nested_conditions", <item:minecraft:dirt>, [<item:minecraft:stone_shovel>.onlyDamaged().onlyIf("enchanted_condition", stack => stack.isEnchanted)]);
craftingTable.addShapeless("list_conditions", <item:minecraft:dirt>, [(<item:minecraft:stone_axe>.onlyDamaged() | <item:minecraft:golden_axe>).onlyIf("enchanted_condition", stack => stack.isEnchanted)]);
