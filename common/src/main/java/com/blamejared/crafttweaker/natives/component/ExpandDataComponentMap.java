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

/**
 * A map of {@link DataComponentType}s to their values.
 */
@ZenRegister
@Document("vanilla/api/component/DataComponentMap")
@NativeTypeRegistration(value = DataComponentMap.class, zenCodeName = "crafttweaker.api.component.DataComponentMap")
public class ExpandDataComponentMap {
    
    /**
     * Gets the size of the data component map.
     *
     * @return The size of the data component map.
     */
    @ZenCodeType.Getter("size")
    public static int size(DataComponentMap internal) {
        
        return internal.size();
    }
    
    /**
     * Gets the value of the data component for the given {@link DataComponentType}. If the data component is not present, the default value is returned.
     *
     * @param type         The {@link DataComponentType} to get the value of.
     * @param defaultValue The default value to return if the data component is not present.
     *
     * @return The value of the data component for the given type.
     */
    @ZenCodeType.Method
    public static <T> T getOrDefault(DataComponentMap internal, Class<T> tClass, DataComponentType<T> type, T defaultValue) {
        
        return internal.getOrDefault(type, defaultValue);
    }
    
    /**
     * Gets the {@link TypedDataComponent} for the given {@link DataComponentType}.
     *
     * @param type The {@link DataComponentType} to get the {@link TypedDataComponent} for.
     *
     * @return The {@link TypedDataComponent} for the given type.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    public static <T> TypedDataComponent<T> getTyped(DataComponentMap internal, Class<T> tClass, DataComponentType<T> type) {
        
        return internal.getTyped(type);
    }
    
    /**
     * Gets the value of the data component for the given {@link DataComponentType}.
     *
     * @param type The {@link DataComponentType} to get the value of.
     *
     * @return The value of the data component for the given type.
     */
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
    
    /**
     * Checks if the data component map has a {@link DataComponentType}.
     *
     * @param type The {@link DataComponentType} to check for.
     *
     * @return True if the data component map has the {@link DataComponentType}, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.CONTAINS)
    public static boolean has(DataComponentMap internal, DataComponentType<?> type) {
        
        return internal.has(type);
    }
    
    /**
     * Gets the set of {@link DataComponentType}s in the data component map.
     *
     * @return The set of {@link DataComponentType}s in the data component map.
     */
    @ZenCodeType.Getter("keySet")
    public static Set<DataComponentType<?>> keySet(DataComponentMap internal) {
        
        return internal.keySet();
    }
    
    /**
     * Checks if the data component map is empty.
     *
     * @return True if the data component map is empty, false otherwise.
     */
    @ZenCodeType.Getter("isEmpty")
    public static boolean isEmpty(DataComponentMap internal) {
        
        return internal.isEmpty();
    }
    
    /**
     * Combines two {@link DataComponentMap}s.
     *
     * @param a The first {@link DataComponentMap} to combine.
     * @param b The second {@link DataComponentMap} to combine.
     *
     * @return A new {@link DataComponentMap} that is the result of combining the two {@link DataComponentMap}s.
     */
    @ZenCodeType.StaticExpansionMethod
    public static DataComponentMap composite(DataComponentMap a, DataComponentMap b) {
        
        return DataComponentMap.composite(a, b);
    }
    
    /**
     * Creates a new {@link DataComponentMap.Builder}.
     *
     * @return A new {@link DataComponentMap.Builder}.
     */
    @ZenCodeType.StaticExpansionMethod
    public static DataComponentMap.Builder builder() {
        
        return DataComponentMap.builder();
    }
    
    @ZenRegister
    @Document("vanilla/api/component/DataComponentMapBuilder")
    @NativeTypeRegistration(value = DataComponentMap.Builder.class, zenCodeName = "crafttweaker.api.component.DataComponentMapBuilder")
    public static class ExpandDataComponentMapBuilder {
        
        /**
         * Sets the value of the data component for the given {@link DataComponentType}.
         *
         * @param type  The {@link DataComponentType} to set the value of.
         * @param value The value to set the data component to.
         *
         * @return The {@link DataComponentMap.Builder} with the updated value.
         */
        @ZenCodeType.Method
        public static <T> DataComponentMap.Builder setComponent(DataComponentMap.Builder internal, Class<T> tClass, DataComponentType<T> type, @ZenCodeType.Nullable T value) {
            
            return internal.set(type, value);
        }
        
        /**
         * Builds the {@link DataComponentMap}.
         *
         * @return The built {@link DataComponentMap}.
         */
        @ZenCodeType.Method
        public static DataComponentMap build(DataComponentMap.Builder internal) {
            
            return internal.build();
        }
        
        /**
         * Adds all the {@link DataComponentType}s and values from the other {@link DataComponentMap} to the builder.
         *
         * @param other The {@link DataComponentMap} to add the {@link DataComponentType}s and values from.
         *
         * @return The {@link DataComponentMap.Builder} with the added values.
         */
        @ZenCodeType.Method
        public static DataComponentMap.Builder addAll(DataComponentMap.Builder internal, DataComponentMap other) {
            
            return internal.addAll(other);
        }
        
    }
    
}
