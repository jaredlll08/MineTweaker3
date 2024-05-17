package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.ItemLore;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/item/component/ItemLore")
@NativeTypeRegistration(value = ItemLore.class, zenCodeName = "crafttweaker.api.item.component.ItemLore")
public class ExpandItemLore {
    
    @ZenCodeType.StaticExpansionMethod
    public static ItemLore of(List<Component> lines) {
        
        return new ItemLore(lines);
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static ItemLore of(List<Component> lines, List<Component> styledLines) {
        
        return new ItemLore(lines, styledLines);
    }
    
    @ZenCodeType.Method
    public static ItemLore withLineAdded(ItemLore internal, Component line) {
        
        return internal.withLineAdded(line);
    }
    
    @ZenCodeType.Getter("lines")
    public static List<Component> lines(ItemLore internal) {
        
        return internal.lines();
    }
    
    @ZenCodeType.Getter("styledLines")
    public static List<Component> styledLines(ItemLore internal) {
        
        return internal.styledLines();
    }
    
}
