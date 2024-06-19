package com.blamejared.crafttweaker.natives.util.valueprovider;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.util.valueproviders.MultipliedFloats;
import net.minecraft.util.valueproviders.SampledFloat;

@ZenRegister
@Document("vanilla/api/util/valueprovider/MultipliedFloats")
@NativeTypeRegistration(value = MultipliedFloats.class, zenCodeName = "crafttweaker.api.util.valueprovider.MultipliedFloats")
public class ExpandMultipliedFloats {
    
    public static MultipliedFloats of(SampledFloat... values) {
        
        return new MultipliedFloats(values);
    }
    
}
