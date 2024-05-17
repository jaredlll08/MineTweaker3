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
    
    @ZenCodeType.StaticExpansionMethod
    public static BundleContents of(List<IItemStack> items) {
        
        return new BundleContents(Lists.transform(items, IItemStack::getInternal));
    }
    
    @ZenCodeType.Getter("items")
    public static Iterable<ItemStack> items(BundleContents internal) {
        
        return Lists.newArrayList(internal.itemsCopy());
    }
    
    @ZenCodeType.Getter("isEmpty")
    public static boolean isEmpty(BundleContents internal) {
        
        return internal.isEmpty();
    }
    
    @ZenCodeType.Getter("size")
    public static int size(BundleContents internal) {
        
        return internal.size();
    }
    
    @ZenRegister
    @Document("vanilla/api/item/component/MutableBundleContents")
    @NativeTypeRegistration(value = BundleContents.Mutable.class, zenCodeName = "crafttweaker.api.item.component.MutableBundleContents")
    public static class ExpandBundleContentsMutable {
        
        @ZenCodeType.StaticExpansionMethod
        public static BundleContents.Mutable of(BundleContents contents) {
            
            return new BundleContents.Mutable(contents);
        }
        
        @ZenCodeType.Method
        public static BundleContents.Mutable clearItems(BundleContents.Mutable internal) {
            
            return internal.clearItems();
        }
        
        @ZenCodeType.Method
        @ZenCodeType.Caster(implicit = true)
        public static BundleContents toImmutable(BundleContents.Mutable internal) {
            
            return internal.toImmutable();
        }
        
        @ZenCodeType.Method
        public static int tryInsert(BundleContents.Mutable internal, ItemStack stack) {
            
            return internal.tryInsert(stack);
        }
        
        @ZenCodeType.Nullable
        @ZenCodeType.Method
        public static IItemStack removeOne(BundleContents.Mutable internal) {
            
            return Optionull.map(internal.removeOne(), IItemStack::of);
        }
        
    }
    
}
