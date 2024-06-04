package com.blamejared.crafttweaker.natives.event.block.fluid;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.level.block.CreateFluidSourceEvent;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenEvent
@Document("neoforge/api/event/block/fluid/CreateFluidSourceEvent")
@NativeTypeRegistration(value = CreateFluidSourceEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.block.fluid.CreateFluidSourceEvent")
public class ExpandCreateFluidSourceEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<CreateFluidSourceEvent> BUS = IEventBus.direct(
            CreateFluidSourceEvent.class,
            NeoForgeEventBusWire.of()
    );
    
    @ZenCodeType.Getter("level")
    public static Level getLevel(CreateFluidSourceEvent internal) {
        
        return internal.getLevel();
    }
    
    @ZenCodeType.Getter("pos")
    public static BlockPos getPos(CreateFluidSourceEvent internal) {
        
        return internal.getPos();
    }
    
    
}
