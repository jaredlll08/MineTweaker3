package com.blamejared.crafttweaker.natives.entity.type.player;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.player.Abilities;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/type/player/Abilities")
@NativeTypeRegistration(value = Abilities.class, zenCodeName = "crafttweaker.api.entity.type.player.Abilities")
public class ExpandAbilities {
    
    /**
     * Gets the flying speed of the player.
     *
     * @return The flying speed of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("flyingSpeed")
    public static float getFlyingSpeed(Abilities internal) {
        
        return internal.getFlyingSpeed();
    }
    
    /**
     * Sets the flying speed of the player.
     *
     * @param param0 The new flying speed of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("flyingSpeed")
    public static void setFlyingSpeed(Abilities internal, float param0) {
        
        internal.setFlyingSpeed(param0);
    }
    
    /**
     * Gets the walking speed of the player.
     *
     * @return The walking speed of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("walkingSpeed")
    public static float getWalkingSpeed(Abilities internal) {
        
        return internal.getWalkingSpeed();
    }
    
    /**
     * Sets the walking speed of the player.
     *
     * @param param0 The new walking speed of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("walkingSpeed")
    public static void setWalkingSpeed(Abilities internal, float param0) {
        
        internal.setWalkingSpeed(param0);
    }
    
}
