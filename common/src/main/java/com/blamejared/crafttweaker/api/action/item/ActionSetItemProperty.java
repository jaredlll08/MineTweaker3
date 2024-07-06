package com.blamejared.crafttweaker.api.action.item;

import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
import com.blamejared.crafttweaker.api.action.internal.CraftTweakerAction;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.api.zencode.IScriptLoadSource;
import com.blamejared.crafttweaker.mixin.common.access.item.AccessItem;
import com.blamejared.crafttweaker.natives.item.ExpandItem;
import com.blamejared.crafttweaker.platform.Services;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import org.apache.logging.log4j.Logger;

public class ActionSetItemProperty<T> extends CraftTweakerAction implements IUndoableAction {
    
    private final Item item;
    private final DataComponentType<T> component;
    private final T newValue;
    private T oldValue;
    
    public ActionSetItemProperty(Item item, DataComponentType<T> component, T newValue) {
        
        this.item = item;
        this.component = component;
        this.newValue = newValue;
    }
    
    @Override
    public void apply() {
        // components are interned on Item, and we don't want to change *all* items
        ((AccessItem) item).crafttweaker$setComponents(DataComponentMap.builder()
                .addAll(item.components())
                .build());
        
        if(item.components() instanceof DataComponentMap.Builder.SimpleMap map) {
            if(newValue == null) {
                oldValue = GenericUtil.uncheck(map.map().remove(component));
            } else {
                oldValue = GenericUtil.uncheck(map.map().put(component, newValue));
            }
        } else {
            throw new IllegalStateException("Unknown DataComponentMap: " + item.components().getClass());
        }
    }
    
    @Override
    public String describe() {
        
        if(newValue == null) {
            return "Removing '" + Services.REGISTRY.keyOrThrow(Registries.DATA_COMPONENT_TYPE, component) + "' from item '" + ExpandItem.getCommandString(item) + "'";
        } else {
            return "Setting the value of '" + Services.REGISTRY.keyOrThrow(Registries.DATA_COMPONENT_TYPE, component) + "' to '" + newValue + "' on item '" + ExpandItem.getCommandString(item) + "'";
        }
    }
    
    @Override
    public void undo() {
        
        ((AccessItem) item).crafttweaker$setComponents(DataComponentMap.builder()
                .addAll(item.components())
                .build());
        
        if(item.components() instanceof DataComponentMap.Builder.SimpleMap map) {
            if(oldValue == null) {
                map.map().remove(component);
            } else {
                map.map().put(component, oldValue);
            }
        } else {
            throw new IllegalStateException("Unknown DataComponentMap: " + item.components().getClass());
        }
    }
    
    @Override
    public String describeUndo() {
        
        return "Reset the value of '" + Services.REGISTRY.keyOrThrow(Registries.DATA_COMPONENT_TYPE, component) + "' on item '" + ExpandItem.getCommandString(item) + "'";
    }
    
    @Override
    public boolean shouldApplyOn(IScriptLoadSource source, Logger logger) {
        
        return true;
    }
    
}