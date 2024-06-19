package com.blamejared.crafttweaker.natives.util.valueprovider;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.util.valueproviders.ConstantFloat;

@ZenRegister
@Document("vanilla/api/util/valueprovider/ConstantFloat")
@NativeTypeRegistration(value = ConstantFloat.class, zenCodeName = "crafttweaker.api.util.valueprovider.ConstantFloat")
public class ExpandConstantFloat {
    
    public static ConstantFloat of(float value) {
        
        return ConstantFloat.of(value);
    }
    
}
