package com.blamejared.crafttweaker.natives.entity.type.player;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.tag.type.KnownTag;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/type/player/Inventory")
@NativeTypeRegistration(value = Inventory.class, zenCodeName = "crafttweaker.api.entity.type.player.Inventory")
public class ExpandInventory {
    
    /**
     * Gets the selected item in the inventory, this is usually the selected slot in the hotbar.
     *
     * @return The selected item in the inventory.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("selected")
    public static ItemStack getSelected(Inventory internal) {
        
        return internal.getSelected();
    }
    
    /**
     * Gets the first free slot in the inventory.
     *
     * @return The first free slot in the inventory.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("freeSlot")
    public static int getFreeSlot(Inventory internal) {
        
        return internal.getFreeSlot();
    }
    
    /**
     * Sets the selected item in the hotbar to the first matching stack in the inventory.
     *
     * @param stack The item to set as the selected item.
     */
    @ZenCodeType.Method
    public static void setPickedItem(Inventory internal, ItemStack stack) {
        
        internal.setPickedItem(stack);
    }
    
    /**
     * Moves the item at the given index to the first available hotbar slot.
     *
     * @param index The index of the slot to pick.
     */
    @ZenCodeType.Method
    public static void pickSlot(Inventory internal, int index) {
        
        internal.pickSlot(index);
    }
    
    /**
     * Finds the first slot in the inventory that matches the given item.
     *
     * @param stack The item to find.
     *
     * @return The index of the first slot that matches the given item.
     */
    @ZenCodeType.Method
    public static int findSlotMatchingItem(Inventory internal, ItemStack stack) {
        
        return internal.findSlotMatchingItem(stack);
    }
    
    /**
     * Finds the first slot in the inventory that matches the given stack, without being damaged, enchanted, or having a custom name.
     *
     * @param stack The item to find.
     *
     * @return The index of the first slot that matches the given stack.
     */
    @ZenCodeType.Method
    public static int findSlotMatchingUnusedItem(Inventory internal, ItemStack stack) {
        
        return internal.findSlotMatchingUnusedItem(stack);
    }
    
    /**
     * Finds the first suitable hotbar slot for the given item.
     *
     * @return The index of the first suitable hotbar slot.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("suitableHotbarSlot")
    public static int getSuitableHotbarSlot(Inventory internal) {
        
        return internal.getSuitableHotbarSlot();
    }
    
    /**
     * Finds the first slot in the inventory that has remaining space for the given item.
     *
     * @param stack The item to find.
     *
     * @return The index of the first slot that has remaining space for the given item.
     */
    @ZenCodeType.Method
    public static int getSlotWithRemainingSpace(Inventory internal, ItemStack stack) {
        
        return internal.getSlotWithRemainingSpace(stack);
    }
    
    /**
     * Adds the given item to the inventory.
     *
     * @param stack The item to add.
     *
     * @return Whether the item was added successfully.
     */
    @ZenCodeType.Method
    public static boolean add(Inventory internal, ItemStack stack) {
        
        return internal.add(stack);
    }
    
    /**
     * Adds the given item to the inventory at the given index.
     *
     * @param index The index to add the item to.
     * @param stack The item to add.
     *
     * @return Whether the item was added successfully.
     */
    @ZenCodeType.Method
    public static boolean add(Inventory internal, int index, ItemStack stack) {
        
        return internal.add(index, stack);
    }
    
    /**
     * Removes the given item from the inventory.
     *
     * @param stack The item to remove.
     */
    @ZenCodeType.Method
    public static void removeItem(Inventory internal, ItemStack stack) {
        
        internal.removeItem(stack);
    }
    
    /**
     * Gets the armor from the given index.
     *
     * @param armorIndex The index of the armor to get.
     *
     * @return The armor from the given index.
     */
    @ZenCodeType.Method
    public static ItemStack getArmor(Inventory internal, int armorIndex) {
        
        return internal.getArmor(armorIndex);
    }
    
    /**
     * Drops all items from the inventory.
     */
    @ZenCodeType.Method
    public static void dropAll(Inventory internal) {
        
        internal.dropAll();
    }
    
    /**
     * Gets the number of times the inventory has changed.
     *
     * @return The number of times the inventory has changed.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("timesChanged")
    public static int getTimesChanged(Inventory internal) {
        
        return internal.getTimesChanged();
    }
    
    /**
     * Checks if the inventory contains the given item.
     *
     * @param stack The item to check.
     *
     * @return Whether the inventory contains the given item.
     */
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.CONTAINS)
    public static boolean contains(Inventory internal, ItemStack stack) {
        
        return internal.contains(stack);
    }
    
    /**
     * Checks if the inventory contains the given item tag.
     *
     * @param tag The item tag to check.
     *
     * @return Whether the inventory contains the given item tag.
     */
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.CONTAINS)
    public static boolean contains(Inventory internal, KnownTag<Item> tag) {
        
        return internal.contains(tag.<TagKey<Item>> getTagKey());
    }
    
}
