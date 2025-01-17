package com.blamejared.crafttweaker.natives.entity.type.animal;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/type/animal/Sheep")
@NativeTypeRegistration(value = Sheep.class, zenCodeName = "crafttweaker.api.entity.type.animal.Sheep")
public class ExpandSheep {
    
    /**
     * Gets the scale of the sheep's head when it is eating.
     *
     * @param partialTick The partial tick value.
     *
     * @return The scale of the sheep's head when it is eating.
     */
    @ZenCodeType.Method
    public static float getHeadEatPositionScale(Sheep internal, float partialTick) {
        
        return internal.getHeadEatPositionScale(partialTick);
    }
    
    /**
     * Gets the angle scale of the sheep's head when it is eating.
     *
     * @param partialTick The partial tick value.
     *
     * @return The angle scale of the sheep's head when it is eating.
     */
    @ZenCodeType.Method
    public static float getHeadEatAngleScale(Sheep internal, float partialTick) {
        
        return internal.getHeadEatAngleScale(partialTick);
    }
    
    /**
     * Gets the {@link DyeColor} of the sheep.
     *
     * @return The {@link DyeColor} of the sheep.
     */
    @ZenCodeType.Getter("color")
    public static DyeColor getColor(Sheep internal) {
        
        return internal.getColor();
    }
    
    /**
     * Sets the {@link DyeColor} of the sheep.
     *
     * @param color The {@link DyeColor} to set the sheep to.
     */
    @ZenCodeType.Setter("color")
    public static void setColor(Sheep internal, DyeColor color) {
        
        internal.setColor(color);
    }
    
    /**
     * Checks if the sheep is sheared.
     *
     * @return True if the sheep is sheared, false otherwise.
     */
    @ZenCodeType.Getter("sheared")
    public static boolean isSheared(Sheep internal) {
        
        return internal.isSheared();
    }
    
    /**
     * Sets if the sheep is sheared.
     *
     * @param sheared True if the sheep is sheared, false otherwise.
     */
    @ZenCodeType.Setter("sheared")
    public static void setSheared(Sheep internal, boolean sheared) {
        
        internal.setSheared(sheared);
    }
    
}
