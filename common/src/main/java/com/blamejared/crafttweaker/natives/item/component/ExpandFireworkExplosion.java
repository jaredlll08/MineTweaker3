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
    
    /**
     * Creates a new FireworkExplosion with the given shape, colors, fade colors, whether it has a trail, and whether it has twinkle.
     *
     * @param shape      The shape of the FireworkExplosion.
     * @param colors     The colors of the FireworkExplosion.
     * @param fadeColors The fade colors of the FireworkExplosion.
     * @param hasTrail   Whether the FireworkExplosion has a trail.
     * @param hasTwinkle Whether the FireworkExplosion has twinkle.
     *
     * @return The new FireworkExplosion.
     */
    @ZenCodeType.StaticExpansionMethod
    public static FireworkExplosion of(FireworkExplosion.Shape shape, List<Integer> colors, List<Integer> fadeColors, boolean hasTrail, boolean hasTwinkle) {
        
        return new FireworkExplosion(shape, new IntArrayList(colors), new IntArrayList(fadeColors), hasTrail, hasTwinkle);
    }
    
    /**
     * Gets the fade colors of the FireworkExplosion.
     *
     * @return The fade colors of the FireworkExplosion.
     */
    @ZenCodeType.Getter("fadeColors")
    public static List<Integer> fadeColors(FireworkExplosion internal) {
        
        return internal.fadeColors();
    }
    
    /**
     * Sets the fade colors of the FireworkExplosion.
     *
     * @param colors The fade colors of the FireworkExplosion.
     *
     * @return The FireworkExplosion with the new fade colors.
     */
    @ZenCodeType.Method
    public static FireworkExplosion withFadeColors(FireworkExplosion internal, List<Integer> colors) {
        
        return internal.withFadeColors(new IntArrayList(colors));
    }
    
    /**
     * Gets whether the FireworkExplosion has a trail.
     *
     * @return Whether the FireworkExplosion has a trail.
     */
    @ZenCodeType.Getter("hasTrail")
    public static boolean hasTrail(FireworkExplosion internal) {
        
        return internal.hasTrail();
    }
    
    /**
     * Gets the colors of the FireworkExplosion.
     *
     * @return The colors of the FireworkExplosion.
     */
    @ZenCodeType.Getter("colors")
    public static List<Integer> colors(FireworkExplosion internal) {
        
        return internal.colors();
    }
    
    /**
     * Gets whether the FireworkExplosion has twinkle.
     *
     * @return Whether the FireworkExplosion has twinkle.
     */
    @ZenCodeType.Getter("hasTwinkle")
    public static boolean hasTwinkle(FireworkExplosion internal) {
        
        return internal.hasTwinkle();
    }
    
    /**
     * Gets the shape of the FireworkExplosion.
     *
     * @return The shape of the FireworkExplosion.
     */
    @ZenCodeType.Getter("shape")
    public static FireworkExplosion.Shape shape(FireworkExplosion internal) {
        
        return internal.shape();
    }
    
    @ZenRegister
    @Document("vanilla/api/item/component/FireworkExplosionShape")
    @NativeTypeRegistration(value = FireworkExplosion.Shape.class, zenCodeName = "crafttweaker.api.item.component.FireworkExplosionShape")
    @BracketEnum("minecraft:firework_explosion/shape")
    public static class ExpandFireworkExplosionShape {
        
        /**
         * Gets the ID of the FireworkExplosionShape.
         *
         * @return The ID of the FireworkExplosionShape.
         */
        @ZenCodeType.Getter("id")
        public static int getId(FireworkExplosion.Shape internal) {
            
            return internal.getId();
        }
        
        /**
         * Gets the name of the FireworkExplosionShape.
         *
         * @return The name of the FireworkExplosionShape.
         */
        @ZenCodeType.Getter("name")
        public static MutableComponent getName(FireworkExplosion.Shape internal) {
            
            return internal.getName();
        }
        
    }
    
}
