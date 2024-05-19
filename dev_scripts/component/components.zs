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


