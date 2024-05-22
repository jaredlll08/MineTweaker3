package com.blamejared.crafttweaker.natives.event.tick;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.NeoForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@ZenRegister
@Document("neoforge/api/event/tick/EntityTickEvent")
@NativeTypeRegistration(value = EntityTickEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.tick.EntityTickEvent")
public class ExpandEntityTickEvent {
    
    @ZenRegister
    @ZenEvent
    @Document("neoforge/api/event/tick/EntityTickPreEvent")
    @NativeTypeRegistration(value = EntityTickEvent.Pre.class, zenCodeName = "crafttweaker.neoforge.api.event.tick.EntityTickPreEvent")
    public static class ExpandServerTickPreEvent {
        
        @ZenEvent.Bus
        public static final IEventBus<EntityTickEvent.Pre> BUS = IEventBus.cancelable(
                EntityTickEvent.Pre.class,
                NeoForgeEventBusWire.of(),
                NeoForgeEventCancellationCarrier.of()
        );
        
    }
    
    @ZenRegister
    @ZenEvent
    @Document("neoforge/api/event/tick/EntityTickPostEvent")
    @NativeTypeRegistration(value = EntityTickEvent.Post.class, zenCodeName = "crafttweaker.neoforge.api.event.tick.EntityTickPostEvent")
    public static class ExpandServerTickPostEvent {
        
        @ZenEvent.Bus
        public static final IEventBus<EntityTickEvent.Post> BUS = IEventBus.direct(
                EntityTickEvent.Post.class,
                NeoForgeEventBusWire.of()
        );
        
    }
    
}
