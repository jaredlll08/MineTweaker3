package com.blamejared.crafttweaker.natives.item.enchantment.provider.type;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.providers.EnchantmentsByCost;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/enchantment/provider/type/EnchantmentsByCost")
@NativeTypeRegistration(value = EnchantmentsByCost.class, zenCodeName = "crafttweaker.api.item.enchantment.provider.type.EnchantmentsByCost")
public class ExpandEnchantmentByCost {
    
    @ZenCodeType.StaticExpansionMethod
    public static EnchantmentsByCost of(Enchantment[] enchantments, IntProvider cost) {
        
        return new EnchantmentsByCost(HolderSet.direct(o -> Services.REGISTRY.holderOrThrow(Registries.ENCHANTMENT, o), enchantments), cost);
    }
    
    @ZenCodeType.Getter("enchantments")
    public static Enchantment[] enchantment(EnchantmentsByCost internal) {
        
        return internal.enchantments().stream().map(Holder::value).toArray(Enchantment[]::new);
    }
    
    @ZenCodeType.Getter("cost")
    public static IntProvider cost(EnchantmentsByCost internal) {
        
        return internal.cost();
    }
    
}
