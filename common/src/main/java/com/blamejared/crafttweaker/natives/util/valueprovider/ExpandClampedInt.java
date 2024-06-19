package com.blamejared.crafttweaker.natives.util.valueprovider;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.util.valueproviders.ClampedInt;
import net.minecraft.util.valueproviders.IntProvider;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/util/valueprovider/ClampedInt")
@NativeTypeRegistration(value = ClampedInt.class, zenCodeName = "crafttweaker.api.util.valueprovider.ClampedInt")
public class ExpandClampedInt {
    
    @ZenCodeType.StaticExpansionMethod
    public static ClampedInt of(IntProvider delegate, int minInclusive, int maxInclusive) {
        
        return ClampedInt.of(delegate, minInclusive, maxInclusive);
    }
    
}
