package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.PotDecorations;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/item/component/PotDecorations")
@NativeTypeRegistration(value = PotDecorations.class, zenCodeName = "crafttweaker.api.item.component.PotDecorations")
public class ExpandPotDecorations {
    
    @ZenCodeType.StaticExpansionMethod
    public static PotDecorations of(@ZenCodeType.Nullable Item back, @ZenCodeType.Nullable Item left, @ZenCodeType.Nullable Item right, @ZenCodeType.Nullable Item front) {
        
        return new PotDecorations(back, left, right, front);
    }
    
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("back")
    public static Item back(PotDecorations internal) {
        
        return internal.back().orElse(null);
    }
    
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("front")
    public static Item front(PotDecorations internal) {
        
        return internal.front().orElse(null);
    }
    
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("right")
    public static Item right(PotDecorations internal) {
        
        return internal.right().orElse(null);
    }
    
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("left")
    public static Item left(PotDecorations internal) {
        
        return internal.left().orElse(null);
    }
    
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("ordered")
    public static List<Item> ordered(PotDecorations internal) {
        
        return internal.ordered();
    }
    
}
