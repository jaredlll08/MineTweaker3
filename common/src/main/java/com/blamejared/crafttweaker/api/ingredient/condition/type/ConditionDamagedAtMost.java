package com.blamejared.crafttweaker.api.ingredient.condition.type;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientCondition;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionDamagedAtMostSerializer;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.ingredient.condition.type.ConditionDamagedAtMost")
@Document("vanilla/api/ingredient/condition/type/ConditionDamagedAtMost")
public record ConditionDamagedAtMost(int maxDamage) implements IIngredientCondition {
    
    @Override
    public boolean matches(IItemStack stack) {
        
        return stack.getDamage() <= maxDamage;
    }
    
    @Override
    public boolean ignoresDamage() {
        
        return true;
    }
    
    @Override
    public String getCommandString(String base) {
        
        return String.format("%s.onlyDamagedAtMost(%s)", base, maxDamage);
    }
    
    @Override
    public ConditionDamagedAtMostSerializer getSerializer() {
        
        return ConditionDamagedAtMostSerializer.INSTANCE;
    }
    
}
