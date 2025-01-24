package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.component.CustomModelData;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/component/CustomModelData")
@NativeTypeRegistration(value = CustomModelData.class, zenCodeName = "crafttweaker.api.item.component.CustomModelData")
public class ExpandCustomModelData {
    
    /**
     * Creates a new CustomModelData with the given value.
     *
     * @param value The value to create the CustomModelData with.
     *
     * @return The new CustomModelData.
     */
    @ZenCodeType.StaticExpansionMethod
    public static CustomModelData of(int value) {
        
        return new CustomModelData(value);
    }
    
    /**
     * Gets the value of the CustomModelData.
     *
     * @return The value of the CustomModelData.
     */
    @ZenCodeType.Getter("value")
    public static int value(CustomModelData internal) {
        
        return internal.value();
    }
    
}
