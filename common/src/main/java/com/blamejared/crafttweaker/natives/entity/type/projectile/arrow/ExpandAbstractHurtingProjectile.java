package com.blamejared.crafttweaker.natives.entity.type.projectile.arrow;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/type/projectile/AbstractHurtingProjectile")
@NativeTypeRegistration(value = AbstractHurtingProjectile.class, zenCodeName = "crafttweaker.api.entity.type.projectile.AbstractHurtingProjectile")
public class ExpandAbstractHurtingProjectile {
    
    /**
     * Gets the accelerationPower of this projectile.
     *
     * @return The accelerationPower of this projectile.
     */
    @ZenCodeType.Getter("accelerationPower")
    public static double getAccelerationPower(AbstractHurtingProjectile internal) {
        
        return internal.accelerationPower;
    }
    
    /**
     * Sets the accelerationPower of this projectile.
     *
     * @param accelerationPower The accelerationPower of this projectile.
     *
     * @docParam xPower 4
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("accelerationPower")
    public static void setAccelerationPower(AbstractHurtingProjectile internal, double accelerationPower) {
        
        internal.accelerationPower = accelerationPower;
    }
    
}
