package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.component.MapPostProcessing;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/component/MapPostProcessing")
@NativeTypeRegistration(value = MapPostProcessing.class, zenCodeName = "crafttweaker.api.item.component.MapPostProcessing")
@BracketEnum("minecraft:map_post_processing")
public class ExpandMapPostProcessing {
    
    @ZenCodeType.Getter("id")
    public static int id(MapPostProcessing internal) {
        
        return internal.id();
    }
    
}
