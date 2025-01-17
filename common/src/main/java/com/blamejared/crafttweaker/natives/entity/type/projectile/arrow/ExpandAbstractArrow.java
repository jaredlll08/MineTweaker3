package com.blamejared.crafttweaker.natives.entity.type.projectile.arrow;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/type/projectile/arrow/Arrow")
@NativeTypeRegistration(value = AbstractArrow.class, zenCodeName = "crafttweaker.api.entity.type.projectile.arrow.Arrow")
public class ExpandAbstractArrow {
    
    /**
     * Sets the sound event that this arrow plays when it hits an entity or block.
     *
     * @param event The sound event to play when
     *
     * @docParam soundevent <soundevent:minecraft:ambient.cave>
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("soundEvent")
    public static void setSoundEvent(AbstractArrow internal, SoundEvent event) {
        
        internal.setSoundEvent(event);
    }
    
    /**
     * Sets the base damage that this arrow does.
     *
     * @param damage The base damage.
     *
     * @docParam damage 0.5
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("baseDamage")
    public static void setBaseDamage(AbstractArrow internal, double damage) {
        
        internal.setBaseDamage(damage);
    }
    
    /**
     * Gets the base damage that this arrow does.
     *
     * @return The base damage of this arrow.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("baseDamage")
    public static double getBaseDamage(AbstractArrow internal) {
        
        return internal.getBaseDamage();
    }
    
    /**
     * Sets this the crit value of this arrow.
     *
     * @param crit The crit value to set.
     *
     * @docParam crit true
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("isCritArrow")
    public static void setCritArrow(AbstractArrow internal, boolean crit) {
        
        internal.setCritArrow(crit);
    }
    
    /**
     * Checks whether this arrow is a crit arrow.
     *
     * @return true if this is a crit arrow, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isCritArrow")
    public static boolean isCritArrow(AbstractArrow internal) {
        
        return internal.isCritArrow();
    }
    
    /**
     * Gets the pierce level of this arrow.
     *
     * @return the pierce level of this arrow.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("pierceLevel")
    public static byte getPierceLevel(AbstractArrow internal) {
        
        return internal.getPierceLevel();
    }
    
    /**
     * Gets the {@link IItemStack} that fired this arrow.
     *
     * @return The weapon item of this arrow.
     */
    @ZenCodeType.Getter("weaponItem")
    public static IItemStack getWeaponItem(AbstractArrow internal) {
        
        return IItemStack.of(internal.getWeaponItem());
    }
    
    /**
     * Sets if this arrow has physics or not.
     *
     * @param noPhysics If this arrow has physics or not.
     *
     * @docParam noPhysics true
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("isNoPhysics")
    public static void setNoPhysics(AbstractArrow internal, boolean noPhysics) {
        
        internal.setNoPhysics(noPhysics);
    }
    
    /**
     * Checks if this arrow has physics or not.
     *
     * @return true if this arrow doesn't have physics, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isNoPhysics")
    public static boolean isNoPhysics(AbstractArrow internal) {
        
        return internal.isNoPhysics();
    }
    
}
