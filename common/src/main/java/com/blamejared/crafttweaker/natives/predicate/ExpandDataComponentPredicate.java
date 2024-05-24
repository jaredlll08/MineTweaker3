package com.blamejared.crafttweaker.natives.predicate;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPredicate;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/predicate/DataComponentPredicate")
@NativeTypeRegistration(value = DataComponentPredicate.class, zenCodeName = "crafttweaker.api.predicate.DataComponentPredicate")
public final class ExpandDataComponentPredicate {
    
    @ZenCodeType.StaticExpansionMethod
    public static DataComponentPredicate.Builder create() {
        
        return DataComponentPredicate.builder();
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static DataComponentPredicate allOf(DataComponentMap map) {
        
        return DataComponentPredicate.allOf(map);
    }
    
}
