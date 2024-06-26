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
import net.neoforged.neoforge.common.damagesource.IReductionFunction;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.openzen.zencode.java.ZenCodeType;

@SuppressWarnings("UnstableApiUsage")
@ZenRegister
@ZenEvent
@Document("neoforge/api/event/entity/living/LivingIncomingDamageEvent")
@NativeTypeRegistration(value = LivingIncomingDamageEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.entity.living.LivingIncomingDamageEvent")
public class ExpandLivingIncomingDamageEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<LivingIncomingDamageEvent> BUS = IEventBus.cancelable(
            LivingIncomingDamageEvent.class,
            NeoForgeEventBusWire.of(),
            NeoForgeEventCancellationCarrier.of()
    );
    
    @ZenCodeType.Method
    public static void setInvulnerabilityTicks(LivingIncomingDamageEvent internal, int ticks) {
        
        internal.setInvulnerabilityTicks(ticks);
    }
    
    @ZenCodeType.Method
    public static void addReductionModifier(LivingIncomingDamageEvent internal, DamageContainer.Reduction type, IReductionFunction reductionFunc) {
        
        internal.addReductionModifier(type, reductionFunc);
    }
    
    @ZenCodeType.Setter("amount")
    public static void setAmount(LivingIncomingDamageEvent internal, float newDamage) {
        
        internal.setAmount(newDamage);
    }
    
    @ZenCodeType.Getter("originalAmount")
    public static float getOriginalAmount(LivingIncomingDamageEvent internal) {
        
        return internal.getOriginalAmount();
    }
    
    @ZenCodeType.Getter("amount")
    public static float getAmount(LivingIncomingDamageEvent internal) {
        
        return internal.getAmount();
    }
    
    @ZenCodeType.Getter("source")
    public static DamageSource getSource(LivingIncomingDamageEvent internal) {
        
        return internal.getSource();
    }
    
    @ZenCodeType.Getter("container")
    public static DamageContainer getContainer(LivingIncomingDamageEvent internal) {
        
        return internal.getContainer();
    }
    
}
