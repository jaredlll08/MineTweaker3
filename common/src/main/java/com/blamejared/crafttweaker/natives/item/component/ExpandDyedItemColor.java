package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.component.DyedItemColor;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/component/DyedItemColor")
@NativeTypeRegistration(value = DyedItemColor.class, zenCodeName = "crafttweaker.api.item.component.DyedItemColor")
public class ExpandDyedItemColor {
    
    /**
     * Creates a new DyedItemColor with the given RGB color and whether it should show in the tooltip.
     *
     * @param rgb           The RGB color of the DyedItemColor.
     * @param showInTooltip Whether the DyedItemColor should show in the tooltip.
     *
     * @return The new DyedItemColor.
     */
    @ZenCodeType.StaticExpansionMethod
    public static DyedItemColor of(int rgb, boolean showInTooltip) {
        
        return new DyedItemColor(rgb, showInTooltip);
    }
    
    /**
     * Gets whether the DyedItemColor should show in the tooltip.
     *
     * @return Whether the DyedItemColor should show in the tooltip.
     */
    @ZenCodeType.Getter("showInTooltip")
    public static boolean showInTooltip(DyedItemColor internal) {
        
        return internal.showInTooltip();
    }
    
    /**
     * Gets the RGB color of the DyedItemColor.
     *
     * @return The RGB color of the DyedItemColor.
     */
    @ZenCodeType.Getter("rgb")
    public static int rgb(DyedItemColor internal) {
        
        return internal.rgb();
    }
    
    /**
     * Sets whether the DyedItemColor should show in the tooltip.
     *
     * @param tooltip Whether the DyedItemColor should show in the tooltip.
     *
     * @return The DyedItemColor with the new tooltip setting.
     */
    @ZenCodeType.Method
    public static DyedItemColor withTooltip(DyedItemColor internal, boolean tooltip) {
        
        return internal.withTooltip(tooltip);
    }
    
}
