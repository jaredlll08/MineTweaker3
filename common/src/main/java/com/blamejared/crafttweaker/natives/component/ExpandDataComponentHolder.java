package com.blamejared.crafttweaker.natives.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.IData;
import com.blamejared.crafttweaker.api.data.op.IDataOps;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.mojang.serialization.DataResult;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import org.openzen.zencode.java.ZenCodeType;

/**
 * An interface that stores DataComponents
 */
@ZenRegister
@Document("vanilla/api/component/DataComponentHolder")
@NativeTypeRegistration(value = DataComponentHolder.class, zenCodeName = "crafttweaker.api.component.DataComponentHolder")
public class ExpandDataComponentHolder {
    
    /**
     * Gets the internal map of ComponentType -> Data
     * @return The internal map.
     */
    @ZenCodeType.Getter("components")
    public static DataComponentMap getComponents(DataComponentHolder internal) {
        
        return internal.getComponents();
    }
    
    /**
     * Checks whether the holder contains the given DataComponent, which is indentified by the type.
     * @param type The componenttype to check for.
     * @return Whether the holder contains the DataComponent.
     *
     * @docParam type <componenttype:minecraft:stack_size>
     */
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.CONTAINS)
    public static boolean has(DataComponentHolder internal, DataComponentType<?> type) {
        
        return internal.has(type);
    }
    
    /**
     * Gets the data identified by the given DataComponentType. If the ComponentHolder does not have the Component, then the default value is returned.
     *
     * @param tClass The type of the class stored within the component.
     * @param type The componenttype to get
     * @param defaultValue The default value to return in the event that the holder does not have the component.
     * @param <T> The type of the ComponentType
     *
     * @return The value if it exists or the default value.
     *
     * @docParam <T> int?
     * @docParam type <componenttype:minecraft:stack_size>
     * @docParam defaultValue 64
     *
     */
    @ZenCodeType.Method
    public static <T> T getOrDefault(DataComponentHolder internal, Class<T> tClass, DataComponentType<T> type, T defaultValue) {
        
        return internal.getOrDefault(type, defaultValue);
    }
    
    /**
     * Gets the data identified by the given DataComponentType. If the ComponentHolder does not have the Component, then null is returned.
     *
     * @param <T> The type of the class stored within the component.
     * @param type The componenttype to get
     * @return The value if it exists or null.
     *
     * @docParam <T> int?
     * @docParam type <componenttype:minecraft:stack_size>
     * @docParam defaultValue 64
     *
     */
    @ZenCodeType.Method
    public static <T> T getComponent(DataComponentHolder internal, Class<T> tClass, DataComponentType<T> type) {
        
        return internal.get(type);
    }
    
    /**
     * Get an arbitrary component by type.
     * This is useful to interact with components added by mods that do not have a CraftTweaker method.
     *
     * If the ComponentAccess does not have the type, an exception is thrown.
     * If the ComponentAccess is not serializable, an exception is thrown.
     *
     * @param type The componenttype to target.
     * @return A {@link IData} representation of the Serialized DataComponent
     *
     * @docParam type <componenttype:minecraft:stack_size>
     */
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static IData getJsonComponent(DataComponentHolder internal, DataComponentType type) {
        
        DataResult<IData> result = type.codecOrThrow().encodeStart(IDataOps.INSTANCE, type);
        return result.getOrThrow();
    }
    
}
