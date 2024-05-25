package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.block.state.BlockState;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Map;

@ZenRegister
@Document("vanilla/api/item/component/BlockItemStateProperties")
@NativeTypeRegistration(value = BlockItemStateProperties.class, zenCodeName = "crafttweaker.api.item.component.BlockItemStateProperties")
public class ExpandBlockItemStateProperties {
    
    // there are more methods in BlockItemStateProperties, but they use Property, which isn't exposed.
    @ZenCodeType.StaticExpansionMethod
    public static BlockItemStateProperties of(Map<String, String> properties) {
        
        return new BlockItemStateProperties(properties);
    }
    
    @ZenCodeType.Method
    public static Map<String, String> properties(BlockItemStateProperties internal) {
        
        return internal.properties();
    }
    
    @ZenCodeType.Getter("isEmpty")
    public static boolean isEmpty(BlockItemStateProperties internal) {
        
        return internal.isEmpty();
    }
    
    @ZenCodeType.Method
    public static BlockState apply(BlockItemStateProperties internal, BlockState state) {
        
        return internal.apply(state);
    }
    
}
