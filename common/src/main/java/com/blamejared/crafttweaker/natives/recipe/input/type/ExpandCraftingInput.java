package com.blamejared.crafttweaker.natives.recipe.input.type;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.google.common.collect.Lists;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/recipe/input/type/CraftingInput")
@NativeTypeRegistration(value = CraftingInput.class, zenCodeName = "crafttweaker.api.recipe.input.type.CraftingInput")
public class ExpandCraftingInput {
    
    
    @ZenCodeType.StaticExpansionMethod
    public static CraftingInput of(int width, int height, List<IItemStack> items) {
        
        return CraftingInput.of(width, height, Lists.transform(items, IItemStack::getInternal));
    }
    
    @ZenCodeType.Getter("items")
    public static List<IItemStack> items(CraftingInput internal) {
        
        return Lists.transform(internal.items(), IItemStack::of);
    }
    
    @ZenCodeType.Getter("ingredientCount")
    public static int ingredientCount(CraftingInput internal) {
        
        return internal.ingredientCount();
    }
    
    @ZenCodeType.Getter("width")
    public static int width(CraftingInput internal) {
        
        return internal.width();
    }
    
    @ZenCodeType.Method
    public static ItemStack getItem(CraftingInput internal, int col, int row) {
        
        return internal.getItem(col, row);
    }
    
    @ZenCodeType.Getter("height")
    public static int height(CraftingInput internal) {
        
        return internal.height();
    }
    
    
}
