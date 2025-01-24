package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.google.common.collect.Lists;
import net.minecraft.Optionull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/item/component/BundleContents")
@NativeTypeRegistration(value = BundleContents.class, zenCodeName = "crafttweaker.api.item.component.BundleContents")
public class ExpandBundleContents {
    
    //TODO this doesn't handle any of the weight stuff since it uses Fraction which isn't exposed
    
    /**
     * Creates a new BundleContents with the given items.
     *
     * @param items The items to add to the bundle.
     *
     * @return The new BundleContents.
     */
    @ZenCodeType.StaticExpansionMethod
    public static BundleContents of(List<IItemStack> items) {
        
        return new BundleContents(Lists.transform(items, IItemStack::getInternal));
    }
    
    /**
     * Gets the items of the BundleContents.
     *
     * @param internal The BundleContents.
     *
     * @return The items of the BundleContents.
     */
    @ZenCodeType.Getter("items")
    public static Iterable<ItemStack> items(BundleContents internal) {
        
        return Lists.newArrayList(internal.itemsCopy());
    }
    
    /**
     * Checks if the BundleContents is empty.
     *
     * @param internal The BundleContents.
     *
     * @return Whether the BundleContents is empty.
     */
    @ZenCodeType.Getter("isEmpty")
    public static boolean isEmpty(BundleContents internal) {
        
        return internal.isEmpty();
    }
    
    /**
     * Gets the size of the BundleContents.
     *
     * @param internal The BundleContents.
     *
     * @return The size of the BundleContents.
     */
    @ZenCodeType.Getter("size")
    public static int size(BundleContents internal) {
        
        return internal.size();
    }
    
    @ZenRegister
    @Document("vanilla/api/item/component/MutableBundleContents")
    @NativeTypeRegistration(value = BundleContents.Mutable.class, zenCodeName = "crafttweaker.api.item.component.MutableBundleContents")
    public static class ExpandBundleContentsMutable {
        
        /**
         * Creates a new MutableBundleContents with the given contents.
         *
         * @param contents The contents to add to the MutableBundleContents.
         *
         * @return The new MutableBundleContents.
         */
        @ZenCodeType.StaticExpansionMethod
        public static BundleContents.Mutable of(BundleContents contents) {
            
            return new BundleContents.Mutable(contents);
        }
        
        /**
         * Clears the items of the MutableBundleContents.
         *
         * @return The new MutableBundleContents.
         */
        @ZenCodeType.Method
        public static BundleContents.Mutable clearItems(BundleContents.Mutable internal) {
            
            return internal.clearItems();
        }
        
        /**
         * Converts the MutableBundleContents to an immutable BundleContents.
         *
         * @param internal The MutableBundleContents.
         *
         * @return The immutable BundleContents.
         */
        @ZenCodeType.Method
        public static BundleContents toImmutable(BundleContents.Mutable internal) {
            
            return internal.toImmutable();
        }
        
        /**
         * Tries to insert the given item into the MutableBundleContents.
         *
         * @param internal The MutableBundleContents.
         * @param stack    The item to insert.
         *
         * @return The number of items that were inserted.
         */
        @ZenCodeType.Method
        public static int tryInsert(BundleContents.Mutable internal, ItemStack stack) {
            
            return internal.tryInsert(stack);
        }
        
        /**
         * Removes one item from the MutableBundleContents.
         *
         * @param internal The MutableBundleContents.
         *
         * @return The item that was removed.
         */
        @ZenCodeType.Nullable
        public static IItemStack removeOne(BundleContents.Mutable internal) {
            
            return Optionull.map(internal.removeOne(), IItemStack::of);
        }
        
    }
    
}
