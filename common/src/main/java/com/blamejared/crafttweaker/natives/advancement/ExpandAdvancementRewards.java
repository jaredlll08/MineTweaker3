package com.blamejared.crafttweaker.natives.advancement;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/advancement/AdvancementRewards")
@NativeTypeRegistration(value = AdvancementRewards.class, zenCodeName = "crafttweaker.api.advancement.AdvancementRewards")
public class ExpandAdvancementRewards {
    
    /**
     * Gets the recipes that are unlocked when the advancement is completed.
     *
     * @return The recipes that are unlocked when the advancement is completed.
     */
    @ZenCodeType.Getter("recipes")
    public static List<ResourceLocation> getRecipes(AdvancementRewards internal) {
        
        return internal.recipes();
    }
    
    /**
     * Grants the advancement rewards to the player.
     *
     * @param player The player to grant the advancement rewards to.
     */
    @ZenCodeType.Method
    public static void grant(AdvancementRewards internal, ServerPlayer player) {
        
        internal.grant(player);
    }
    
}
