package com.blamejared.crafttweaker.natives.villager.trade.type;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeConstructor;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.level.ItemLike;

@ZenRegister
@Document("vanilla/api/villager/trade/type/ItemsAndEmeraldsToItems")
@NativeTypeRegistration(value = VillagerTrades.ItemsAndEmeraldsToItems.class, zenCodeName = "crafttweaker.api.villager.trade.type.ItemsAndEmeraldsToItems",
        constructors = {
                @NativeConstructor({
                        @NativeConstructor.ConstructorParameter(type = ItemCost.class, name = "fromItem", description = "The secondary item that is being given to the villager", examples = "ItemCost.of(<item:minecraft:dirt>)"),
                        @NativeConstructor.ConstructorParameter(type = int.class, name = "emeraldCost", description = "The amount of the emeralds that is being given to the villager", examples = "4"),
                        @NativeConstructor.ConstructorParameter(type = ItemStack.class, name = "toItem", description = "The itemstack that is being sold by the villager", examples = "<item:minecraft:diamond>"),
                        @NativeConstructor.ConstructorParameter(type = int.class, name = "maxUses", description = "How many times can this trade be used", examples = "1"),
                        @NativeConstructor.ConstructorParameter(type = int.class, name = "villagerXp", description = "How much experience does this trade reward the villager", examples = "8"),
                        @NativeConstructor.ConstructorParameter(type = float.class, name = "priceMultiplier", description = "How much the price is affected by demand, Hero of the Village, and village reputation", examples = "0.5"),
                })
        })
public class ExpandItemsAndEmeraldsToItems {
}
