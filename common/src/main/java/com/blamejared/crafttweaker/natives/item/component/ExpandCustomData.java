package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.component.CustomData;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.Consumer;

@ZenRegister
@Document("vanilla/api/item/component/CustomData")
@NativeTypeRegistration(value = CustomData.class, zenCodeName = "crafttweaker.api.item.component.CustomData")
public class ExpandCustomData {
    
    @ZenCodeType.StaticExpansionMethod
    public static CustomData of(MapData tag) {
        
        return CustomData.of(tag.getInternal());
    }
    
    @ZenCodeType.Getter("copyTag")
    public static CompoundTag copyTag(CustomData internal) {
        
        return internal.copyTag();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.CONTAINS)
    public static boolean contains(CustomData internal, String key) {
        
        return internal.contains(key);
    }
    
    @ZenCodeType.Getter("size")
    public static int size(CustomData internal) {
        
        return internal.size();
    }
    
    @ZenCodeType.Method
    public static boolean matchedBy(CustomData internal, MapData tag) {
        
        return internal.matchedBy(tag.getInternal());
    }
    
    @ZenCodeType.Method
    public static CustomData update(CustomData internal, Consumer<MapData> updater) {
        
        return internal.update(compoundTag -> updater.accept(new MapData(compoundTag)));
    }
    
    @ZenCodeType.Getter("isEmpty")
    public static boolean isEmpty(CustomData internal) {
        
        return internal.isEmpty();
    }
    
    
}
