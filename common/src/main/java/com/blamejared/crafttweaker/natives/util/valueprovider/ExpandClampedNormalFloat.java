package com.blamejared.crafttweaker.natives.util.valueprovider;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/util/valueprovider/ClampedNormalFloat")
@NativeTypeRegistration(value = ClampedNormalFloat.class, zenCodeName = "crafttweaker.api.util.valueprovider.ClampedNormalFloat")
public class ExpandClampedNormalFloat {
    
    @ZenCodeType.StaticExpansionMethod
    public static ClampedNormalFloat of(float mean, float deviation, float min, float max) {
        
        return ClampedNormalFloat.of(mean, deviation, min, max);
    }
    
}
