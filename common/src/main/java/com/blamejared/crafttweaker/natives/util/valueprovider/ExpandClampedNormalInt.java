package com.blamejared.crafttweaker.natives.util.valueprovider;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.util.valueproviders.ClampedNormalInt;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/util/valueprovider/ClampedNormalInt")
@NativeTypeRegistration(value = ClampedNormalInt.class, zenCodeName = "crafttweaker.api.util.valueprovider.ClampedNormalInt")
public class ExpandClampedNormalInt {
    
    @ZenCodeType.StaticExpansionMethod
    public static ClampedNormalInt of(float mean, float deviation, int minInclusive, int maxInclusive) {
        
        return ClampedNormalInt.of(mean, deviation, minInclusive, maxInclusive);
    }
    
}
