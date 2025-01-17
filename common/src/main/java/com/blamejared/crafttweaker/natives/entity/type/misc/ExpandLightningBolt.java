package com.blamejared.crafttweaker.natives.entity.type.misc;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.mixin.common.access.entity.AccessLightningBolt;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LightningBolt;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/type/misc/LightningBolt")
@NativeTypeRegistration(value = LightningBolt.class, zenCodeName = "crafttweaker.api.entity.type.misc.LightningBolt")
public class ExpandLightningBolt {
    
    /**
     * Sets whether the lightning bolt is visual only.
     *
     * @param visualOnly Whether the lightning bolt is visual only.
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("visualOnly")
    public static void setVisualOnly(LightningBolt internal, boolean visualOnly) {
        
        internal.setVisualOnly(visualOnly);
    }
    
    /**
     * Checks if the lightning bolt is visual only.
     *
     * @return Whether the lightning bolt is visual only.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("visualOnly")
    public static boolean isVisualOnly(LightningBolt internal) {
        
        return ((AccessLightningBolt) internal).crafttweaker$isVisualOnly();
    }
    
    /**
     * Gets the cause of the lightning bolt if it exists.
     *
     * @return The cause of the lightning bolt.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    @ZenCodeType.Getter("cause")
    public static ServerPlayer getCause(LightningBolt internal) {
        
        return internal.getCause();
    }
    
    /**
     * Gets the number of blocks that will be set on fire by the lightning bolt.
     *
     * @return The number of blocks that will be set on fire by the lightning bolt.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("blocksSetOnFire")
    public static int getBlocksSetOnFire(LightningBolt internal) {
        
        return internal.getBlocksSetOnFire();
    }
    
    
}
