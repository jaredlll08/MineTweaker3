package com.blamejared.crafttweaker.natives.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.neoforge.common.MutableDataComponentHolder;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

/**
 * An interface that stores DataComponents
 */
@ZenRegister
@Document("neoforge/api/component/MutableDataComponentHolder")
@NativeTypeRegistration(value = MutableDataComponentHolder.class, zenCodeName = "crafttweaker.neoforge.api.component.MutableDataComponentHolder")
public class ExpandMutableDataComponentHolder {
    
    
    @ZenCodeType.Method
    public static void applyComponents(MutableDataComponentHolder internal, DataComponentMap components) {
        
        internal.applyComponents(components);
    }
    
    @ZenCodeType.Method
    public static void applyComponents(MutableDataComponentHolder internal, DataComponentPatch patch) {
        
        internal.applyComponents(patch);
    }
    
    @ZenCodeType.Method
    public static void copyFrom(MutableDataComponentHolder internal, DataComponentHolder src, DataComponentType<?>... componentTypes) {
        
        internal.copyFrom(src, componentTypes);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static <T> T remove(MutableDataComponentHolder internal, DataComponentType<T> componentType) {
        
        return internal.remove(componentType);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static <T> T update(MutableDataComponentHolder internal, DataComponentType<T> componentType, T value, UnaryOperator<T> updater) {
        
        return internal.update(componentType, value, updater);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static <T, U> T update(MutableDataComponentHolder internal, DataComponentType<T> componentType, T value, U updateContext, BiFunction<T, U, T> updater) {
        
        return internal.update(componentType, value, updateContext, updater);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static <T> T setComponent(MutableDataComponentHolder internal, DataComponentType<T> componentType, @ZenCodeType.Nullable T value) {
        
        return internal.set(componentType, value);
    }
    
}
