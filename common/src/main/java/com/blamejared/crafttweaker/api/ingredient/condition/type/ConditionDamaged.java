package com.blamejared.crafttweaker.api.ingredient.condition.type;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientCondition;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientConditionSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionDamagedSerializer;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.ingredient.condition.type.ConditionDamaged")
@Document("vanilla/api/ingredient/condition/type/ConditionDamaged")
public class ConditionDamaged implements IIngredientCondition {
    
    public static final ConditionDamaged INSTANCE = new ConditionDamaged();
    
    private ConditionDamaged() {}
    
    @Override
    public boolean matches(IItemStack stack) {
        
        return stack.getDamage() > 0;
    }
    
    @Override
    public boolean ignoresDamage() {
        
        return true;
    }
    
    @Override
    public String getCommandString(String base) {
        
        return "%s.onlyDamaged()".formatted(base);
    }
    
    @Override
    public ConditionDamagedSerializer getSerializer() {
        
        return ConditionDamagedSerializer.INSTANCE;
    }
    
    @Override
    public boolean equals(Object o) {
        
        return o instanceof ConditionDamaged;
    }
    
    @Override
    public int hashCode() {
        
        return ConditionDamaged.class.hashCode();
    }
    
}
