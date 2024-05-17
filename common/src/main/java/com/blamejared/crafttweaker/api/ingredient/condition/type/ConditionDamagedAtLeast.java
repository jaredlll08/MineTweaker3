package com.blamejared.crafttweaker.api.ingredient.condition.type;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientCondition;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientConditionSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionDamagedAtLeastSerializer;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.ingredient.condition.type.ConditionDamagedAtLeast")
@Document("vanilla/api/ingredient/condition/type/ConditionDamagedAtLeast")
public record ConditionDamagedAtLeast(int minDamage) implements IIngredientCondition {
    
    @Override
    public boolean matches(IItemStack stack) {
        
        return stack.getDamage() >= minDamage;
    }
    
    @Override
    public boolean ignoresDamage() {
        
        return true;
    }
    
    @Override
    public String getCommandString(String base) {
        
        return String.format("%s.onlyDamagedAtLeast(%s)", base, minDamage);
    }
    
    @Override
    public ConditionDamagedAtLeastSerializer getSerializer() {
        
        return ConditionDamagedAtLeastSerializer.INSTANCE;
    }
    
}
