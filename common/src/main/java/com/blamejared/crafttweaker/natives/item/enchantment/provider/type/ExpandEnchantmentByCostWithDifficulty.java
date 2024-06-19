package com.blamejared.crafttweaker.natives.item.enchantment.provider.type;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.providers.EnchantmentsByCostWithDifficulty;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/enchantment/provider/type/EnchantmentsByCostWithDifficulty")
@NativeTypeRegistration(value = EnchantmentsByCostWithDifficulty.class, zenCodeName = "crafttweaker.api.item.enchantment.provider.type.EnchantmentsByCostWithDifficulty")
public class ExpandEnchantmentByCostWithDifficulty {
    
    @ZenCodeType.StaticExpansionMethod
    public static EnchantmentsByCostWithDifficulty of(Enchantment[] enchantments, int minCost, int maxCostSpan) {
        
        return new EnchantmentsByCostWithDifficulty(HolderSet.direct(o -> Services.REGISTRY.holderOrThrow(Registries.ENCHANTMENT, o), enchantments), minCost, maxCostSpan);
    }
    
    @ZenCodeType.Getter("enchantments")
    public static Enchantment[] enchantment(EnchantmentsByCostWithDifficulty internal) {
        
        return internal.enchantments().stream().map(Holder::value).toArray(Enchantment[]::new);
    }
    
    @ZenCodeType.Getter("minCost")
    public static int minCost(EnchantmentsByCostWithDifficulty internal) {
        
        return internal.minCost();
    }
    
    @ZenCodeType.Getter("maxCostSpan")
    public static int maxCostSpan(EnchantmentsByCostWithDifficulty internal) {
        
        return internal.maxCostSpan();
    }
    
}
