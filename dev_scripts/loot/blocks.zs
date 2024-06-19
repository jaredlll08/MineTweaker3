import crafttweaker.api.loot.condition.LootConditions;
import crafttweaker.api.loot.condition.BlockStatePropertyLootCondition;
import crafttweaker.api.loot.condition.MatchToolLootCondition;
import crafttweaker.api.predicate.ItemPredicate;
import crafttweaker.api.loot.modifier.CommonLootModifiers;

loot.modifiers.register(
  "bracket_drop",
  LootConditions.allOf([
    BlockStatePropertyLootCondition.create(<block:minecraft:dirt>),
    MatchToolLootCondition.create(ItemPredicate.create(<tag:item:minecraft:wool>))
  ]),
  CommonLootModifiers.add(<item:minecraft:diamond>)
);