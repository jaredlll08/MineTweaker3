package com.blamejared.crafttweaker.natives.recipe.input.type;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/recipe/input/type/SingleRecipeInput")
@NativeTypeRegistration(value = SingleRecipeInput.class, zenCodeName = "crafttweaker.api.recipe.input.type.SingleRecipeInput")
public class ExpandSingleRecipeInput {
    
    @ZenCodeType.StaticExpansionMethod
    public static SingleRecipeInput of(IItemStack item) {
        
        return new SingleRecipeInput(item.getInternal());
    }
    
    
    @ZenCodeType.Getter("item")
    public static IItemStack item(SingleRecipeInput internal) {
        
        return IItemStack.of(internal.item());
    }
    
}
