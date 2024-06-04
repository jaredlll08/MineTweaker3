package com.blamejared.crafttweaker.natives.event.entity.player;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.CanContinueSleepingEvent;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenEvent
@Document("neoforge/api/event/entity/player/CanContinueSleepingEvent")
@NativeTypeRegistration(value = CanContinueSleepingEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.entity.player.CanContinueSleepingEvent")
public class ExpandSleepingLocationCheckEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<CanContinueSleepingEvent> BUS = IEventBus.direct(
            CanContinueSleepingEvent.class,
            NeoForgeEventBusWire.of()
    );
    
    @ZenCodeType.Setter("continueSleeping")
    public static void setContinueSleeping(CanContinueSleepingEvent internal, boolean sleeping) {
        
        internal.setContinueSleeping(sleeping);
    }
    
    @ZenCodeType.Getter("continueSleeping")
    public static boolean mayContinueSleeping(CanContinueSleepingEvent internal) {
        
        return internal.mayContinueSleeping();
    }
    
    @ZenCodeType.Getter("problem")
    public static Player.@ZenCodeType.Nullable BedSleepingProblem getProblem(CanContinueSleepingEvent internal) {
        
        return internal.getProblem();
    }
    
}
