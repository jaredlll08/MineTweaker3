package com.blamejared.crafttweaker.natives.entity;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ZenRegister
@Document("vanilla/api/entity/LivingEntity")
@NativeTypeRegistration(value = LivingEntity.class, zenCodeName = "crafttweaker.api.entity.LivingEntity")
public class ExpandLivingEntity {
    
    /**
     * Gets this entity's held items.
     *
     * @return An iterable of item stacks representing the held items.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("handSlots")
    public static Iterable<ItemStack> getHandSlots(LivingEntity internal) {
        
        return internal.getHandSlots();
    }
    
    /**
     * Gets this entity's armor items.
     *
     * @return An iterable of item stacks representing the armor items.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("armorSlots")
    public static Iterable<ItemStack> getArmorSlots(LivingEntity internal) {
        
        return internal.getArmorSlots();
    }
    
    /**
     * Gets all slots.
     *
     * @return An iterable of item stacks representing all slots.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("allSlots")
    public static Iterable<ItemStack> getAllSlots(LivingEntity internal) {
        
        return internal.getAllSlots();
    }
    
    /**
     * Sets the item in a specific equipment slot.
     *
     * @param slot  The equipment slot to set the item in.
     * @param stack The item stack to set in the slot.
     */
    @ZenCodeType.Method
    public static void setItemSlot(LivingEntity internal, EquipmentSlot slot, ItemStack stack) {
        
        internal.setItemSlot(slot, stack);
    }
    
    /**
     * Checks if this entity can breathe underwater.
     *
     * @return True if the entity can breathe underwater, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("canBreatheUnderwater")
    public static boolean canBreatheUnderwater(LivingEntity internal) {
        
        return internal.canBreatheUnderwater();
    }
    
    /**
     * Gets the swim amount.
     *
     * @param partialTicks The partial ticks to get the swim amount for.
     *
     * @return The swim amount.
     */
    @ZenCodeType.Method
    public static float getSwimAmount(LivingEntity internal, float partialTicks) {
        
        return internal.getSwimAmount(partialTicks);
    }
    
    /**
     * Checks if this entity is a baby.
     *
     * @return True if the entity is a baby, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isBaby")
    public static boolean isBaby(LivingEntity internal) {
        
        return internal.isBaby();
    }
    
    /**
     * Gets this entity's scale.
     *
     * @return The scale of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("scale")
    public static float getScale(LivingEntity internal) {
        
        return internal.getScale();
    }
    
    /**
     * Gets this entity's random source.
     *
     * @return The random source for the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("random")
    public static RandomSource getRandom(LivingEntity internal) {
        
        return internal.getRandom();
    }
    
    /**
     * Gets the last entity that hurt this entity.
     *
     * @return The last entity that hurt this entity.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    @ZenCodeType.Getter("lastHurtByMob")
    public static LivingEntity getLastHurtByMob(LivingEntity internal) {
        
        return internal.getLastHurtByMob();
    }
    
    /**
     * Gets the timestamp when this entity was last hurt by a mob.
     *
     * @return The timestamp when this entity was last hurt by a mob.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("lastHurtByMobTimestamp")
    public static int getLastHurtByMobTimestamp(LivingEntity internal) {
        
        return internal.getLastHurtByMobTimestamp();
    }
    
    /**
     * Sets the last entity that hurt this entity.
     *
     * @param player The player that hurt this entity.
     */
    @ZenCodeType.Method
    public static void setLastHurtByPlayer(LivingEntity internal, @ZenCodeType.Nullable Player player) {
        
        internal.setLastHurtByPlayer(player);
    }
    
    /**
     * Sets the last entity that hurt this entity.
     *
     * @param entity The entity that hurt this entity.
     */
    @ZenCodeType.Method
    public static void setLastHurtByMob(LivingEntity internal, @ZenCodeType.Nullable LivingEntity entity) {
        
        internal.setLastHurtByMob(entity);
    }
    
    /**
     * Gets the last entity that this entity hurt.
     *
     * @return The last entity that this entity hurt.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    @ZenCodeType.Getter("lastHurtMob")
    public static LivingEntity getLastHurtMob(LivingEntity internal) {
        
        return internal.getLastHurtMob();
    }
    
    /**
     * Gets the timestamp when this entity last hurt a mob.
     *
     * @return The timestamp when this entity last hurt a mob.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("lastHurtMobTimestamp")
    public static int getLastHurtMobTimestamp(LivingEntity internal) {
        
        return internal.getLastHurtMobTimestamp();
    }
    
    /**
     * Sets the last entity that this entity hurt.
     *
     * @param entity The entity that this entity hurt.
     */
    @ZenCodeType.Method
    public static void setLastHurtMob(LivingEntity internal, Entity entity) {
        
        internal.setLastHurtMob(entity);
    }
    
    /**
     * Gets the time since this entity last performed an action.
     *
     * @return The time since this entity last performed an action.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("noActionTime")
    public static int getNoActionTime(LivingEntity internal) {
        
        return internal.getNoActionTime();
    }
    
    /**
     * Sets the time since this entity last performed an action.
     *
     * @param idleTime The time since this entity last performed an action.
     */
    @ZenCodeType.Method
    public static void setNoActionTime(LivingEntity internal, int idleTime) {
        
        internal.setNoActionTime(idleTime);
    }
    
    /**
     * Checks if this entity should discard friction.
     *
     * @return True if this entity should discard friction, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("shouldDiscardFriction")
    public static boolean shouldDiscardFriction(LivingEntity internal) {
        
        return internal.shouldDiscardFriction();
    }
    
    /**
     * Sets if this entity should discard friction.
     *
     * @param discardFriction True if this entity should discard friction, false otherwise.
     */
    @ZenCodeType.Method
    public static void setDiscardFriction(LivingEntity internal, boolean discardFriction) {
        
        internal.setDiscardFriction(discardFriction);
    }
    
    /**
     * Gets the visibility percent for this entity.
     *
     * @param lookingEntity The entity that is looking at this entity.
     *
     * @return The visibility percent.
     */
    @ZenCodeType.Method
    public static double getVisibilityPercent(LivingEntity internal, @ZenCodeType.Nullable Entity lookingEntity) {
        
        return internal.getVisibilityPercent(lookingEntity);
    }
    
    /**
     * Checks if this entity can attack a target.
     *
     * @param target The target to check if this entity can attack.
     *
     * @return True if this entity can attack the target, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean canAttack(LivingEntity internal, LivingEntity target) {
        
        return internal.canAttack(target);
    }
    
    /**
     * Checks if this entity can be seen as an enemy.
     * Some things this checks are if this entity is a player and it is in creative mode, or if this entity is an axolotl if it is playing dead.
     *
     * @return True if this entity can be seen as an enemy, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("canBeSeenAsEnemy")
    public static boolean canBeSeenAsEnemy(LivingEntity internal) {
        
        return internal.canBeSeenAsEnemy();
    }
    
    /**
     * Checks if this entity can be seen by anyone.
     *
     * @return True if this entity can be seen by anyone, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("canBeSeenByAnyone")
    public static boolean canBeSeenByAnyone(LivingEntity internal) {
        
        return internal.canBeSeenByAnyone();
    }
    
    /**
     * Removes all effects from this entity.
     *
     * @return True if all effects were removed, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean removeAllEffects(LivingEntity internal) {
        
        return internal.removeAllEffects();
    }
    
    /**
     * Gets the active effects.
     *
     * @return The active effects.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("activeEffects")
    public static Collection<MobEffectInstance> getActiveEffects(LivingEntity internal) {
        
        return internal.getActiveEffects();
    }
    
    /**
     * Gets a map of active effects.
     *
     * @return The active effects map.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("activeEffectsMap")
    public static Map<MobEffect, MobEffectInstance> getActiveEffectsMap(LivingEntity internal) {
        
        return internal.getActiveEffectsMap()
                .entrySet()
                .stream()
                .map(holderMobEffectInstanceEntry -> Map.entry(holderMobEffectInstanceEntry.getKey()
                        .value(), holderMobEffectInstanceEntry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    
    /**
     * Checks if this entity has a specific effect.
     *
     * @param effect The effect to check if this entity has.
     *
     * @return True if this entity has the effect, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean hasEffect(LivingEntity internal, MobEffect effect) {
        
        return internal.hasEffect(Services.REGISTRY.holderOrThrow(Registries.MOB_EFFECT, effect));
    }
    
    /**
     * Gets the {@link MobEffectInstance} for the given effect on this entity.
     *
     * @param effect The effect to get.
     *
     * @return The effect instance.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    public static MobEffectInstance getEffect(LivingEntity internal, MobEffect effect) {
        
        return internal.getEffect(Services.REGISTRY.holderOrThrow(Registries.MOB_EFFECT, effect));
    }
    
    /**
     * Adds an effect to this entity.
     *
     * @param effectInstance The effect to add.
     *
     * @return True if the effect was added, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean addEffect(LivingEntity internal, MobEffectInstance effectInstance) {
        
        return internal.addEffect(effectInstance);
    }
    
    /**
     * Adds an effect to this entity.
     *
     * @param effectInstance The effect to add.
     * @param entity         The entity that is the source of the effect.
     *
     * @return True if the effect was added, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean addEffect(LivingEntity internal, MobEffectInstance effectInstance, @ZenCodeType.Nullable Entity entity) {
        
        return internal.addEffect(effectInstance, entity);
    }
    
    /**
     * Checks if this entity can be affected by a specific effect.
     *
     * @param effectInstance The effect to check if this entity can be affected by.
     *
     * @return True if this entity can be affected by the effect, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean canBeAffected(LivingEntity internal, MobEffectInstance effectInstance) {
        
        return internal.canBeAffected(effectInstance);
    }
    
    /**
     * Forces this entity to have a specific effect, this does not force effects that can't affect this entity.
     *
     * @param effectInstance The effect to force this entity to have.
     * @param entity         The entity that is the source of the effect.
     */
    @ZenCodeType.Method
    public static void forceAddEffect(LivingEntity internal, MobEffectInstance effectInstance, @ZenCodeType.Nullable Entity entity) {
        
        internal.forceAddEffect(effectInstance, entity);
    }
    
    /**
     * Checks if this entity is inverted for heal and harm.
     * This usually happens for undead mobs where potions of healing with harm them.
     *
     * @return True if this entity is inverted for heal and harm, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isInvertedHealAndHarm")
    public static boolean isInvertedHealAndHarm(LivingEntity internal) {
        
        return internal.isInvertedHealAndHarm();
    }
    
    /**
     * Removes an effect from this entity without causing an update.
     *
     * @param effect The effect to remove.
     *
     * @return The removed effect.
     */
    @ZenCodeType.Method
    public static MobEffectInstance removeEffectNoUpdate(LivingEntity internal, MobEffect effect) {
        
        return internal.removeEffectNoUpdate(Services.REGISTRY.holderOrThrow(Registries.MOB_EFFECT, effect));
    }
    
    /**
     * Removes an effect from this entity.
     *
     * @param effect The effect to remove.
     *
     * @return True if the effect was removed, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean removeEffect(LivingEntity internal, MobEffect effect) {
        
        return internal.removeEffect(Services.REGISTRY.holderOrThrow(Registries.MOB_EFFECT, effect));
    }
    
    /**
     * Heals this entity.
     *
     * @param amount The amount to heal.
     */
    @ZenCodeType.Method
    public static void heal(LivingEntity internal, float amount) {
        
        internal.heal(amount);
    }
    
    /**
     * Gets the health of this entity.
     *
     * @return The health of this entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("health")
    public static float getHealth(LivingEntity internal) {
        
        return internal.getHealth();
    }
    
    /**
     * Sets the health of this entity.
     *
     * @param health The health to set.
     */
    @ZenCodeType.Method
    public static void setHealth(LivingEntity internal, float health) {
        
        internal.setHealth(health);
    }
    
    /**
     * Checks if this entity is dead or dying.
     *
     * @return True if this entity is dead or dying, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isDeadOrDying")
    public static boolean isDeadOrDying(LivingEntity internal) {
        
        return internal.isDeadOrDying();
    }
    
    /**
     * Gets the last damage source for this entity.
     *
     * @return The last damage source.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    @ZenCodeType.Getter("lastDamageSource")
    public static DamageSource getLastDamageSource(LivingEntity internal) {
        
        return internal.getLastDamageSource();
    }
    
    /**
     * Determines whether the entity can block the damage source based on the damage source's location, whether the damage source is blockable, and whether the entity is blocking.
     *
     * @param source The damage source to check.
     *
     * @return True if the entity can block the damage source, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean isDamageSourceBlocked(LivingEntity internal, DamageSource source) {
        
        return internal.isDamageSourceBlocked(source);
    }
    
    /**
     * Kills this entity.
     *
     * @param source The damage source that killed this entity.
     */
    @ZenCodeType.Method
    public static void die(LivingEntity internal, DamageSource source) {
        
        internal.die(source);
    }
    
    /**
     * Gets the loot table for this entity.
     *
     * @return The loot table.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("lootTable")
    public static ResourceLocation getLootTable(LivingEntity internal) {
        
        return internal.getLootTable().location();
    }
    
    /**
     * Gets the seed used when generating loot from this entity.
     *
     * @return The seed used to generate loot.
     */
    @ZenCodeType.Getter("lootTableSeed")
    public static long getLootTableSeed(LivingEntity internal) {
        
        return internal.getLootTableSeed();
    }
    
    /**
     * Knocks back this entity.
     *
     * @param x The x coordinate to knock back to.
     * @param y The y coordinate to knock back to.
     * @param z The z coordinate to knock back to.
     */
    @ZenCodeType.Method
    public static void knockback(LivingEntity internal, double x, double y, double z) {
        
        internal.knockback(x, y, z);
    }
    
    /**
     * Gets the last climbable position for this entity.
     *
     * @return The last climbable position.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    @ZenCodeType.Getter("lastClimbablePos")
    public static BlockPos getLastClimbablePos(LivingEntity internal) {
        
        return internal.getLastClimbablePos().orElse(null);
    }
    
    /**
     * Gets the armor value for this entity.
     *
     * @return The armor value.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("armorValue")
    public static int getArmorValue(LivingEntity internal) {
        
        return internal.getArmorValue();
    }
    
    /**
     * Gets the kill credit for this entity.
     * If this entity was last hit by a player, then it will be that player, otherwise it will be the last mob that hit this entity.
     *
     * @return The kill credit.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    @ZenCodeType.Getter("killCredit")
    public static LivingEntity getKillCredit(LivingEntity internal) {
        
        return internal.getKillCredit();
    }
    
    /**
     * Gets the maximum health for this entity.
     *
     * @return The maximum health.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("maxHealth")
    public static float getMaxHealth(LivingEntity internal) {
        
        return internal.getMaxHealth();
    }
    
    /**
     * Gets how many arrows are currently in this entity.
     *
     * @return The arrow count.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("arrowCount")
    public static int getArrowCount(LivingEntity internal) {
        
        return internal.getArrowCount();
    }
    
    /**
     * Sets how many arrows are currently in this entity.
     *
     * @param count The number of arrows to set.
     */
    @ZenCodeType.Method
    public static void setArrowCount(LivingEntity internal, int count) {
        
        internal.setArrowCount(count);
    }
    
    /**
     * Gets how many stingers are currently in this entity.
     *
     * @return The stinger count.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("stingerCount")
    public static int getStingerCount(LivingEntity internal) {
        
        return internal.getStingerCount();
    }
    
    /**
     * Sets how many stingers are currently in this entity.
     *
     * @param count The number of stingers to set.
     */
    @ZenCodeType.Method
    public static void setStingerCount(LivingEntity internal, int count) {
        
        internal.setStingerCount(count);
    }
    
    /**
     * Swings the entity's main hand.
     *
     * @param hand The hand to swing.
     */
    @ZenCodeType.Method
    public static void swing(LivingEntity internal, InteractionHand hand) {
        
        internal.swing(hand);
    }
    
    /**
     * Swings the entity's main hand.
     *
     * @param hand       The hand to swing.
     * @param updateSelf Whether to update the entity's self.
     */
    @ZenCodeType.Method
    public static void swing(LivingEntity internal, InteractionHand hand, boolean updateSelf) {
        
        internal.swing(hand, updateSelf);
    }
    
    /**
     * Gets the attribute for this entity.
     *
     * @param attribute The attribute to get.
     *
     * @return The attribute.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    public static AttributeInstance getAttribute(LivingEntity internal, Attribute attribute) {
        
        return internal.getAttribute(Services.REGISTRY.holderOrThrow(Registries.ATTRIBUTE, attribute));
    }
    
    /**
     * Gets the attribute value for this entity.
     *
     * @param attribute The attribute to get the value of.
     *
     * @return The attribute value.
     */
    @ZenCodeType.Method
    public static double getAttributeValue(LivingEntity internal, Attribute attribute) {
        
        return internal.getAttributeValue(Services.REGISTRY.holderOrThrow(Registries.ATTRIBUTE, attribute));
    }
    
    /**
     * Gets the base attribute value for this entity.
     *
     * @param attribute The attribute to get the base value of.
     *
     * @return The base attribute value.
     */
    @ZenCodeType.Method
    public static double getAttributeBaseValue(LivingEntity internal, Attribute attribute) {
        
        return internal.getAttributeBaseValue(Services.REGISTRY.holderOrThrow(Registries.ATTRIBUTE, attribute));
    }
    
    /**
     * Gets the main hand item for this entity.
     *
     * @return The main hand item.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("mainHandItem")
    public static ItemStack getMainHandItem(LivingEntity internal) {
        
        return internal.getMainHandItem();
    }
    
    /**
     * Gets the offhand item for this entity.
     *
     * @return The offhand item.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("offHandItem")
    public static ItemStack getOffhandItem(LivingEntity internal) {
        
        return internal.getOffhandItem();
    }
    
    /**
     * Checks if the entity is holding an item in their hand.
     *
     * @param item The item to check.
     *
     * @return True if the entity is holding the item, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean isHolding(LivingEntity internal, Item item) {
        
        return internal.isHolding(item);
    }
    
    /**
     * Checks if the entity is holding an item in their hand.
     *
     * @param predicate The predicate to check.
     *
     * @return True if the entity is holding the item, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean isHolding(LivingEntity internal, Predicate<ItemStack> predicate) {
        
        return internal.isHolding(predicate);
    }
    
    /**
     * Gets the item in the entity's hand.
     *
     * @param hand The hand to get the item from.
     *
     * @return The item in the hand.
     */
    @ZenCodeType.Method
    public static ItemStack getItemInHand(LivingEntity internal, InteractionHand hand) {
        
        return internal.getItemInHand(hand);
    }
    
    /**
     * Sets the item in the entity's hand.
     *
     * @param hand  The hand to set the item in.
     * @param stack The item to set.
     */
    @ZenCodeType.Method
    public static void setItemInHand(LivingEntity internal, InteractionHand hand, ItemStack stack) {
        
        internal.setItemInHand(hand, stack);
    }
    
    /**
     * Checks if the entity has an item in a specific slot.
     *
     * @param slot The slot to check.
     *
     * @return True if the entity has an item in the slot, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean hasItemInSlot(LivingEntity internal, EquipmentSlot slot) {
        
        return internal.hasItemInSlot(slot);
    }
    
    /**
     * Gets the item in a specific slot.
     *
     * @param slot The slot to get the item from.
     *
     * @return The item in the slot.
     */
    @ZenCodeType.Method
    public static ItemStack getItemBySlot(LivingEntity internal, EquipmentSlot slot) {
        
        return internal.getItemBySlot(slot);
    }
    
    /**
     * Gets the armor cover percentage for this entity.
     *
     * This is a value from 0 to 1 that represents the percentage of the entity's body that is covered by armor.
     *
     * @return The armor cover percentage.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("armorCoverPercentage")
    public static float getArmorCoverPercentage(LivingEntity internal) {
        
        return internal.getArmorCoverPercentage();
    }
    
    /**
     * Gets the voice pitch for this entity.
     *
     * @return The voice pitch.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("voicePitch")
    public static float getVoicePitch(LivingEntity internal) {
        
        return internal.getVoicePitch();
    }
    
    /**
     * Gets the jump boost power for this entity.
     *
     * @return The jump boost power.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("jumpBoostPower")
    public static double getJumpBoostPower(LivingEntity internal) {
        
        return internal.getJumpBoostPower();
    }
    
    /**
     * Checks if the entity can stand on a specific fluid.
     *
     * @param fluid The fluid to check.
     *
     * @return True if the entity can stand on the fluid, false otherwise.
     */
    //TODO When we have FluidState, make this use FluidState
    @ZenCodeType.Method
    public static boolean canStandOnFluid(LivingEntity internal, Fluid fluid) {
        
        return internal.canStandOnFluid(fluid.defaultFluidState());
    }
    
    /**
     * Moves this entity towards the given location.
     *
     * @param vec The direction to move in.
     */
    @ZenCodeType.Method
    public static void travel(LivingEntity internal, Vec3 vec) {
        
        internal.travel(vec);
    }
    
    /**
     * Gets the movement speed of this entity.
     *
     * @return The movement speed of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("speed")
    public static float getSpeed(LivingEntity internal) {
        
        return internal.getSpeed();
    }
    
    /**
     * Sets the movement speed of this entity.
     *
     * @param speed The movement speed to set.
     */
    @ZenCodeType.Method
    public static void setSpeed(LivingEntity internal, float speed) {
        
        internal.setSpeed(speed);
    }
    
    @ZenCodeType.Method
    public static boolean doHurtTarget(LivingEntity internal, Entity entity) {
        
        return internal.doHurtTarget(entity);
    }
    
    /**
     * Checks if this entity is sensitive to water, meaning that it will take damage from being in water.
     *
     * @return True if the entity is sensitive to water, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isSensitiveToWater")
    public static boolean isSensitiveToWater(LivingEntity internal) {
        
        return internal.isSensitiveToWater();
    }
    
    /**
     * Checks if this entity is performing an auto spin attack.
     *
     * @return True if the entity is performing an auto spin attack, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isAutoSpinAttack")
    public static boolean isAutoSpinAttack(LivingEntity internal) {
        
        return internal.isAutoSpinAttack();
    }
    
    /**
     * Sets whether this entity is jumping.
     *
     * @param jumping True if the entity should jump, false otherwise.
     */
    @ZenCodeType.Method
    public static void setJumping(LivingEntity internal, boolean jumping) {
        
        internal.setJumping(jumping);
    }
    
    /**
     * Checks if this entity has a line of sight to a specific entity.
     *
     * @param entity The entity to check the line of sight to.
     *
     * @return True if the entity has a line of sight to the entity, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean hasLineOfSight(LivingEntity internal, Entity entity) {
        
        return internal.hasLineOfSight(entity);
    }
    
    /**
     * Gets the absorption amount for this entity.
     *
     * @return The absorption amount.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("absorptionAmount")
    public static float getAbsorptionAmount(LivingEntity internal) {
        
        return internal.getAbsorptionAmount();
    }
    
    /**
     * Sets the absorption amount for this entity.
     *
     * @param absorption The absorption amount to set.
     */
    @ZenCodeType.Method
    public static void setAbsorptionAmount(LivingEntity internal, float absorption) {
        
        internal.setAbsorptionAmount(absorption);
    }
    
    /**
     * Gets the main arm for this entity.
     *
     * @return The main arm.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("mainArm")
    public static HumanoidArm getMainArm(LivingEntity internal) {
        
        return internal.getMainArm();
    }
    
    /**
     * Checks if this entity is using an item.
     *
     * @return True if the entity is using an item, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isUsingItem")
    public static boolean isUsingItem(LivingEntity internal) {
        
        return internal.isUsingItem();
    }
    
    /**
     * Gets the hand that the entity is using an item with.
     *
     * @return The hand that the entity is using an item with.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("usedItemHand")
    public static InteractionHand getUsedItemHand(LivingEntity internal) {
        
        return internal.getUsedItemHand();
    }
    
    /**
     * Starts using an item.
     *
     * @param hand The hand to use the item with.
     */
    @ZenCodeType.Method
    public static void startUsingItem(LivingEntity internal, InteractionHand hand) {
        
        internal.startUsingItem(hand);
    }
    
    /**
     * Gets the item that the entity is using.
     *
     * @return The item that the entity is using.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("useItem")
    public static ItemStack getUseItem(LivingEntity internal) {
        
        return internal.getUseItem();
    }
    
    /**
     * Gets the remaining ticks left of the use duration of the item that is being used.
     *
     * @return The remaining ticks for the item that the entity is using.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("useItemRemainingTicks")
    public static int getUseItemRemainingTicks(LivingEntity internal) {
        
        return internal.getUseItemRemainingTicks();
    }
    
    /**
     * Gets how long the entity has been using an item.
     *
     * @return The ticks that the entity has been using an item.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("ticksUsingItem")
    public static int getTicksUsingItem(LivingEntity internal) {
        
        return internal.getTicksUsingItem();
    }
    
    /**
     * Releases the item that the entity is using.
     */
    @ZenCodeType.Method
    public static void releaseUsingItem(LivingEntity internal) {
        
        internal.releaseUsingItem();
    }
    
    /**
     * Stops using an item.
     */
    @ZenCodeType.Method
    public static void stopUsingItem(LivingEntity internal) {
        
        internal.stopUsingItem();
    }
    
    /**
     * Checks if the entity is blocking.
     *
     * @return True if the entity is blocking, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isBlocking")
    public static boolean isBlocking(LivingEntity internal) {
        
        return internal.isBlocking();
    }
    
    /**
     * Checks if the entity is suppressing sliding down a ladder, such as sneaking while on a ladder.
     *
     * @return True if the entity is suppressing sliding down a ladder, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isSuppressingSlidingDownLadder")
    public static boolean isSuppressingSlidingDownLadder(LivingEntity internal) {
        
        return internal.isSuppressingSlidingDownLadder();
    }
    
    /**
     * Checks if the entity is flying with an elytra.
     *
     * @return True if the entity is flying with an elytra, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isFallFlying")
    public static boolean isFallFlying(LivingEntity internal) {
        
        return internal.isFallFlying();
    }
    
    /**
     * Checks if the entity is visually swimming.
     *
     * @return True if the entity is visually swimming, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isVisuallySwimming")
    public static boolean isVisuallySwimming(LivingEntity internal) {
        
        return internal.isVisuallySwimming();
    }
    
    /**
     * Gets the ticks that the entity has been flying with an elytra.
     *
     * @return The ticks that the entity has been flying with an elytra.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("fallFlyingTicks")
    public static int getFallFlyingTicks(LivingEntity internal) {
        
        return internal.getFallFlyingTicks();
    }
    
    /**
     * Checks if the entity is affected by potions, entities such as armor stands are not affected by potions.
     *
     * @return True if the entity is affected by potions, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isAffectedByPotions")
    public static boolean isAffectedByPotions(LivingEntity internal) {
        
        return internal.isAffectedByPotions();
    }
    
    /**
     * Checks if the entity is attackable.
     *
     * @return True if the entity is attackable, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("attackable")
    public static boolean attackable(LivingEntity internal) {
        
        return internal.attackable();
    }
    
    /**
     * Checks if the item can be worn as armor.
     *
     * @param stack The item to check.
     *
     * @return True if the item can be worn as armor, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean canTakeItem(LivingEntity internal, ItemStack stack) {
        
        return internal.canTakeItem(stack);
    }
    
    /**
     * Gets the position that the entity is sleeping at.
     *
     * @return The position that the entity is sleeping at.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    @ZenCodeType.Getter("sleepingPos")
    public static BlockPos getSleepingPos(LivingEntity internal) {
        
        return internal.getSleepingPos().orElse(null);
    }
    
    /**
     * Sets the position that the entity is sleeping at.
     *
     * @param pos The position to set the entity to sleep at.
     */
    @ZenCodeType.Method
    public static void setSleepingPos(LivingEntity internal, BlockPos pos) {
        
        internal.setSleepingPos(pos);
    }
    
    /**
     * Clears the position that the entity is sleeping at.
     */
    @ZenCodeType.Method
    public static void clearSleepingPos(LivingEntity internal) {
        
        internal.clearSleepingPos();
    }
    
    /**
     * Checks if the entity is sleeping.
     *
     * @return True if the entity is sleeping, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isSleeping")
    public static boolean isSleeping(LivingEntity internal) {
        
        return internal.isSleeping();
    }
    
    /**
     * Starts the entity to sleep at a specific position.
     *
     * @param pos The position to set the entity to sleep at.
     */
    @ZenCodeType.Method
    public static void startSleeping(LivingEntity internal, BlockPos pos) {
        
        internal.startSleeping(pos);
    }
    
    /**
     * Stops the entity from sleeping.
     */
    @ZenCodeType.Method
    public static void stopSleeping(LivingEntity internal) {
        
        internal.stopSleeping();
    }
    
    /**
     * Gets the orientation of the bed that the entity is sleeping on.
     *
     * @return The orientation of the bed that the entity is sleeping on.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    @ZenCodeType.Getter("bedOrientation")
    public static Direction getBedOrientation(LivingEntity internal) {
        
        return internal.getBedOrientation();
    }
    
    /**
     * Eats an item.
     *
     * @param level The level that the entity is in.
     * @param stack The item to eat.
     *
     * @return The item that the entity ate.
     */
    @ZenCodeType.Method
    public static ItemStack eat(LivingEntity internal, Level level, ItemStack stack) {
        
        return internal.eat(level, stack);
    }
    
    /**
     * Checks if the entity is currently glowing.
     *
     * @return True if the entity is currently glowing, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isCurrentlyGlowing")
    public static boolean isCurrentlyGlowing(LivingEntity internal) {
        
        return internal.isCurrentlyGlowing();
    }
    
}
