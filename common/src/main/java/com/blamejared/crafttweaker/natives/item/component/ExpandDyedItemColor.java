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
    
    @ZenCodeType.StaticExpansionMethod
    public static DyedItemColor of(int rgb, boolean showInTooltip) {
        
        return new DyedItemColor(rgb, showInTooltip);
    }
    
    @ZenCodeType.Getter("showInTooltip")
    public static boolean showInTooltip(DyedItemColor internal) {
        
        return internal.showInTooltip();
    }
    
    @ZenCodeType.Getter("rgb")
    public static int rgb(DyedItemColor internal) {
        
        return internal.rgb();
    }
    
    @ZenCodeType.Method
    public static DyedItemColor withTooltip(DyedItemColor internal, boolean tooltip) {
        
        return internal.withTooltip(tooltip);
    }
    
}
