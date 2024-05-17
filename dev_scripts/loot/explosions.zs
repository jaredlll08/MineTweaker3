import crafttweaker.api.loot.condition.LootConditions;
 import crafttweaker.api.loot.condition.ExplosionLootCondition;


loot.modifiers.register(
    "ore_rework",
    LootConditions.only(ExplosionLootCondition.create()),
    (drops, ctx) => {
        drops.add(<item:minecraft:arrow> * 8);
        return drops;
    }
);