package com.blamejared.crafttweaker.api.action.misc;

import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
import com.blamejared.crafttweaker.api.action.internal.CraftTweakerAction;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.zencode.IScriptLoadSource;
import com.blamejared.crafttweaker.platform.Services;
import net.minecraft.world.level.block.ComposterBlock;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ActionSetCompostable extends CraftTweakerAction implements IUndoableAction {
    
    private final IItemStack stack;
    private final float newValue;
    private final float oldValue;
    
    public ActionSetCompostable(IItemStack stack, float newValue) {
        
        this.stack = stack;
        this.newValue = newValue;
        this.oldValue = ComposterBlock.COMPOSTABLES.getOrDefault(stack.getInternal().getItem(), -1);
    }
    
    @Override
    public void apply() {
        
        Services.PLATFORM.setCompostable(stack, Optional.of(newValue).filter(aFloat -> aFloat > 0), false);
    }
    
    @Override
    public String describe() {
        
        return "Setting Composter value of: " + stack.getCommandString() + ", to: " + newValue + ", from: " + oldValue;
    }
    
    @Override
    public void undo() {
        
        Services.PLATFORM.setCompostable(stack, Optional.of(oldValue).filter(aFloat -> aFloat > 0), true);
    }
    
    @Override
    public String describeUndo() {
        
        return "Undoing setting Composter value of: " + stack.getCommandString() + ", to: " + newValue + ", reverting to: " + oldValue;
    }
    
    @Override
    public boolean shouldApplyOn(final IScriptLoadSource source, final Logger logger) {
        
        return true;
    }
    
}
