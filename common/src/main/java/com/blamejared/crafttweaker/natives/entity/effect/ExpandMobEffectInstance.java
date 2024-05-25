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

@ZenRegister
@Document("vanilla/api/entity/effect/MobEffectInstance")
@NativeTypeRegistration(value = MobEffectInstance.class, zenCodeName = "crafttweaker.api.entity.effect.MobEffectInstance")
public class ExpandMobEffectInstance {
    
    @ZenCodeType.StaticExpansionMethod
    public static MobEffectInstance of(MobEffect mobEffect, @ZenCodeType.OptionalInt int duration, @ZenCodeType.OptionalInt int amplifier, @ZenCodeType.OptionalBoolean boolean ambient, @ZenCodeType.OptionalBoolean(true) boolean visible, @ZenCodeType.OptionalBoolean(true) boolean showIcon, @ZenCodeType.Optional @ZenCodeType.Nullable MobEffectInstance hiddenEffect) {
        
        return new MobEffectInstance(Services.REGISTRY.holderOrThrow(Registries.MOB_EFFECT, mobEffect), duration, amplifier, ambient, visible, showIcon, hiddenEffect);
    }
    
    @ZenCodeType.Method
    public static boolean update(MobEffectInstance internal, MobEffectInstance instance) {
        
        return internal.update(instance);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("effect")
    public static MobEffect getEffect(MobEffectInstance internal) {
        
        return internal.getEffect().value();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("duration")
    public static int getDuration(MobEffectInstance internal) {
        
        return internal.getDuration();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("amplifier")
    public static int getAmplifier(MobEffectInstance internal) {
        
        return internal.getAmplifier();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("ambient")
    public static boolean isAmbient(MobEffectInstance internal) {
        
        return internal.isAmbient();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("visible")
    public static boolean isVisible(MobEffectInstance internal) {
        
        return internal.isVisible();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("showIcon")
    public static boolean showIcon(MobEffectInstance internal) {
        
        return internal.showIcon();
    }
    
    @ZenCodeType.Method
    public static boolean tick(MobEffectInstance internal, LivingEntity entity, @ZenCodeType.Optional("null") @ZenCodeType.Nullable Runnable onFinish) {
        
        if(onFinish == null) {
            onFinish = () -> {};
        }
        return internal.tick(entity, onFinish);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("descriptionId")
    public static String getDescriptionId(MobEffectInstance internal) {
        
        return internal.getDescriptionId();
    }
    
    @ZenCodeType.Method
    public static MapData save(MobEffectInstance internal) {
        
        Tag save = internal.save();
        if(save instanceof CompoundTag ct) {
            return new MapData(ct);
        }
        throw new IllegalStateException("MobEffectInstance#save returned something other than a CompoundTag! " + save);
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static MobEffectInstance load(MapData data) {
        
        return MobEffectInstance.load(data.getInternal());
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.COMPARE)
    public static int compareTo(MobEffectInstance internal, MobEffectInstance other) {
        
        return internal.compareTo(other);
    }
    
}
