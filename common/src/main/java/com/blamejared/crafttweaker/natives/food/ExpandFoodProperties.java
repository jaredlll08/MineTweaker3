package com.blamejared.crafttweaker.natives.food;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@Document("vanilla/api/food/FoodProperties")
@NativeTypeRegistration(value = FoodProperties.class, zenCodeName = "crafttweaker.api.food.FoodProperties")
public class ExpandFoodProperties {
    
    @ZenCodeType.StaticExpansionMethod
    public static FoodProperties create(int nutrition, float saturation, boolean canAlwaysEat, float eatSeconds) {
        
        return create(nutrition, saturation, canAlwaysEat, eatSeconds, List.of());
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static FoodProperties create(int nutrition, float saturation, boolean canAlwaysEat, float eatSeconds, List<FoodProperties.PossibleEffect> effects) {
        
        return new FoodProperties(nutrition, saturation, canAlwaysEat, eatSeconds, effects);
    }
    
    @ZenCodeType.Getter("nutrition")
    public static int nutrition(FoodProperties internal) {
        
        return internal.nutrition();
    }
    
    @ZenCodeType.Method
    public static FoodProperties withNutrition(FoodProperties internal, int nutrition) {
        
        return new FoodProperties(nutrition, internal.saturation(), internal.canAlwaysEat(), internal.eatSeconds(), internal.effects());
    }
    
    @ZenCodeType.Getter("saturationModifier")
    public static float saturation(FoodProperties internal) {
        
        return internal.saturation();
    }
    
    @ZenCodeType.Method
    public static FoodProperties withSaturation(FoodProperties internal, float saturation) {
        
        return new FoodProperties(internal.nutrition(), saturation, internal.canAlwaysEat(), internal.eatSeconds(), internal.effects());
    }
    
    @ZenCodeType.Getter("canAlwaysEat")
    public static boolean canAlwaysEat(FoodProperties internal) {
        
        return internal.canAlwaysEat();
    }
    
    @ZenCodeType.Method
    public static FoodProperties withCanAlwaysEat(FoodProperties internal, boolean canAlwaysEat) {
        
        return new FoodProperties(internal.nutrition(), internal.saturation(), canAlwaysEat, internal.eatSeconds(), internal.effects());
    }
    
    @ZenCodeType.Getter("eatSeconds")
    public static float eatSeconds(FoodProperties internal) {
        
        return internal.eatSeconds();
    }
    
    @ZenCodeType.Method
    public static FoodProperties withEatSeconds(FoodProperties internal, int eatSeconds) {
        
        return new FoodProperties(internal.nutrition(), internal.saturation(), internal.canAlwaysEat(), eatSeconds, internal.effects());
    }
    
    @ZenCodeType.Getter("effects")
    public static List<FoodProperties.PossibleEffect> getEffects(FoodProperties internal) {
        
        return internal.effects();
    }
    
    @ZenCodeType.Method
    public static FoodProperties withEffect(FoodProperties internal, MobEffectInstance effect, float probability) {
        
        ArrayList<FoodProperties.PossibleEffect> possibleEffects = new ArrayList<>(internal.effects());
        possibleEffects.add(new FoodProperties.PossibleEffect(effect, probability));
        return new FoodProperties(internal.nutrition(), internal.saturation(), internal.canAlwaysEat(), internal.eatSeconds(), possibleEffects);
    }
    
    @ZenCodeType.Method
    public static FoodProperties withEffect(FoodProperties internal, FoodProperties.PossibleEffect effect) {
        
        ArrayList<FoodProperties.PossibleEffect> possibleEffects = new ArrayList<>(internal.effects());
        possibleEffects.add(effect);
        return new FoodProperties(internal.nutrition(), internal.saturation(), internal.canAlwaysEat(), internal.eatSeconds(), possibleEffects);
    }
    
    @ZenCodeType.Method
    public static FoodProperties withEffects(FoodProperties internal, List<FoodProperties.PossibleEffect> effects) {
        
        return new FoodProperties(internal.nutrition(), internal.saturation(), internal.canAlwaysEat(), internal.eatSeconds(), effects);
    }
    
    @ZenCodeType.Method
    public static FoodProperties withoutEffect(FoodProperties internal, MobEffect effect) {
        
        return new FoodProperties(internal.nutrition(), internal.saturation(), internal.canAlwaysEat(), internal.eatSeconds(), internal.effects()
                .stream()
                .filter(possibleEffect -> possibleEffect.effect().getEffect().value() != effect)
                .toList());
    }
    
    @ZenCodeType.Method
    public static FoodProperties withoutEffect(FoodProperties internal, FoodProperties.PossibleEffect effect) {
        
        return new FoodProperties(internal.nutrition(), internal.saturation(), internal.canAlwaysEat(), internal.eatSeconds(), internal.effects()
                .stream()
                .filter(possibleEffect -> !possibleEffect.equals(effect))
                .toList());
    }
    
    @ZenRegister
    @Document("vanilla/api/food/FoodPropertiesBuilder")
    @NativeTypeRegistration(value = FoodProperties.Builder.class, zenCodeName = "crafttweaker.api.food.FoodPropertiesBuilder")
    public static class ExpandFoodPropertiesBuilder {
        
        @ZenCodeType.StaticExpansionMethod
        public static FoodProperties.Builder of() {
            
            return new FoodProperties.Builder();
        }
        
        @ZenCodeType.Method
        public static FoodProperties.Builder nutrition(FoodProperties.Builder internal, int nutrition) {
            
            return internal.nutrition(nutrition);
        }
        
        @ZenCodeType.Method
        public static FoodProperties.Builder fast(FoodProperties.Builder internal) {
            
            return internal.fast();
        }
        
        @ZenCodeType.Method
        public static FoodProperties.Builder effect(FoodProperties.Builder internal, MobEffectInstance effect, float probability) {
            
            return internal.effect(effect, probability);
        }
        
        @ZenCodeType.Method
        public static FoodProperties.Builder saturationModifier(FoodProperties.Builder internal, float saturationModifier) {
            
            return internal.saturationModifier(saturationModifier);
        }
        
        @ZenCodeType.Method
        @ZenCodeType.Caster(implicit = true)
        public static FoodProperties build(FoodProperties.Builder internal) {
            
            return internal.build();
        }
        
        @ZenCodeType.Method
        public static FoodProperties.Builder alwaysEdible(FoodProperties.Builder internal) {
            
            return internal.alwaysEdible();
        }
        
    }
    
    @ZenRegister
    @Document("vanilla/api/food/FoodPropertiesPossibleEffect")
    @NativeTypeRegistration(value = FoodProperties.PossibleEffect.class, zenCodeName = "crafttweaker.api.food.FoodPropertiesPossibleEffect")
    public static class ExpandFoodPropertiesPossibleEffect {
        
        @ZenCodeType.StaticExpansionMethod
        public static FoodProperties.PossibleEffect of(MobEffectInstance effect, float probability) {
            
            return new FoodProperties.PossibleEffect(effect, probability);
        }
        
        @ZenCodeType.Getter("effect")
        public static MobEffectInstance effect(FoodProperties.PossibleEffect internal) {
            
            return internal.effect();
        }
        
        @ZenCodeType.Getter("probability")
        public static float probability(FoodProperties.PossibleEffect internal) {
            
            return internal.probability();
        }
        
    }
    
}
