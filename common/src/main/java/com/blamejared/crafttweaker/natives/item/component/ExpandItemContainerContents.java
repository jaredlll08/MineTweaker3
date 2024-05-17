package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;
import java.util.stream.Collectors;

@ZenRegister
@Document("vanilla/api/item/component/ItemContainerContents")
@NativeTypeRegistration(value = ItemContainerContents.class, zenCodeName = "crafttweaker.api.item.component.ItemContainerContents")
public class ExpandItemContainerContents {
    
    @ZenCodeType.StaticExpansionMethod
    public static ItemContainerContents of(List<IItemStack> items) {
        
        return ItemContainerContents.fromItems(Lists.transform(items, IItemStack::getInternal));
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static ItemContainerContents empty() {
        
        return ItemContainerContents.EMPTY;
    }
    
    @ZenCodeType.Method
    public static void copyInto(ItemContainerContents internal, List<IItemStack> stacks) {
        
        internal.copyInto(stacks.stream()
                .map(IItemStack::getInternal)
                .collect(Collectors.toCollection(() -> NonNullList.withSize(stacks.size(), ItemStack.EMPTY))));
    }
    
    @ZenCodeType.Getter("items")
    public static List<IItemStack> stream(ItemContainerContents internal) {
        
        return internal.stream().map(IItemStack::of).toList();
    }
    
    @ZenCodeType.Getter("nonEmptyItems")
    public static List<IItemStack> nonEmptyItemsCopy(ItemContainerContents internal) {
        
        return Lists.newArrayList(Iterables.transform(internal.nonEmptyItemsCopy(), IItemStack::of));
    }
    
    @ZenCodeType.Getter("copyOne")
    public static IItemStack copyOne(ItemContainerContents internal) {
        
        return IItemStack.of(internal.copyOne());
    }
    
}
