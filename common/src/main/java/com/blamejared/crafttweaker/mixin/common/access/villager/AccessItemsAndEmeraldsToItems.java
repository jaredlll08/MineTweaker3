package com.blamejared.crafttweaker.mixin.common.access.villager;

import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(VillagerTrades.ItemsAndEmeraldsToItems.class)
public interface AccessItemsAndEmeraldsToItems {
    
    @Accessor("fromItem")
    ItemCost crafttweaker$getFromItem();
    
    @Accessor("toItem")
    ItemStack crafttweaker$getToItem();
    
}
