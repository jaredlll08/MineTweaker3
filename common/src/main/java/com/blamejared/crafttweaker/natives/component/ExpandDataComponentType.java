package com.blamejared.crafttweaker.natives.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/component/DataComponentType")
@NativeTypeRegistration(value = DataComponentType.class, zenCodeName = "crafttweaker.api.component.DataComponentType")
public class ExpandDataComponentType {
    
    @ZenCodeType.Getter("isTransient")
    public static boolean isTransient(DataComponentType internal) {
        
        return internal.isTransient();
    }
    
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(DataComponentType internal) {
        
        return "<componenttype:" + BuiltInRegistries.DATA_COMPONENT_TYPE.getKey(internal) + ">";
    }
    
}
