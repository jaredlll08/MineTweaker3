package com.blamejared.crafttweaker.natives.villager;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.trading.ItemCost;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/villager/ItemCost")
@NativeTypeRegistration(value = ItemCost.class, zenCodeName = "crafttweaker.api.villager.ItemCost")
public class ExpandItemCost {
    
    @ZenCodeType.StaticExpansionMethod
    public static ItemCost of(IItemStack stack) {
        
        return new ItemCost(stack.asItemLike(), stack.amount());
    }
    
    //TODO 1.20.5 do a data component predicate version
//    @ZenCodeType.StaticExpansionMethod
//    public static ItemCost of(IItemStack stack) {
//
//        return new ItemCost(stack.asItemLike());
//    }
    
    
    //TODO 1.20.5 expose!
    //    public static ItemCost withComponents(UnaryOperator<DataComponentPredicate.Builder> $$0) {
    //
    //        return internal.withComponents($$0);
    //    }
    
    @ZenCodeType.Method
    public static boolean test(ItemCost internal, IItemStack stack) {
        
        return internal.test(stack.getInternal());
    }
    
    //TODO 1.20.5 expose!
    //    public static DataComponentPredicate components() {
    //
    //        return internal.components();
    //    }
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
