package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.component.LodestoneTracker;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Optional;

@ZenRegister
@Document("vanilla/api/item/component/LodestoneTracker")
@NativeTypeRegistration(value = LodestoneTracker.class, zenCodeName = "crafttweaker.api.item.component.LodestoneTracker")
public class ExpandLodestoneTracker {
    
    @ZenCodeType.StaticExpansionMethod
    public static LodestoneTracker of(@ZenCodeType.Nullable GlobalPos target, boolean tracked) {
        
        return new LodestoneTracker(Optional.ofNullable(target), tracked);
    }
    
    @ZenCodeType.Method
    public static LodestoneTracker tick(LodestoneTracker internal, ServerLevel level) {
        
        return internal.tick(level);
    }
    
    @ZenCodeType.Getter("tracked")
    public static boolean tracked(LodestoneTracker internal) {
        
        return internal.tracked();
    }
    
    @ZenCodeType.Getter("target")
    public static Optional<GlobalPos> target(LodestoneTracker internal) {
        
        return internal.target();
    }
    
}
