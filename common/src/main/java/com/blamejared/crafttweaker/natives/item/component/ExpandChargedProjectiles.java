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
    
    @ZenCodeType.StaticExpansionMethod
    public static ChargedProjectiles of(IItemStack stack) {
        
        return ChargedProjectiles.of(stack.getInternal());
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static ChargedProjectiles of(List<IItemStack> stacks) {
        
        return ChargedProjectiles.of(Lists.transform(stacks, IItemStack::getInternal));
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.CONTAINS)
    public static boolean contains(ChargedProjectiles internal, Item item) {
        
        return internal.contains(item);
    }
    
    @ZenCodeType.Getter("items")
    public static List<IItemStack> getItems(ChargedProjectiles internal) {
        
        return Lists.transform(internal.getItems(), IItemStack::of);
    }
    
    @ZenCodeType.Getter("empty")
    public static boolean isEmpty(ChargedProjectiles internal) {
        
        return internal.isEmpty();
    }
    
}
