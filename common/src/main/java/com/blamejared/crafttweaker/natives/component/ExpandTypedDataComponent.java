package com.blamejared.crafttweaker.natives.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.IData;
import com.blamejared.crafttweaker.api.data.op.IDataOps;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.mojang.serialization.DataResult;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

/**
 * Holds a {@link DataComponentType} and its value.
 */
@ZenRegister
@Document("vanilla/api/component/TypedDataComponent")
@NativeTypeRegistration(value = TypedDataComponent.class, zenCodeName = "crafttweaker.api.component.TypedDataComponent")
public class ExpandTypedDataComponent {
    
    /**
     * Creates a new {@link TypedDataComponent}.
     *
     * @param type  The {@link DataComponentType} of the component.
     * @param value The value of the component.
     *
     * @return A new {@link TypedDataComponent}.
     */
    @ZenCodeType.StaticExpansionMethod
    public static <T> TypedDataComponent<T> of(Class<T> tClass, DataComponentType<T> type, T value) {
        
        return new TypedDataComponent<>(type, value);
    }
    
    /**
     * Applies the {@link TypedDataComponent} to a {@link PatchedDataComponentMap}.
     *
     * @param patchedMap The {@link PatchedDataComponentMap} to apply the {@link TypedDataComponent} to.
     */
    @ZenCodeType.Method
    public static <T> void applyTo(TypedDataComponent<T> internal, Class<T> tClass, PatchedDataComponentMap patchedMap) {
        
        internal.applyTo(patchedMap);
    }
    
    /**
     * Gets the value of the {@link TypedDataComponent}.
     *
     * @return The value of the {@link TypedDataComponent}.
     */
    @ZenCodeType.Method
    public static <T> T value(TypedDataComponent<T> internal, Class<T> tClass) {
        
        return internal.value();
    }
    
    /**
     * Gets the {@link DataComponentType} of the {@link TypedDataComponent}.
     *
     * @return The {@link DataComponentType} of the {@link TypedDataComponent}.
     */
    @ZenCodeType.Method
    public static <T> DataComponentType<T> type(TypedDataComponent<T> internal, Class<T> tClass) {
        
        return internal.type();
    }
    
    /**
     * Gets the registry name of the stored {@link DataComponentType}.
     *
     * @return The registry name of the stored {@link DataComponentType}.
     */
    @ZenCodeType.Getter("registryName")
    public static ResourceLocation getRegistryName(TypedDataComponent internal) {
        
        return ExpandDataComponentType.getRegistryName(internal.type());
    }
    
    /**
     * Converts the {@link TypedDataComponent} to an {@link IData}.
     *
     * @return The {@link IData} representation of the {@link TypedDataComponent}.
     */
    @ZenCodeType.Method
    @ZenCodeType.Caster(implicit = true)
    public static IData asIData(TypedDataComponent internal) {
        
        DataResult<IData> dataResult = internal.encodeValue(IDataOps.INSTANCE);
        return dataResult.getOrThrow();
    }
    
}
