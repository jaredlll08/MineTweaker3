package com.blamejared.crafttweaker.natives.entity.type.projectile;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/type/projectile/Projectile")
@NativeTypeRegistration(value = Projectile.class, zenCodeName = "crafttweaker.api.entity.type.projectile.Projectile")
public class ExpandProjectile {
    
    /**
     * Sets the owner of the projectile.
     *
     * @param entity The entity to set as the owner.
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("owner")
    public static void setOwner(Projectile internal, @ZenCodeType.Nullable Entity entity) {
        
        internal.setOwner(entity);
    }
    
    /**
     * Gets the owner of the projectile.
     *
     * @return The owner of the projectile.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    @ZenCodeType.Getter("owner")
    public static Entity getOwner(Projectile internal) {
        
        return internal.getOwner();
    }
    
    /**
     * Gets the effect source of the projectile.
     *
     * @return The effect source of the projectile.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("effectSource")
    public static Entity getEffectSource(Projectile internal) {
        
        return internal.getEffectSource();
    }
    
    /**
     * Shoots the projectile from the given position and rotation.
     *
     * @param x          The x position to shoot from.
     * @param y          The y position to shoot from.
     * @param z          The z position to shoot from.
     * @param velocity   The velocity of the projectile.
     * @param innacuracy The innacuracy of the projectile.
     */
    public static void shoot(Projectile internal, double x, double y, double z, float velocity, float innacuracy) {
        
        internal.shoot(x, y, z, velocity, innacuracy);
    }
    
    /**
     * Shoots the projectile from the given rotation.
     *
     * @param projectile The projectile to shoot.
     * @param x          The x rotation.
     * @param y          The y rotation.
     * @param z          The z rotation.
     * @param velocity   The velocity of the projectile.
     * @param innacuracy The innacuracy of the projectile.
     */
    public static void shootFromRotation(Projectile internal, Entity projectile, float x, float y, float z, float velocity, float innacuracy) {
        
        internal.shootFromRotation(projectile, x, y, z, velocity, innacuracy);
    }
    
}
