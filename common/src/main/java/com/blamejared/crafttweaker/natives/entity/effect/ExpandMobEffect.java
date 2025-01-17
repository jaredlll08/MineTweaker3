package com.blamejared.crafttweaker.natives.entity.effect;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.blamejared.crafttweaker_annotations.annotations.TaggableElement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.openzen.zencode.java.ZenCodeType;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@ZenRegister
@Document("vanilla/api/entity/effect/MobEffect")
@NativeTypeRegistration(value = MobEffect.class, zenCodeName = "crafttweaker.api.entity.effect.MobEffect")
@TaggableElement("minecraft:mob_effect")
public class ExpandMobEffect {
    
    /**
     * Applies the effect to the entity.
     *
     * @param entity    The entity to apply the effect to.
     * @param amplifier The amplifier of the effect.
     */
    @ZenCodeType.Method
    public static void applyEffectTick(MobEffect internal, LivingEntity entity, int amplifier) {
        
        internal.applyEffectTick(entity, amplifier);
    }
    
    /**
     * Applies the effect to the entity.
     *
     * @param source         The source of the effect.
     * @param indirectSource The indirect source of the effect.
     * @param target         The target of the effect.
     * @param amplifier      The amplifier of the effect.
     * @param effectiveness  The effectiveness of the effect.
     */
    @ZenCodeType.Method
    public static void applyInstantenousEffect(MobEffect internal, @Nullable Entity source, @Nullable Entity indirectSource, LivingEntity target, int amplifier, double effectiveness) {
        
        internal.applyInstantenousEffect(source, indirectSource, target, amplifier, effectiveness);
    }
    
    /**
     * Checks if the effect should be applied this tick.
     *
     * @param duration  The duration of the effect.
     * @param amplifier The amplifier of the effect.
     *
     * @return True if the effect should be applied this tick, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean shouldApplyEffectTickThisTick(MobEffect internal, int duration, int amplifier) {
        
        return internal.shouldApplyEffectTickThisTick(duration, amplifier);
    }
    
    /**
     * Checks if the effect is instantenous.
     *
     * @return True if the effect is instantenous, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("instantenous")
    public static boolean isInstantenous(MobEffect internal) {
        
        return internal.isInstantenous();
    }
    
    /**
     * Gets the description ID of the effect.
     *
     * @return The description ID of the effect.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("descriptionId")
    public static String getDescriptionId(MobEffect internal) {
        
        return internal.getDescriptionId();
    }
    
    /**
     * Gets the display name of the effect.
     *
     * @return The display name of the effect.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("displayName")
    public static Component getDisplayName(MobEffect internal) {
        
        return internal.getDisplayName();
    }
    
    /**
     * Gets the {@link MobEffectCategory} of the effect.
     *
     * @return The {@link MobEffectCategory} of the effect.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("category")
    public static MobEffectCategory getCategory(MobEffect internal) {
        
        return internal.getCategory();
    }
    
    /**
     * Gets the color of the effect.
     *
     * @return The color of the effect.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("color")
    public static int getColor(MobEffect internal) {
        
        return internal.getColor();
    }
    
    /**
     * Adds an attribute modifier to the effect.
     *
     * @param attribute The attribute to add the modifier to.
     * @param id        The ID of the modifier.
     * @param value     The value of the modifier.
     * @param operation The operation to apply to the modifier.
     *
     * @return The effect with the added modifier.
     */
    @ZenCodeType.Method
    public static MobEffect addAttributeModifier(MobEffect internal, Attribute attribute, ResourceLocation id, double value, AttributeModifier.Operation operation) {
        
        return internal.addAttributeModifier(Services.REGISTRY.holderOrThrow(Registries.ATTRIBUTE, attribute), id, value, operation);
    }
    
    /**
     * Gets the {@link AttributeModifier} that this effect adds with the given amplifier.
     *
     * @param amplifier The amplifier of the effect.
     *
     * @return The {@link AttributeModifier} that this effect adds with the given amplifier.
     */
    @ZenCodeType.Method
    public static Map<Attribute, AttributeModifier> getAttributeModifiers(MobEffect internal, int amplifier) {
        
        Map<Attribute, AttributeModifier> attributes = new HashMap<>();
        internal.createModifiers(amplifier, (attributeHolder, attributeModifier) -> attributes.put(attributeHolder.value(), attributeModifier));
        return attributes;
    }
    
    //TODO when we have attributemap
    
    //    @ZenCodeType.Method
    //    public static void removeAttributeModifiers(MobEffect internal, LivingEntity entity, AttributeMap attributes, int amplifier) {
    //
    //        internal.removeAttributeModifiers(entity, attributes, amplifier);
    //    }
    //
    //    @ZenCodeType.Method
    //    public static void addAttributeModifiers(MobEffect internal, LivingEntity entity, AttributeMap map, int amplifier) {
    //
    //        internal.addAttributeModifiers(entity, map, amplifier);
    //    }
    
    /**
     * Checks if the effect is beneficial.
     *
     * @return True if the effect is beneficial, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("beneficial")
    public static boolean isBeneficial(MobEffect internal) {
        
        return internal.isBeneficial();
    }
    
    /**
     * Gets the registry name of the effect.
     *
     * @return The registry name of the effect.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("registryName")
    public static ResourceLocation getRegistryName(MobEffect internal) {
        
        return BuiltInRegistries.MOB_EFFECT.getKey(internal);
    }
    
    /**
     * Gets the command string of the effect.
     *
     * @return The command string of the effect.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(MobEffect internal) {
        
        return "<mobeffect:" + BuiltInRegistries.MOB_EFFECT.getKey(internal) + ">";
    }
    
}
