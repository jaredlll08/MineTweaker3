package com.blamejared.crafttweaker.natives.util.valueprovider;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.util.valueproviders.TrapezoidFloat;

@ZenRegister
@Document("vanilla/api/util/valueprovider/TrapezoidFloat")
@NativeTypeRegistration(value = TrapezoidFloat.class, zenCodeName = "crafttweaker.api.util.valueprovider.TrapezoidFloat")
public class ExpandTrapezoidFloat {
    
    public static TrapezoidFloat of(float min, float max, float plateau) {
        
        return TrapezoidFloat.of(min, max, plateau);
    }
    
}
