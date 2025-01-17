package com.blamejared.crafttweaker.natives.entity.type.animal;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/type/animal/Animal")
@NativeTypeRegistration(value = Animal.class, zenCodeName = "crafttweaker.api.entity.type.animal.Animal")
public class ExpandAnimal {
    
    /**
     * Checks if this Animal can eat the {@link ItemStack}.
     *
     * @param stack The {@link ItemStack} to check.
     *
     * @return True if this Animal can eat the {@link ItemStack}, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean isFood(Animal internal, ItemStack stack) {
        
        return internal.isFood(stack);
    }
    
    /**
     * Checks if this Animal can fall in love.
     *
     * @return True if this Animal can fall in love, false otherwise.
     */
    @ZenCodeType.Getter("canFAllInLove")
    public static boolean canFallInLove(Animal internal) {
        
        return internal.canFallInLove();
    }
    
    /**
     * Sets this Animal in love, with the given {@link Player} as the cause.
     *
     * @param loveCause The {@link Player} that caused this Animal to fall in love.
     */
    @ZenCodeType.Method
    public static void setInLove(Animal internal, @ZenCodeType.Optional @ZenCodeType.Nullable Player loveCause) {
        
        internal.setInLove(loveCause);
    }
    
    /**
     * Sets the time in ticks this Animal has been in love.
     *
     * @param inLove The time in ticks this Animal has been in love.
     */
    @ZenCodeType.Setter("inLoveTime")
    public static void setInLoveTime(Animal internal, int inLove) {
        
        internal.setInLoveTime(inLove);
    }
    
    /**
     * Gets the time in ticks this Animal has been in love.
     *
     * @return The time in ticks this Animal has been in love.
     */
    @ZenCodeType.Getter("inLoveTime")
    public static int getInLoveTime(Animal internal) {
        
        return internal.getInLoveTime();
    }
    
    /**
     * Gets the {@link ServerPlayer} that caused this Animal to fall in love.
     *
     * @return The {@link ServerPlayer} that caused this Animal to fall in love.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("loveCause")
    public static ServerPlayer getLoveCause(Animal internal) {
        
        return internal.getLoveCause();
    }
    
    /**
     * Checks if this Animal is in love.
     *
     * @return True if this Animal is in love, false otherwise.
     */
    @ZenCodeType.Getter("isInLove")
    public static boolean isInLove(Animal internal) {
        
        return internal.isInLove();
    }
    
    /**
     * Resets this Animal's love status.
     */
    @ZenCodeType.Method
    public static void resetLove(Animal internal) {
        
        internal.resetLove();
    }
    
    /**
     * Checks if this Animal can mate with the given {@link Animal}.
     *
     * @param other The {@link Animal} to check.
     *
     * @return True if this Animal can mate with the given {@link Animal}, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean canMate(Animal internal, Animal other) {
        
        return internal.canMate(other);
    }
    
    /**
     * Spawns a child from the breeding of this Animal and the given Animal.
     *
     * @param level       The {@link ServerLevel} to spawn the child in.
     * @param otherParent The other Animal to mate with.
     */
    @ZenCodeType.Method
    public static void spawnChildFromBreeding(Animal internal, ServerLevel level, Animal otherParent) {
        
        internal.spawnChildFromBreeding(level, otherParent);
    }
    
    /**
     * Finalizes the spawning of a child from the breeding of this Animal and the given Animal.
     *
     * @param level       The {@link ServerLevel} to spawn the child in.
     * @param otherParent The other Animal to mate with.
     * @param child       The {@link AgeableMob} to finalize the spawning of.
     */
    @ZenCodeType.Method
    public static void finalizeSpawnChildFromBreeding(Animal internal, ServerLevel level, Animal otherParent, @ZenCodeType.Nullable AgeableMob child) {
        
        internal.finalizeSpawnChildFromBreeding(level, otherParent, child);
    }
    
}
