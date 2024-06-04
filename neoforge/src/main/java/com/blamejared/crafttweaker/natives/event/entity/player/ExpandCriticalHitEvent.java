package com.blamejared.crafttweaker.natives.event.entity.player;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenEvent
@Document("neoforge/api/event/entity/player/CriticalHitEvent")
@NativeTypeRegistration(value = CriticalHitEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.entity.player.CriticalHitEvent")
public class ExpandCriticalHitEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<CriticalHitEvent> BUS = IEventBus.direct(
            CriticalHitEvent.class,
            NeoForgeEventBusWire.of()
    );
    
    @ZenCodeType.Getter("target")
    public static Entity getTarget(CriticalHitEvent internal) {
        
        return internal.getTarget();
    }
    
    @ZenCodeType.Setter("damageMultiplier")
    public static void setDamageMultiplier(CriticalHitEvent internal, float mult) {
        
        internal.setDamageMultiplier(mult);
    }
    
    @ZenCodeType.Getter("damageMultiplier")
    public static float getDamageMultiplier(CriticalHitEvent internal) {
        
        return internal.getDamageMultiplier();
    }
    
    @ZenCodeType.Getter("vanillaMultiplier")
    public static float getVanillaMultiplier(CriticalHitEvent internal) {
        
        return internal.getVanillaMultiplier();
    }
    
    @ZenCodeType.Getter("isVanillaCritical")
    public static boolean isVanillaCritical(CriticalHitEvent internal) {
        
        return internal.isVanillaCritical();
    }
    
}
