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
attributes.add(<attribute:minecraft:generic.jump_strength>, AttributeModifier.create(<resource:crafttweaker:jump_strength_1>, 0.5, <constant:minecraft:attribute/operation:add_value>), <constant:minecraft:equipmentslot/group:chest>);
<item:minecraft:diamond_chestplate>.definition.attributeModifiers = attributes.build();

<item:minecraft:golden_chestplate>.definition.addAttributeModifier(<attribute:minecraft:generic.jump_strength>, AttributeModifier.create(<resource:crafttweaker:jump_strength_2>, 0.5, <constant:minecraft:attribute/operation:add_value>), <constant:minecraft:equipmentslot/group:chest>);


<item:minecraft:stick>.definition.setJsonComponent(<componenttype:minecraft:max_stack_size>, 5);


import crafttweaker.api.item.component.AdventureModePredicate;

<tag:block:c:all_blocks>.addId(<resource:minecraft:cobblestone>);

<item:minecraft:flint_and_steel>.getDefinition().setJsonComponent(<componenttype:minecraft:can_place_on>, {blocks: '#c:all_blocks', show_in_tooltip: true});
// <item:minecraft:flint_and_steel>.getDefinition().setComponent<AdventureModePredicate>(<componenttype:minecraft:can_place_on>, AdventureModePredicate.ofTags([<tag:block:c:stones>], true));


<item:minecraft:cake>.definition.setComponent<int?>(<componenttype:minecraft:max_stack_size>, 8);