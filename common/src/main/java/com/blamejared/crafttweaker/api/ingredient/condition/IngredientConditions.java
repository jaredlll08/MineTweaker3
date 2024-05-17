package com.blamejared.crafttweaker.api.ingredient.condition;

import com.blamejared.crafttweaker.api.item.IItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class IngredientConditions implements Predicate<IItemStack> {
    
    public static IngredientConditions EMPTY = new IngredientConditions();
    private final List<IIngredientCondition> conditions;
    private final List<IIngredientCondition> conditionsView;
    
    public IngredientConditions() {
        
        this(new ArrayList<>());
    }
    
    public IngredientConditions(List<IIngredientCondition> conditions) {
        
        this.conditions = conditions;
        conditionsView = Collections.unmodifiableList(this.conditions);
    }
    
    public IngredientConditions copy() {
        
        return new IngredientConditions(new ArrayList<>(conditions));
    }
    
    public boolean isEmpty() {
        
        return this == EMPTY || this.conditions().isEmpty();
    }
    
    @Override
    public boolean test(IItemStack stack) {
        
        return conditions.stream().allMatch(iIngredientCondition -> iIngredientCondition.matches(stack));
    }
    
    public String getCommandString(String base) {
        
        return conditions().stream()
                .map(iIngredientTransformer -> iIngredientTransformer.getCommandString(base))
                .collect(Collectors.joining());
    }
    
    public void add(IIngredientCondition transformer) {
        
        if(this == EMPTY) {
            throw new UnsupportedOperationException("Unable to add '" + transformer + "' to an empty conditions!");
        }
        this.conditions.add(transformer);
    }
    
    public List<IIngredientCondition> conditions() {
        
        return conditionsView;
    }
    
    @Override
    public boolean equals(Object o) {
        
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        IngredientConditions that = (IngredientConditions) o;
        return Objects.equals(conditions, that.conditions) && Objects.equals(conditionsView, that.conditionsView);
    }
    
    @Override
    public int hashCode() {
        
        return Objects.hash(conditions, conditionsView);
    }
    
}
