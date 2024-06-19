package com.blamejared.crafttweaker.natives.recipe.input.type;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/recipe/input/type/SmithingRecipeInput")
@NativeTypeRegistration(value = SmithingRecipeInput.class, zenCodeName = "crafttweaker.api.recipe.input.type.SmithingRecipeInput")
public class ExpandSmithingRecipeInput {
    
    @ZenCodeType.StaticExpansionMethod
    public static SmithingRecipeInput of(IItemStack template, IItemStack base, IItemStack addition) {
        
        return new SmithingRecipeInput(template.getInternal(), base.getInternal(), addition.getInternal());
    }
    
    @ZenCodeType.Getter("template")
    public static ItemStack template(SmithingRecipeInput internal) {
        
        return internal.template();
    }
    
    @ZenCodeType.Getter("base")
    public static ItemStack base(SmithingRecipeInput internal) {
        
        return internal.base();
    }
    
    @ZenCodeType.Getter("addition")
    public static ItemStack addition(SmithingRecipeInput internal) {
        
        return internal.addition();
    }
    
}
