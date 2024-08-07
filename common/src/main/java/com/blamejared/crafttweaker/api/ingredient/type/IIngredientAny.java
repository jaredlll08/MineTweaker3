package com.blamejared.crafttweaker.api.ingredient.type;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientCondition;
import com.blamejared.crafttweaker.api.ingredient.condition.IngredientConditions;
import com.blamejared.crafttweaker.api.ingredient.transformer.IIngredientTransformer;
import com.blamejared.crafttweaker.api.ingredient.transformer.IngredientTransformers;
import com.blamejared.crafttweaker.api.ingredient.vanilla.type.IngredientAny;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import org.openzen.zencode.java.ZenCodeType;

/**
 * An IIngredient which matches all items
 *
 * @docParam this IIngredientAny.getInstance()
 */
@ZenRegister
@ZenCodeType.Name("crafttweaker.api.ingredient.type.IIngredientAny")
@Document("vanilla/api/ingredient/type/IIngredientAny")
public class IIngredientAny implements IIngredient {
    
    public static final ResourceLocation ID = CraftTweakerConstants.rl("any");
    public static final IIngredientAny INSTANCE = new IIngredientAny();
    private final IngredientConditions conditions = new IngredientConditions();
    private final IngredientTransformers transformers = new IngredientTransformers();
    
    private IIngredientAny() {}
    
    @ZenCodeType.Method
    public static IIngredientAny getInstance() {
        
        return INSTANCE;
    }
    
    @Override
    public boolean matches(IItemStack stack) {
        
        return stack != null && !stack.isEmpty() && this.conditions().test(stack);
    }
    
    @Override
    public Ingredient asVanillaIngredient() {
        
        return IngredientAny.ingredient();
    }
    
    @Override
    public String getCommandString() {
        
        return "IIngredientAny.getInstance()";
    }
    
    @Override
    public IItemStack[] getItems() {
        
        return new IItemStack[0];
    }
    
    @Override
    public IIngredient transform(IIngredientTransformer transformer) {
        if(this == INSTANCE) {
            IIngredientAny newIngredient = new IIngredientAny();
            newIngredient.transformers().add(transformer);
            return newIngredient;
        }
        return IIngredient.super.transform(transformer);
    }
    
    
    @Override
    public IngredientTransformers transformers() {
        
        return transformers;
    }
    
    @Override
    public IIngredient condition(IIngredientCondition condition) {
        if(this == INSTANCE) {
            IIngredientAny newIngredient = new IIngredientAny();
            newIngredient.conditions().add(condition);
            return newIngredient;
        }
        return IIngredient.super.condition(condition);
    }
    
    @Override
    public IngredientConditions conditions() {
        
        return conditions;
    }
    
}
