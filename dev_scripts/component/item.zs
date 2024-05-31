import crafttweaker.api.item.component.CustomData;
import crafttweaker.api.item.component.Unbreakable;
import crafttweaker.api.text.Component;
import crafttweaker.api.item.component.ItemAttributeModifiers;
import crafttweaker.api.entity.attribute.AttributeModifier;

<item:minecraft:dirt>.definition.maxStackSize = 59;
<item:minecraft:diamond_shovel>.definition.maxDamage = 5;
<item:minecraft:diamond_sword>.definition.unbreakable = Unbreakable.of(true);
<item:minecraft:stick>.definition.customName = Component.literal("Walking stick");

val attributes = ItemAttributeModifiers.builder();
attributes.add(<attribute:minecraft:generic.jump_strength>, AttributeModifier.create("name", 0.5, <constant:minecraft:attribute/operation:add_value>, "6d79f9c1-a4ab-4e72-a0ab-71870b89b4c6"), <constant:minecraft:equipmentslot/group:chest>);
<item:minecraft:diamond_chestplate>.definition.attributeModifiers = attributes.build();

<item:minecraft:golden_chestplate>.definition.addAttributeModifier(<attribute:minecraft:generic.jump_strength>, AttributeModifier.create("name", 0.5, <constant:minecraft:attribute/operation:add_value>, "7d79f9c1-a4ab-4e72-a0ab-71870b89b4c6"), <constant:minecraft:equipmentslot/group:chest>);


<item:minecraft:stick>.definition.setJsonComponent(<componenttype:minecraft:max_stack_size>, 5);