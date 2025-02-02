import crafttweaker.api.item.component.CustomData;
import crafttweaker.api.food.FoodProperties;
import crafttweaker.api.text.Component;

println(<item:minecraft:diamond_pickaxe>.withJsonComponent(<componenttype:minecraft:max_stack_size>, 5).remove(<componenttype:minecraft:tool>).commandString);
// is the same as
println(<item:minecraft:diamond_pickaxe>.with<int>(<componenttype:minecraft:max_stack_size>, 5).remove(<componenttype:minecraft:tool>).commandString);

println(<item:minecraft:iron_sword>.withJsonComponent(<componenttype:minecraft:custom_data>, {foo: 1}).commandString);
// is the same as
println(<item:minecraft:iron_sword>.with<CustomData>(<componenttype:minecraft:custom_data>, CustomData.of({foo: 1})).commandString);


println(<item:minecraft:sponge>.withJsonComponent(<componenttype:minecraft:food>, {saturation: 1.0, nutrition: 3, can_always_eat: true, eat_seconds: 2.4}).commandString);
// is the same as
var food = FoodProperties.create(3, 1, true, 2.4);
println(<item:minecraft:sponge>.with<FoodProperties>(<componenttype:minecraft:food>, food).commandString);


<item:minecraft:diamond>.definition.setJsonComponent(<componenttype:minecraft:lore>, ["line1", "line2", "line3"]);
<item:minecraft:diamond>.definition.setJsonComponent(<componenttype:minecraft:lore>, [Component.translatable("tooltip.test.line1"), Component.translatable("tooltip.test.line2")]);