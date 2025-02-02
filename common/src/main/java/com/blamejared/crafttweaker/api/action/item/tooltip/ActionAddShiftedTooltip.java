package com.blamejared.crafttweaker.api.action.item.tooltip;

import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.tooltip.ITooltipFunction;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ActionAddShiftedTooltip extends ActionTooltipBase {
    
    private final Component content;
    private final ITooltipFunction function;
    
    public ActionAddShiftedTooltip(IIngredient stack, Component content, Component showMessage) {
        
        super(stack);
        this.content = content;
        this.function = (stack1, tooltip, context, flag) -> {
            
            if(Screen.hasShiftDown()) {
                tooltip.add(content);
            } else {
                if(showMessage != null && !showMessage.getString().isEmpty()) {
                    tooltip.add(showMessage);
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
        
        return "Adding \"" + content.getString() + "\" to the shift tooltip for: " + stack.getCommandString();
    }
    
    @Override
    public String describeUndo() {
        
        return "Undoing addition of \"" + content.getString() + "\" to the shift tooltip for: " + stack.getCommandString();
    }
    
}
