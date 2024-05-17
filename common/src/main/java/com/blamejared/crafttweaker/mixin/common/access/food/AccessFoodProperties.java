package com.blamejared.crafttweaker.mixin.common.access.food;

import net.minecraft.world.food.FoodProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FoodProperties.class)
public interface AccessFoodProperties {
    
    @Mutable
    @Accessor("nutrition")
    void crafttweaker$setNutrition(int nutrition);
    
    @Mutable
    @Accessor("saturation")
    void crafttweaker$setSaturationModifier(float saturationModifier);
    
    @Mutable
    @Accessor("canAlwaysEat")
    void crafttweaker$setCanAlwaysEat(boolean canAlwaysEat);
    
    @Mutable
    @Accessor("eatSeconds")
    void crafttweaker$setEatSeconds(float eatSeconds);
    
}
