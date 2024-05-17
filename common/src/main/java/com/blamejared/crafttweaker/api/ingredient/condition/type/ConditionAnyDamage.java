package com.blamejared.crafttweaker.api.ingredient.condition.type;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientCondition;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientConditionSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionAnyDamagedSerializer;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.ingredient.condition.type.ConditionAnyDamage")
@Document("vanilla/api/ingredient/condition/type/ConditionAnyDamage")
public class ConditionAnyDamage implements IIngredientCondition {
    
    public static final ConditionAnyDamage INSTANCE = new ConditionAnyDamage();
    
    private ConditionAnyDamage() {}
    
    @Override
    public boolean matches(IItemStack stack) {
        
        return true;
    }
    
    @Override
    public boolean ignoresDamage() {
        
        return true;
    }
    
    @Override
    public String getCommandString(String base) {
        
        return "%s.anyDamage()".formatted(base);
    }
    
    @Override
    public ConditionAnyDamagedSerializer getSerializer() {
        
        return ConditionAnyDamagedSerializer.INSTANCE;
    }
    
    @Override
    public boolean equals(Object o) {
        
        return o instanceof ConditionAnyDamage;
    }
    
    @Override
    public int hashCode() {
        
        return ConditionAnyDamage.class.hashCode();
    }
    
}
