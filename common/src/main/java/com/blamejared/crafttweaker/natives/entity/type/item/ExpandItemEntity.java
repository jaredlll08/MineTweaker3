package com.blamejared.crafttweaker.natives.entity.type.item;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeConstructor;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/type/item/ItemEntity")
@NativeTypeRegistration(value = ItemEntity.class, zenCodeName = "crafttweaker.api.entity.type.item.ItemEntity", constructors = {
        @NativeConstructor({
                @NativeConstructor.ConstructorParameter(type = Level.class, name = "level"),
                @NativeConstructor.ConstructorParameter(type = double.class, name = "x"),
                @NativeConstructor.ConstructorParameter(type = double.class, name = "y"),
                @NativeConstructor.ConstructorParameter(type = double.class, name = "z"),
                @NativeConstructor.ConstructorParameter(type = ItemStack.class, name = "stack")
        })
})
public class ExpandItemEntity {
    
    /**
     * Set this item entity to have the default pickup delay.
     */
    @ZenCodeType.Method
    public static void setDefaultPickUpDelay(ItemEntity internal) {
        
        internal.setDefaultPickUpDelay();
    }
    
    /**
     * Set this item entity to not have a pickup delay.
     */
    @ZenCodeType.Method
    public static void setNoPickUpDelay(ItemEntity internal) {
        
        internal.setNoPickUpDelay();
    }
    
    /**
     * Set this item entity to never be picked up.
     */
    @ZenCodeType.Method
    public static void setNeverPickUp(ItemEntity internal) {
        
        internal.setNeverPickUp();
    }
    
    /**
     * Sets the pickup delay in ticks for this item entity.
     *
     * @param ticks The number of ticks to delay the pickup for.
     */
    @ZenCodeType.Method
    public static void setPickUpDelay(ItemEntity internal, int ticks) {
        
        internal.setPickUpDelay(ticks);
    }
    
    /**
     * Checks if this item entity has a pickup delay.
     *
     * @return True if this item entity has a pickup delay, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("hasPickupDelay")
    public static boolean hasPickUpDelay(ItemEntity internal) {
        
        return internal.hasPickUpDelay();
    }
    
    /**
     * Gets the {@link IItemStack} inside this ItemEntity.
     *
     * @return The {@link IItemStack} inside this ItemEntity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("item")
    public static IItemStack getItem(ItemEntity itemEntity) {
        
        return IItemStack.of(itemEntity.getItem());
    }
    
    /**
     * Sets the {@link IItemStack} inside this ItemEntity.
     *
     * @param stack The new {@link IItemStack}.
     *
     * @docParam stack <item:minecraft:diamond>
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("item")
    public static void setItem(ItemEntity itemEntity, IItemStack stack) {
        
        itemEntity.setItem(stack.getInternal());
    }
    
}
