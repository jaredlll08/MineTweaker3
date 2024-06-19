package com.blamejared.crafttweaker.natives.util.valueprovider;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.SampledFloat;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/util/valueprovider/SampledFloat")
@NativeTypeRegistration(value = SampledFloat.class, zenCodeName = "crafttweaker.api.util.valueprovider.SampledFloat")
public class ExpandSampledFloat {
    
    @ZenCodeType.Method
    public static float sample(SampledFloat internal, RandomSource random) {
        
        return internal.sample(random);
    }
    
}
