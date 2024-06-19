package com.blamejared.crafttweaker.natives.item.enchantment.provider.type;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.providers.SingleEnchantment;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/enchantment/provider/type/SingleEnchantment")
@NativeTypeRegistration(value = SingleEnchantment.class, zenCodeName = "crafttweaker.api.item.enchantment.provider.type.SingleEnchantment")
public class ExpandSingleEnchantment {
    
    @ZenCodeType.StaticExpansionMethod
    public static SingleEnchantment of(Enchantment enchantment, IntProvider level) {
        
        return new SingleEnchantment(Services.REGISTRY.holderOrThrow(Registries.ENCHANTMENT, enchantment), level);
    }
    
    
    @ZenCodeType.Getter("enchantment")
    public static Enchantment enchantment(SingleEnchantment internal) {
        
        return internal.enchantment().value();
    }
    
    @ZenCodeType.Getter("level")
    public static IntProvider level(SingleEnchantment internal) {
        
        return internal.level();
    }
    
}
