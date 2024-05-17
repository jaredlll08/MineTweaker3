package com.blamejared.crafttweaker.natives.tooltip;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.TooltipFlag;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/tooltip/TooltipFlag")
@NativeTypeRegistration(value = TooltipFlag.class, zenCodeName = "crafttweaker.api.tooltip.TooltipFlag")
public class ExpandTooltipFlag {
    
    @ZenCodeType.Getter("advanced")
    public static boolean isAdvanced(TooltipFlag internal) {
        
        return internal.isAdvanced();
    }
    
    @ZenCodeType.Getter("isCreative")
    public static boolean isCreative(TooltipFlag internal) {
        
        return internal.isCreative();
    }
    
}
