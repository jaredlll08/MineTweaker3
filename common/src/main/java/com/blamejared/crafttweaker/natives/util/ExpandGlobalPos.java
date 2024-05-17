package com.blamejared.crafttweaker.natives.util;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/util/GlobalPos")
@NativeTypeRegistration(value = GlobalPos.class, zenCodeName = "crafttweaker.api.util.GlobalPos")
public class ExpandGlobalPos {
    
    @ZenCodeType.StaticExpansionMethod
    public static GlobalPos of(ResourceKey<Level> $$0, BlockPos $$1) {
        
        return GlobalPos.of($$0, $$1);
    }
    
    @ZenCodeType.Getter("pos")
    public static BlockPos pos(GlobalPos internal) {
        
        return internal.pos();
    }
    
    @ZenCodeType.Getter("dimension")
    public static ResourceKey<Level> dimension(GlobalPos internal) {
        
        return internal.dimension();
    }
    
    
}
