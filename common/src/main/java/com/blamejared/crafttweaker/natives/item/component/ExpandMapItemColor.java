package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.component.MapItemColor;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/component/MapItemColor")
@NativeTypeRegistration(value = MapItemColor.class, zenCodeName = "crafttweaker.api.item.component.MapItemColor")
public class ExpandMapItemColor {
    
    @ZenCodeType.StaticExpansionMethod
    public static MapItemColor of(int rgb) {
        
        return new MapItemColor(rgb);
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static MapItemColor defaultColor() {
        
        return MapItemColor.DEFAULT;
    }
    
    @ZenCodeType.Getter("rgb")
    public static int rgb(MapItemColor internal) {
        
        return internal.rgb();
    }
    
}
