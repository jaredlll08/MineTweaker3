package com.blamejared.crafttweaker.natives.food;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/food/FoodData")
@NativeTypeRegistration(value = FoodData.class, zenCodeName = "crafttweaker.api.food.FoodData")
public class ExpandFoodData {
    
    /**
     * Replenishes the food level and saturation level.
     *
     * @param foodLevelModifier       The amount to modify the food level by.
     * @param saturationLevelModifier The amount to modify the saturation level by.
     */
    @ZenCodeType.Method
    public static void eat(FoodData internal, int foodLevelModifier, float saturationLevelModifier) {
        
        internal.eat(foodLevelModifier, saturationLevelModifier);
    }
    
    /**
     * Replenishes the food level and saturation level.
     *
     * @param foodProperties The food properties to eat.
     */
    @ZenCodeType.Method
    public static void eat(FoodData internal, FoodProperties foodProperties) {
        
        internal.eat(foodProperties);
    }
    
    /**
     * Gets the food level.
     *
     * @return The food level.
     */
    @ZenCodeType.Method
    public static int getFoodLevel(FoodData internal) {
        
        return internal.getFoodLevel();
    }
    
    /**
     * Gets the last food level.
     *
     * @return The last food level.
     */
    @ZenCodeType.Method
    public static int getLastFoodLevel(FoodData internal) {
        
        return internal.getLastFoodLevel();
    }
    
    /**
     * Checks if the entity needs food.
     *
     * @return True if the entity needs food, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean needsFood(FoodData internal) {
        
        return internal.needsFood();
    }
    
    /**
     * Adds exhaustion to the entity.
     *
     * @param exhaustion The amount of exhaustion to add.
     */
    @ZenCodeType.Method
    public static void addExhaustion(FoodData internal, float exhaustion) {
        
        internal.addExhaustion(exhaustion);
    }
    
    /**
     * Gets the exhaustion level.
     *
     * @return The exhaustion level.
     */
    @ZenCodeType.Method
    public static float getExhaustionLevel(FoodData internal) {
        
        return internal.getExhaustionLevel();
    }
    
    /**
     * Gets the saturation level.
     *
     * @return The saturation level.
     */
    @ZenCodeType.Method
    public static float getSaturationLevel(FoodData internal) {
        
        return internal.getSaturationLevel();
    }
    
    /**
     * Sets the food level.
     *
     * @param foodLevel The amount of food to set.
     */
    @ZenCodeType.Method
    public static void setFoodLevel(FoodData internal, int foodLevel) {
        
        internal.setFoodLevel(foodLevel);
    }
    
    /**
     * Sets the saturation level.
     *
     * @param saturationLevel The amount of saturation to set.
     */
    @ZenCodeType.Method
    public static void setSaturation(FoodData internal, float saturationLevel) {
        
        internal.setSaturation(saturationLevel);
    }
    
    /**
     * Sets the exhaustion level.
     *
     * @param exhaustionLevel The amount of exhaustion to set.
     */
    @ZenCodeType.Method
    public static void setExhaustion(FoodData internal, float exhaustionLevel) {
        
        internal.setExhaustion(exhaustionLevel);
    }
    
}
