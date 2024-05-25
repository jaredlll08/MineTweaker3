package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/item/component/SuspiciousStewEffects")
@NativeTypeRegistration(value = SuspiciousStewEffects.class, zenCodeName = "crafttweaker.api.item.component.SuspiciousStewEffects")
public class ExpandSuspiciousStewEffects {
    
    @ZenCodeType.StaticExpansionMethod
    public static SuspiciousStewEffects of(List<SuspiciousStewEffects.Entry> entries) {
        
        return new SuspiciousStewEffects(entries);
    }
    
    @ZenCodeType.Method
    public static SuspiciousStewEffects withEffectAdded(SuspiciousStewEffects internal, SuspiciousStewEffects.Entry entry) {
        
        return internal.withEffectAdded(entry);
    }
    
    @ZenCodeType.Getter("effects")
    public static List<SuspiciousStewEffects.Entry> effects(SuspiciousStewEffects internal) {
        
        return internal.effects();
    }
    
    @ZenRegister
    @Document("vanilla/api/item/component/SuspiciousStewEffectsEntry")
    @NativeTypeRegistration(value = SuspiciousStewEffects.Entry.class, zenCodeName = "crafttweaker.api.item.component.SuspiciousStewEffectsEntry")
    public static class ExpandSuspiciousStewEffectsEntry {
        
        @ZenCodeType.StaticExpansionMethod
        public static SuspiciousStewEffects.Entry of(MobEffect effect, int duration) {
            
            return new SuspiciousStewEffects.Entry(Services.REGISTRY.holderOrThrow(Registries.MOB_EFFECT, effect), duration);
        }
        
        @ZenCodeType.Method
        public static MobEffectInstance createEffectInstance(SuspiciousStewEffects.Entry internal) {
            
            return internal.createEffectInstance();
        }
        
        @ZenCodeType.Getter("duration")
        public static int duration(SuspiciousStewEffects.Entry internal) {
            
            return internal.duration();
        }
        
        @ZenCodeType.Getter("effect")
        public static MobEffect effect(SuspiciousStewEffects.Entry internal) {
            
            return internal.effect().value();
        }
        
    }
    
}
