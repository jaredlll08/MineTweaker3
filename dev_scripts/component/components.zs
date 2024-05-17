println(">>>");
import crafttweaker.api.component.DataComponentType;
var components = <item:minecraft:dirt>.components;
for key in components.keySet {
    println(key.commandString);
}
println(components.has(<componenttype:minecraft:max_stack_size>));
println(components.getComponent<int>(<componenttype:minecraft:max_stack_size>));
println(components.getOrDefault<int>(<componenttype:minecraft:damage>, 5));

import crafttweaker.api.component.DataComponentPatch;
DataComponentPatch.builder().setComponent<int?>(<componenttype:minecraft:damage>, 5);

import crafttweaker.api.component.TypedDataComponent;
var typedComp = TypedDataComponent<int?>.of<int?>(<componenttype:minecraft:damage>, 5);
println(typedComp.value<int?>());

import crafttweaker.api.item.component.SeededContainerLoot;
import crafttweaker.api.resource.ResourceKey;
import crafttweaker.api.loot.LootTable;

SeededContainerLoot.of(ResourceKey<LootTable>.createRegistryKey(<resource:minecraft:thing>), 59);

var sword_comps = <item:minecraft:diamond_pickaxe>.components;
for key in sword_comps.keySet {
    println(key.commandString);
}

craftingTable.addShapeless("component_test", <item:minecraft:diamond_pickaxe>.withJsonComponent(<componenttype:minecraft:max_stack_size>, 5).remove(<componenttype:minecraft:tool>), [<item:minecraft:stick>]);



import crafttweaker.api.item.component.CustomData;
import crafttweaker.api.food.FoodProperties;

<item:minecraft:diamond_pickaxe>.withJsonComponent(<componenttype:minecraft:max_stack_size>, 5).remove(<componenttype:minecraft:tool>);
// is the same as
<item:minecraft:diamond_pickaxe>.with<int>(<componenttype:minecraft:max_stack_size>, 5).remove(<componenttype:minecraft:tool>);

<item:minecraft:iron_sword>.withJsonComponent(<componenttype:minecraft:custom_data>, {foo: 1});
// is the same as
<item:minecraft:iron_sword>.with<CustomData>(<componenttype:minecraft:custom_data>, CustomData.of({foo: 1}));


<item:minecraft:sponge>.withJsonComponent(<componenttype:minecraft:food>, {saturation: 1.0, nutrition: 3, can_always_eat: true, eat_seconds: 2.4});
// is the same as
var food = FoodProperties.create(3, 1);
food.canAlwaysEat = true;
food.eatSeconds = 2.4;
<item:minecraft:sponge>.with<FoodProperties>(<componenttype:minecraft:food>, food);

