package com.blamejared.crafttweaker.api.ingredient.transformer.type;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.transformer.IIngredientTransformer;
import com.blamejared.crafttweaker.api.ingredient.transformer.serializer.TransformReplaceSerializer;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.ingredient.transform.type.TransformReplace")
@Document("vanilla/api/ingredient/transform/type/TransformReplace")
public record TransformReplace(IItemStack replaceWith) implements IIngredientTransformer {
    
    @Override
    public IItemStack transform(IItemStack stack) {
        
        return replaceWith.copy();
    }
    
    @Override
    public String getCommandString(String base) {
        
        return String.format("%s.transformReplace(%s)", base, replaceWith.getCommandString());
    }
    
    @Override
    public TransformReplaceSerializer getSerializer() {
        
        return TransformReplaceSerializer.INSTANCE;
    }
    
}
