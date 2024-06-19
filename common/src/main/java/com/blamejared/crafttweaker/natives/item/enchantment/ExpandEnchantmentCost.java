package com.blamejared.crafttweaker.natives.item.enchantment;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.enchantment.Enchantment;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/enchantment/EnchantmentCost")
@NativeTypeRegistration(value = Enchantment.Cost.class, zenCodeName = "crafttweaker.api.item.enchantment.EnchantmentCost")
public class ExpandEnchantmentCost {
    
    @ZenCodeType.Method
    public static int calculate(Enchantment.Cost internal, int level) {
        
        return internal.calculate(level);
    }
    
    @ZenCodeType.Getter("perLevelAboveFirst")
    public static int perLevelAboveFirst(Enchantment.Cost internal) {
        
        return internal.perLevelAboveFirst();
    }
    
    @ZenCodeType.Getter("base")
    public static int base(Enchantment.Cost internal) {
        
        return internal.base();
    }
    
}
