import crafttweaker.api.item.component.ItemAttributeModifiers;
import crafttweaker.api.entity.attribute.AttributeModifier;

val my_uuid_v4 = "596e0826-7c66-42c6-b3da-45a6d667ccf7";
val modifiers = ItemAttributeModifiers.builder().add(<attribute:minecraft:player.block_break_speed>,
                     AttributeModifier.create("test", 2.0, <constant:minecraft:attribute/operation:add_value>, my_uuid_v4),
                     <constant:minecraft:equipmentslot/group:mainhand>).build();

val better_pickaxe = <item:minecraft:diamond_pickaxe>.withAttributeModifiers(modifiers).withItemName("Drill");

craftingTable.addShaped("better_pickaxe", better_pickaxe, [
    [<item:minecraft:diamond>, <item:minecraft:netherite_ingot>, <item:minecraft:diamond>],
    [<item:minecraft:air>, <item:minecraft:stick>, <item:minecraft:air>],
    [<item:minecraft:air>, <item:minecraft:stick>, <item:minecraft:air>]
]);