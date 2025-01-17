package com.blamejared.crafttweaker.natives.entity.type.player;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker.platform.helper.inventory.IInventoryWrapper;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.openzen.zencode.java.ZenCodeType;

import java.util.OptionalInt;

@ZenRegister
@Document("vanilla/api/entity/type/player/Player")
@NativeTypeRegistration(value = Player.class, zenCodeName = "crafttweaker.api.entity.type.player.Player")
public class ExpandPlayer {
    
    /**
     * Gets the abilities of the player.
     *
     * @return The abilities of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("abilities")
    public static Abilities getAbilities(Player internal) {
        
        return internal.getAbilities();
    }
    
    /**
     * Checks if the secondary use is active, this usually means that the player is sneaking.
     *
     * @return Whether the secondary use is active.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isSecondaryUseActive")
    public static boolean isSecondaryUseActive(Player internal) {
        
        return internal.isSecondaryUseActive();
    }
    
    /**
     * Plays a sound for the player.
     *
     * @param event  The sound event to play.
     * @param source The source of the sound.
     * @param volume The volume of the sound.
     * @param pitch  The pitch of the sound.
     */
    @ZenCodeType.Method
    public static void playNotifySound(Player internal, SoundEvent event, SoundSource source, float volume, float pitch) {
        
        internal.playNotifySound(event, source, volume, pitch);
    }
    
    /**
     * Gets the score of the player.
     *
     * @return The score of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("score")
    public static int getScore(Player internal) {
        
        return internal.getScore();
    }
    
    /**
     * Sets the score of the player.
     *
     * @param score The score to set.
     */
    @ZenCodeType.Method
    public static void setScore(Player internal, int score) {
        
        internal.setScore(score);
    }
    
    /**
     * Increases the score of the player.
     *
     * @param score The score to increase.
     */
    @ZenCodeType.Method
    public static void increaseScore(Player internal, int score) {
        
        internal.increaseScore(score);
    }
    
    /**
     * Drops an item in the world as if the player dropped it.
     *
     * @param stack     The item to drop.
     * @param traceItem Whether to trace the item.
     *
     * @return The item entity that was dropped.
     */
    @ZenCodeType.Nullable
    public static ItemEntity drop(Player internal, ItemStack stack, boolean traceItem) {
        
        return internal.drop(stack, traceItem);
    }
    
    /**
     * Gets the destroy speed of the player for a given {@link BlockState}.
     *
     * @param state The block state to get the destroy speed for.
     *
     * @return The destroy speed of the player for the given block state.
     */
    @ZenCodeType.Method
    public static float getDestroySpeed(Player internal, BlockState state) {
        
        return internal.getDestroySpeed(state);
    }
    
    /**
     * Checks if the player has the correct tool for the given {@link BlockState}.
     *
     * @param state The block state to check.
     *
     * @return Whether the player has the correct tool for the given block state.
     */
    @ZenCodeType.Method
    public static boolean hasCorrectToolForDrops(Player internal, BlockState state) {
        
        return internal.hasCorrectToolForDrops(state);
    }
    
    /**
     * Checks if the player can harm another player.
     *
     * This does not check if pvp is enabled, it checks if players are on the same scoreboard team.
     *
     * @param player The player to check.
     *
     * @return Whether the player can harm the other player.
     */
    @ZenCodeType.Method
    public static boolean canHarmPlayer(Player internal, Player player) {
        
        return internal.canHarmPlayer(player);
    }
    
    /**
     * Attacks an entity.
     *
     * @param entity The entity to attack.
     */
    @ZenCodeType.Method
    public static void attack(Player internal, Entity entity) {
        
        internal.attack(entity);
    }
    
    /**
     * Disables the shield of the player.
     */
    @ZenCodeType.Method
    public static void disableShield(Player internal) {
        
        internal.disableShield();
    }
    
    /**
     * Spawns critical hit particle at the hit entity.
     *
     * @param entity The entity to hit.
     */
    @ZenCodeType.Method
    public static void crit(Player internal, Entity entity) {
        
        internal.crit(entity);
    }
    
    /**
     * Spawns magic critical hit particle at the hit entity.
     *
     * @param entity The entity to hit.
     */
    @ZenCodeType.Method
    public static void magicCrit(Player internal, Entity entity) {
        
        internal.magicCrit(entity);
    }
    
    /**
     * Spawns a sweep attack particle.
     */
    @ZenCodeType.Method
    public static void sweepAttack(Player internal) {
        
        internal.sweepAttack();
    }
    
    /**
     * Respawns the player.
     */
    @ZenCodeType.Method
    public static void respawn(Player internal) {
        
        internal.respawn();
    }
    
    /**
     * Checks if the player is the local player.
     *
     * @return Whether the player is the local player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isLocalPlayer")
    public static boolean isLocalPlayer(Player internal) {
        
        return internal.isLocalPlayer();
    }
    
    /**
     * Gets the inventory of the player.
     *
     * @return The inventory of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("inventory")
    public static Inventory getInventory(Player internal) {
        
        return internal.getInventory();
    }
    
    /**
     * Wakes up the player if they are sleeping
     */
    @ZenCodeType.Method
    public static void stopSleeping(Player internal) {
        
        internal.stopSleeping();
    }
    
    /**
     * Checks if the player has been in bed long enough to trigger sleeping.
     *
     * @return Whether the player has been sleeping long enough.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isSleepingLongEnough")
    public static boolean isSleepingLongEnough(Player internal) {
        
        return internal.isSleepingLongEnough();
    }
    
    /**
     * Gets the sleep timer of the player.
     *
     * @return The sleep timer of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("sleepTimer")
    public static int getSleepTimer(Player internal) {
        
        return internal.getSleepTimer();
    }
    
    /**
     * Displays a message to the client.
     *
     * @param component The message to display.
     * @param actionBar Whether to display the message in the action bar.
     */
    @ZenCodeType.Method
    public static void displayClientMessage(Player internal, Component component, boolean actionBar) {
        
        internal.displayClientMessage(component, actionBar);
    }
    
    /**
     * Awards a stat to the player.
     *
     * @param stat The stat to award.
     */
    @ZenCodeType.Method
    public static void awardStat(Player internal, ResourceLocation stat) {
        
        internal.awardStat(stat);
    }
    
    /**
     * Awards a stat to the player.
     *
     * @param stat   The stat to award.
     * @param amount The amount to award.
     */
    @ZenCodeType.Method
    public static void awardStat(Player internal, ResourceLocation stat, int amount) {
        
        internal.awardStat(stat, amount);
    }
    
    /**
     * Makes the player jump from the ground.
     */
    @ZenCodeType.Method
    public static void jumpFromGround(Player internal) {
        
        internal.jumpFromGround();
    }
    
    /**
     * Gives the player experience points.
     *
     * @param amount The amount of experience to give.
     */
    @ZenCodeType.Method
    public static void giveExperiencePoints(Player internal, int amount) {
        
        internal.giveExperiencePoints(amount);
    }
    
    /**
     * Gets the enchantment seed of the player.
     *
     * @return The enchantment seed of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("enchantmentSeed")
    public static int getEnchantmentSeed(Player internal) {
        
        return internal.getEnchantmentSeed();
    }
    
    /**
     * Gives the player experience levels.
     *
     * @param levels The amount of experience levels to give.
     */
    @ZenCodeType.Method
    public static void giveExperienceLevels(Player internal, int levels) {
        
        internal.giveExperienceLevels(levels);
    }
    
    /**
     * Gets the amount of experience needed to reach the next level.
     *
     * @return The amount of experience needed to reach the next level.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("xpNeededForNextLevel")
    public static int getXpNeededForNextLevel(Player internal) {
        
        return internal.getXpNeededForNextLevel();
    }
    
    /**
     * Gets the experience level of the player.
     *
     * @return The experience level of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("experienceLevel")
    public static int getExperienceLevel(Player internal) {
        
        return internal.experienceLevel;
    }
    
    /**
     * Sets the experience level of the player.
     *
     * @param level The experience level to set.
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("experienceLevel")
    public static void setExperienceLevel(Player internal, int level) {
        
        internal.experienceLevel = level;
    }
    
    /**
     * Adds food exhaustion to the player.
     *
     * @param exhaustion The amount of exhaustion to add.
     */
    @ZenCodeType.Method
    public static void causeFoodExhaustion(Player internal, float exhaustion) {
        
        internal.causeFoodExhaustion(exhaustion);
    }
    
    /**
     * Gets the food data of the player.
     *
     * @return The food data of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("foodData")
    public static FoodData getFoodData(Player internal) {
        
        return internal.getFoodData();
    }
    
    /**
     * Checks if the player can eat.
     *
     * @param ignoreHunger Whether to ignore hunger.
     *
     * @return Whether the player can eat.
     */
    @ZenCodeType.Method
    public static boolean canEat(Player internal, boolean ignoreHunger) {
        
        return internal.canEat(ignoreHunger);
    }
    
    /**
     * Checks if the player is hurt.
     *
     * @return Whether the player is hurt.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isHurt")
    public static boolean isHurt(Player internal) {
        
        return internal.isHurt();
    }
    
    /**
     * Checks if the player may build.
     *
     * @return Whether the player may build.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("mayBuild")
    public static boolean mayBuild(Player internal) {
        
        return internal.mayBuild();
    }
    
    /**
     * Adds an item to the player's inventory.
     *
     * @param stack The item to add.
     *
     * @return Whether the item was added.
     */
    @ZenCodeType.Method
    public static boolean addItem(Player internal, ItemStack stack) {
        
        return internal.addItem(stack);
    }
    
    /**
     * Sets an entity on the player's shoulder.
     *
     * @param entityData The entity data to set.
     *
     * @return Whether the entity was set.
     */
    @ZenCodeType.Method
    public static boolean setEntityOnShoulder(Player internal, MapData entityData) {
        
        return internal.setEntityOnShoulder(entityData.getInternal());
    }
    
    /**
     * Checks if the player is creative.
     *
     * @return Whether the player is creative.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isCreative")
    public static boolean isCreative(Player internal) {
        
        return internal.isCreative();
    }
    
    /**
     * Checks if the player has reduced debug info.
     *
     * @return Whether the player has reduced debug info.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isReducedDebugInfo")
    public static boolean isReducedDebugInfo(Player internal) {
        
        return internal.isReducedDebugInfo();
    }
    
    /**
     * Sets if the player has reduced debug info.
     *
     * @param reducedDebugInfo Whether the player has reduced debug info.
     */
    @ZenCodeType.Method
    public static void setReducedDebugInfo(Player internal, boolean reducedDebugInfo) {
        
        internal.setReducedDebugInfo(reducedDebugInfo);
    }
    
    /**
     * Gets the main arm of the player.
     *
     * @return The main arm of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("mainArm")
    public static HumanoidArm getMainArm(Player internal) {
        
        return internal.getMainArm();
    }
    
    /**
     * Sets the main arm of the player.
     *
     * @param arm The arm to set.
     */
    @ZenCodeType.Method
    public static void setMainArm(Player internal, HumanoidArm arm) {
        
        internal.setMainArm(arm);
    }
    
    /**
     * Gets the entity on the player's left shoulder.
     *
     * @return The entity on the player's left shoulder.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("shoulderEntityLeft")
    public static MapData getShoulderEntityLeft(Player internal) {
        
        return new MapData(internal.getShoulderEntityLeft());
    }
    
    /**
     * Gets the entity on the player's right shoulder.
     *
     * @return The entity on the player's right shoulder.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("shoulderEntityRight")
    public static MapData getShoulderEntityRight(Player internal) {
        
        return new MapData(internal.getShoulderEntityRight());
    }
    
    /**
     * Gets the current item attack strength delay of the player.
     *
     * @return The current item attack strength delay of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("currentItemAttackStrengthDelay")
    public static float getCurrentItemAttackStrengthDelay(Player internal) {
        
        return internal.getCurrentItemAttackStrengthDelay();
    }
    
    /**
     * Gets the cooldowns of the player.
     *
     * @return The cooldowns of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("cooldowns")
    public static ItemCooldowns getCooldowns(Player internal) {
        
        return internal.getCooldowns();
    }
    
    /**
     * Gets the luck of the player.
     *
     * @return The luck of the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("luck")
    public static float getLuck(Player internal) {
        
        return internal.getLuck();
    }
    
    /**
     * Checks if the player can use game master blocks such as command blocks.
     *
     * @return Whether the player can use game master blocks.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("canUseGameMasterBlocks")
    public static boolean canUseGameMasterBlocks(Player internal) {
        
        return internal.canUseGameMasterBlocks();
    }
    
    /**
     * Checks if the player is scoping like when using a SpyGlass.
     *
     * @return Whether the player is scoping.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isScoping")
    public static boolean isScoping(Player internal) {
        
        return internal.isScoping();
    }
    
    /**
     * Gives the player an item.
     *
     * @param stack The item to give.
     * @param slot  The slot to give the item to.
     */
    @ZenCodeType.Method
    public static void give(Player internal, IItemStack stack, @ZenCodeType.OptionalInt(-1) int slot) {
        
        if(stack.isEmpty()) {
            return;
        }
        
        IInventoryWrapper inventory = Services.PLATFORM.getPlayerInventory(internal);
        Level level = internal.level();
        // How much *wasn't* inserted?
        ItemStack leftOvers = inventory.insertItem(slot, stack.getInternal(), false);
        OptionalInt availableSlot = inventory.getSlotFor(leftOvers);
        
        // While there is an available slot, and items still to insert, insert them
        while(availableSlot.isPresent() && !leftOvers.isEmpty()) {
            leftOvers = inventory.insertItem(availableSlot.getAsInt(), leftOvers, false);
            availableSlot = inventory.getSlotFor(leftOvers);
        }
        
        if(leftOvers.isEmpty() || leftOvers.getCount() != stack.getInternal().getCount()) {
            // Magic numbers from AdvancementRewards
            level.playSound(null, internal.getX(), internal.getY(), internal.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.2F, ((level.random.nextFloat() - level.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        }
        
        // Drop into the world
        if(!leftOvers.isEmpty() && !level.isClientSide()) {
            ItemEntity itemEntity = internal.drop(leftOvers, true, true);
            if(itemEntity != null) {
                itemEntity.setNoPickUpDelay();
            }
        }
    }
    
    /**
     * Sends a message to the player.
     *
     * @param text The message to send.
     */
    @ZenCodeType.Method
    public static void sendMessage(Player internal, Component text) {
        
        internal.sendSystemMessage(text);
    }
    
    /**
     * Checks if this player is a fake player, mainly used for machines.
     *
     * @return true if this is a fake player, false otherwise.
     */
    @ZenCodeType.Getter("isFakePlayer")
    public static boolean isFakePlayer(Player internal) {
        
        return Services.PLATFORM.isFakePlayer(internal);
    }
    
    
}
