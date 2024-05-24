package com.blamejared.crafttweaker.natives.villager;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.trading.ItemCost;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.UnaryOperator;

@ZenRegister
@Document("vanilla/api/villager/ItemCost")
@NativeTypeRegistration(value = ItemCost.class, zenCodeName = "crafttweaker.api.villager.ItemCost")
public class ExpandItemCost {
    
    @ZenCodeType.StaticExpansionMethod
    public static ItemCost of(IItemStack stack) {
        
        return new ItemCost(stack.asItemLike(), stack.amount());
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static ItemCost of(IItemStack stack, DataComponentPredicate predicate) {
        
        //noinspection deprecation
        return new ItemCost(stack.asItemLike().asItem().builtInRegistryHolder(), stack.amount(), predicate);
    }
    
    @ZenCodeType.Method
    public static ItemCost withComponents(ItemCost internal, UnaryOperator<DataComponentPredicate.Builder> operator) {
        
        return internal.withComponents(operator);
    }
    
    @ZenCodeType.Method
    public static boolean test(ItemCost internal, IItemStack stack) {
        
        return internal.test(stack.getInternal());
    }
    
    @ZenCodeType.Method
    public static DataComponentPredicate components(ItemCost internal) {
        
        return internal.components();
    }
    
    @ZenCodeType.Getter("count")
    public static int count(ItemCost internal) {
        
        return internal.count();
    }
    
    @ZenCodeType.Getter("itemStack")
    public static IItemStack itemStack(ItemCost internal) {
        
        return IItemStack.of(internal.itemStack());
    }
    
    @ZenCodeType.Getter("item")
    public static Item item(ItemCost internal) {
        
        return internal.item().value();
    }
    
}
