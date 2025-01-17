package com.blamejared.crafttweaker.natives.entity.type.misc;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/type/misc/Mob")
@NativeTypeRegistration(value = Mob.class, zenCodeName = "crafttweaker.api.entity.type.misc.Mob")
public class ExpandMob {
    
    /**
     * Sets the target of the mob.
     *
     * @param target The target of the mob.
     */
    @ZenCodeType.Setter("target")
    public static void setTarget(Mob internal, @ZenCodeType.Nullable LivingEntity target) {
        
        internal.setTarget(target);
    }
    
    /**
     * Checks if the mob can fire a projectile weapon.
     *
     * @param weapon The weapon to check.
     *
     * @return Whether the mob can fire the weapon.
     */
    @ZenCodeType.Method
    public static boolean canFireProjectileWeapon(Mob internal, ProjectileWeaponItem weapon) {
        
        return internal.canFireProjectileWeapon(weapon);
    }
    
    /**
     * Triggers the eat event for this mob.
     *
     * This is used by sheep when they eat grass to regrow their wool / grow up.
     */
    @ZenCodeType.Method
    public static void ate(Mob internal) {
        
        internal.ate();
    }
    
    /**
     * Gets the interval of the ambient sound for this mob.
     *
     * @return The interval of the ambient sound for this mob.
     */
    @ZenCodeType.Getter("ambientSoundInterval")
    public static int getAmbientSoundInterval(Mob internal) {
        
        return internal.getAmbientSoundInterval();
    }
    
    /**
     * Plays the ambient sound for this mob.
     */
    @ZenCodeType.Method
    public static void playAmbientSound(Mob internal) {
        
        internal.playAmbientSound();
    }
    
    /**
     * Equips the mob with the given item if possible.
     *
     * @param stack The item to equip.
     *
     * @return The item that was equipped.
     */
    @ZenCodeType.Method
    public static ItemStack equipItemIfPossible(Mob internal, ItemStack stack) {
        
        return internal.equipItemIfPossible(stack);
    }
    
    /**
     * Sets that the item in the given {@link EquipmentSlot} should always drop when it is killed.
     *
     * @param slot The equipment slot to set the guaranteed drop rate for.
     */
    @ZenCodeType.Method
    public static void setGuaranteedDrop(Mob internal, EquipmentSlot slot) {
        
        internal.setGuaranteedDrop(slot);
    }
    
    /**
     * Checks if the mob can replace an item with another item.
     *
     * @param toReplace The item to replace.
     * @param with      The item to replace with.
     *
     * @return Whether the mob can replace the item.
     */
    @ZenCodeType.Method
    public static boolean canReplaceEqualItem(Mob internal, ItemStack toReplace, ItemStack with) {
        
        return internal.canReplaceEqualItem(toReplace, with);
    }
    
    /**
     * Checks if the mob can hold the given item.
     *
     * @param stack The item to check.
     *
     * @return Whether the mob can hold the item.
     */
    @ZenCodeType.Method
    public static boolean canHoldItem(Mob internal, ItemStack stack) {
        
        return internal.canHoldItem(stack);
    }
    
    /**
     * Checks if the mob wants to pick up the given item.
     *
     * @param stack The item to check.
     *
     * @return Whether the mob wants to pick up the item.
     */
    @ZenCodeType.Method
    public static boolean wantsToPickUp(Mob internal, ItemStack stack) {
        
        return internal.wantsToPickUp(stack);
    }
    
    /**
     * Gets the maximum head rotation in the X direction.
     *
     * @return The maximum head rotation in the X direction.
     */
    @ZenCodeType.Getter("maxHeadXRot")
    public static int getMaxHeadXRot(Mob internal) {
        
        return internal.getMaxHeadXRot();
    }
    
    /**
     * Gets the maximum head rotation in the Y direction.
     *
     * @return The maximum head rotation in the Y direction.
     */
    @ZenCodeType.Getter("maxHeadYRot")
    public static int getMaxHeadYRot(Mob internal) {
        
        return internal.getMaxHeadYRot();
    }
    
    /**
     * Gets the maximum head rotation speed.
     *
     * @return The maximum head rotation speed.
     */
    @ZenCodeType.Getter("maxHeadRotSpeed")
    public static int getHeadRotSpeed(Mob internal) {
        
        return internal.getHeadRotSpeed();
    }
    
    /**
     * Makes the mob look at the given entity.
     *
     * @param entity          The entity to look at.
     * @param maxXRotIncrease The maximum rotation in the X direction.
     * @param maxYRotIncrease The maximum rotation in the Y direction.
     */
    @ZenCodeType.Method
    public static void lookAt(Mob internal, Entity entity, float maxXRotIncrease, float maxYRotIncrease) {
        
        internal.lookAt(entity, maxXRotIncrease, maxYRotIncrease);
    }
    
    /**
     * Checks if the mob can spawn in the given {@link LevelAccessor level} with the given {@link MobSpawnType}.
     *
     * @param level     The level to check.
     * @param spawnType The type of spawn.
     *
     * @return Whether the mob can spawn in the given level.
     */
    @ZenCodeType.Method
    public static boolean checkSpawnRules(Mob internal, LevelAccessor level, MobSpawnType spawnType) {
        
        return internal.checkSpawnRules(level, spawnType);
    }
    
    /**
     * Checks if the mob can spawn in the given {@link LevelReader level}.
     *
     * @param level The level to check.
     *
     * @return Whether the mob can spawn in the given level.
     */
    @ZenCodeType.Method
    public static boolean checkSpawnObstruction(Mob internal, LevelReader level) {
        
        return internal.checkSpawnObstruction(level);
    }
    
    /**
     * Gets the maximum spawn cluster size.
     *
     * @return The maximum spawn cluster size.
     */
    @ZenCodeType.Getter("maxSpawnClusterSize")
    public static int getMaxSpawnClusterSize(Mob internal) {
        
        return internal.getMaxSpawnClusterSize();
    }
    
    /**
     * Checks if the mob's maximum group size is reached.
     * This is mainly used by fish when they are spawned naturally.
     *
     * @param size The size to check.
     *
     * @return Whether the mob's maximum group size is reached.
     */
    @ZenCodeType.Method
    public static boolean isMaxGroupSizeReached(Mob internal, int size) {
        
        return internal.isMaxGroupSizeReached(size);
    }
    
    /**
     * Sets that this mob should persist in the world.
     */
    @ZenCodeType.Method
    public static void setPersistenceRequired(Mob internal) {
        
        internal.setPersistenceRequired();
    }
    
    /**
     * Sets the drop chance for the given {@link EquipmentSlot}.
     *
     * @param slot   The equipment slot to set the drop chance for.
     * @param chance The chance to drop the item.
     */
    @ZenCodeType.Method
    public static void setDropChance(Mob internal, EquipmentSlot slot, float chance) {
        
        internal.setDropChance(slot, chance);
    }
    
    /**
     * Checks if the mob can pick up loot.
     *
     * @return Whether the mob can pick up loot.
     */
    @ZenCodeType.Getter("canPickUpLoot")
    public static boolean canPickUpLoot(Mob internal) {
        
        return internal.canPickUpLoot();
    }
    
    /**
     * Sets whether the mob can pick up loot.
     *
     * @param value Whether the mob can pick up loot.
     */
    @ZenCodeType.Setter("canPickUpLoot")
    public static void setCanPickUpLoot(Mob internal, boolean value) {
        
        internal.setCanPickUpLoot(value);
    }
    
    /**
     * Checks if the mob should persist in the world.
     *
     * @return Whether the mob should persist in the world.
     */
    @ZenCodeType.Getter("isPersistenceRequired")
    public static boolean isPersistenceRequired(Mob internal) {
        
        return internal.isPersistenceRequired();
    }
    
    /**
     * Checks if the mob is within a restriction.
     *
     * @return Whether the mob is within a restriction.
     */
    @ZenCodeType.Getter("isWithinRestriction")
    public static boolean isWithinRestriction(Mob internal) {
        
        return internal.isWithinRestriction();
    }
    
    /**
     * Checks if the mob is within a restriction.
     *
     * @param position The position to check.
     *
     * @return Whether the mob is within a restriction.
     */
    @ZenCodeType.Method
    public static boolean isWithinRestriction(Mob internal, BlockPos position) {
        
        return internal.isWithinRestriction(position);
    }
    
    /**
     * Restricts the mob to the given position and radius.
     *
     * @param restrictCenter The position to restrict the mob to.
     * @param restrictRadius The radius to restrict the mob to.
     */
    @ZenCodeType.Method
    public static void restrictTo(Mob internal, BlockPos restrictCenter, int restrictRadius) {
        
        internal.restrictTo(restrictCenter, restrictRadius);
    }
    
    /**
     * Gets the position that the mob is restricted to.
     *
     * @return The position that the mob is restricted to.
     */
    @ZenCodeType.Getter("getRestrictCenter")
    public static BlockPos getRestrictCenter(Mob internal) {
        
        return internal.getRestrictCenter();
    }
    
    /**
     * Gets the radius that the mob is restricted to.
     *
     * @return The radius that the mob is restricted to.
     */
    @ZenCodeType.Getter("getRestrictRadius")
    public static float getRestrictRadius(Mob internal) {
        
        return internal.getRestrictRadius();
    }
    
    /**
     * Clears the restriction for the mob.
     */
    @ZenCodeType.Method
    public static void clearRestriction(Mob internal) {
        
        internal.clearRestriction();
    }
    
    /**
     * Checks if the mob has a restriction.
     *
     * @return Whether the mob has a restriction.
     */
    @ZenCodeType.Getter("hasRestriction")
    public static boolean hasRestriction(Mob internal) {
        
        return internal.hasRestriction();
    }
    
    /**
     * Drops the leash for the mob.
     *
     * @param broadcastPacket Whether to broadcast a packet to the client.
     * @param dropLeash       Whether to drop the leash item.
     */
    @ZenCodeType.Method
    public static void dropLeash(Mob internal, boolean broadcastPacket, boolean dropLeash) {
        
        internal.dropLeash(broadcastPacket, dropLeash);
    }
    
    /**
     * Checks if the mob can be leashed.
     *
     * @return Whether the mob can be leashed.
     */
    @ZenCodeType.Method
    public static boolean canBeLeashed(Mob internal) {
        
        return internal.canBeLeashed();
    }
    
    /**
     * Checks if the mob is leashed.
     *
     * @return Whether the mob is leashed.
     */
    @ZenCodeType.Getter("leashed")
    public static boolean isLeashed(Mob internal) {
        
        return internal.isLeashed();
    }
    
    /**
     * Gets the entity that the mob is leashed to.
     *
     * @return The entity that the mob is leashed to.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("getRestrictCenter")
    public static Entity getLeashHolder(Mob internal) {
        
        return internal.getLeashHolder();
    }
    
    /**
     * Sets the entity that the mob is leashed to.
     *
     * @param leashHolder     The entity that the mob is leashed to.
     * @param broadcastPacket Whether to broadcast a packet to the client.
     */
    @ZenCodeType.Method
    public static void setLeashedTo(Mob internal, Entity leashHolder, boolean broadcastPacket) {
        
        internal.setLeashedTo(leashHolder, broadcastPacket);
    }
    
    /**
     * Sets whether the mob should have AI disabled.
     *
     * @param value Whether the mob should have AI disabled.
     */
    @ZenCodeType.Setter("noAi")
    public static void setNoAi(Mob internal, boolean value) {
        
        internal.setNoAi(value);
    }
    
    /**
     * Sets whether the mob should be left-handed.
     *
     * @param value Whether the mob should be left-handed.
     */
    @ZenCodeType.Setter("leftHanded")
    public static void setLeftHanded(Mob internal, boolean value) {
        
        internal.setLeftHanded(value);
    }
    
    /**
     * Sets whether the mob should be aggressive.
     *
     * @param value Whether the mob should be aggressive.
     */
    @ZenCodeType.Setter("aggressive")
    public static void setAggressive(Mob internal, boolean value) {
        
        internal.setAggressive(value);
    }
    
    /**
     * Checks if the mob has AI disabled.
     *
     * @return Whether the mob has AI disabled.
     */
    @ZenCodeType.Getter("noAi")
    public static boolean isNoAi(Mob internal) {
        
        return internal.isNoAi();
    }
    
    /**
     * Checks if the mob is left-handed.
     *
     * @return Whether the mob is left-handed.
     */
    @ZenCodeType.Getter("leftHanded")
    public static boolean isLeftHanded(Mob internal) {
        
        return internal.isLeftHanded();
    }
    
    /**
     * Checks if the mob is aggressive.
     *
     * @return Whether the mob is aggressive.
     */
    @ZenCodeType.Getter("aggressive")
    public static boolean isAggressive(Mob internal) {
        
        return internal.isAggressive();
    }
    
    /**
     * Sets whether the mob should be a baby.
     *
     * @param value Whether the mob should be a baby.
     */
    @ZenCodeType.Setter("baby")
    public static void setBaby(Mob internal, boolean value) {
        
        internal.setBaby(value);
    }
    
    /**
     * Checks if the mob is within melee attack range of the given {@link LivingEntity}.
     *
     * @param entity The entity to check.
     *
     * @return Whether the mob is within melee attack range of the given entity.
     */
    @ZenCodeType.Method
    public static boolean isWithinMeleeAttackRange(Mob internal, LivingEntity entity) {
        
        return internal.isWithinMeleeAttackRange(entity);
    }
    
}
