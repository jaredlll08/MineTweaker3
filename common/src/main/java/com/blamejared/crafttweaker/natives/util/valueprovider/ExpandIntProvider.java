package com.blamejared.crafttweaker.natives.util.valueprovider;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/util/valueprovider/IntProvider")
@NativeTypeRegistration(value = IntProvider.class, zenCodeName = "crafttweaker.api.util.valueprovider.IntProvider")
public class ExpandIntProvider {
    
    //TODO expose WeightedListInt
    @ZenCodeType.Getter("maxValue")
    public static int getMaxValue(IntProvider internal) {
        
        return internal.getMaxValue();
    }
    
    @ZenCodeType.Method
    public static int sample(IntProvider internal, RandomSource var1) {
        
        return internal.sample(var1);
    }
    
    @ZenCodeType.Getter("minValue")
    public static int getMinValue(IntProvider internal) {
        
        return internal.getMinValue();
    }
    
}
