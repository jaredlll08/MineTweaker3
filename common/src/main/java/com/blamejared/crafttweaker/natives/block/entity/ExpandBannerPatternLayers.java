package com.blamejared.crafttweaker.natives.block.entity;

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
    
    /**
     * Creates a new {@link BannerPatternLayers} with the provided layers.
     *
     * @param layers The layers to create the {@link BannerPatternLayers} with.
     *
     * @return The new {@link BannerPatternLayers}.
     */
    @ZenCodeType.StaticExpansionMethod
    public static BannerPatternLayers of(List<BannerPatternLayers.Layer> layers) {
        
        return new BannerPatternLayers(layers);
    }
    
    /**
     * Removes the last layer from the {@link BannerPatternLayers}.
     *
     * @return The {@link BannerPatternLayers} with the last layer removed.
     */
    @ZenCodeType.Method
    public static BannerPatternLayers removeLast(BannerPatternLayers internal) {
        
        return internal.removeLast();
    }
    
    /**
     * Gets the layers of the {@link BannerPatternLayers}.
     *
     * @return The layers of the {@link BannerPatternLayers}.
     */
    @ZenCodeType.Getter("layers")
    public static List<BannerPatternLayers.Layer> layers(BannerPatternLayers internal) {
        
        return internal.layers();
    }
    
    @ZenRegister
    @Document("vanilla/api/block/entity/BannerPatternLayersBuilder")
    @NativeTypeRegistration(value = BannerPatternLayers.Builder.class, zenCodeName = "crafttweaker.api.block.entity.BannerPatternLayersBuilder")
    public static class ExpandBannerPatternLayersBuilder {
        
        /**
         * Creates a new {@link BannerPatternLayers.Builder}.
         *
         * @return The new {@link BannerPatternLayers.Builder}.
         */
        @ZenCodeType.StaticExpansionMethod
        public static BannerPatternLayers.Builder of() {
            
            return new BannerPatternLayers.Builder();
        }
        
        /**
         * Adds a layer to the {@link BannerPatternLayers.Builder}.
         *
         * @param pattern The {@link BannerPattern} to add.
         * @param color   The {@link DyeColor} to add.
         *
         * @return The {@link BannerPatternLayers.Builder} with the layer added.
         */
        @ZenCodeType.Method
        public static BannerPatternLayers.Builder add(BannerPatternLayers.Builder internal, BannerPattern pattern, DyeColor color) {
            
            return internal.add(Services.REGISTRY.holderOrThrow(Registries.BANNER_PATTERN, pattern), color);
        }
        
        /**
         * Adds all layers from the provided {@link BannerPatternLayers} to the {@link BannerPatternLayers.Builder}.
         *
         * @param layers The {@link BannerPatternLayers} to add.
         *
         * @return The {@link BannerPatternLayers.Builder} with the layers added.
         */
        @ZenCodeType.Method
        public static BannerPatternLayers.Builder addAll(BannerPatternLayers.Builder internal, BannerPatternLayers layers) {
            
            return internal.addAll(layers);
        }
        
        /**
         * Adds a layer to the {@link BannerPatternLayers.Builder}.
         *
         * @param layer The {@link BannerPatternLayers.Layer} to add.
         *
         * @return The {@link BannerPatternLayers.Builder} with the layer added.
         */
        @ZenCodeType.Method
        public static BannerPatternLayers.Builder add(BannerPatternLayers.Builder internal, BannerPatternLayers.Layer layer) {
            
            return internal.add(layer);
        }
        
        /**
         * Builds the {@link BannerPatternLayers} from the {@link BannerPatternLayers.Builder}.
         *
         * @return The {@link BannerPatternLayers} built from the {@link BannerPatternLayers.Builder}.
         */
        @ZenCodeType.Method
        public static BannerPatternLayers build(BannerPatternLayers.Builder internal) {
            
            return internal.build();
        }
        
    }
    
    @ZenRegister
    @Document("vanilla/api/block/entity/BannerPatternLayersLayer")
    @NativeTypeRegistration(value = BannerPatternLayers.Layer.class, zenCodeName = "crafttweaker.api.block.entity.BannerPatternLayersLayer")
    public static class ExpandBannerPatternLayersLayer {
        
        /**
         * Creates a new {@link BannerPatternLayers.Layer}.
         *
         * @param pattern The {@link BannerPattern} to create the {@link BannerPatternLayers.Layer} with.
         * @param color   The {@link DyeColor} to create the {@link BannerPatternLayers.Layer} with.
         *
         * @return The new {@link BannerPatternLayers.Layer}.
         */
        @ZenCodeType.StaticExpansionMethod
        public static BannerPatternLayers.Layer of(BannerPattern pattern, DyeColor color) {
            
            return new BannerPatternLayers.Layer(Services.REGISTRY.holderOrThrow(Registries.BANNER_PATTERN, pattern), color);
        }
        
        /**
         * Gets the description of the {@link BannerPatternLayers.Layer}.
         *
         * @return The description of the {@link BannerPatternLayers.Layer}.
         */
        @ZenCodeType.Getter("description")
        public static MutableComponent description(BannerPatternLayers.Layer internal) {
            
            return internal.description();
        }
        
        /**
         * Gets the {@link BannerPattern} of the {@link BannerPatternLayers.Layer}.
         *
         * @return The {@link BannerPattern} of the {@link BannerPatternLayers.Layer}.
         */
        @ZenCodeType.Getter("pattern")
        public static BannerPattern pattern(BannerPatternLayers.Layer internal) {
            
            return internal.pattern().value();
        }
        
        /**
         * Gets the {@link DyeColor} of the {@link BannerPatternLayers.Layer}.
         *
         * @return The {@link DyeColor} of the {@link BannerPatternLayers.Layer}.
         */
        @ZenCodeType.Getter("color")
        public static DyeColor color(BannerPatternLayers.Layer internal) {
            
            return internal.color();
        }
        
    }
    
}
