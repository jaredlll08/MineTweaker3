package com.blamejared.crafttweaker.natives.block.entity;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/block/entity/BannerPatternLayers")
@NativeTypeRegistration(value = BannerPatternLayers.class, zenCodeName = "crafttweaker.api.block.entity.BannerPatternLayers")
public class ExpandBannerPatternLayers {
    
    @ZenCodeType.StaticExpansionMethod
    public static BannerPatternLayers of(List<BannerPatternLayers.Layer> layers) {
        
        return new BannerPatternLayers(layers);
    }
    
    @ZenCodeType.Method
    public static BannerPatternLayers removeLast(BannerPatternLayers internal) {
        
        return internal.removeLast();
    }
    
    @ZenCodeType.Getter("layers")
    public static List<BannerPatternLayers.Layer> layers(BannerPatternLayers internal) {
        
        return internal.layers();
    }
    
    @ZenRegister
    @Document("vanilla/api/block/entity/BannerPatternLayersBuilder")
    @NativeTypeRegistration(value = BannerPatternLayers.Builder.class, zenCodeName = "crafttweaker.api.block.entity.BannerPatternLayersBuilder")
    public static class ExpandBannerPatternLayersBuilder {
        
        @ZenCodeType.StaticExpansionMethod
        public static BannerPatternLayers.Builder of() {
            
            return new BannerPatternLayers.Builder();
        }
        
        @ZenCodeType.Method
        public static BannerPatternLayers.Builder add(BannerPatternLayers.Builder internal, BannerPattern pattern, DyeColor color) {
            
            return internal.add(Services.REGISTRY.holderOrThrow(Registries.BANNER_PATTERN, pattern),color);
        }
        
        @ZenCodeType.Method
        public static BannerPatternLayers.Builder addAll(BannerPatternLayers.Builder internal, BannerPatternLayers layers) {
            
            return internal.addAll(layers);
        }
        
        @ZenCodeType.Method
        public static BannerPatternLayers.Builder add(BannerPatternLayers.Builder internal, BannerPatternLayers.Layer layer) {
            
            return internal.add(layer);
        }
        
        @ZenCodeType.Method
        public static BannerPatternLayers build(BannerPatternLayers.Builder internal) {
            
            return internal.build();
        }
        
    }
    
    @ZenRegister
    @Document("vanilla/api/block/entity/BannerPatternLayersLayer")
    @NativeTypeRegistration(value = BannerPatternLayers.Layer.class, zenCodeName = "crafttweaker.api.block.entity.BannerPatternLayersLayer")
    public static class ExpandBannerPatternLayersLayer {
        
        @ZenCodeType.StaticExpansionMethod
        public static BannerPatternLayers.Layer of(BannerPattern pattern, DyeColor color) {
            
            return new BannerPatternLayers.Layer(Services.REGISTRY.holderOrThrow(Registries.BANNER_PATTERN, pattern), color);
        }
        
        @ZenCodeType.Getter("description")
        public static MutableComponent description(BannerPatternLayers.Layer internal) {
            
            return internal.description();
        }
        
        @ZenCodeType.Getter("pattern")
        public static BannerPattern pattern(BannerPatternLayers.Layer internal) {
            
            return internal.pattern().value();
        }
        
        @ZenCodeType.Getter("color")
        public static DyeColor color(BannerPatternLayers.Layer internal) {
            
            return internal.color();
        }
        
    }
    
}
