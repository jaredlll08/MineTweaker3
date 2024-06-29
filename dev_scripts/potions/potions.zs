import crafttweaker.api.text.Component;
import crafttweaker.api.entity.effect.MobEffectInstance;

val water_bottle = <item:minecraft:potion>.withPotionContents(<potion:minecraft:water>);

val juice_effects = [
    MobEffectInstance.of(<mobeffect:minecraft:strength>, 20 * 20, 1, false, true, true),
    MobEffectInstance.of(<mobeffect:minecraft:speed>, 20*20, 1, false, false, true)
];

val pinneaple_juice = water_bottle
    .withCustomName(Component.literal("Pinneapple Juice"))
    .withPotionContents(<potion:minecraft:water>, 0xFFFFFF00, juice_effects);

craftingTable.addShapeless("juice", pinneaple_juice, [water_bottle, <item:minecraft:torchflower>]);