package com.blamejared.crafttweaker.natives.util.valueprovider;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.util.valueproviders.UniformFloat;

@ZenRegister
@Document("vanilla/api/util/valueprovider/UniformFloat")
@NativeTypeRegistration(value = UniformFloat.class, zenCodeName = "crafttweaker.api.util.valueprovider.UniformFloat")
public class ExpandUniformFloat {
    
    public static UniformFloat of(float minInclusive, float maxExclusive) {
        
        return UniformFloat.of(minInclusive, maxExclusive);
    }
    
}
