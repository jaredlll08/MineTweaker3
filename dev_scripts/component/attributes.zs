import crafttweaker.api.item.component.ItemAttributeModifiers;
import crafttweaker.api.entity.attribute.AttributeModifier;

val modifiers = ItemAttributeModifiers.builder().add(<attribute:minecraft:player.block_break_speed>,
                     AttributeModifier.create(<resource:crafttweaker:break_speed>, 2.0, <constant:minecraft:attribute/operation:add_value>),
                     <constant:minecraft:equipmentslot/group:mainhand>).build();

val better_pickaxe = <item:minecraft:diamond_pickaxe>.withAttributeModifiers(modifiers).withItemName("Drill");

craftingTable.addShaped("better_pickaxe", better_pickaxe, [
    [<item:minecraft:diamond>, <item:minecraft:netherite_ingot>, <item:minecraft:diamond>],
    [<item:minecraft:air>, <item:minecraft:stick>, <item:minecraft:air>],
    [<item:minecraft:air>, <item:minecraft:stick>, <item:minecraft:air>]
]);