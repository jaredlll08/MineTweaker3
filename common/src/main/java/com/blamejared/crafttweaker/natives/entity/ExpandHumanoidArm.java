package com.blamejared.crafttweaker.natives.entity;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.HumanoidArm;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/HumanoidArm")
@NativeTypeRegistration(value = HumanoidArm.class, zenCodeName = "crafttweaker.api.entity.HumanoidArm")
@BracketEnum("minecraft:entity/humanoidarm")
public class ExpandHumanoidArm {
    
    /**
     * Gets the opposite arm.
     *
     * @return The opposite arm.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("opposite")
    public static HumanoidArm getOpposite(HumanoidArm internal) {
        
        return internal.getOpposite();
    }
    
    /**
     * Gets the key of the humanoid arm.
     *
     * @return The key of the humanoid arm.
     */
    @ZenCodeType.Method("key")
    public static String getKey(HumanoidArm internal) {
        
        return internal.getKey();
    }
    
}
