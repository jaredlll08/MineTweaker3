package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.component.FireworkExplosion;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/item/component/FireworkExplosion")
@NativeTypeRegistration(value = FireworkExplosion.class, zenCodeName = "crafttweaker.api.item.component.FireworkExplosion")
public class ExpandFireworkExplosion {
    
    @ZenCodeType.StaticExpansionMethod
    public static FireworkExplosion of(FireworkExplosion.Shape shape, List<Integer> colors, List<Integer> fadeColors, boolean hasTrail, boolean hasTwinkle) {
        
        return new FireworkExplosion(shape, new IntArrayList(colors), new IntArrayList(fadeColors), hasTrail, hasTwinkle);
    }
    
    @ZenCodeType.Getter("fadeColors")
    public static List<Integer> fadeColors(FireworkExplosion internal) {
        
        return internal.fadeColors();
    }
    
    @ZenCodeType.Method
    public static FireworkExplosion withFadeColors(FireworkExplosion internal, List<Integer> colors) {
        
        return internal.withFadeColors(new IntArrayList(colors));
    }
    
    @ZenCodeType.Getter("hasTrail")
    public static boolean hasTrail(FireworkExplosion internal) {
        
        return internal.hasTrail();
    }
    
    @ZenCodeType.Getter("colors")
    public static List<Integer> colors(FireworkExplosion internal) {
        
        return internal.colors();
    }
    
    @ZenCodeType.Getter("hasTwinkle")
    public static boolean hasTwinkle(FireworkExplosion internal) {
        
        return internal.hasTwinkle();
    }
    
    @ZenCodeType.Getter("shape")
    public static FireworkExplosion.Shape shape(FireworkExplosion internal) {
        
        return internal.shape();
    }
    
    @ZenRegister
    @Document("vanilla/api/item/component/FireworkExplosionShape")
    @NativeTypeRegistration(value = FireworkExplosion.Shape.class, zenCodeName = "crafttweaker.api.item.component.FireworkExplosionShape")
    @BracketEnum("minecraft:firework_explosion/shape")
    public static class ExpandFireworkExplosionShape {
        
        @ZenCodeType.Getter("id")
        public static int getId(FireworkExplosion.Shape internal) {
            
            return internal.getId();
        }
        
        @ZenCodeType.Getter("name")
        public static MutableComponent getName(FireworkExplosion.Shape internal) {
            
            return internal.getName();
        }
        
    }
    
}
