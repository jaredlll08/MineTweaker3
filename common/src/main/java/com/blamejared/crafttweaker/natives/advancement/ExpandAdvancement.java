package com.blamejared.crafttweaker.natives.advancement;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Map;

@ZenRegister
@Document("vanilla/api/advancement/Advancement")
@NativeTypeRegistration(value = Advancement.class, zenCodeName = "crafttweaker.api.advancement.Advancement")
public class ExpandAdvancement {
    
    /**
     * Checks if this is a root advancement
     *
     * @return true if this advancement does not have a parent, false otherwise
     */
    @ZenCodeType.Getter("isRoot")
    public static boolean isRoot(Advancement internal) {
        
        return internal.isRoot();
    }
    
    /**
     * Gets the parent of this advancement.
     *
     * @return the parent of this advancement, or null if it doesn't have one.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("parent")
    public static ResourceLocation parent(Advancement internal) {
        
        return internal.parent().orElse(null);
    }
    
    /**
     * Gets the {@link DisplayInfo} for this advancement
     *
     * @return The {@link DisplayInfo} for this advancement.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("display")
    public static DisplayInfo display(Advancement internal) {
        
        return internal.display().orElse(null);
    }
    
    /**
     * Gets the {@link AdvancementRewards} for this advancement
     *
     * @return The {@link AdvancementRewards} for this advancement.
     */
    @ZenCodeType.Getter("rewards")
    public static AdvancementRewards rewards(Advancement internal) {
        
        return internal.rewards();
    }
    
    @ZenCodeType.Getter("criteria")
    public static Map<String, Criterion> criteria(Advancement internal) {
        
        return GenericUtil.uncheck(internal.criteria());
    }
    
    @ZenCodeType.Getter("requirements")
    public static AdvancementRequirements requirements(Advancement internal) {
        
        return internal.requirements();
    }
    
    /**
     * Checks if this advancement sends a telemetry event
     *
     * @return true if this advancement sends a telemetry event, false otherwise
     */
    @ZenCodeType.Getter("sendsTelemetryEvent")
    public static boolean sendsTelemetryEvent(Advancement internal) {
        
        return internal.sendsTelemetryEvent();
    }
    
    /**
     * Gets the name of this advancement
     *
     * @return The name of this advancement.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("name")
    public static Component name(Advancement internal) {
        
        return internal.name().orElse(null);
    }
    
}
