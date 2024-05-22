package com.blamejared.crafttweaker.natives.event.tick;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@ZenRegister
@Document("neoforge/api/event/tick/PlayerTickEvent")
@NativeTypeRegistration(value = PlayerTickEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.tick.PlayerTickEvent")
public class ExpandPlayerTickEvent {
    
    @ZenRegister
    @ZenEvent
    @Document("neoforge/api/event/tick/PlayerTickPreEvent")
    @NativeTypeRegistration(value = PlayerTickEvent.Pre.class, zenCodeName = "crafttweaker.neoforge.api.event.tick.PlayerTickPreEvent")
    public static class ExpandServerTickPreEvent {
        
        @ZenEvent.Bus
        public static final IEventBus<PlayerTickEvent.Pre> BUS = IEventBus.direct(
                PlayerTickEvent.Pre.class,
                NeoForgeEventBusWire.of()
        );
        
    }
    
    @ZenRegister
    @ZenEvent
    @Document("neoforge/api/event/tick/PlayerTickPostEvent")
    @NativeTypeRegistration(value = PlayerTickEvent.Post.class, zenCodeName = "crafttweaker.neoforge.api.event.tick.PlayerTickPostEvent")
    public static class ExpandServerTickPostEvent {
        
        @ZenEvent.Bus
        public static final IEventBus<PlayerTickEvent.Post> BUS = IEventBus.direct(
                PlayerTickEvent.Post.class,
                NeoForgeEventBusWire.of()
        );
        
    }
    
}
