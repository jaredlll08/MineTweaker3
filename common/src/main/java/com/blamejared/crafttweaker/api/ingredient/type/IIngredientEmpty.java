package com.blamejared.crafttweaker.api.ingredient.type;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientCondition;
import com.blamejared.crafttweaker.api.ingredient.condition.IngredientConditions;
import com.blamejared.crafttweaker.api.ingredient.transformer.IIngredientTransformer;
import com.blamejared.crafttweaker.api.ingredient.transformer.IngredientTransformers;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.world.item.crafting.Ingredient;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.ingredient.type.IIngredientEmpty")
@Document("vanilla/api/ingredient/type/IIngredientEmpty")
public class IIngredientEmpty implements IIngredient {
    
    public static final IIngredientEmpty INSTANCE = new IIngredientEmpty();
    
    private IIngredientEmpty() {}
    
    @ZenCodeType.Method
    public static IIngredientEmpty getInstance() {
        
        return INSTANCE;
    }
    
    @Override
    public boolean matches(IItemStack stack) {
        
        //I'm not sure *what* condition you could test for, but it is here
        return stack.isEmpty() && this.conditions().test(stack);
    }
    
    @Override
    public boolean isEmpty() {
        
        return true;
    }
    
    @Override
    public Ingredient asVanillaIngredient() {
        
        return Ingredient.EMPTY;
    }
    
    @Override
    public String getCommandString() {
        
        return "IIngredientEmpty.getInstance()";
    }
    
    @Override
    public IItemStack[] getItems() {
        
        return new IItemStack[0];
    }
    
    @Override
    public IngredientTransformers transformers() {
        
        return IngredientTransformers.EMPTY;
    }
    
    @Override
    public IngredientConditions conditions() {
        
        return IngredientConditions.EMPTY;
    }
    
}
