package com.blamejared.crafttweaker.natives.advancement;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

/**
 * An AdvancementHolder links an {@link Advancement} to a {@link ResourceLocation}.
 */
@ZenRegister
@Document("vanilla/api/advancement/AdvancementHolder")
@NativeTypeRegistration(value = AdvancementHolder.class, zenCodeName = "crafttweaker.api.advancement.AdvancementHolder")
public class ExpandAdvancementHolder {
    
    /**
     * Gets the id of this advancement holder
     *
     * @return The id of this advancement holder.
     */
    @ZenCodeType.Getter("id")
    public static ResourceLocation id(AdvancementHolder internal) {
        
        return internal.id();
    }
    /**
         * Gets the {@link Advancement} for this advancement holder
     *
     * @return The {@link Advancement} for this advancement holder.
     */
    @ZenCodeType.Getter("value")
    public static Advancement value(AdvancementHolder internal) {
        
        return internal.value();
    }
    
}
