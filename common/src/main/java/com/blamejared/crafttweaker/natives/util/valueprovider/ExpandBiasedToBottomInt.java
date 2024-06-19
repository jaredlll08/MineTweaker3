package com.blamejared.crafttweaker.natives.util.valueprovider;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/util/valueprovider/BiasedToBottomInt")
@NativeTypeRegistration(value = BiasedToBottomInt.class, zenCodeName = "crafttweaker.api.util.valueprovider.BiasedToBottomInt")
public class ExpandBiasedToBottomInt {
    
    @ZenCodeType.StaticExpansionMethod
    public static BiasedToBottomInt of(int minInclusive, int maxInclusive) {
        
        return BiasedToBottomInt.of(minInclusive, maxInclusive);
    }
    
}
