package com.blamejared.crafttweaker.natives.event.entity.living;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.damagesource.DamageSource;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import org.openzen.zencode.java.ZenCodeType;

@SuppressWarnings("UnstableApiUsage")
@ZenRegister
@Document("neoforge/api/event/entity/living/LivingDamageEvent")
@NativeTypeRegistration(value = LivingDamageEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.entity.living.LivingDamageEvent")
public class ExpandLivingDamageEvent {
    
    
    @ZenRegister
    @ZenEvent
    @Document("neoforge/api/event/entity/living/LivingPreDamageEvent")
    @NativeTypeRegistration(value = LivingDamageEvent.Pre.class, zenCodeName = "crafttweaker.neoforge.api.event.entity.living.LivingPreDamageEvent")
    public static class ExpandLivingPreDamageEvent {
        
        @ZenEvent.Bus
        public static final IEventBus<LivingDamageEvent.Pre> BUS = IEventBus.direct(
                LivingDamageEvent.Pre.class,
                NeoForgeEventBusWire.of()
        );
        
        @ZenCodeType.Getter("container")
        public static DamageContainer getContainer(LivingDamageEvent.Pre internal) {
            
            return internal.getContainer();
        }
        
    }
    
    @ZenRegister
    @ZenEvent
    @Document("neoforge/api/event/entity/living/LivingPostDamageEvent")
    @NativeTypeRegistration(value = LivingDamageEvent.Post.class, zenCodeName = "crafttweaker.neoforge.api.event.entity.living.LivingPostDamageEvent")
    public static class ExpandLivingPostDamageEvent {
        
        @ZenEvent.Bus
        public static final IEventBus<LivingDamageEvent.Post> BUS = IEventBus.direct(
                LivingDamageEvent.Post.class,
                NeoForgeEventBusWire.of()
        );
        
        @ZenCodeType.Method
        public static float getReduction(LivingDamageEvent.Post internal, DamageContainer.Reduction reduction) {
            
            return internal.getReduction(reduction);
        }
        
        @ZenCodeType.Getter("postAttackInvulnerabilityTicks")
        public static int getPostAttackInvulnerabilityTicks(LivingDamageEvent.Post internal) {
            
            return internal.getPostAttackInvulnerabilityTicks();
        }
        
        @ZenCodeType.Getter("shieldDamage")
        public static float getShieldDamage(LivingDamageEvent.Post internal) {
            
            return internal.getShieldDamage();
        }
        
        @ZenCodeType.Getter("blockedDamage")
        public static float getBlockedDamage(LivingDamageEvent.Post internal) {
            
            return internal.getBlockedDamage();
        }
        
        @ZenCodeType.Getter("newDamage")
        public static float getNewDamage(LivingDamageEvent.Post internal) {
            
            return internal.getNewDamage();
        }
        
        @ZenCodeType.Getter("source")
        public static DamageSource getSource(LivingDamageEvent.Post internal) {
            
            return internal.getSource();
        }
        
        @ZenCodeType.Getter("originalDamage")
        public static float getOriginalDamage(LivingDamageEvent.Post internal) {
            
            return internal.getOriginalDamage();
        }
        
    }
    
}
