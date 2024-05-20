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

@ZenRegister
@Document("vanilla/api/component/DataComponentHolder")
@NativeTypeRegistration(value = DataComponentHolder.class, zenCodeName = "crafttweaker.api.component.DataComponentHolder")
public class ExpandDataComponentHolder {
    
    @ZenCodeType.Getter("components")
    public static DataComponentMap getComponents(DataComponentHolder internal) {
        
        return internal.getComponents();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.CONTAINS)
    public static boolean has(DataComponentHolder internal, DataComponentType<?> type) {
        
        return internal.has(type);
    }
    
    @ZenCodeType.Method
    public static <T> T getOrDefault(DataComponentHolder internal, Class<T> tClass, DataComponentType<T> type, T defaultValue) {
        
        return internal.getOrDefault(type, defaultValue);
    }
    
    @ZenCodeType.Method
    public static <T> T getComponent(DataComponentHolder internal, Class<T> tClass, DataComponentType<T> type) {
        
        return internal.get(type);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static IData getJsonComponent(DataComponentHolder internal, DataComponentType type) {
        
        DataResult<IData> result = type.codecOrThrow().encodeStart(IDataOps.INSTANCE, type);
        return result.getOrThrow();
    }
    
}
