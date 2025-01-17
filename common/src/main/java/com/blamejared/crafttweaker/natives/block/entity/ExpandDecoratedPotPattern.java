package com.blamejared.crafttweaker.natives.block.entity;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.blamejared.crafttweaker_annotations.annotations.TaggableElement;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/block/entity/DecoratedPotPattern")
@NativeTypeRegistration(value = DecoratedPotPattern.class, zenCodeName = "crafttweaker.api.block.entity.DecoratedPotPattern")
@TaggableElement("minecraft:decorated_pot_pattern")
public class ExpandDecoratedPotPattern {
    
    /**
     * Gets the location of the asset used by the pot pattern.
     *
     * @return The pot pattern asset ID.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("assetId")
    public static ResourceLocation assetId(DecoratedPotPattern internal) {
        
        return internal.assetId();
    }
    
    /**
     * Gets the command string for the pot pattern.
     *
     * @return The command string for the pot pattern.
     */
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(DecoratedPotPattern internal) {
        
        return "<decoratedpotpattern:" + Services.REGISTRY.keyOrThrow(Registries.DECORATED_POT_PATTERN, internal) + ">";
    }
    
}