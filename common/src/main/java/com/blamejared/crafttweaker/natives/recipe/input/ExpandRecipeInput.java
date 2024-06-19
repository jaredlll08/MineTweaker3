package com.blamejared.crafttweaker.natives.recipe.input;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.crafting.RecipeInput;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/recipe/input/RecipeInput")
@NativeTypeRegistration(value = RecipeInput.class, zenCodeName = "crafttweaker.api.recipe.input.RecipeInput")
public class ExpandRecipeInput {
    
    @ZenCodeType.Method
    public static IItemStack getItem(RecipeInput internal, int slot) {
        
        return IItemStack.of(internal.getItem(slot));
    }
    
    @ZenCodeType.Getter("size")
    public static int size(RecipeInput internal) {
        
        return internal.size();
    }
    
    @ZenCodeType.Getter("isEmpty")
    public static boolean isEmpty(RecipeInput internal) {
        
        return internal.isEmpty();
    }
    
}
