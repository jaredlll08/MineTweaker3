package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.component.Unbreakable;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/component/Unbreakable")
@NativeTypeRegistration(value = Unbreakable.class, zenCodeName = "crafttweaker.api.item.component.Unbreakable")
public class ExpandUnbreakable {
    
    @ZenCodeType.StaticExpansionMethod
    public static Unbreakable of(boolean showInTooltip) {
        
        return new Unbreakable(showInTooltip);
    }
    
    @ZenCodeType.Method
    public static Unbreakable withTooltip(Unbreakable internal, boolean showInTooltip) {
        
        return internal.withTooltip(showInTooltip);
    }
    
    @ZenCodeType.Getter("showInTooltip")
    public static boolean showInTooltip(Unbreakable internal) {
        
        return internal.showInTooltip();
    }
    
}
