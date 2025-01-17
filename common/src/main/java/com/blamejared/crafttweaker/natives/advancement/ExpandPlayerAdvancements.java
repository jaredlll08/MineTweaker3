package com.blamejared.crafttweaker.natives.advancement;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/advancement/PlayerAdvancements")
@NativeTypeRegistration(value = PlayerAdvancements.class, zenCodeName = "crafttweaker.api.advancement.PlayerAdvancements")
public class ExpandPlayerAdvancements {
    
    /**
     * Awards an advancement to the player.
     *
     * @param advancement The advancement to award.
     * @param criteria    The criteria to award.
     *
     * @return Whether the advancement was awarded.
     */
    @ZenCodeType.Method
    public static boolean award(PlayerAdvancements internal, AdvancementHolder advancement, String criteria) {
        
        return internal.award(advancement, criteria);
    }
    
    /**
     * Revokes an advancement from the player.
     *
     * @param advancement The advancement to revoke.
     * @param criteria    The criteria to revoke.
     *
     * @return Whether the advancement was revoked.
     */
    @ZenCodeType.Method
    public static boolean revoke(PlayerAdvancements internal, AdvancementHolder advancement, String criteria) {
        
        return internal.revoke(advancement, criteria);
    }
    
    /**
     * Updates the advancements on the client
     *
     * @param player The player to flush the dirty state of.
     */
    @ZenCodeType.Method
    public static void flushDirty(PlayerAdvancements internal, ServerPlayer player) {
        
        internal.flushDirty(player);
    }
    
    /**
     * Gets the progress of an advancement.
     *
     * @param advancement The advancement to get the progress of.
     *
     * @return The progress of the advancement.
     */
    @ZenCodeType.Method
    public static AdvancementProgress getOrStartProgress(PlayerAdvancements internal, AdvancementHolder advancement) {
        
        return internal.getOrStartProgress(advancement);
    }
    
}
