import crafttweaker.api.item.component.Tool;
import crafttweaker.api.item.component.ToolRule;
var tool = <item:minecraft:diamond_pickaxe>.getComponent<Tool>(<componenttype:minecraft:tool>);
println(tool.damagePerBlock);

val rules = [
    ToolRule.minesAndDrops(<tag:blocks:minecraft:planks>, 6.0f)
];
val myStickAxe = <item:minecraft:stick>.withTool(rules, 0.25f, 2)
    .withMaxDamage(10)
    .withDamage(0)
    .withCustomName("Stickaxe");    

craftingTable.addShaped("stick_axe", myStickAxe, [[<item:minecraft:stick>, <item:minecraft:stick>]]);