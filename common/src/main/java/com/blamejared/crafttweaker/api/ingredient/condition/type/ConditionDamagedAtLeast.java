package com.blamejared.crafttweaker.api.ingredient.condition.type;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientCondition;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientConditionSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionDamagedAtLeastSerializer;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.Predicate;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.ingredient.condition.type.ConditionDamagedAtLeast")
@Document("vanilla/api/ingredient/condition/type/ConditionDamagedAtLeast")
public record ConditionDamagedAtLeast(int minDamage) implements IIngredientCondition {
    
    @Override
    public boolean matches(IItemStack stack) {
        
        return stack.getDamage() >= minDamage;
    }
    
    @Override
    public Predicate<DataComponentType<?>> componentFilter() {
        
        return dataComponentType -> dataComponentType != DataComponents.DAMAGE;
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
