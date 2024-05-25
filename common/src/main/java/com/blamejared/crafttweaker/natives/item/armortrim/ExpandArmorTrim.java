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
    
    @ZenCodeType.StaticExpansionMethod
    public static ArmorTrim of(TrimMaterial material, TrimPattern pattern, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return new ArmorTrim(Services.REGISTRY.holderOrThrow(Registries.TRIM_MATERIAL, material), Services.REGISTRY.holderOrThrow(Registries.TRIM_PATTERN, pattern), showInTooltip);
    }
    
    @ZenCodeType.Method
    public static boolean hasPatternAndMaterial(ArmorTrim internal, TrimPattern pattern, TrimMaterial material) {
        
        return internal.hasPatternAndMaterial(Services.REGISTRY.holderOrThrow(Registries.TRIM_PATTERN, pattern), Services.REGISTRY.holderOrThrow(Registries.TRIM_MATERIAL, material));
    }
    
    @ZenCodeType.Method
    public static ArmorTrim withTooltip(ArmorTrim internal, boolean withTooltip) {
        
        return internal.withTooltip(withTooltip);
    }
    
    @ZenCodeType.Method
    public static ResourceLocation innerTexture(ArmorTrim internal, ArmorMaterial material) {
        
        return internal.innerTexture(Services.REGISTRY.holderOrThrow(Registries.ARMOR_MATERIAL, material));
    }
    
    @ZenCodeType.Getter("material")
    public static TrimMaterial material(ArmorTrim internal) {
        
        return internal.material().value();
    }
    
    @ZenCodeType.Method
    public static ResourceLocation outerTexture(ArmorTrim internal, ArmorMaterial material) {
        
        return internal.outerTexture(Services.REGISTRY.holderOrThrow(Registries.ARMOR_MATERIAL, material));
    }
    
    @ZenCodeType.Getter("pattern")
    public static TrimPattern pattern(ArmorTrim internal) {
        
        return internal.pattern().value();
    }
    
}
