package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.component.MapDecorations;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Map;

@ZenRegister
@Document("vanilla/api/item/component/MapDecorations")
@NativeTypeRegistration(value = MapDecorations.class, zenCodeName = "crafttweaker.api.item.component.MapDecorations")
public class ExpandMapDecorations {
    
    @ZenCodeType.StaticExpansionMethod
    public static MapDecorations of(Map<String, MapDecorations.Entry> decorations) {
        
        return new MapDecorations(decorations);
    }
    
    @ZenCodeType.Method
    public static MapDecorations withDecoration(MapDecorations internal, String name, MapDecorations.Entry entry) {
        
        return internal.withDecoration(name, entry);
    }
    
    @ZenCodeType.Getter("decorations")
    public static Map<String, MapDecorations.Entry> decorations(MapDecorations internal) {
        
        return internal.decorations();
    }
    
    @ZenRegister
    @Document("vanilla/api/item/component/MapDecorationsEntry")
    @NativeTypeRegistration(value = MapDecorations.Entry.class, zenCodeName = "crafttweaker.api.item.component.MapDecorationsEntry")
    public static class ExpandMapDecorationsEntry {
        
        @ZenCodeType.Getter("type")
        public static MapDecorationType type(MapDecorations.Entry internal) {
            
            return internal.type().value();
        }
        
        @ZenCodeType.Getter("z")
        public static double z(MapDecorations.Entry internal) {
            
            return internal.z();
        }
        
        @ZenCodeType.Getter("x")
        public static double x(MapDecorations.Entry internal) {
            
            return internal.x();
        }
        
        @ZenCodeType.Getter("rotation")
        public static float rotation(MapDecorations.Entry internal) {
            
            return internal.rotation();
        }
        
    }
    
}
