package com.blamejared.crafttweaker.natives.item.armortrim;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import org.openzen.zencode.java.ZenCodeType;

import java.util.HashMap;
import java.util.Map;

@ZenRegister
@Document("vanilla/api/item/armortrim/TrimMaterial")
@NativeTypeRegistration(value = TrimMaterial.class, zenCodeName = "crafttweaker.api.item.armortrim.TrimMaterial")
public class ExpandTrimMaterial {
    
    /**
     * Gets the ingredient of the trim material.
     *
     * @return The ingredient of the trim material.
     */
    @ZenCodeType.Getter("ingredient")
    public static Item ingredient(TrimMaterial internal) {
        
        return internal.ingredient().value();
    }
    
    /**
     * Gets the description of the trim material.
     *
     * @return The description of the trim material.
     */
    @ZenCodeType.Getter("description")
    public static Component description(TrimMaterial internal) {
        
        return internal.description();
    }
    
    /**
     * Gets the override armor materials of the trim material.
     *
     * @return The override armor materials of the trim material.
     */
    @ZenCodeType.Getter("overrideArmorMaterials")
    public static Map<ArmorMaterial, String> overrideArmorMaterials(TrimMaterial internal) {
        
        Map<ArmorMaterial, String> holderStringMap = new HashMap<>();
        internal.overrideArmorMaterials()
                .forEach((armorMaterialHolder, s) -> holderStringMap.put(armorMaterialHolder.value(), s));
        
        return holderStringMap;
    }
    
    /**
     * Gets the asset name of the trim material.
     *
     * @return The asset name of the trim material.
     */
    @ZenCodeType.Getter("assetName")
    public static String assetName(TrimMaterial internal) {
        
        return internal.assetName();
    }
    
    /**
     * Gets the item model index of the trim material.
     *
     * @return The item model index of the trim material.
     */
    @ZenCodeType.Getter("itemModelIndex")
    public static float itemModelIndex(TrimMaterial internal) {
        
        return internal.itemModelIndex();
    }
    
    /**
     * Gets the command string of the trim material.
     *
     * @param internal The trim material.
     *
     * @return The command string of the trim material.
     */
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(TrimMaterial internal) {
        
        return "<trimmaterial:" + Services.REGISTRY.keyOrThrow(Registries.TRIM_MATERIAL, internal) + ">";
    }
    
}
