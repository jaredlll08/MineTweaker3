package com.blamejared.crafttweaker.natives.entity;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@ZenRegister
@Document("vanilla/api/entity/Entity")
@NativeTypeRegistration(value = Entity.class, zenCodeName = "crafttweaker.api.entity.Entity")
public class ExpandEntity {
    
    @ZenCodeType.Method
    public static void updateCustomEntityTag(Entity internal, Level level, @ZenCodeType.Nullable Player player, MapData data) {
        
        EntityType.updateCustomEntityTag(level, player, internal, CustomData.of(data.getInternal()));
    }
    
    /**
     * Checks if this entity is colliding with the given {@link BlockState} at the given {@link BlockPos}.
     *
     * @param pos   The position of the block to check.
     * @param state The state of the block to check.
     *
     * @return true if the entity is colliding with the block.
     */
    @ZenCodeType.Method
    public static boolean isColliding(Entity internal, BlockPos pos, BlockState state) {
        
        return internal.isColliding(pos, state);
    }
    
    /**
     * Gets the {@link Level} of the entity.
     *
     * @return The {@link Level} of the entity.
     */
    @ZenCodeType.Getter("level")
    public static Level getLevel(Entity internal) {
        
        return internal.level();
    }
    
    /**
     * Gets the team color of the entity.
     *
     * @return The team color of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("teamColor")
    public static int getTeamColor(Entity internal) {
        
        return internal.getTeamColor();
    }
    
    /**
     * Checks if the entity is a spectator.
     *
     * @return true if the entity is a spectator.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isSpectator")
    public static boolean isSpectator(Entity internal) {
        
        return internal.isSpectator();
    }
    
    /**
     * Removes any passengers and stops riding the current entity.
     */
    @ZenCodeType.Method
    public static void unRide(Entity internal) {
        
        internal.unRide();
    }
    
    /**
     * Gets the {@link EntityType} of the entity.
     *
     * @return The {@link EntityType} of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("type")
    public static EntityType<Entity> getType(Entity internal) {
        
        return GenericUtil.uncheck(internal.getType());
    }
    
    /**
     * Gets the tags of the entity.
     *
     * These tags are arbitrary strings that can be attached to an entity. A single entity is limited to 1024.
     *
     * @return The tags of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("tags")
    public static Set<String> getTags(Entity internal) {
        
        return internal.getTags();
    }
    
    /**
     * Adds a tag to the entity, returning false if the entity has over 1024
     *
     * @param tagName The name of the tag to add.
     *
     * @return true if the tag was added.
     */
    @ZenCodeType.Method
    public static boolean addTag(Entity internal, String tagName) {
        
        return internal.addTag(tagName);
    }
    
    /**
     * Removes a tag from the entity.
     *
     * @param tagName The name of the tag to remove.
     *
     * @return true if the tag was removed.
     */
    @ZenCodeType.Method
    public static boolean removeTag(Entity internal, String tagName) {
        
        return internal.removeTag(tagName);
    }
    
    /**
     * Kills the entity.
     */
    @ZenCodeType.Method
    public static void kill(Entity internal) {
        
        internal.kill();
    }
    
    /**
     * Discards the entity.
     */
    @ZenCodeType.Method
    public static void discard(Entity internal) {
        
        internal.discard();
    }
    
    /**
     * Checks if the entity is closer than the given distance to another entity.
     *
     * @param other    The other entity to check the distance to.
     * @param distance The distance to check.
     *
     * @return true if the entity is closer than the given distance to the other entity.
     */
    @ZenCodeType.Method
    public static boolean closerThan(Entity internal, Entity other, double distance) {
        
        return internal.closerThan(other, distance);
    }
    
    /**
     * Sets the position of the entity.
     *
     * @param position The position to set the entity to.
     */
    @ZenCodeType.Method
    public static void setPos(Entity internal, Vec3 position) {
        
        internal.setPos(position);
    }
    
    /**
     * Sets the position of the entity.
     *
     * @param x The x position to set the entity to.
     * @param y The y position to set the entity to.
     * @param z The z position to set the entity to.
     */
    @ZenCodeType.Method
    public static void setPos(Entity internal, double x, double y, double z) {
        
        internal.setPos(x, y, z);
    }
    
    /**
     * Turns the entity.
     *
     * @param yaw   The yaw to turn the entity to.
     * @param pitch The pitch to turn the entity to.
     */
    @ZenCodeType.Method
    public static void turn(Entity internal, double yaw, double pitch) {
        
        internal.turn(yaw, pitch);
    }
    
    /**
     * Sets the portal cooldown of the entity.
     */
    @ZenCodeType.Method
    public static void setPortalCooldown(Entity internal) {
        
        internal.setPortalCooldown();
    }
    
    /**
     * Checks if the entity is on portal cooldown.
     *
     * @return true if the entity is on portal cooldown.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isOnPortalCooldown")
    public static boolean isOnPortalCooldown(Entity internal) {
        
        return internal.isOnPortalCooldown();
    }
    
    /**
     * Causes the entity to take damage from lava.
     */
    @ZenCodeType.Method
    public static void lavaHurt(Entity internal) {
        
        internal.lavaHurt();
    }
    
    /**
     * Sets the remaining fire ticks of the entity.
     *
     * @param ticks The number of ticks to set the remaining fire ticks to.
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("fireTicks")
    public static void setRemainingFireTicks(Entity internal, int ticks) {
        
        internal.setRemainingFireTicks(ticks);
    }
    
    /**
     * Gets the remaining fire ticks of the entity.
     *
     * @return The remaining fire ticks of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("fireTicks")
    public static int getRemainingFireTicks(Entity internal) {
        
        return internal.getRemainingFireTicks();
    }
    
    /**
     * Clears the fire of the entity.
     */
    @ZenCodeType.Method
    public static void clearFire(Entity internal) {
        
        internal.clearFire();
    }
    
    /**
     * Checks if the offset position from the entity's current position has a collision with a block or a liquid.
     *
     * @param x The x position to check.
     * @param y The y position to check.
     * @param z The z position to check.
     *
     * @return true if the entity is free to move in the given position.
     */
    @ZenCodeType.Method
    public static boolean isFree(Entity internal, double x, double y, double z) {
        
        return internal.isFree(x, y, z);
    }
    
    /**
     * Sets if the entity is on the ground.
     *
     * @param onGround The on ground state to set the entity to.
     */
    @ZenCodeType.Method
    public static void setOnGround(Entity internal, boolean onGround) {
        
        internal.setOnGround(onGround);
    }
    
    /**
     * Checks if the entity is on the ground.
     *
     * @return true if the entity is on the ground.
     */
    @ZenCodeType.Getter("onGround")
    public static boolean onGround(Entity internal) {
        
        return internal.onGround();
    }
    
    /**
     * Gets the position of the entity on the ground.
     *
     * @return The position of the entity on the ground.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("onPos")
    public static BlockPos getOnPos(Entity internal) {
        
        return internal.getOnPos();
    }
    
    /**
     * Plays a sound at the entity's location.
     *
     * @param sound  The sound to play.
     * @param volume The volume of the sound.
     * @param pitch  The pitch of the sound.
     */
    @ZenCodeType.Method
    public static void playSound(Entity internal, SoundEvent sound, float volume, float pitch) {
        
        internal.playSound(sound, volume, pitch);
    }
    
    /**
     * Checks if the entity is silent, meaning it won't play any sounds.
     *
     * @return true if the entity is silent.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isSilent")
    public static boolean isSilent(Entity internal) {
        
        return internal.isSilent();
    }
    
    /**
     * Sets if the entity is silent.
     *
     * @param silent The silent state to set the entity to.
     */
    @ZenCodeType.Method
    public static void setSilent(Entity internal, boolean silent) {
        
        internal.setSilent(silent);
    }
    
    /**
     * Checks if the entity has no gravity.
     *
     * @return true if the entity has no gravity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isNoGravity")
    public static boolean isNoGravity(Entity internal) {
        
        return internal.isNoGravity();
    }
    
    /**
     * Sets if the entity has no gravity.
     *
     * @param noGravity The no gravity state to set the entity to.
     */
    @ZenCodeType.Method
    public static void setNoGravity(Entity internal, boolean noGravity) {
        
        internal.setNoGravity(noGravity);
    }
    
    /**
     * Checks if the entity dampens vibrations, such as {@link net.minecraft.world.entity.item.ItemEntity}s who's item is in the dampens_vibrations item tag.
     *
     * @return true if the entity dampens vibrations.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("dampensVibrations")
    public static boolean dampensVibrations(Entity internal) {
        
        return internal.dampensVibrations();
    }
    
    /**
     * Checks if the entity is fire immune.
     *
     * @return true if the entity is fire immune.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("fireImmune")
    public static boolean fireImmune(Entity internal) {
        
        return internal.fireImmune();
    }
    
    /**
     * Checks if the entity is in water.
     *
     * @return true if the entity is in water.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isInWater")
    public static boolean isInWater(Entity internal) {
        
        return internal.isInWater();
    }
    
    /**
     * Checks if the entity is in water or rain.
     *
     * @return true if the entity is in water or rain.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isInWaterOrRain")
    public static boolean isInWaterOrRain(Entity internal) {
        
        return internal.isInWaterOrRain();
    }
    
    /**
     * Checks if the entity is in water or rain or a bubble column.
     *
     * @return true if the entity is in water or rain or a bubble column.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isInWaterRainOrBubble")
    public static boolean isInWaterRainOrBubble(Entity internal) {
        
        return internal.isInWaterRainOrBubble();
    }
    
    /**
     * Checks if the entity is in water or a bubble column.
     *
     * @return true if the entity is in water or a bubble column.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isInWaterOrBubble")
    public static boolean isInWaterOrBubble(Entity internal) {
        
        return internal.isInWaterOrBubble();
    }
    
    /**
     * Checks if the entity is underwater.
     *
     * @return true if the entity is underwater.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isUnderWater")
    public static boolean isUnderWater(Entity internal) {
        
        return internal.isUnderWater();
    }
    
    /**
     * Checks if the entity is in lava.
     *
     * @return true if the entity is in lava.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isInLava")
    public static boolean isInLava(Entity internal) {
        
        return internal.isInLava();
    }
    
    /**
     * Moves the entity relative to its current position.
     *
     * @param amount   The amount to move the entity by.
     * @param relative The relative position to move the entity by.
     */
    @ZenCodeType.Method
    public static void moveRelative(Entity internal, float amount, Vec3 relative) {
        
        internal.moveRelative(amount, relative);
    }
    
    /**
     * Moves the entity to the given position.
     *
     * @param vec The position to move the entity to.
     */
    @ZenCodeType.Method
    public static void moveTo(Entity internal, Vec3 vec) {
        
        internal.moveTo(vec);
    }
    
    /**
     * Moves the entity to the given position.
     *
     * @param x The x position to move the entity to.
     * @param y The y position to move the entity to.
     * @param z The z position to move the entity to.
     */
    @ZenCodeType.Method
    public static void moveTo(Entity internal, double x, double y, double z) {
        
        internal.moveTo(x, y, z);
    }
    
    /**
     * Moves the entity to the given position.
     *
     * @param pos   The position to move the entity to.
     * @param yaw   The yaw to move the entity to.
     * @param pitch The pitch to move the entity to.
     */
    @ZenCodeType.Method
    public static void moveTo(Entity internal, BlockPos pos, float yaw, float pitch) {
        
        internal.moveTo(pos, yaw, pitch);
    }
    
    /**
     * Moves the entity to the given position.
     *
     * @param x     The x position to move the entity to.
     * @param y     The y position to move the entity to.
     * @param z     The z position to move the entity to.
     * @param yaw   The yaw to move the entity to.
     * @param pitch The pitch to move the entity to.
     */
    @ZenCodeType.Method
    public static void moveTo(Entity internal, double x, double y, double z, float yaw, float pitch) {
        
        internal.moveTo(x, y, z, yaw, pitch);
    }
    
    /**
     * Sets the old position and rotation of the entity.
     */
    @ZenCodeType.Method
    public static void setOldPosAndRot(Entity internal) {
        
        internal.setOldPosAndRot();
    }
    
    /**
     * Gets the distance to the given entity.
     *
     * @param entity The entity to get the distance to.
     *
     * @return The distance to the given entity.
     */
    @ZenCodeType.Method
    public static float distanceTo(Entity internal, Entity entity) {
        
        return internal.distanceTo(entity);
    }
    
    /**
     * Gets the squared distance to the given position.
     *
     * @param x The x position to get the squared distance to.
     * @param y The y position to get the squared distance to.
     * @param z The z position to get the squared distance to.
     *
     * @return The squared distance to the given position.
     */
    @ZenCodeType.Method
    public static double distanceToSqr(Entity internal, double x, double y, double z) {
        
        return internal.distanceToSqr(x, y, z);
    }
    
    /**
     * Gets the squared distance to the given entity.
     *
     * @param entity The entity to get the squared distance to.
     *
     * @return The squared distance to the given entity.
     */
    @ZenCodeType.Method
    public static double distanceToSqr(Entity internal, Entity entity) {
        
        return internal.distanceToSqr(entity);
    }
    
    /**
     * Gets the squared distance to the given vector.
     *
     * @param vec The vector to get the squared distance to.
     *
     * @return The squared distance to the given vector.
     */
    @ZenCodeType.Method
    public static double distanceToSqr(Entity internal, Vec3 vec) {
        
        return internal.distanceToSqr(vec);
    }
    
    /**
     * Hurts the entity with the given {@link DamageSource} and amount.
     *
     * @param source The {@link DamageSource} to hurt the entity with.
     * @param amount The amount of damage to deal.
     *
     * @return true if the entity was hurt.
     */
    @ZenCodeType.Method
    public static boolean hurt(Entity internal, DamageSource source, float amount) {
        
        return internal.hurt(source, amount);
    }
    
    /**
     * Gets the view vector of the entity.
     *
     * @param partialTicks The partial ticks to get the view vector for.
     *
     * @return The view vector of the entity.
     */
    @ZenCodeType.Method
    public static Vec3 getViewVector(Entity internal, float partialTicks) {
        
        return internal.getViewVector(partialTicks);
    }
    
    /**
     * Gets the up vector of the entity.
     *
     * @param partialTicks The partial ticks to get the up vector for.
     *
     * @return The up vector of the entity.
     */
    @ZenCodeType.Method
    public static Vec3 getUpVector(Entity internal, float partialTicks) {
        
        return internal.getUpVector(partialTicks);
    }
    
    /**
     * Gets the eye position of the entity.
     *
     * @return The eye position of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("eyePosition")
    public static Vec3 getEyePosition(Entity internal) {
        
        return internal.getEyePosition();
    }
    
    /**
     * Gets the eye position of the entity.
     *
     * @param partialTicks The partial ticks to get the eye position for.
     *
     * @return The eye position of the entity.
     */
    @ZenCodeType.Method
    public static Vec3 getEyePosition(Entity internal, float partialTicks) {
        
        return internal.getEyePosition(partialTicks);
    }
    
    /**
     * Gets the position of the entity.
     *
     * @param partialTicks The partial ticks to get the position for.
     *
     * @return The position of the entity.
     */
    @ZenCodeType.Method
    public static Vec3 getPosition(Entity internal, float partialTicks) {
        
        return internal.getPosition(partialTicks);
    }
    
    /**
     * Checks if the entity is pickable.
     *
     * @return true if the entity is pickable.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isPickable")
    public static boolean isPickable(Entity internal) {
        
        return internal.isPickable();
    }
    
    /**
     * Checks if the entity is pushable.
     *
     * @return true if the entity is pushable.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isPushable")
    public static boolean isPushable(Entity internal) {
        
        return internal.isPushable();
    }
    
    /**
     * Checks if the entity is alive.
     *
     * @return true if the entity is alive.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isAlive")
    public static boolean isAlive(Entity internal) {
        
        return internal.isAlive();
    }
    
    /**
     * Checks if the entity is in a wall.
     *
     * @return true if the entity is in a wall.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isInWall")
    public static boolean isInWall(Entity internal) {
        
        return internal.isInWall();
    }
    
    /**
     * Checks if the entity can collide with the given entity.
     *
     * @param other The entity to check if the entity can collide with.
     *
     * @return true if the entity can collide with the given entity.
     */
    @ZenCodeType.Method
    public static boolean canCollideWith(Entity internal, Entity other) {
        
        return internal.canCollideWith(other);
    }
    
    /**
     * Checks if the entity can be collided with.
     *
     * @return true if the entity can be collided with.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("canBeCollidedWith")
    public static boolean canBeCollidedWith(Entity internal) {
        
        return internal.canBeCollidedWith();
    }
    
    /**
     * Positions the rider of the entity.
     *
     * @param entity The entity to position the rider of.
     */
    @ZenCodeType.Method
    public static void positionRider(Entity internal, Entity entity) {
        
        internal.positionRider(entity);
    }
    
    /**
     * Starts riding the given entity.
     *
     * @param entity The entity to start riding.
     *
     * @return true if the entity started riding.
     */
    @ZenCodeType.Method
    public static boolean startRiding(Entity internal, Entity entity) {
        
        return internal.startRiding(entity);
    }
    
    /**
     * Checks if the entity should show vehicle health.
     *
     * @return true if the entity should show vehicle health.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("showVehicleHealth")
    public static boolean showVehicleHealth(Entity internal) {
        
        return internal.showVehicleHealth();
    }
    
    /**
     * Starts riding the given entity.
     *
     * @param entity The entity to start riding.
     * @param force  true if the entity should be forced to start riding.
     *
     * @return true if the entity started riding.
     */
    @ZenCodeType.Method
    public static boolean startRiding(Entity internal, Entity entity, boolean force) {
        
        return internal.startRiding(entity, force);
    }
    
    /**
     * Ejects the passengers of the entity.
     */
    @ZenCodeType.Method
    public static void ejectPassengers(Entity internal) {
        
        internal.ejectPassengers();
    }
    
    /**
     * Removes the vehicle of the entity.
     */
    @ZenCodeType.Method
    public static void removeVehicle(Entity internal) {
        
        internal.removeVehicle();
    }
    
    /**
     * Stops riding the entity.
     */
    @ZenCodeType.Method
    public static void stopRiding(Entity internal) {
        
        internal.stopRiding();
    }
    
    /**
     * Gets the look angle of the entity.
     *
     * @return The look angle of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("lookAngle")
    public static Vec3 getLookAngle(Entity internal) {
        
        return internal.getLookAngle();
    }
    
    /**
     * Gets the forward vector of the entity.
     *
     * @return The forward vector of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("forward")
    public static Vec3 getForward(Entity internal) {
        
        return internal.getForward();
    }
    
    /**
     * Gets the dimension changing delay of the entity.
     *
     * @return The dimension changing delay of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("dimensionChangingDelay")
    public static int getDimensionChangingDelay(Entity internal) {
        
        return internal.getDimensionChangingDelay();
    }
    
    /**
     * Checks if the entity is on fire.
     *
     * @return true if the entity is on fire.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isOnFire")
    public static boolean isOnFire(Entity internal) {
        
        return internal.isOnFire();
    }
    
    /**
     * Checks if the entity is a passenger.
     *
     * @return true if the entity is a passenger.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isPassenger")
    public static boolean isPassenger(Entity internal) {
        
        return internal.isPassenger();
    }
    
    /**
     * Checks if the entity is a vehicle.
     *
     * @return true if the entity is a vehicle.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isVehicle")
    public static boolean isVehicle(Entity internal) {
        
        return internal.isVehicle();
    }
    
    /**
     * Checks if the entity dismounts underwater.
     *
     * @return true if the entity dismounts underwater.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("dismountsUnderwater")
    public static boolean dismountsUnderwater(Entity internal) {
        
        return internal.dismountsUnderwater();
    }
    
    /**
     * Sets the shift key down state of the entity.
     *
     * @param keyDown true if the shift key is down.
     */
    @ZenCodeType.Method
    public static void setShiftKeyDown(Entity internal, boolean keyDown) {
        
        internal.setShiftKeyDown(keyDown);
    }
    
    /**
     * Checks if the shift key is down.
     *
     * @return true if the shift key is down.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isShiftKeyDown")
    public static boolean isShiftKeyDown(Entity internal) {
        
        return internal.isShiftKeyDown();
    }
    
    /**
     * Checks if the entity is stepping carefully.
     *
     * @return true if the entity is stepping carefully.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isSteppingCarefully")
    public static boolean isSteppingCarefully(Entity internal) {
        
        return internal.isSteppingCarefully();
    }
    
    /**
     * Checks if the entity is suppressing bounce.
     *
     * @return true if the entity is suppressing bounce.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isSuppressingBounce")
    public static boolean isSuppressingBounce(Entity internal) {
        
        return internal.isSuppressingBounce();
    }
    
    /**
     * Checks if the entity is being discrete.
     *
     * @return true if the entity is being discrete.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isDiscrete")
    public static boolean isDiscrete(Entity internal) {
        
        return internal.isDiscrete();
    }
    
    /**
     * Checks if the entity is descending.
     *
     * @return true if the entity is descending.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isDescending")
    public static boolean isDescending(Entity internal) {
        
        return internal.isDescending();
    }
    
    /**
     * Checks if the entity is crouching.
     *
     * @return true if the entity is crouching.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isCrouching")
    public static boolean isCrouching(Entity internal) {
        
        return internal.isCrouching();
    }
    
    /**
     * Checks if the entity is sprinting.
     *
     * @return true if the entity is sprinting.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isSprinting")
    public static boolean isSprinting(Entity internal) {
        
        return internal.isSprinting();
    }
    
    /**
     * Sets the sprinting state of the entity.
     *
     * @param sprinting true if the entity should be sprinting.
     */
    @ZenCodeType.Method
    public static void setSprinting(Entity internal, boolean sprinting) {
        
        internal.setSprinting(sprinting);
    }
    
    /**
     * Checks if the entity is swimming.
     *
     * @return true if the entity is swimming.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isSwimming")
    public static boolean isSwimming(Entity internal) {
        
        return internal.isSwimming();
    }
    
    /**
     * Checks if the entity is visually swimming.
     *
     * @return true if the entity is visually swimming.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isVisuallySwimming")
    public static boolean isVisuallySwimming(Entity internal) {
        
        return internal.isVisuallySwimming();
    }
    
    /**
     * Checks if the entity is visually crawling.
     *
     * @return true if the entity is visually crawling.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isVisuallyCrawling")
    public static boolean isVisuallyCrawling(Entity internal) {
        
        return internal.isVisuallyCrawling();
    }
    
    /**
     * Sets the swimming state of the entity.
     *
     * @param swimming true if the entity should be swimming.
     */
    @ZenCodeType.Method
    public static void setSwimming(Entity internal, boolean swimming) {
        
        internal.setSwimming(swimming);
    }
    
    /**
     * Checks if the entity is currently glowing.
     *
     * @return true if the entity is currently glowing.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("hasGlowingTag")
    public static boolean hasGlowingTag(Entity internal) {
        
        return internal.hasGlowingTag();
    }
    
    /**
     * Makes the entity glow.
     *
     * @param glowing true if the entity should be glowing.
     */
    @ZenCodeType.Method
    public static void setGlowingTag(Entity internal, boolean glowing) {
        
        internal.setGlowingTag(glowing);
    }
    
    /**
     * Checks if the entity is currently glowing.
     *
     * @return true if the entity is currently glowing.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isCurrentlyGlowing")
    public static boolean isCurrentlyGlowing(Entity internal) {
        
        return internal.isCurrentlyGlowing();
    }
    
    /**
     * Checks if the entity is invisible.
     *
     * @return true if the entity is invisible.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isInvisible")
    public static boolean isInvisible(Entity internal) {
        
        return internal.isInvisible();
    }
    
    /**
     * Checks if the entity is invisible to the given {@link Player}.
     *
     * @param player The player to check.
     *
     * @return true if the entity is invisible to the player.
     */
    @ZenCodeType.Method
    public static boolean isInvisibleTo(Entity internal, Player player) {
        
        return internal.isInvisibleTo(player);
    }
    
    /**
     * Sets the invisible state of the entity.
     *
     * @param invisible true if the entity should be invisible.
     */
    @ZenCodeType.Method
    public static void setInvisible(Entity internal, boolean invisible) {
        
        internal.setInvisible(invisible);
    }
    
    /**
     * Gets the maximum air supply of the entity.
     *
     * @return The maximum air supply of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("maxAirSupply")
    public static int getMaxAirSupply(Entity internal) {
        
        return internal.getMaxAirSupply();
    }
    
    /**
     * Gets the current air supply of the entity.
     *
     * @return The current air supply of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("airSupply")
    public static int getAirSupply(Entity internal) {
        
        return internal.getAirSupply();
    }
    
    /**
     * Sets the air supply of the entity.
     *
     * @param air The new air supply of the entity.
     */
    @ZenCodeType.Method
    public static void setAirSupply(Entity internal, int air) {
        
        internal.setAirSupply(air);
    }
    
    /**
     * Gets the number of ticks the entity has been frozen.
     *
     * @return The number of ticks the entity has been frozen.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("ticksFrozen")
    public static int getTicksFrozen(Entity internal) {
        
        return internal.getTicksFrozen();
    }
    
    /**
     * Sets the number of ticks the entity has been frozen.
     *
     * @param ticks The new number of ticks the entity has been frozen.
     */
    @ZenCodeType.Method
    public static void setTicksFrozen(Entity internal, int ticks) {
        
        internal.setTicksFrozen(ticks);
    }
    
    /**
     * Gets the percent the entity is frozen.
     *
     * @return The percent the entity is frozen.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("percentFrozen")
    public static float getPercentFrozen(Entity internal) {
        
        return internal.getPercentFrozen();
    }
    
    /**
     * Checks if the entity is fully frozen.
     *
     * @return true if the entity is fully frozen.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isFullyFrozen")
    public static boolean isFullyFrozen(Entity internal) {
        
        return internal.isFullyFrozen();
    }
    
    /**
     * Gets the number of ticks required to freeze the entity.
     *
     * @return The number of ticks required to freeze the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("ticksRequiredToFreeze")
    public static int getTicksRequiredToFreeze(Entity internal) {
        
        return internal.getTicksRequiredToFreeze();
    }
    
    /**
     * Gets the name of the entity.
     *
     * @return The name of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("name")
    public static Component getName(Entity internal) {
        
        return internal.getName();
    }
    
    /**
     * Checks if the entity is attackable.
     *
     * @return true if the entity is attackable.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isAttackable")
    public static boolean isAttackable(Entity internal) {
        
        return internal.isAttackable();
    }
    
    /**
     * Checks if the entity is invulnerable to the given {@link DamageSource}.
     *
     * @param source The damage source to check.
     *
     * @return true if the entity is invulnerable to the damage source.
     */
    @ZenCodeType.Method
    public static boolean isInvulnerableTo(Entity internal, DamageSource source) {
        
        return internal.isInvulnerableTo(source);
    }
    
    /**
     * Checks if the entity is invulnerable.
     *
     * @return true if the entity is invulnerable.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isInvulnerable")
    public static boolean isInvulnerable(Entity internal) {
        
        return internal.isInvulnerable();
    }
    
    /**
     * Sets the invulnerable state of the entity.
     *
     * @param invulnerable true if the entity should be invulnerable.
     */
    @ZenCodeType.Method
    public static void setInvulnerable(Entity internal, boolean invulnerable) {
        
        internal.setInvulnerable(invulnerable);
    }
    
    /**
     * Gets the maximum fall distance of the entity.
     *
     * @return The maximum fall distance of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("maxFallDistance")
    public static int getMaxFallDistance(Entity internal) {
        
        return internal.getMaxFallDistance();
    }
    
    /**
     * Gets the UUID of the entity as a string.
     *
     * @return The UUID of the entity as a string.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("stringUUID")
    public static String getStringUUID(Entity internal) {
        
        return internal.getStringUUID();
    }
    
    /**
     * Checks if the entity is pushed by fluid.
     *
     * @return true if the entity is pushed by fluid.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isPushedByFluid")
    public static boolean isPushedByFluid(Entity internal) {
        
        return internal.isPushedByFluid();
    }
    
    /**
     * Gets the display name of the entity.
     *
     * @return The display name of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("displayName")
    public static Component getDisplayName(Entity internal) {
        
        return internal.getDisplayName();
    }
    
    /**
     * Sets the custom name of the entity.
     *
     * @param name The new custom name of the entity.
     */
    @ZenCodeType.Method
    public static void setCustomName(Entity internal, @ZenCodeType.Nullable Component name) {
        
        internal.setCustomName(name);
    }
    
    /**
     * Gets the custom name of the entity.
     *
     * @return The custom name of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("customName")
    public static Component getCustomName(Entity internal) {
        
        return internal.getCustomName();
    }
    
    /**
     * Checks if the entity has a custom name.
     *
     * @return true if the entity has a custom name.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("hasCustomName")
    public static boolean hasCustomName(Entity internal) {
        
        return internal.hasCustomName();
    }
    
    /**
     * Sets the custom name visible state of the entity.
     *
     * @param visible true if the custom name should be visible.
     */
    @ZenCodeType.Method
    public static void setCustomNameVisible(Entity internal, boolean visible) {
        
        internal.setCustomNameVisible(visible);
    }
    
    /**
     * Checks if the custom name of the entity is visible.
     *
     * @return true if the custom name is visible.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isCustomNameVisible")
    public static boolean isCustomNameVisible(Entity internal) {
        
        return internal.isCustomNameVisible();
    }
    
    /**
     * Teleports the entity to the given position.
     *
     * @param x The x coordinate to teleport to.
     * @param y The y coordinate to teleport to.
     * @param z The z coordinate to teleport to.
     */
    @ZenCodeType.Method
    public static void teleportTo(Entity internal, double x, double y, double z) {
        
        internal.teleportTo(x, y, z);
    }
    
    /**
     * Checks if the entity should show its name.
     *
     * @return true if the entity should show its name.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("shouldShowName")
    public static boolean shouldShowName(Entity internal) {
        
        return internal.shouldShowName();
    }
    
    /**
     * Gets the direction of the entity.
     *
     * @return The direction of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("direction")
    public static Direction getDirection(Entity internal) {
        
        return internal.getDirection();
    }
    
    /**
     * Gets the motion direction of the entity.
     *
     * @return The motion direction of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("motionDirection")
    public static Direction getMotionDirection(Entity internal) {
        
        return internal.getMotionDirection();
    }
    
    /**
     * Gets the bounding box for culling of the entity.
     *
     * @return The bounding box for culling of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("boundingBoxForCulling")
    public static AABB getBoundingBoxForCulling(Entity internal) {
        
        return internal.getBoundingBoxForCulling();
    }
    
    /**
     * Gets the eye height of the entity.
     *
     * @return The eye height of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("eyeHeight")
    public static float getEyeHeight(Entity internal) {
        
        return internal.getEyeHeight();
    }
    
    /**
     * Gets the {@link Level} used when sending commands as this entity.
     *
     * @return The {@link Level} used when sending commands as this entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("commandSenderWorld")
    public static Level getCommandSenderWorld(Entity internal) {
        
        return internal.getCommandSenderWorld();
    }
    
    /**
     * Gets the controlling passenger of the entity.
     *
     * @return The controlling passenger of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("controllingPassenger")
    public static Entity getControllingPassenger(Entity internal) {
        
        return internal.getControllingPassenger();
    }
    
    /**
     * Gets the passengers of the entity.
     *
     * @return The passengers of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("passengers")
    public static List<Entity> getPassengers(Entity internal) {
        
        return internal.getPassengers();
    }
    
    /**
     * Gets the first passenger of the entity.
     *
     * @return The first passenger of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("firstPassenger")
    public static Entity getFirstPassenger(Entity internal) {
        
        return internal.getFirstPassenger();
    }
    
    /**
     * Checks if the entity has a passenger.
     *
     * @param entity The entity to check.
     *
     * @return true if the entity has the given passenger.
     */
    @ZenCodeType.Method
    public static boolean hasPassenger(Entity internal, Entity entity) {
        
        return internal.hasPassenger(entity);
    }
    
    /**
     * Checks if the entity has a passenger that matches the given predicate.
     *
     * @param predicate The predicate to check.
     *
     * @return true if the entity has a passenger that matches the predicate.
     */
    @ZenCodeType.Method
    public static boolean hasPassenger(Entity internal, Predicate<Entity> predicate) {
        
        return internal.hasPassenger(predicate);
    }
    
    /**
     * Checks if the entity has exactly one player passenger.
     *
     * @return true if the entity has exactly one player passenger.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("hasExactlyOnePlayerPassenger")
    public static boolean hasExactlyOnePlayerPassenger(Entity internal) {
        
        return internal.hasExactlyOnePlayerPassenger();
    }
    
    /**
     * Gets the root vehicle of the entity.
     *
     * @return The root vehicle of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("rootVehicle")
    public static Entity getRootVehicle(Entity internal) {
        
        return internal.getRootVehicle();
    }
    
    /**
     * Checks if the entity is a passenger of the same vehicle.
     *
     * @param entity The entity to check.
     *
     * @return true if the entity is a passenger of the same vehicle.
     */
    @ZenCodeType.Method
    public static boolean isPassengerOfSameVehicle(Entity internal, Entity entity) {
        
        return internal.isPassengerOfSameVehicle(entity);
    }
    
    /**
     * Checks if the entity has an indirect passenger.
     *
     * @param entity The entity to check.
     *
     * @return true if the entity has an indirect passenger.
     */
    @ZenCodeType.Method
    public static boolean hasIndirectPassenger(Entity internal, Entity entity) {
        
        return internal.hasIndirectPassenger(entity);
    }
    
    /**
     * Gets the vehicle of the entity.
     *
     * @return The vehicle of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("vehicle")
    public static Entity getVehicle(Entity internal) {
        
        return internal.getVehicle();
    }
    
    /**
     * Gets the piston push reaction of the entity.
     *
     * @return The piston push reaction of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("pistonPushReaction")
    public static PushReaction getPistonPushReaction(Entity internal) {
        
        return internal.getPistonPushReaction();
    }
    
    /**
     * Gets the {@link SoundSource} of the entity.
     *
     * @return The sound source of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("soundSource")
    public static SoundSource getSoundSource(Entity internal) {
        
        return internal.getSoundSource();
    }
    
    /**
     * Gets the fluid jump threshold of the entity.
     *
     * @return The fluid jump threshold of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("fluidJumpThreshold")
    public static double getFluidJumpThreshold(Entity internal) {
        
        return internal.getFluidJumpThreshold();
    }
    
    /**
     * Gets the width of the bounding box of the entity.
     *
     * @return The width of the bounding box of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("bbWidth")
    public static float getBbWidth(Entity internal) {
        
        return internal.getBbWidth();
    }
    
    /**
     * Gets the height of the bounding box of the entity.
     *
     * @return The height of the bounding box of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("bbHeight")
    public static float getBbHeight(Entity internal) {
        
        return internal.getBbHeight();
    }
    
    /**
     * Gets the position of the entity.
     *
     * @return The position of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("position")
    public static Vec3 position(Entity internal) {
        
        return internal.position();
    }
    
    /**
     * Gets the {@link BlockPos position} of the entity.
     *
     * @return The block position of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("blockPosition")
    public static BlockPos blockPosition(Entity internal) {
        
        return internal.blockPosition();
    }
    
    /**
     * Gets the {@link BlockState} that this entity is currently inside.
     *
     * @return The block state that this entity is currently inside
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("inBlockState")
    public static BlockState getInBlockState(Entity internal) {
        
        return internal.getInBlockState();
    }
    
    /**
     * Gets the x coordinate of the entity.
     *
     * @return The x coordinate of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("blockX")
    public static int getBlockX(Entity internal) {
        
        return internal.getBlockX();
    }
    
    /**
     * Gets the x coordinate of the entity.
     *
     * @return The x coordinate of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("x")
    public static double getX(Entity internal) {
        
        return internal.getX();
    }
    
    /**
     * Gets the x coordinate of the entity with the given scale.
     *
     * @param scale The scale to get the x coordinate of the entity.
     *
     * @return The x coordinate of the entity.
     */
    @ZenCodeType.Method
    public static double getX(Entity internal, double scale) {
        
        return internal.getX(scale);
    }
    
    /**
     * Gets the y coordinate of the entity.
     *
     * @return The y coordinate of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("blockY")
    public static int getBlockY(Entity internal) {
        
        return internal.getBlockY();
    }
    
    /**
     * Gets the y coordinate of the entity.
     *
     * @return The y coordinate of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("y")
    public static double getY(Entity internal) {
        
        return internal.getY();
    }
    
    /**
     * Gets the y coordinate of the entity with the given scale.
     *
     * @param scale The scale to get the y coordinate of the entity.
     *
     * @return The y coordinate of the entity.
     */
    @ZenCodeType.Method
    public static double getY(Entity internal, double scale) {
        
        return internal.getY(scale);
    }
    
    /**
     * Gets the y coordinate of the entity's eyes.
     *
     * @return The y coordinate of the entity's eyes.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("eyeY")
    public static double getEyeY(Entity internal) {
        
        return internal.getEyeY();
    }
    
    /**
     * Gets the z coordinate of the entity.
     *
     * @return The z coordinate of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("blockZ")
    public static int getBlockZ(Entity internal) {
        
        return internal.getBlockZ();
    }
    
    /**
     * Gets the z coordinate of the entity.
     *
     * @return The z coordinate of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("z")
    public static double getZ(Entity internal) {
        
        return internal.getZ();
    }
    
    /**
     * Gets the z coordinate of the entity with the given scale.
     *
     * @param scale The scale to get the z coordinate of the entity.
     *
     * @return The z coordinate of the entity.
     */
    @ZenCodeType.Method
    public static double getZ(Entity internal, double scale) {
        
        return internal.getZ(scale);
    }
    
    /**
     * Sets the position of the entity to the given coordinates.
     *
     * @param x The x coordinate to set the position to.
     * @param y The y coordinate to set the position to.
     * @param z The z coordinate to set the position to.
     */
    @ZenCodeType.Method
    public static void setPosRaw(Entity internal, double x, double y, double z) {
        
        internal.setPosRaw(x, y, z);
    }
    
    /**
     * Sets whether the entity is in powder snow.
     *
     * @param inPowderSnow Whether the entity is in powder snow.
     */
    @ZenCodeType.Method
    public static void setIsInPowderSnow(Entity internal, boolean inPowderSnow) {
        
        internal.setIsInPowderSnow(inPowderSnow);
    }
    
    /**
     * Checks if the entity can freeze.
     *
     * @return Whether the entity can freeze.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("canFreeze")
    public static boolean canFreeze(Entity internal) {
        
        return internal.canFreeze();
    }
    
    /**
     * Checks if the entity has been removed.
     *
     * @return Whether the entity has been removed.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isRemoved")
    public static boolean isRemoved(Entity internal) {
        
        return internal.isRemoved();
    }
    
    /**
     * Gets the NBT data of this Entity.
     *
     * @return The NBT data of this Entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("data")
    public static MapData getData(Entity internal) {
        
        return new MapData(internal.saveWithoutId(new CompoundTag()));
    }
    
    /**
     * Updates the NBT data of this Entity.
     *
     * @param data The new Data for this Entity
     *
     * @docParam data {key: "value"}
     */
    @ZenCodeType.Method
    public static void updateData(Entity internal, MapData data) {
        
        internal.load(internal.saveWithoutId(new CompoundTag())
                .merge(data.getInternal()));
    }
    
    /**
     * Gets the custom NBT data for this Entity.
     *
     * @return The custom data for this Entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("customData")
    public static MapData getCustomData(Entity internal) {
        
        return new MapData(Services.PLATFORM.getCustomData(internal));
    }
    
    /**
     * Updates the custom NBT data for this Entity.
     *
     * @param data The custom data to store.
     *
     * @docParam data {custom: "data"}
     */
    @ZenCodeType.Method
    public static void updateCustomData(Entity internal, MapData data) {
        
        CompoundTag persistentData = Services.PLATFORM.getCustomData(internal);
        persistentData.merge(data.getInternal());
    }
    
    /**
     * Gets the delta movement of the entity.
     *
     * @return The delta movement of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("deltaMovement")
    public static Vec3 getDeltaMovement(Entity internal) {
        
        return internal.getDeltaMovement();
    }
    
    /**
     * Sets the delta movement of the entity.
     *
     * @param deltaMovement The delta movement to set.
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("deltaMovement")
    public static void setDeltaMovement(Entity internal, Vec3 deltaMovement) {
        
        internal.setDeltaMovement(deltaMovement);
    }
    
    /**
     * Sets the delta movement of the entity.
     *
     * @param xDelta The x delta movement to set.
     * @param yDelta The y delta movement to set.
     * @param zDelta The z delta movement to set.
     */
    @ZenCodeType.Method
    public static void setDeltaMovement(Entity internal, double xDelta, double yDelta, double zDelta) {
        
        internal.setDeltaMovement(xDelta, yDelta, zDelta);
    }
    
    /**
     * Gets the registry name of the entity.
     *
     * @return The registry name of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("registryName")
    public static ResourceLocation getRegistryName(Entity internal) {
        
        return BuiltInRegistries.ENTITY_TYPE.getKey(internal.getType());
    }
    
}
