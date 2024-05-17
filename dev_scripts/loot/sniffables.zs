import crafttweaker.api.loot.condition.LootConditions;
import crafttweaker.api.loot.condition.LootTableIdLootCondition;
import crafttweaker.api.entity.Entity;
import stdlib.List;
import crafttweaker.api.item.IItemStack;

// Add the soul blocks to the sniffer diggable tag
<tag:blocks:minecraft:sniffer_diggable_block>.add(<block:minecraft:soul_sand>, <block:minecraft:soul_soil>);

loot.modifiers.register(
    "sniffers",
    // Target the sniffer loot table
    LootConditions.only(LootTableIdLootCondition.create(<resource:minecraft:gameplay/sniffer_digging>)),
    (stacks, context) => {
        val level = context.level;
        val entity = context.thisEntity as Entity;
        val blockpos = entity.blockPosition;
        // soulsand allows you to fall through the block a tiny bit, and sniffers hitbox goes
        // into the block beneath it slightly, so we need to check both the block beneath the sniffer
        // for soul soil, as well as the block the sniffer is currently inside for soul sand
        if <block:minecraft:soul_soil> ==  level.getBlockState(blockpos.below()) || <blockstate:minecraft:soul_sand> == level.getBlockState(blockpos) {
            val newList = new List<IItemStack>();
            newList.add(<item:minecraft:netherite_scrap>);
            return newList;
        }
        return stacks;
    }
);