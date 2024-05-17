package com.blamejared.crafttweaker.api.item;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.condition.IngredientConditions;
import com.blamejared.crafttweaker.api.ingredient.transformer.IngredientTransformers;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.world.item.ItemStack;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Objects;
import java.util.function.Consumer;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.item.MCItemStackMutable")
@Document("neoforge/api/item/MCItemStackMutable")
public class MCItemStackMutable implements NeoForgeItemStack {
    
    private final ItemStack internal;
    private final IngredientConditions conditions;
    private final IngredientTransformers transformers;
    
    public MCItemStackMutable(ItemStack internal) {
        
        this.internal = internal;
        this.conditions = new IngredientConditions();
        this.transformers = new IngredientTransformers();
    }
    
    public MCItemStackMutable(ItemStack internal, IngredientConditions conditions, IngredientTransformers transformers) {
        
        this.internal = internal;
        this.conditions = conditions;
        this.transformers = transformers;
    }
    
    @Override
    public IItemStack[] getItems() {
        
        return new IItemStack[] {this};
    }
    
    @Override
    public IngredientTransformers transformers() {
        
        return transformers;
    }
    
    @Override
    public IngredientConditions conditions() {
        
        return conditions;
    }
    
    @Override
    public IItemStack copy() {
        
        return new MCItemStackMutable(getImmutableInternal(), conditions.copy(), transformers.copy());
    }
    
    @Override
    public IItemStack asMutable() {
        
        return this;
    }
    
    @Override
    public IItemStack asImmutable() {
        
        return new MCItemStack(getInternal().copy(), conditions.copy(), transformers.copy());
    }
    
    @Override
    public boolean isImmutable() {
        
        return false;
    }
    
    @Override
    public ItemStack getInternal() {
        
        return internal;
    }
    
    @Override
    public IItemStack modify(Consumer<ItemStack> stackModifier) {
        
        stackModifier.accept(getInternal());
        return this;
    }
    
    @Override
    public IItemStack modifyThis(Consumer<IItemStack> modifier) {
        
        modifier.accept(this);
        return this;
    }
    
    @Override
    @ZenCodeType.Operator(ZenCodeType.OperatorType.EQUALS)
    public boolean equals(Object o) {
        
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        
        //Implemented manually instead of using ItemStack.areItemStacksEqual to ensure it
        // stays the same as hashCode even if MC's impl would change
        final ItemStack thatStack = ((MCItemStackMutable) o).getInternal();
        final ItemStack thisStack = getInternal();
        
        if(thisStack.isEmpty()) {
            return thatStack.isEmpty();
        }
        
        if(thisStack.getCount() != thatStack.getCount()) {
            return false;
        }
        
        if(!Objects.equals(thisStack.getItem(), thatStack.getItem())) {
            return false;
        }
        
        //TODO 1.20.5
        //        if(!Objects.equals(thisStack.getTag(), thatStack.getTag())) {
        //            return false;
        //        }
        //
        //        return thisStack.areAttachmentsCompatible(thatStack);
        return true;
    }
    
    @Override
    public int hashCode() {
        //TODO 1.20.5
        return Objects.hash(getInternal().getCount(), getInternal().getItem()/*, getInternal().getTag()*/);
    }
    
    @Override
    public String toString() {
        
        return this.getCommandString();
    }
    
}
