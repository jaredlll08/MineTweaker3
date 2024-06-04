package com.blamejared.crafttweaker.natives.event;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.neoforged.bus.api.Event;

@ZenRegister
@Document("neoforge/api/event/Event")
@NativeTypeRegistration(value = Event.class, zenCodeName = "crafttweaker.neoforge.api.event.Event")
public class ExpandEvent {
    
}
