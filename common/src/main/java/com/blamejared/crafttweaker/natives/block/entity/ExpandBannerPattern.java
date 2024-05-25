package com.blamejared.crafttweaker.natives.block.entity;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BannerPattern;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/block/entity/BannerPattern")
@NativeTypeRegistration(value = BannerPattern.class, zenCodeName = "crafttweaker.api.block.entity.BannerPattern")
public class ExpandBannerPattern {
    
    @ZenCodeType.Getter("assetId")
    public static ResourceLocation assetId(BannerPattern internal) {
        
        return internal.assetId();
    }
    
    @ZenCodeType.Getter("translationKey")
    public static String translationKey(BannerPattern internal) {
        
        return internal.translationKey();
    }
    
    @ZenCodeType.Getter("registryName")
    public static ResourceLocation getRegistryName(BannerPattern internal) {
        
        return CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess(registryAccess -> registryAccess.registryOrThrow(Registries.BANNER_PATTERN)
                        .getKey(internal));
    }
    
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(BannerPattern internal) {
        
        return "<bannerpattern:" + Services.REGISTRY.keyOrThrow(Registries.BANNER_PATTERN, internal) + ">";
    }
    
}
