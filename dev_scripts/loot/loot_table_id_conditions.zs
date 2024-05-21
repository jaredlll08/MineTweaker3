import crafttweaker.api.loot.condition.LootConditions;
import crafttweaker.api.loot.condition.LootTableIdLootCondition;
import crafttweaker.api.item.IItemStack;

val ocean_loot as float[IItemStack] = {
    <item:minecraft:potion>: 0.4
};

// /loot insert ~ ~ ~ loot minecraft:chests/underwater_ruin_big
loot.modifiers.register("loot_name_in_oceans",
    LootConditions.anyOf([
        LootTableIdLootCondition.create(<resource:minecraft:chests/shipwreck_map>),
        LootTableIdLootCondition.create(<resource:minecraft:chests/shipwreck_supply>),
        LootTableIdLootCondition.create(<resource:minecraft:chests/underwater_ruin_big>),
        LootTableIdLootCondition.create(<resource:minecraft:chests/underwater_ruin_small>),
        LootTableIdLootCondition.create(<resource:minecraft:chests/buried_treasure>),
    ]),
    (stacks, context) => {
        for item, chance in ocean_loot {
            if context.random.nextFloat() < chance {
                stacks.add(item);
            }
        }
        return stacks;
    }
);

loot.modifiers.register(
   "sands",
   LootConditions.only(LootTableIdLootCondition.create(<resource:minecraft:blocks/sand>)),
   (stacks, context) => {
       stacks.add(<item:minecraft:dirt>.withCustomName("from loot.zs"));
       return stacks;
   }
);