package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Instrument;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/component/Instrument")
@NativeTypeRegistration(value = Instrument.class, zenCodeName = "crafttweaker.api.item.component.Instrument")
public class ExpandInstrument {
    
    @ZenCodeType.Getter("soundEvent")
    public static SoundEvent soundEvent(Instrument internal) {
        
        return internal.soundEvent().value();
    }
    
    @ZenCodeType.Getter("useDuration")
    public static int useDuration(Instrument internal) {
        
        return internal.useDuration();
    }
    
    @ZenCodeType.Getter("range")
    public static float range(Instrument internal) {
        
        return internal.range();
    }
    
    @ZenCodeType.Getter("registryName")
    public static ResourceLocation getRegistryName(Instrument internal) {
        
        return CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess(registryAccess -> registryAccess.registryOrThrow(Registries.INSTRUMENT)
                        .getKey(internal));
    }
    
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(Instrument internal) {
        
        return "<instrument:" + Services.REGISTRY.keyOrThrow(Registries.INSTRUMENT, internal) + ">";
    }
    
}
