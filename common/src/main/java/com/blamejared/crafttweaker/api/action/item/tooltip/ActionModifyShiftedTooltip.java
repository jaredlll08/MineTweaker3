package com.blamejared.crafttweaker.api.action.item.tooltip;

import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.tooltip.ITooltipFunction;
import net.minecraft.client.gui.screens.Screen;

public class ActionModifyShiftedTooltip extends ActionTooltipBase {
    
    private final ITooltipFunction function;
    
    public ActionModifyShiftedTooltip(IIngredient stack, ITooltipFunction shiftedFunction, ITooltipFunction unshiftedFunction) {
        
        super(stack);
        this.function = (stack1, tooltip, context, flag) -> {
            
            if(Screen.hasShiftDown()) {
                shiftedFunction.apply(stack1, tooltip, context, flag);
            } else {
                if(unshiftedFunction != null) {
                    unshiftedFunction.apply(stack1, tooltip, context, flag);
                }
            }
        };
    }
    
    @Override
    public void apply() {
        
        getTooltip().add(function);
    }
    
    @Override
    public void undo() {
        
        getTooltip().remove(function);
    }
    
    @Override
    public String describe() {
        
        return "Adding advanced shifted tooltip to: " + stack.getCommandString();
    }
    
    @Override
    public String describeUndo() {
        
        return "Undoing addition of advanced shifted tooltip to: " + stack.getCommandString();
    }
    
}
