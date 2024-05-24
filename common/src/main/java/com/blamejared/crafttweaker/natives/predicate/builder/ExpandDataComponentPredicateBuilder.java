package com.blamejared.crafttweaker.natives.predicate.builder;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.core.component.DataComponentType;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/predicate/builder/DataComponentPredicateBuilder")
@NativeTypeRegistration(value = DataComponentPredicate.Builder.class, zenCodeName = "crafttweaker.api.predicate.builder.DataComponentPredicateBuilder")
public class ExpandDataComponentPredicateBuilder {
    
    @ZenCodeType.StaticExpansionMethod
    public static DataComponentPredicate empty() {
        
        return DataComponentPredicate.EMPTY;
    }
    
    @ZenCodeType.Method
    public static <T> DataComponentPredicate.Builder expect(final DataComponentPredicate.Builder internal, Class<T> tClass, DataComponentType<? super T> type, T value) {
        
        return internal.expect(type, value);
    }
    
    @ZenCodeType.Method
    public static DataComponentPredicate build(final DataComponentPredicate.Builder internal) {
        
        return internal.build();
    }
    
}
