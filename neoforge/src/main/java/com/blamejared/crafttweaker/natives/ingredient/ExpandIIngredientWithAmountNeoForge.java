package com.blamejared.crafttweaker.natives.ingredient;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredientWithAmount;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Expansion("crafttweaker.api.ingredient.IIngredientWithAmount")
public class ExpandIIngredientWithAmountNeoForge {
    
    @ZenCodeType.Caster(implicit = true)
    public static SizedIngredient asSizedIngredient(IIngredientWithAmount internal) {
        
        return new SizedIngredient(internal.ingredient().asVanillaIngredient(), internal.amount());
    }
    
}
