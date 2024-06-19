package com.blamejared.crafttweaker.natives.util.valueprovider;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/util/valueprovider/ConstantInt")
@NativeTypeRegistration(value = ConstantInt.class, zenCodeName = "crafttweaker.api.util.valueprovider.ConstantInt")
public class ExpandConstantInt {
    
    @ZenCodeType.StaticExpansionMethod
    public static ConstantInt of(int value) {
        
        return ConstantInt.of(value);
    }
    
    @ZenCodeType.Getter("value")
    public static int getValue(ConstantInt internal) {
        
        return internal.getValue();
    }
    
}
