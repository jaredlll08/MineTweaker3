package com.blamejared.crafttweaker.natives.villager.trade.type;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.providers.EnchantmentProvider;
import net.minecraft.world.item.trading.ItemCost;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Optional;

@ZenRegister
@Document("vanilla/api/villager/trade/type/ItemsAndEmeraldsToItems")
@NativeTypeRegistration(value = VillagerTrades.ItemsAndEmeraldsToItems.class, zenCodeName = "crafttweaker.api.villager.trade.type.ItemsAndEmeraldsToItems")
public class ExpandItemsAndEmeraldsToItems {
    
    @ZenCodeType.StaticExpansionMethod
    public static VillagerTrades.ItemsAndEmeraldsToItems of(ItemCost fromItem, int emeraldCost, ItemStack toItem, int maxUses, int villagerXp, float priceMultiplier, @ZenCodeType.Nullable @ZenCodeType.Optional ResourceKey<EnchantmentProvider> enchantmentProvider) {
        
        return new VillagerTrades.ItemsAndEmeraldsToItems(fromItem, emeraldCost, toItem, maxUses, villagerXp, priceMultiplier, Optional.ofNullable(enchantmentProvider));
    }
    
}
