package com.blamejared.crafttweaker.natives.advancement;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/advancement/AdvancementType")
@NativeTypeRegistration(value = AdvancementType.class, zenCodeName = "crafttweaker.api.advancement.AdvancementType")
@BracketEnum("minecraft:advancement/type")
public class ExpandAdvancementType {
    
    /**
     * Gets the name of the advancement type.
     *
     * @return The name of the advancement type.
     */
    @ZenCodeType.Getter("name")
    public static String getName(AdvancementType internal) {
        
        return internal.name();
    }
    
    /**
     * Gets the chat color of the advancement type.
     *
     * @return The chat color of the advancement type.
     */
    @ZenCodeType.Getter("chatColor")
    public static ChatFormatting getChatColor(AdvancementType internal) {
        
        return internal.getChatColor();
    }
    
    /**
     * Gets the display name of the advancement type.
     *
     * @return The display name of the advancement type.
     */
    @ZenCodeType.Getter("displayName")
    public static Component getDisplayName(AdvancementType internal) {
        
        return internal.getDisplayName();
    }
    
    /**
     * Creates an announcement component for the advancement type.
     *
     * @param advancement  The advancement to create the announcement for.
     * @param serverPlayer The server player to create the announcement for.
     *
     * @return The announcement for the advancement type.
     */
    @ZenCodeType.Method
    public static MutableComponent createAnnouncement(AdvancementType internal, AdvancementHolder advancement, ServerPlayer serverPlayer) {
        
        return internal.createAnnouncement(advancement, serverPlayer);
    }
    
}
