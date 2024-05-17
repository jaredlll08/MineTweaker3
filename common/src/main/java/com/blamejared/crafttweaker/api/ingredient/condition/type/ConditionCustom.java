package com.blamejared.crafttweaker.api.ingredient.condition.type;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientCondition;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientConditionSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionCustomSerializer;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import org.openzen.zencode.java.ZenCodeType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.ingredient.condition.type.ConditionCustom")
@Document("vanilla/api/ingredient/condition/type/ConditionCustom")
public class ConditionCustom implements IIngredientCondition {
    
    private static final Map<String, Predicate<IItemStack>> KNOWN_CONDITIONS = new HashMap<>();
    
    private final String uid;
    private Predicate<IItemStack> function;
    
    public ConditionCustom(String uid) {
        
        this(uid, null);
    }
    
    public ConditionCustom(String uid, Predicate<IItemStack> function) {
        
        this.uid = uid;
        this.function = function;
        
        if(function != null) {
            KNOWN_CONDITIONS.put(uid, function);
        }
    }
    
    @Override
    public boolean matches(IItemStack stack) {
        
        if(function == null) {
            function = KNOWN_CONDITIONS.get(uid);
        }
        
        if(function == null) {
            throw new IllegalStateException("No condition named '" + uid + "' known!");
        }
        
        return function.test(stack);
    }
    
    @Override
    public String getCommandString(String base) {
        
        return "%s.onlyIf('%s')".formatted(base, uid);
    }
    
    @Override
    public boolean ignoresDamage() {
        // Give people a clean slate, mojang really made damage annoying to deal with in 1.14+, so this way if someone wants to ignore the damage they can
        return true;
    }
    
    public String getUid() {
        
        return uid;
    }
    
    @Override
    public ConditionCustomSerializer getSerializer() {
        
        return ConditionCustomSerializer.INSTANCE;
    }
    
    @Override
    public boolean equals(Object o) {
        
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        
        ConditionCustom that = (ConditionCustom) o;
        
        return Objects.equals(uid, that.uid);
    }
    
    @Override
    public int hashCode() {
        
        return uid != null ? uid.hashCode() : 0;
    }
    
}
