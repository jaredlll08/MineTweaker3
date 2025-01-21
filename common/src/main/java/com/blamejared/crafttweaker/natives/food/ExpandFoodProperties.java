package com.blamejared.crafttweaker.natives.food;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents the properties of a food item.
 *
 * @docParam this FoodProperties.create(1, 1.0, true, 1.0)
 */
@ZenRegister
@Document("vanilla/api/food/FoodProperties")
@NativeTypeRegistration(value = FoodProperties.class, zenCodeName = "crafttweaker.api.food.FoodProperties")
public class ExpandFoodProperties {
    
    /**
     * Creates a new food properties.
     *
     * @param nutrition    The nutrition value.
     * @param saturation   The saturation value.
     * @param canAlwaysEat Whether the food can always be eaten.
     * @param eatSeconds   The number of seconds it takes to eat the food.
     *
     * @return The new food properties.
     */
    @ZenCodeType.StaticExpansionMethod
    public static FoodProperties create(int nutrition, float saturation, boolean canAlwaysEat, float eatSeconds) {
        
        return create(nutrition, saturation, canAlwaysEat, eatSeconds, IItemStack.empty());
    }
    
    /**
     * Creates a new food properties.
     *
     * @param nutrition       The nutrition value.
     * @param saturation      The saturation value.
     * @param canAlwaysEat    Whether the food can always be eaten.
     * @param eatSeconds      The number of seconds it takes to eat the food.
     * @param usingConvertsTo The item that the food converts to when eaten.
     *
     * @return The new food properties.
     */
    @ZenCodeType.StaticExpansionMethod
    public static FoodProperties create(int nutrition, float saturation, boolean canAlwaysEat, float eatSeconds, IItemStack usingConvertsTo) {
        
        return create(nutrition, saturation, canAlwaysEat, eatSeconds, usingConvertsTo, List.of());
    }
    
    /**
     * Creates a new food properties.
     *
     * @param nutrition       The nutrition value.
     * @param saturation      The saturation value.
     * @param canAlwaysEat    Whether the food can always be eaten.
     * @param eatSeconds      The number of seconds it takes to eat the food.
     * @param usingConvertsTo The item that the food converts to when eaten.
     * @param effects         The effects that the food applies when eaten.
     *
     * @return The new food properties.
     */
    @ZenCodeType.StaticExpansionMethod
    public static FoodProperties create(int nutrition, float saturation, boolean canAlwaysEat, float eatSeconds, IItemStack usingConvertsTo, List<FoodProperties.PossibleEffect> effects) {
        
        return new FoodProperties(nutrition, saturation, canAlwaysEat, eatSeconds, usingConvertsTo.isEmpty() ? Optional.empty() : Optional.of(usingConvertsTo.getInternal()), effects);
    }
    
    /**
     * Gets the nutrition value of the food properties.
     *
     * @return The nutrition value.
     */
    @ZenCodeType.Getter("nutrition")
    public static int nutrition(FoodProperties internal) {
        
        return internal.nutrition();
    }
    
    /**
     * Creates a new food properties based on this instance, with the given nutrition value.
     *
     * @param nutrition The new nutrition value.
     *
     * @return The new food properties.
     */
    @ZenCodeType.Method
    public static FoodProperties withNutrition(FoodProperties internal, int nutrition) {
        
        return new FoodProperties(nutrition, internal.saturation(), internal.canAlwaysEat(), internal.eatSeconds(), internal.usingConvertsTo(), internal.effects());
    }
    
    /**
     * Gets the saturation modifier of the food properties.
     *
     * @return The saturation modifier.
     */
    @ZenCodeType.Getter("saturationModifier")
    public static float saturation(FoodProperties internal) {
        
        return internal.saturation();
    }
    
    /**
     * Creates a new food properties based on this instance, with the given saturation modifier.
     *
     * @param saturation The new saturation modifier.
     *
     * @return The new food properties.
     */
    @ZenCodeType.Method
    public static FoodProperties withSaturation(FoodProperties internal, float saturation) {
        
        return new FoodProperties(internal.nutrition(), saturation, internal.canAlwaysEat(), internal.eatSeconds(), internal.usingConvertsTo(), internal.effects());
    }
    
    /**
     * Gets whether the food can always be eaten.
     *
     * @return Whether the food can always be eaten.
     */
    @ZenCodeType.Getter("canAlwaysEat")
    public static boolean canAlwaysEat(FoodProperties internal) {
        
        return internal.canAlwaysEat();
    }
    
    /**
     * Creates a new food properties based on this instance, with the given can always eat value.
     *
     * @param canAlwaysEat The new can always eat value.
     *
     * @return The new food properties.
     */
    @ZenCodeType.Method
    public static FoodProperties withCanAlwaysEat(FoodProperties internal, boolean canAlwaysEat) {
        
        return new FoodProperties(internal.nutrition(), internal.saturation(), canAlwaysEat, internal.eatSeconds(), internal.usingConvertsTo(), internal.effects());
    }
    
    /**
     * Gets the number of seconds it takes to eat the food.
     *
     * @return The number of seconds it takes to eat the food.
     */
    @ZenCodeType.Getter("eatSeconds")
    public static float eatSeconds(FoodProperties internal) {
        
        return internal.eatSeconds();
    }
    
    /**
     * Creates a new food properties based on this instance, with the given eat seconds value.
     *
     * @param eatSeconds The new eat seconds value.
     *
     * @return The new food properties.
     */
    @ZenCodeType.Method
    public static FoodProperties withEatSeconds(FoodProperties internal, int eatSeconds) {
        
        return new FoodProperties(internal.nutrition(), internal.saturation(), internal.canAlwaysEat(), eatSeconds, internal.usingConvertsTo(), internal.effects());
    }
    
    /**
     * Gets the item that the food converts to when eaten.
     *
     * @return The item that the food converts to when eaten.
     */
    @ZenCodeType.Getter("usingConvertsTo")
    public static IItemStack usingConvertsTo(FoodProperties internal) {
        
        return internal.usingConvertsTo().map(IItemStack::of).orElseGet(IItemStack::empty);
    }
    
    /**
     * Creates a new food properties based on this instance, with the given using converts to value.
     *
     * @param usingConvertsTo The new using converts to value.
     *
     * @return The new food properties.
     */
    @ZenCodeType.Method
    public static FoodProperties withUsingConvertsTo(FoodProperties internal, IItemStack usingConvertsTo) {
        
        return new FoodProperties(internal.nutrition(), internal.saturation(), internal.canAlwaysEat(), internal.eatSeconds(), usingConvertsTo.isEmpty() ? Optional.empty() : Optional.of(usingConvertsTo.getInternal()), internal.effects());
    }
    
    /**
     * Gets the effects of the food properties.
     *
     * @return The effects of the food properties.
     */
    @ZenCodeType.Getter("effects")
    public static List<FoodProperties.PossibleEffect> getEffects(FoodProperties internal) {
        
        return internal.effects();
    }
    
    /**
     * Creates a new food properties based on this instance, with the given effect.
     *
     * @param effect      The effect to add.
     * @param probability The probability of the effect.
     *
     * @return The new food properties.
     */
    @ZenCodeType.Method
    public static FoodProperties withEffect(FoodProperties internal, MobEffectInstance effect, float probability) {
        
        ArrayList<FoodProperties.PossibleEffect> possibleEffects = new ArrayList<>(internal.effects());
        possibleEffects.add(Services.PLATFORM.createPossibleEffect(effect, probability));
        return new FoodProperties(internal.nutrition(), internal.saturation(), internal.canAlwaysEat(), internal.eatSeconds(), internal.usingConvertsTo(), possibleEffects);
    }
    
    /**
     * Creates a new food properties based on this instance, with the given effect.
     *
     * @param effect The effect to add.
     *
     * @return The new food properties.
     */
    @ZenCodeType.Method
    public static FoodProperties withEffect(FoodProperties internal, FoodProperties.PossibleEffect effect) {
        
        ArrayList<FoodProperties.PossibleEffect> possibleEffects = new ArrayList<>(internal.effects());
        possibleEffects.add(effect);
        return new FoodProperties(internal.nutrition(), internal.saturation(), internal.canAlwaysEat(), internal.eatSeconds(), internal.usingConvertsTo(), possibleEffects);
    }
    
    /**
     * Creates a new food properties based on this instance, with the given effects.
     *
     * @param effects The new effects.
     *
     * @return The new food properties.
     */
    @ZenCodeType.Method
    public static FoodProperties withEffects(FoodProperties internal, List<FoodProperties.PossibleEffect> effects) {
        
        return new FoodProperties(internal.nutrition(), internal.saturation(), internal.canAlwaysEat(), internal.eatSeconds(), internal.usingConvertsTo(), effects);
    }
    
    /**
     * Creates a new food properties based on this instance, without the given effect.
     *
     * @param effect The effect to remove.
     *
     * @return The new food properties.
     */
    @ZenCodeType.Method
    public static FoodProperties withoutEffect(FoodProperties internal, MobEffect effect) {
        
        return new FoodProperties(internal.nutrition(), internal.saturation(), internal.canAlwaysEat(), internal.eatSeconds(), internal.usingConvertsTo(), internal.effects()
                .stream()
                .filter(possibleEffect -> possibleEffect.effect().getEffect().value() != effect)
                .toList());
    }
    
    /**
     * Creates a new food properties based on this instance, without the given effect.
     *
     * @param effect The effect to remove.
     *
     * @return The new food properties.
     */
    @ZenCodeType.Method
    public static FoodProperties withoutEffect(FoodProperties internal, FoodProperties.PossibleEffect effect) {
        
        return new FoodProperties(internal.nutrition(), internal.saturation(), internal.canAlwaysEat(), internal.eatSeconds(), internal.usingConvertsTo(), internal.effects()
                .stream()
                .filter(possibleEffect -> !possibleEffect.equals(effect))
                .toList());
    }
    
    /**
     * A builder to help create new food properties.
     *
     * @docParam this FoodProperties.Builder.of()
     */
    @ZenRegister
    @Document("vanilla/api/food/FoodPropertiesBuilder")
    @NativeTypeRegistration(value = FoodProperties.Builder.class, zenCodeName = "crafttweaker.api.food.FoodPropertiesBuilder")
    public static class ExpandFoodPropertiesBuilder {
        
        /**
         * Creates a new food properties builder.
         *
         * @return The new food properties builder.
         */
        @ZenCodeType.StaticExpansionMethod
        public static FoodProperties.Builder of() {
            
            return new FoodProperties.Builder();
        }
        
        /**
         * Sets the nutrition value of the food properties.
         *
         * @param nutrition The new nutrition value.
         *
         * @return this builder for chaining.
         */
        @ZenCodeType.Method
        public static FoodProperties.Builder nutrition(FoodProperties.Builder internal, int nutrition) {
            
            return internal.nutrition(nutrition);
        }
        
        /**
         * Sets the fast eat value of the food properties.
         *
         * @return this builder for chaining.
         */
        @ZenCodeType.Method
        public static FoodProperties.Builder fast(FoodProperties.Builder internal) {
            
            return internal.fast();
        }
        
        /**
         * Adds an effect to the food properties.
         *
         * @param effect      The effect to add.
         * @param probability The probability of the effect.
         *
         * @return this builder for chaining.
         */
        @ZenCodeType.Method
        public static FoodProperties.Builder effect(FoodProperties.Builder internal, MobEffectInstance effect, float probability) {
            
            return internal.effect(effect, probability);
        }
        
        /**
         * Sets the saturation modifier of the food properties.
         *
         * @param saturationModifier The new saturation modifier.
         *
         * @return this builder for chaining.
         */
        @ZenCodeType.Method
        public static FoodProperties.Builder saturationModifier(FoodProperties.Builder internal, float saturationModifier) {
            
            return internal.saturationModifier(saturationModifier);
        }
        
        /**
         * Builds the food properties.
         *
         * @return The new food properties.
         */
        @ZenCodeType.Method
        @ZenCodeType.Caster(implicit = true)
        public static FoodProperties build(FoodProperties.Builder internal) {
            
            return internal.build();
        }
        
        /**
         * Sets the always edible value of the food properties.
         *
         * @return this builder for chaining.
         */
        @ZenCodeType.Method
        public static FoodProperties.Builder alwaysEdible(FoodProperties.Builder internal) {
            
            return internal.alwaysEdible();
        }
        
    }
    
    /**
     * Represents a possible effect of the food properties.
     */
    @ZenRegister
    @Document("vanilla/api/food/FoodPropertiesPossibleEffect")
    @NativeTypeRegistration(value = FoodProperties.PossibleEffect.class, zenCodeName = "crafttweaker.api.food.FoodPropertiesPossibleEffect")
    public static class ExpandFoodPropertiesPossibleEffect {
        
        /**
         * Creates a new food properties possible effect.
         *
         * @param effect      The effect.
         * @param probability The probability.
         *
         * @return The new food properties possible effect.
         */
        @ZenCodeType.StaticExpansionMethod
        public static FoodProperties.PossibleEffect of(MobEffectInstance effect, float probability) {
            
            return Services.PLATFORM.createPossibleEffect(effect, probability);
        }
        
        /**
         * Gets the effect of the food properties possible effect.
         *
         * @return The effect.
         */
        @ZenCodeType.Getter("effect")
        public static MobEffectInstance effect(FoodProperties.PossibleEffect internal) {
            
            return internal.effect();
        }
        
        /**
         * Gets the probability of the food properties possible effect.
         *
         * @return The probability.
         */
        @ZenCodeType.Getter("probability")
        public static float probability(FoodProperties.PossibleEffect internal) {
            
            return internal.probability();
        }
        
    }
    
}
