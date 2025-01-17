package com.blamejared.crafttweaker.natives.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.Optionull;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.TypedDataComponent;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Optional;
import java.util.Set;

/**
 * Stores information about changes that should be made to a {@link DataComponentMap}, keeping track of which {@link DataComponentType}s should be added or removed.
 */
@ZenRegister
@Document("vanilla/api/component/DataComponentPatch")
@NativeTypeRegistration(value = DataComponentPatch.class, zenCodeName = "crafttweaker.api.component.DataComponentPatch")
public class ExpandDataComponentPatch {
    
    /**
     * Creates a new {@link DataComponentPatch.Builder}.
     *
     * @return A new {@link DataComponentPatch.Builder}.
     */
    @ZenCodeType.StaticExpansionMethod
    public static DataComponentPatch.Builder builder() {
        
        return DataComponentPatch.builder();
    }
    
    /**
     * Removes a {@link DataComponentType} from the {@link DataComponentPatch}.
     *
     * @param component The {@link DataComponentType} to remove.
     *
     * @return The {@link DataComponentPatch} with the {@link DataComponentType} removed.
     */
    @ZenCodeType.Method
    public static <T> DataComponentPatch forget(DataComponentPatch internal, Class<T> tClass, DataComponentType<T> component) {
        
        return internal.forget(dataComponentType -> dataComponentType == component);
    }
    
    //    We can't deal with the optional<?>
    //    public static Set<Map.Entry<DataComponentType<?>, Optional<?>>> entrySet(DataComponentPatch internal) {
    //
    //        return internal.entrySet();
    //    }
    
    /**
     * Checks if the {@link DataComponentPatch} is empty.
     *
     * @return True if the {@link DataComponentPatch} is empty, false otherwise.
     */
    @ZenCodeType.Getter("isEmpty")
    public static boolean isEmpty(DataComponentPatch internal) {
        
        return internal.isEmpty();
    }
    
    /**
     * Gets the size of the {@link DataComponentPatch}.
     *
     * @return The size of the {@link DataComponentPatch}.
     */
    @ZenCodeType.Getter("size")
    public static int size(DataComponentPatch internal) {
        
        return internal.size();
    }
    
    /**
     * Gets the value for the given {@link DataComponentType}.
     *
     * @param type The {@link DataComponentType} to get the value for.
     *
     * @return The value for the given {@link DataComponentType}.
     */
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static <T> T getComponent(DataComponentPatch internal, DataComponentType<T> type) {
        
        Optional<? extends T> component = internal.get(type);
        // This isn't ideal, but we can't deal with a nullable optional
        return Optionull.map(component, t -> t.orElse(null));
    }
    
    /**
     * Splits the {@link DataComponentPatch} into a {@link DataComponentPatch.SplitResult} containing the added and removed {@link DataComponentMap}s.
     *
     * @return A {@link DataComponentPatch.SplitResult} containing the added and removed {@link DataComponentMap}s.
     */
    @ZenCodeType.Method
    public static DataComponentPatch.SplitResult split(DataComponentPatch internal) {
        
        return internal.split();
    }
    
    @ZenRegister
    @Document("vanilla/api/component/DataComponentPatchBuilder")
    @NativeTypeRegistration(value = DataComponentPatch.Builder.class, zenCodeName = "crafttweaker.api.component.DataComponentPatchBuilder")
    public static class ExpandDataComponentPatchBuilder {
        
        /**
         * Sets the value for the given {@link DataComponentType}.
         *
         * @param type  The {@link DataComponentType} to set the value for.
         * @param value The value to set for the given {@link DataComponentType}.
         *
         * @return The {@link DataComponentPatch.Builder} with the value set.
         */
        @ZenCodeType.Method
        public static <T> DataComponentPatch.Builder setComponent(DataComponentPatch.Builder internal, Class<T> tClass, DataComponentType<T> type, T value) {
            
            return internal.set(type, value);
        }
        
        /**
         * Removes the {@link DataComponentType} from the {@link DataComponentPatch.Builder}.
         *
         * @param type The {@link DataComponentType} to remove.
         *
         * @return The {@link DataComponentPatch.Builder} with the {@link DataComponentType} removed.
         */
        @ZenCodeType.Method
        public static <T> DataComponentPatch.Builder remove(DataComponentPatch.Builder internal, Class<T> tClass, DataComponentType<T> type) {
            
            return internal.remove(type);
        }
        
        /**
         * Sets the {@link TypedDataComponent} for the given {@link DataComponentType}.
         *
         * @param type The {@link DataComponentType} to set the {@link TypedDataComponent} for.
         *
         * @return The {@link DataComponentPatch.Builder} with the {@link TypedDataComponent} set.
         */
        @ZenCodeType.Method
        public static <T> DataComponentPatch.Builder setComponent(DataComponentPatch.Builder internal, Class<T> tClass, TypedDataComponent<T> type) {
            
            return internal.set(type);
        }
        
        /**
         * Builds the {@link DataComponentPatch}.
         *
         * @return The {@link DataComponentPatch}.
         */
        @ZenCodeType.Method
        public static DataComponentPatch build(DataComponentPatch.Builder internal) {
            
            return internal.build();
        }
        
    }
    
    @ZenRegister
    @Document("vanilla/api/component/DataComponentPatchSplitResult")
    @NativeTypeRegistration(value = DataComponentPatch.SplitResult.class, zenCodeName = "crafttweaker.api.component.DataComponentPatchSplitResult")
    public static class ExpandDataComponentPatchSplitResult {
        
        /**
         * Gets the added components.
         *
         * @return The added components.
         */
        @ZenCodeType.Getter("added")
        public static DataComponentMap added(DataComponentPatch.SplitResult internal) {
            
            return internal.added();
        }
        
        /**
         * Gets the removed components.
         *
         * @return The removed components.
         */
        @ZenCodeType.Getter("removed")
        public static Set<DataComponentType<?>> removed(DataComponentPatch.SplitResult internal) {
            
            return internal.removed();
        }
        
    }
    
}
