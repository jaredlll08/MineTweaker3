package com.blamejared.crafttweaker.api.ingredient.type;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.ingredient.condition.IngredientConditions;
import com.blamejared.crafttweaker.api.ingredient.transformer.IngredientTransformers;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.tag.type.KnownTag;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.natives.item.ExpandItem;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.ingredient.type.TagIngredient")
@Document("vanilla/api/ingredient/type/TagIngredient")
public class TagIngredient implements IIngredient {
    
    private final KnownTag<Item> internal;
    
    private final IngredientConditions conditions = new IngredientConditions();
    private final IngredientTransformers transformers = new IngredientTransformers();
    
    public TagIngredient(KnownTag<Item> internal) {
        
        this.internal = internal;
    }
    
    @Override
    public boolean matches(IItemStack stack) {
        
        return Arrays.stream(getItems()).anyMatch(item -> item.matches(stack));
    }
    
    
    @Override
    public Ingredient asVanillaIngredient() {
        
        return Ingredient.of(internal.<TagKey<Item>>getTagKey());
    }
    
    @Override
    public String getCommandString() {
        
        return internal.getCommandString();
    }
    
    @Override
    public IItemStack[] getItems() {
        
        return internal.getInternal()
                .stream()
                .map(GenericUtil::<Holder<Item>>uncheck)
                .map(Holder::value)
                .map(item -> IItemStack.of(item.getDefaultInstance(), this.conditions(), this.transformers()))
                .toArray(IItemStack[]::new);
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
    public String toString() {
        
        return this.getCommandString();
    }
    
}
