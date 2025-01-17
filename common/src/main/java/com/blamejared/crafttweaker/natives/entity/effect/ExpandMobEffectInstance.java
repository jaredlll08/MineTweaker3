package com.blamejared.crafttweaker.natives.entity.effect;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.openzen.zencode.java.ZenCodeType;

/**
 * A MobEffectInstance is a wrapper around a {@link MobEffect} that has a duration, amplifier, and other properties.
 */
@ZenRegister
@Document("vanilla/api/entity/effect/MobEffectInstance")
@NativeTypeRegistration(value = MobEffectInstance.class, zenCodeName = "crafttweaker.api.entity.effect.MobEffectInstance")
public class ExpandMobEffectInstance {
    
    /**
     * Creates a new {@link MobEffectInstance}.
     *
     * @param mobEffect    The {@link MobEffect} to create the instance for.
     * @param duration     The duration of the effect.
     * @param amplifier    The amplifier of the effect.
     * @param ambient      Whether the effect is ambient.
     * @param visible      Whether the effect is visible.
     * @param showIcon     Whether the effect should show an icon.
     * @param hiddenEffect The hidden effect of the effect.
     *
     * @return A new {@link MobEffectInstance}.
     */
    @ZenCodeType.StaticExpansionMethod
    public static MobEffectInstance of(MobEffect mobEffect, @ZenCodeType.OptionalInt int duration, @ZenCodeType.OptionalInt int amplifier, @ZenCodeType.OptionalBoolean boolean ambient, @ZenCodeType.OptionalBoolean(true) boolean visible, @ZenCodeType.OptionalBoolean(true) boolean showIcon, @ZenCodeType.Optional @ZenCodeType.Nullable MobEffectInstance hiddenEffect) {
        
        return new MobEffectInstance(Services.REGISTRY.holderOrThrow(Registries.MOB_EFFECT, mobEffect), duration, amplifier, ambient, visible, showIcon, hiddenEffect);
    }
    
    /**
     * Updates the {@link MobEffectInstance} with the given {@link MobEffectInstance}.
     *
     * @param instance The {@link MobEffectInstance} to update with.
     *
     * @return True if the {@link MobEffectInstance} was updated, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean update(MobEffectInstance internal, MobEffectInstance instance) {
        
        return internal.update(instance);
    }
    
    /**
     * Gets the {@link MobEffect} of the {@link MobEffectInstance}.
     *
     * @return The {@link MobEffect} of the {@link MobEffectInstance}.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("effect")
    public static MobEffect getEffect(MobEffectInstance internal) {
        
        return internal.getEffect().value();
    }
    
    /**
     * Gets the duration of the {@link MobEffectInstance}.
     *
     * @return The duration of the {@link MobEffectInstance}.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("duration")
    public static int getDuration(MobEffectInstance internal) {
        
        return internal.getDuration();
    }
    
    /**
     * Gets the amplifier of the {@link MobEffectInstance}.
     *
     * @return The amplifier of the {@link MobEffectInstance}.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("amplifier")
    public static int getAmplifier(MobEffectInstance internal) {
        
        return internal.getAmplifier();
    }
    
    /**
     * Gets whether the {@link MobEffectInstance} is ambient.
     *
     * @return True if the {@link MobEffectInstance} is ambient, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("ambient")
    public static boolean isAmbient(MobEffectInstance internal) {
        
        return internal.isAmbient();
    }
    
    /**
     * Gets whether the {@link MobEffectInstance} is visible.
     *
     * @return True if the {@link MobEffectInstance} is visible, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("visible")
    public static boolean isVisible(MobEffectInstance internal) {
        
        return internal.isVisible();
    }
    
    /**
     * Gets whether the {@link MobEffectInstance} should show an icon.
     *
     * @return True if the {@link MobEffectInstance} should show an icon, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("showIcon")
    public static boolean showIcon(MobEffectInstance internal) {
        
        return internal.showIcon();
    }
    
    /**
     * Ticks the {@link MobEffectInstance} for the given {@link LivingEntity}.
     *
     * @param entity   The {@link LivingEntity} to tick the {@link MobEffectInstance} for.
     * @param onFinish The {@link Runnable} to run when the {@link MobEffectInstance} finishes.
     *
     * @return True if the {@link MobEffectInstance} was ticked, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean tick(MobEffectInstance internal, LivingEntity entity, @ZenCodeType.Optional("null") @ZenCodeType.Nullable Runnable onFinish) {
        
        if(onFinish == null) {
            onFinish = () -> {};
        }
        return internal.tick(entity, onFinish);
    }
    
    /**
     * Gets the description ID of the {@link MobEffectInstance}.
     *
     * @return The description ID of the {@link MobEffectInstance}.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("descriptionId")
    public static String getDescriptionId(MobEffectInstance internal) {
        
        return internal.getDescriptionId();
    }
    
    /**
     * Saves the {@link MobEffectInstance} to a {@link MapData}.
     *
     * @return The {@link MapData} representation of the {@link MobEffectInstance}.
     */
    @ZenCodeType.Method
    public static MapData save(MobEffectInstance internal) {
        
        Tag save = internal.save();
        if(save instanceof CompoundTag ct) {
            return new MapData(ct);
        }
        throw new IllegalStateException("MobEffectInstance#save returned something other than a CompoundTag! " + save);
    }
    
    /**
     * Loads a {@link MobEffectInstance} from a {@link MapData}.
     *
     * @param data The {@link MapData} to load the {@link MobEffectInstance} from.
     *
     * @return The {@link MobEffectInstance} loaded from the {@link MapData}.
     */
    @ZenCodeType.StaticExpansionMethod
    public static MobEffectInstance load(MapData data) {
        
        return MobEffectInstance.load(data.getInternal());
    }
    
    /**
     * Compares the {@link MobEffectInstance} to another {@link MobEffectInstance}.
     *
     * @param internal The {@link MobEffectInstance} to compare.
     * @param other    The {@link MobEffectInstance} to compare to.
     *
     * @return The result of the comparison.
     */
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.COMPARE)
    public static int compareTo(MobEffectInstance internal, MobEffectInstance other) {
        
        return internal.compareTo(other);
    }
    
}
