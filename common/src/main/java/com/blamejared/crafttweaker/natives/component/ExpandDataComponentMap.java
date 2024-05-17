package com.blamejared.crafttweaker.natives.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.TypedDataComponent;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;
import java.util.Set;

@ZenRegister
@Document("vanilla/api/component/DataComponentMap")
@NativeTypeRegistration(value = DataComponentMap.class, zenCodeName = "crafttweaker.api.component.DataComponentMap")
public class ExpandDataComponentMap {
    
    
    @ZenCodeType.Getter("size")
    public static int size(DataComponentMap internal) {
        
        return internal.size();
    }
    
    @ZenCodeType.Method
    public static <T> T getOrDefault(DataComponentMap internal, Class<T> tClass, DataComponentType<T> type, T defaultValue) {
        
        return internal.getOrDefault(type, defaultValue);
    }
    
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    public static <T> TypedDataComponent<T> getTyped(DataComponentMap internal, Class<T> tClass, DataComponentType<T> type) {
        
        return internal.getTyped(type);
    }
    
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    public static <T> T getComponent(DataComponentMap internal, Class<T> tClass, DataComponentType<T> type) {
        
        return internal.get(type);
    }
    
    @ZenCodeType.Getter("list")
    public static List<TypedDataComponent<?>> list(DataComponentMap internal) {
        
        return internal.stream().toList();
    }
    
    // We can't handle not having any type params, but <?> isn't valid in ZC...
    //    @ZenCodeType.Method
    //    public static DataComponentMap filter(DataComponentMap internal, Predicate<DataComponentType> predicate) {
    //
    //        return internal.filter(GenericUtil.uncheck(predicate));
    //    }
    
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.CONTAINS)
    public static boolean has(DataComponentMap internal, DataComponentType<?> type) {
        
        return internal.has(type);
    }
    
    @ZenCodeType.Getter("keySet")
    public static Set<DataComponentType<?>> keySet(DataComponentMap internal) {
        
        return internal.keySet();
    }
    
    @ZenCodeType.Getter("isEmpty")
    public static boolean isEmpty(DataComponentMap internal) {
        
        return internal.isEmpty();
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static DataComponentMap composite(DataComponentMap a, DataComponentMap b) {
        
        return DataComponentMap.composite(a, b);
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static DataComponentMap.Builder builder() {
        
        return DataComponentMap.builder();
    }
    
    @ZenRegister
    @Document("vanilla/api/component/DataComponentMapBuilder")
    @NativeTypeRegistration(value = DataComponentMap.Builder.class, zenCodeName = "crafttweaker.api.component.DataComponentMapBuilder")
    public static class ExpandDataComponentMapBuilder {
        
        @ZenCodeType.Method
        public static <T> DataComponentMap.Builder setComponent(DataComponentMap.Builder internal, Class<T> tClass, DataComponentType<T> type, @ZenCodeType.Nullable T value) {
            
            return internal.set(type, value);
        }
        
        @ZenCodeType.Method
        public static DataComponentMap build(DataComponentMap.Builder internal) {
            
            return internal.build();
        }
        
        @ZenCodeType.Method
        public static DataComponentMap.Builder addAll(DataComponentMap.Builder internal, DataComponentMap other) {
            
            return internal.addAll(other);
        }
        
    }
    
}
