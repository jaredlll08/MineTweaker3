package com.blamejared.crafttweaker.natives.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/component/DataComponents")
@NativeTypeRegistration(value = DataComponents.class, zenCodeName = "crafttweaker.api.component.DataComponents")
public class ExpandDataComponents {
    
    /**
     * Gets the common item components that all items intrinsically have.
     *
     * @return The common item components.
     */
    @ZenCodeType.StaticExpansionMethod
    public static DataComponentMap commonItemComponents() {
        
        return DataComponents.COMMON_ITEM_COMPONENTS;
    }
    
}
