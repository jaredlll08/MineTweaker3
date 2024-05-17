package com.blamejared.crafttweaker.api.ingredient.transformer.type;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.ingredient.transformer.IIngredientTransformer;
import com.blamejared.crafttweaker.api.ingredient.transformer.serializer.TransformReuseSerializer;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.ingredient.transform.type.TransformReuse")
@Document("vanilla/api/ingredient/transform/type/TransformReuse")
public class TransformReuse implements IIngredientTransformer {
    
    public static final TransformReuse INSTANCE = new TransformReuse();
    
    private TransformReuse() {}
    
    @Override
    public IItemStack transform(IItemStack stack) {
        
        return stack.copy().setAmount(1);
    }
    
    @Override
    public String getCommandString(String base) {
        
        return String.format("%s.reuse()", base);
    }
    
    @Override
    public TransformReuseSerializer getSerializer() {
        
        return TransformReuseSerializer.INSTANCE;
    }
    
    
    
}
