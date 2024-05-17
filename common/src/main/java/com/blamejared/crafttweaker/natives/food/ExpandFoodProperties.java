package com.blamejared.crafttweaker.natives.food;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.mixin.common.access.food.AccessFoodProperties;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;
import java.util.function.Consumer;

@ZenRegister
@Document("vanilla/api/food/FoodProperties")
@NativeTypeRegistration(value = FoodProperties.class, zenCodeName = "crafttweaker.api.food.FoodProperties")
public class ExpandFoodProperties {
    
    @ZenCodeType.StaticExpansionMethod
    public static FoodProperties create(int nutrition, float saturationModifier) {
        
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturationModifier).build();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("nutrition")
    public static int nutrition(FoodProperties internal) {
        
        return internal.nutrition();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Setter("nutrition")
    public static FoodProperties setNutrition(FoodProperties internal, int nutrition) {
        
        return accessibleModify(internal, accessFoodProperties -> accessFoodProperties.crafttweaker$setNutrition(nutrition));
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("saturationModifier")
    public static float saturation(FoodProperties internal) {
        
        return internal.saturation();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Setter("saturationModifier")
    public static FoodProperties setSaturationModifier(FoodProperties internal, float saturationModifier) {
        
        return accessibleModify(internal, accessFoodProperties -> accessFoodProperties.crafttweaker$setSaturationModifier(saturationModifier));
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("canAlwaysEat")
    public static boolean canAlwaysEat(FoodProperties internal) {
        
        return internal.canAlwaysEat();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Setter("canAlwaysEat")
    public static FoodProperties setCanAlwaysEat(FoodProperties internal, boolean canAlwaysEat) {
        
        return accessibleModify(internal, accessFoodProperties -> accessFoodProperties.crafttweaker$setCanAlwaysEat(canAlwaysEat));
    }
    
    @ZenCodeType.Getter("eatSeconds")
    public static float eatSeconds(FoodProperties internal) {
        
        return internal.eatSeconds();
    }
    
    @ZenCodeType.Setter("eatSeconds")
    public static FoodProperties eatSeconds(FoodProperties internal, float eatSeconds) {
        
        return accessibleModify(internal, accessFoodProperties -> accessFoodProperties.crafttweaker$setEatSeconds(eatSeconds));
    }
    
    @ZenCodeType.Getter("effects")
    public static List<FoodProperties.PossibleEffect> getEffects(FoodProperties internal) {
        
        return internal.effects();
    }
    
    @ZenCodeType.Method
    public static FoodProperties addEffect(FoodProperties internal, MobEffectInstance effect, float probability) {
        
        return modify(internal, properties -> Services.PLATFORM.addFoodPropertiesEffect(properties, effect, probability));
    }
    
    @ZenCodeType.Method
    public static FoodProperties removeEffect(FoodProperties internal, MobEffectInstance effect) {
        
        return modify(internal, properties -> Services.PLATFORM.removeFoodPropertiesEffect(properties, effect));
    }
    
    @ZenCodeType.Method
    public static FoodProperties removeEffect(FoodProperties internal, MobEffect effect) {
        
        return modify(internal, properties -> Services.PLATFORM.removeFoodPropertiesEffect(properties, effect));
    }
    
    private static FoodProperties accessibleModify(FoodProperties properties, Consumer<AccessFoodProperties> propertyMutator) {
        
        FoodProperties copy = new FoodProperties(properties.nutrition(), properties.saturation(), properties.canAlwaysEat(), properties.eatSeconds(), properties.effects());
        propertyMutator.accept(GenericUtil.uncheck(copy));
        return copy;
    }
    
    private static FoodProperties modify(FoodProperties properties, Consumer<FoodProperties> propertyMutator) {
        
        FoodProperties copy = new FoodProperties(properties.nutrition(), properties.saturation(), properties.canAlwaysEat(), properties.eatSeconds(), properties.effects());
        propertyMutator.accept(copy);
        return copy;
    }
    
}
