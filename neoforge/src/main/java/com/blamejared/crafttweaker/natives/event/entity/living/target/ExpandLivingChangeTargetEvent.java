package com.blamejared.crafttweaker.natives.event.entity.living.target;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.NeoForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenEvent
@Document("neoforge/api/event/entity/living/target/LivingChangeTargetEvent")
@NativeTypeRegistration(value = LivingChangeTargetEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.entity.living.target.LivingChangeTargetEvent")
public class ExpandLivingChangeTargetEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<LivingChangeTargetEvent> BUS = IEventBus.cancelable(
            LivingChangeTargetEvent.class,
            NeoForgeEventBusWire.of(),
            NeoForgeEventCancellationCarrier.of()
    );
    
    @ZenCodeType.Getter("newAboutToBeSetTarget")
    public static LivingEntity getNewAboutToBeSetTarget(LivingChangeTargetEvent internal) {
        
        return internal.getNewAboutToBeSetTarget();
    }
    
    @ZenCodeType.Setter("newAboutToBeSetTarget")
    public static void setNewAboutToBeSetTarget(LivingChangeTargetEvent internal, LivingEntity newTarget) {
        
        internal.setNewAboutToBeSetTarget(newTarget);
    }
    
    @ZenCodeType.Getter("targetType")
    public static LivingChangeTargetEvent.ILivingTargetType getTargetType(LivingChangeTargetEvent internal) {
        
        return internal.getTargetType();
    }
    
    @ZenCodeType.Getter("originalAboutToBeSetTarget")
    public static LivingEntity getOriginalAboutToBeSetTarget(LivingChangeTargetEvent internal) {
        
        return internal.getOriginalAboutToBeSetTarget();
    }
    
}
