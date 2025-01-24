package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.google.common.collect.Lists;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ChargedProjectiles;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/item/component/ChargedProjectiles")
@NativeTypeRegistration(value = ChargedProjectiles.class, zenCodeName = "crafttweaker.api.item.component.ChargedProjectiles")
public class ExpandChargedProjectiles {
    
    /**
     * Creates a new ChargedProjectiles with the given item.
     *
     * @param stack The item to create the ChargedProjectiles from.
     *
     * @return The new ChargedProjectiles.
     */
    @ZenCodeType.StaticExpansionMethod
    public static ChargedProjectiles of(IItemStack stack) {
        
        return ChargedProjectiles.of(stack.getInternal());
    }
    
    /**
     * Creates a new ChargedProjectiles with the given items.
     *
     * @param stacks The items to create the ChargedProjectiles from.
     *
     * @return The new ChargedProjectiles.
     */
    @ZenCodeType.StaticExpansionMethod
    public static ChargedProjectiles of(List<IItemStack> stacks) {
        
        return ChargedProjectiles.of(Lists.transform(stacks, IItemStack::getInternal));
    }
    
    /**
     * Checks if the ChargedProjectiles contains the given item.
     *
     * @param internal The ChargedProjectiles.
     * @param item     The item to check for.
     *
     * @return Whether the ChargedProjectiles contains the given item.
     */
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.CONTAINS)
    public static boolean contains(ChargedProjectiles internal, Item item) {
        
        return internal.contains(item);
    }
    
    /**
     * Gets the items of the ChargedProjectiles.
     *
     * @param internal The ChargedProjectiles.
     *
     * @return The items of the ChargedProjectiles.
     */
    @ZenCodeType.Getter("items")
    public static List<IItemStack> getItems(ChargedProjectiles internal) {
        
        return Lists.transform(internal.getItems(), IItemStack::of);
    }
    
    /**
     * Checks if the ChargedProjectiles is empty.
     *
     * @param internal The ChargedProjectiles.
     *
     * @return Whether the ChargedProjectiles is empty.
     */
    @ZenCodeType.Getter("empty")
    public static boolean isEmpty(ChargedProjectiles internal) {
        
        return internal.isEmpty();
    }
    
}
