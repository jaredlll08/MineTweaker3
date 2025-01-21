package com.blamejared.crafttweaker.natives.item.armortrim;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimPattern;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/armortrim/ArmorTrim")
@NativeTypeRegistration(value = ArmorTrim.class, zenCodeName = "crafttweaker.api.item.armortrim.ArmorTrim")
public class ExpandArmorTrim {
    
    /**
     * Creates a new ArmorTrim.
     *
     * @param material      The material of the armor trim.
     * @param pattern       The pattern of the armor trim.
     * @param showInTooltip Whether the armor trim should show in the tooltip.
     *
     * @return The new ArmorTrim.
     */
    @ZenCodeType.StaticExpansionMethod
    public static ArmorTrim of(TrimMaterial material, TrimPattern pattern, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return new ArmorTrim(Services.REGISTRY.holderOrThrow(Registries.TRIM_MATERIAL, material), Services.REGISTRY.holderOrThrow(Registries.TRIM_PATTERN, pattern), showInTooltip);
    }
    
    /**
     * Checks if the armor trim has a pattern and material.
     *
     * @param pattern  The pattern of the armor trim.
     * @param material The material of the armor trim.
     *
     * @return Whether the armor trim has a pattern and material.
     */
    @ZenCodeType.Method
    public static boolean hasPatternAndMaterial(ArmorTrim internal, TrimPattern pattern, TrimMaterial material) {
        
        return internal.hasPatternAndMaterial(Services.REGISTRY.holderOrThrow(Registries.TRIM_PATTERN, pattern), Services.REGISTRY.holderOrThrow(Registries.TRIM_MATERIAL, material));
    }
    
    /**
     * Sets whether the armor trim should show in the tooltip.
     *
     * @param withTooltip Whether the armor trim should show in the tooltip.
     *
     * @return The new ArmorTrim.
     */
    @ZenCodeType.Method
    public static ArmorTrim withTooltip(ArmorTrim internal, boolean withTooltip) {
        
        return internal.withTooltip(withTooltip);
    }
    
    /**
     * Gets the inner texture of the armor trim.
     *
     * @param material The material of the armor trim.
     *
     * @return The inner texture of the armor trim.
     */
    @ZenCodeType.Method
    public static ResourceLocation innerTexture(ArmorTrim internal, ArmorMaterial material) {
        
        return internal.innerTexture(Services.REGISTRY.holderOrThrow(Registries.ARMOR_MATERIAL, material));
    }
    
    /**
     * Gets the material of the armor trim.
     *
     * @return The material of the armor trim.
     */
    @ZenCodeType.Getter("material")
    public static TrimMaterial material(ArmorTrim internal) {
        
        return internal.material().value();
    }
    
    /**
     * Gets the outer texture of the armor trim.
     *
     * @param material The material of the armor trim.
     *
     * @return The outer texture of the armor trim.
     */
    @ZenCodeType.Method
    public static ResourceLocation outerTexture(ArmorTrim internal, ArmorMaterial material) {
        
        return internal.outerTexture(Services.REGISTRY.holderOrThrow(Registries.ARMOR_MATERIAL, material));
    }
    
    /**
     * Gets the pattern of the armor trim.
     *
     * @return The pattern of the armor trim.
     */
    @ZenCodeType.Getter("pattern")
    public static TrimPattern pattern(ArmorTrim internal) {
        
        return internal.pattern().value();
    }
    
}
