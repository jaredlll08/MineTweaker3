import crafttweaker.api.item.component.Tool;
import crafttweaker.api.item.component.ToolRule;
var tool = <item:minecraft:diamond_pickaxe>.getComponent<Tool>(<componenttype:minecraft:tool>);
println(tool.damagePerBlock);

val rules = [    ToolRule.minesAndDrops(<tag:block:minecraft:planks>, 2.0f)];
val myStickAxe = <item:minecraft:stick>.withTool(rules, 0.5f, 2);

craftingTable.addShaped("stick_axe", myStickAxe, [[<item:minecraft:stick>, <item:minecraft:stick>]]);