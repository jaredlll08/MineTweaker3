import crafttweaker.api.item.component.Tool;

var tool = <item:minecraft:diamond_pickaxe>.getComponent<Tool>(<componenttype:minecraft:tool>);
println(tool.damagePerBlock);