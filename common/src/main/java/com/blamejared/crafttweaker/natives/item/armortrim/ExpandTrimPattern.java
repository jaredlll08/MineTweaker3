package com.blamejared.crafttweaker.natives.item.armortrim;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimPattern;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/armortrim/TrimPattern")
@NativeTypeRegistration(value = TrimPattern.class, zenCodeName = "crafttweaker.api.item.armortrim.TrimPattern")
public class ExpandTrimPattern {
    
    /**
     * Copies the trim pattern's description with a given material's style.
     *  
     * @param material The material.
     *
     * @return The new trim pattern.
     */
    @ZenCodeType.Method
    public static Component copyWithStyle(TrimPattern internal, TrimMaterial material) {
        
        return internal.copyWithStyle(Services.REGISTRY.holderOrThrow(Registries.TRIM_MATERIAL, material));
    }
    
    /**
     * Gets the description of the trim pattern.
     *
     *
     * @return The description of the trim pattern.
     */
    @ZenCodeType.Getter("description")
    public static Component description(TrimPattern internal) {
        
        return internal.description();
    }
    
    /**
     * Gets the template item of the trim pattern.
     *
     * @return The template item of the trim pattern.
     */
    @ZenCodeType.Getter("templateItem")
    public static Item templateItem(TrimPattern internal) {
        
        return internal.templateItem().value();
    }
    
    /**
     * Gets the asset ID of the trim pattern.
     *
     * @return The asset ID of the trim pattern.
     */
    @ZenCodeType.Getter("assetId")
    public static ResourceLocation assetId(TrimPattern internal) {
        
        return internal.assetId();
    }
    
    /**
     * Gets whether the trim pattern is a decal.
     *
     * @return Whether the trim pattern is a decal.
     */
    @ZenCodeType.Getter("decal")
    public static boolean decal(TrimPattern internal) {
        
        return internal.decal();
    }
    
    /**
     * Gets the registry name of the trim pattern.
     *
     * @return The registry name of the trim pattern.
     */
    @ZenCodeType.Getter("registryName")
    public static ResourceLocation getRegistryName(TrimPattern internal) {
        
        return Services.REGISTRY.keyOrThrow(Registries.TRIM_PATTERN, internal);
    }
    
    /**
     * Gets the command string of the trim pattern.
     *
     * @param internal The trim pattern.
     *
     * @return The command string of the trim pattern.
     */
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(TrimPattern internal) {
        
        return "<trimpattern:" + Services.REGISTRY.keyOrThrow(Registries.TRIM_PATTERN, internal) + ">";
    }
    
}
