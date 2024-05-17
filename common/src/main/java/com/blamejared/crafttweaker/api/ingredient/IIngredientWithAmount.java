package com.blamejared.crafttweaker.api.ingredient;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import com.blamejared.crafttweaker.api.data.IData;
import com.blamejared.crafttweaker.api.data.IntData;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import org.openzen.zencode.java.ZenCodeType;

/**
 * Consists of an ingredient and an amount.
 */
@ZenRegister
@Document("vanilla/api/ingredient/IIngredientWithAmount")
@ZenCodeType.Name("crafttweaker.api.ingredient.IIngredientWithAmount")
public interface IIngredientWithAmount extends CommandStringDisplayable {
    
    /**
     * The backing ingredient
     */
    @ZenCodeType.Getter("ingredient")
    IIngredient ingredient();
    
    /**
     * Gets the amount of Items in the ItemStack
     *
     * @return ItemStack's amount
     */
    @ZenCodeType.Getter("amount")
    int amount();
    
    @ZenCodeType.Method
    @ZenCodeType.Caster(implicit = true)
    default IData asIData() {
        IData data = this.ingredient().asMapData();
        data.put("count", new IntData(this.amount()));
        return data;
    }
    
}
