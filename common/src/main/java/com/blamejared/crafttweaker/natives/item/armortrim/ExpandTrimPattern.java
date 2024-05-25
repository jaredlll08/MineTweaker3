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
    
    @ZenCodeType.Method
    public static Component copyWithStyle(TrimPattern internal, TrimMaterial material) {
        
        return internal.copyWithStyle(Services.REGISTRY.holderOrThrow(Registries.TRIM_MATERIAL, material));
    }
    
    @ZenCodeType.Getter("description")
    public static Component description(TrimPattern internal) {
        
        return internal.description();
    }
    
    @ZenCodeType.Getter("templateItem")
    public static Item templateItem(TrimPattern internal) {
        
        return internal.templateItem().value();
    }
    
    @ZenCodeType.Getter("assetId")
    public static ResourceLocation assetId(TrimPattern internal) {
        
        return internal.assetId();
    }
    
    @ZenCodeType.Getter("decal")
    public static boolean decal(TrimPattern internal) {
        
        return internal.decal();
    }
    
    @ZenCodeType.Getter("registryName")
    public static ResourceLocation getRegistryName(TrimPattern internal) {
        
        return Services.REGISTRY.keyOrThrow(Registries.TRIM_PATTERN, internal);
    }
    
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(TrimPattern internal) {
        
        return "<trimpattern:" + Services.REGISTRY.keyOrThrow(Registries.TRIM_PATTERN, internal) + ">";
    }
    
}
