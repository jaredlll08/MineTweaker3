package com.blamejared.crafttweaker.natives.util.valueprovider;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.util.valueproviders.UniformInt;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/util/valueprovider/UniformInt")
@NativeTypeRegistration(value = UniformInt.class, zenCodeName = "crafttweaker.api.util.valueprovider.UniformInt")
public class ExpandUniformInt {
    
    @ZenCodeType.StaticExpansionMethod
    public static UniformInt of(int minInclusive, int maxInclusive) {
        
        return UniformInt.of(minInclusive, maxInclusive);
    }
    
}
