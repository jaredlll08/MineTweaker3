package com.blamejared.crafttweaker.api.ingredient.transformer.type;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.transformer.IIngredientTransformer;
import com.blamejared.crafttweaker.api.ingredient.transformer.serializer.TransformDamageSerializer;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.ingredient.transform.type.TransformDamage")
@Document("vanilla/api/ingredient/transform/type/TransformDamage")
public record TransformDamage(int amount) implements IIngredientTransformer {
    
    @Override
    public IItemStack transform(IItemStack stack) {
        
        int damage = stack.getDamage();
        final int newDamage = damage + amount();
        if(newDamage >= stack.getMaxDamage()) {
            return IItemStack.empty();
        }
        return stack.copy().withDamage(newDamage);
    }
    
    @Override
    public String getCommandString(String base) {
        
        return "%s.transformDamage(%s)".formatted(base, this.amount() != 1 ? amount : "");
    }
    
    @Override
    public TransformDamageSerializer getSerializer() {
        
        return TransformDamageSerializer.INSTANCE;
    }
    
    
}
