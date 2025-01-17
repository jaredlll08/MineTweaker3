package com.blamejared.crafttweaker.natives.entity.effect;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/effect/MobEffectUtil")
@NativeTypeRegistration(value = MobEffectUtil.class, zenCodeName = "crafttweaker.api.entity.effect.MobEffectUtil")
public class ExpandMobEffectUtil {
    
    /**
     * Formats the duration of a {@link MobEffectInstance}.
     *
     * @param durationFactor The factor to multiply the duration by.
     * @param ticksPerSecond The number of ticks per second.
     *
     * @return The formatted duration.
     */
    @ZenCodeType.StaticExpansionMethod
    public static Component formatDuration(MobEffectInstance instance, float durationFactor, float ticksPerSecond) {
        
        return MobEffectUtil.formatDuration(instance, durationFactor, ticksPerSecond);
    }
    
    /**
     * Checks if the {@link LivingEntity} has dig speed.
     *
     * @param entity The {@link LivingEntity} to check.
     *
     * @return True if the {@link LivingEntity} has dig speed, false otherwise.
     */
    @ZenCodeType.StaticExpansionMethod
    public static boolean hasDigSpeed(LivingEntity entity) {
        
        return MobEffectUtil.hasDigSpeed(entity);
    }
    
    /**
     * Gets the dig speed amplification of the {@link LivingEntity}.
     *
     * @param entity The {@link LivingEntity} to get the dig speed amplification of.
     *
     * @return The dig speed amplification of the {@link LivingEntity}.
     */
    @ZenCodeType.StaticExpansionMethod
    public static int getDigSpeedAmplification(LivingEntity entity) {
        
        return MobEffectUtil.getDigSpeedAmplification(entity);
    }
    
    /**
     * Checks if the {@link LivingEntity} has water breathing.
     *
     * @param entity The {@link LivingEntity} to check.
     *
     * @return True if the {@link LivingEntity} has water breathing, false otherwise.
     */
    @ZenCodeType.StaticExpansionMethod
    public static boolean hasWaterBreathing(LivingEntity entity) {
        
        return MobEffectUtil.hasWaterBreathing(entity);
    }
    
}
