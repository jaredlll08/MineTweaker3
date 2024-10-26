package com.blamejared.crafttweaker.natives.item.armortrim;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import org.openzen.zencode.java.ZenCodeType;

import java.util.HashMap;
import java.util.Map;

@ZenRegister
@Document("vanilla/api/item/armortrim/TrimMaterial")
@NativeTypeRegistration(value = TrimMaterial.class, zenCodeName = "crafttweaker.api.item.armortrim.TrimMaterial")
public class ExpandTrimMaterial {
    
    @ZenCodeType.Getter("ingredient")
    public static Item ingredient(TrimMaterial internal) {
        
        return internal.ingredient().value();
    }
    
    @ZenCodeType.Getter("description")
    public static Component description(TrimMaterial internal) {
        
        return internal.description();
    }
    
    @ZenCodeType.Getter("overrideArmorMaterials")
    public static Map<ArmorMaterial, String> overrideArmorMaterials(TrimMaterial internal) {
        
        Map<ArmorMaterial, String> holderStringMap = new HashMap<>();
        internal.overrideArmorMaterials()
                .forEach((armorMaterialHolder, s) -> holderStringMap.put(armorMaterialHolder.value(), s));
        
        return holderStringMap;
    }
    
    @ZenCodeType.Getter("assetName")
    public static String assetName(TrimMaterial internal) {
        
        return internal.assetName();
    }
    
    @ZenCodeType.Getter("itemModelIndex")
    public static float itemModelIndex(TrimMaterial internal) {
        
        return internal.itemModelIndex();
    }
    
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(TrimMaterial internal) {
        
        return "<trimmaterial:" + Services.REGISTRY.keyOrThrow(Registries.TRIM_MATERIAL, internal) + ">";
    }
    
}
