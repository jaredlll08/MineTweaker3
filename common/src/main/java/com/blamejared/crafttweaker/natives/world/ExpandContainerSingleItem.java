package com.blamejared.crafttweaker.natives.world;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/world/ContainerSingleItem")
@NativeTypeRegistration(value = ContainerSingleItem.class, zenCodeName = "crafttweaker.api.world.ContainerSingleItem")
public class ExpandContainerSingleItem {
    
    /**
     * Gets the only item held by the container.
     *
     * @return The item held by the container.
     */
    @ZenCodeType.Method
    public static ItemStack getTheItem(ContainerSingleItem internal) {
        
        return internal.getTheItem();
    }
    
    /**
     * Sets the only item held by the container.
     *
     * @param stack The new item for te container to hold.
     */
    @ZenCodeType.Method
    public static void setTheItem(ContainerSingleItem internal, ItemStack stack) {
        
        internal.setTheItem(stack);
    }
    
    /**
     * Removes up to the specified amount of the contained item.
     *
     * @param amount The maximum amount of the item to split off from the contained item.
     *
     * @return The item that was split from the contained item.
     */
    @ZenCodeType.Method
    public static ItemStack splitTheItem(ContainerSingleItem internal, int amount) {
        
        return internal.splitTheItem(amount);
    }
    
    /**
     * Removes the item stored by the container.
     *
     * @return The new copy of the removed item.
     */
    public static ItemStack removeTheItem(ContainerSingleItem internal) {
        
        return internal.removeTheItem();
    }
    
}
