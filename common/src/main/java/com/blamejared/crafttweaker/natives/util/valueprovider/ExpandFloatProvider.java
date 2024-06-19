package com.blamejared.crafttweaker.natives.util.valueprovider;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.util.valueproviders.FloatProvider;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/util/valueprovider/FloatProvider")
@NativeTypeRegistration(value = FloatProvider.class, zenCodeName = "crafttweaker.api.util.valueprovider.FloatProvider")
public class ExpandFloatProvider {
    
    @ZenCodeType.Getter("maxValue")
    public static float getMaxValue(FloatProvider internal) {
        
        return internal.getMaxValue();
    }
    
    @ZenCodeType.Getter("minValue")
    public static float getMinValue(FloatProvider internal) {
        
        return internal.getMinValue();
    }
    
}
