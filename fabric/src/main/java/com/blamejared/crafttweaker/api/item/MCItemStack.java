package com.blamejared.crafttweaker.api.item;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.condition.IngredientConditions;
import com.blamejared.crafttweaker.api.ingredient.transformer.IngredientTransformers;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.Consumer;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.item.MCItemStack")
@Document("fabric/api/item/MCItemStack")
public class MCItemStack implements FabricItemStack {
    
    private final ItemStack internal;
    private final IngredientConditions conditions;
    private final IngredientTransformers transformers;
    
    public MCItemStack(ItemStack internal) {
        
        this.internal = internal;
        this.conditions = new IngredientConditions();
        this.transformers = new IngredientTransformers();
    }
    
    public MCItemStack(ItemStack internal, IngredientConditions conditions, IngredientTransformers transformers) {
        
        this.internal = internal;
        this.conditions = conditions;
        this.transformers = transformers;
    }
    
    @Override
    public IItemStack[] getItems() {
        
        return new IItemStack[] {this.copy()};
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
        
        return new MCItemStack(getImmutableInternal(), conditions.copy(), transformers.copy());
    }
    
    @Override
    public IItemStack asMutable() {
        
        if(this == IItemStack.empty() || this.getInternal() == ItemStack.EMPTY) { // Check both just in case
            // We don't want to allow mutations to the empty stack, so we just replace it with a stack of air. The game
            // treats air stacks as mostly the same as the empty stack, so this should be transparent to the user
            return new MCItemStackMutable(new ItemStack(Items.AIR));
        }
        return new MCItemStackMutable(getInternal(), conditions.copy(), transformers.copy());
    }
    
    @Override
    public IItemStack asImmutable() {
        
        return this;
    }
    
    @Override
    public boolean isImmutable() {
        
        return true;
    }
    
    @Override
    public ItemStack getInternal() {
        
        return internal;
    }
    
    @Override
    public IItemStack modify(Consumer<ItemStack> stackModifier) {
        
        ItemStack newStack = getInternal().copy();
        stackModifier.accept(newStack);
        return new MCItemStack(newStack);
    }
    
    @Override
    public IItemStack modifyThis(Consumer<IItemStack> modifier) {
        
        IItemStack newStack = copy();
        modifier.accept(newStack);
        return newStack;
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
        
        final ItemStack thatStack = ((MCItemStack) o).getInternal();
        final ItemStack thisStack = getInternal();
        
        return ItemStack.matches(thisStack, thatStack);
    }
    
    @Override
    public int hashCode() {
        
        return ItemStack.hashItemAndComponents(getInternal());
    }
    
    @Override
    public String toString() {
        
        return this.getCommandString();
    }
    
}
