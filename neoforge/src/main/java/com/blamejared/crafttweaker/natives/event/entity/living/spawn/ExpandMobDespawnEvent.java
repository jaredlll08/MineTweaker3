package com.blamejared.crafttweaker.natives.event.entity.living.spawn;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.neoforged.neoforge.event.entity.living.MobDespawnEvent;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenEvent
@Document("neoforge/api/event/entity/living/spawn/MobDespawnEvent")
@NativeTypeRegistration(value = MobDespawnEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.entity.living.spawn.MobDespawnEvent")
public class ExpandMobDespawnEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<MobDespawnEvent> BUS = IEventBus.direct(
            MobDespawnEvent.class,
            NeoForgeEventBusWire.of()
    );
    
    @ZenCodeType.Setter("result")
    public static void setResult(MobDespawnEvent internal, MobDespawnEvent.Result result) {
        
        internal.setResult(result);
    }
    
    @ZenCodeType.Getter("result")
    public static MobDespawnEvent.Result getResult(MobDespawnEvent internal) {
        
        return internal.getResult();
    }
    
    @ZenRegister
    @Document("neoforge/api/event/entity/living/spawn/MobDespawnEventResult")
    @NativeTypeRegistration(value = MobDespawnEvent.Result.class, zenCodeName = "crafttweaker.neoforge.api.event.entity.living.spawn.MobDespawnEventResult")
    @BracketEnum("neoforge:events/mobdespawn/result")
    public static class ExpandMobAllowDespawnEventResult {
    
    }
    
}
