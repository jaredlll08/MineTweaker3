package com.blamejared.crafttweaker.natives.event.entity.living;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.NeoForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.damagesource.DamageSource;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;
import org.openzen.zencode.java.ZenCodeType;

@SuppressWarnings("UnstableApiUsage")
@ZenRegister
@ZenEvent
@Document("neoforge/api/event/entity/living/LivingShieldBlockEvent")
@NativeTypeRegistration(value = LivingShieldBlockEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.entity.living.LivingShieldBlockEvent")
public class ExpandShieldBlockEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<LivingShieldBlockEvent> BUS = IEventBus.cancelable(
            LivingShieldBlockEvent.class,
            NeoForgeEventBusWire.of(),
            NeoForgeEventCancellationCarrier.of()
    );
    
    @ZenCodeType.Setter("blocked")
    public static void setBlocked(LivingShieldBlockEvent internal, boolean isBlocked) {
        
        internal.setBlocked(isBlocked);
    }
    
    @ZenCodeType.Getter("blocked")
    public static boolean getBlocked(LivingShieldBlockEvent internal) {
        
        return internal.getBlocked();
    }
    
    @ZenCodeType.Getter("originalBlock")
    public static boolean getOriginalBlock(LivingShieldBlockEvent internal) {
        
        return internal.getOriginalBlock();
    }
    
    @ZenCodeType.Setter("shieldDamage")
    public static void setShieldDamage(LivingShieldBlockEvent internal, float damage) {
        
        internal.setShieldDamage(damage);
    }
    
    @ZenCodeType.Setter("blockedDamage")
    public static void setBlockedDamage(LivingShieldBlockEvent internal, float blocked) {
        
        internal.setBlockedDamage(blocked);
    }
    
    @ZenCodeType.Getter("shieldDamage")
    public static float shieldDamage(LivingShieldBlockEvent internal) {
        
        return internal.shieldDamage();
    }
    
    @ZenCodeType.Getter("blockedDamage")
    public static float getBlockedDamage(LivingShieldBlockEvent internal) {
        
        return internal.getBlockedDamage();
    }
    
    @ZenCodeType.Getter("originalBlockedDamage")
    public static float getOriginalBlockedDamage(LivingShieldBlockEvent internal) {
        
        return internal.getOriginalBlockedDamage();
    }
    
    @ZenCodeType.Getter("damageSource")
    public static DamageSource getDamageSource(LivingShieldBlockEvent internal) {
        
        return internal.getDamageSource();
    }
    
    @ZenCodeType.Getter("damageContainer")
    public static DamageContainer getDamageContainer(LivingShieldBlockEvent internal) {
        
        return internal.getDamageContainer();
    }
    
}
