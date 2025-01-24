package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.DyeColor;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/component/DyeColor")
@BracketEnum("minecraft:dye_color")
@NativeTypeRegistration(value = DyeColor.class, zenCodeName = "crafttweaker.api.item.component.DyeColor")
public class ExpandDyeColor {
    
    /**
     * Gets the text color of the DyeColor.
     *
     * @return The text color of the DyeColor.
     */
    @ZenCodeType.Getter("textColor")
    public static int getTextColor(DyeColor internal) {
        
        return internal.getTextColor();
    }
    
    /**
     * Gets the firework color of the DyeColor.
     *
     * @return The firework color of the DyeColor.
     */
    @ZenCodeType.Getter("fireworkColor")
    public static int getFireworkColor(DyeColor internal) {
        
        return internal.getFireworkColor();
    }
    
}
