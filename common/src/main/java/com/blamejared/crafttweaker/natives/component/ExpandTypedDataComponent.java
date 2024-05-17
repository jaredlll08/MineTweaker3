package com.blamejared.crafttweaker.natives.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.core.component.TypedDataComponent;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/component/TypedDataComponent")
@NativeTypeRegistration(value = TypedDataComponent.class, zenCodeName = "crafttweaker.api.component.TypedDataComponent")
public class ExpandTypedDataComponent {
    
    @ZenCodeType.StaticExpansionMethod
    public static <T> TypedDataComponent<T> of(Class<T> tClass, DataComponentType<T> type, T value) {
        
        return new TypedDataComponent<>(type, value);
    }
    
    @ZenCodeType.Method
    public static <T> void applyTo(TypedDataComponent<T> internal, Class<T> tClass, PatchedDataComponentMap patchedMap) {
        
        internal.applyTo(patchedMap);
    }
    
    @ZenCodeType.Method
    public static <T> T value(TypedDataComponent<T> internal, Class<T> tClass) {
        
        return internal.value();
    }
    
    @ZenCodeType.Method
    public static <T> DataComponentType<T> type(TypedDataComponent<T> internal, Class<T> tClass) {
        
        return internal.type();
    }
    
}
