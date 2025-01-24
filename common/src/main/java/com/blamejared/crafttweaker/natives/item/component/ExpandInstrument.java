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
    
    /**
     * Gets the sound event of the Instrument.
     *
     * @return The sound event of the Instrument.
     */
    @ZenCodeType.Getter("soundEvent")
    public static SoundEvent soundEvent(Instrument internal) {
        
        return internal.soundEvent().value();
    }
    
    /**
     * Gets the use duration of the Instrument.
     *
     * @return The use duration of the Instrument.
     */
    @ZenCodeType.Getter("useDuration")
    public static int useDuration(Instrument internal) {
        
        return internal.useDuration();
    }
    
    /**
     * Gets the range of the Instrument.
     *
     * @return The range of the Instrument.
     */
    @ZenCodeType.Getter("range")
    public static float range(Instrument internal) {
        
        return internal.range();
    }
    
    /**
     * Gets the registry name of the Instrument.
     *
     * @return The registry name of the Instrument.
     */
    @ZenCodeType.Getter("registryName")
    public static ResourceLocation getRegistryName(Instrument internal) {
        
        return CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess(registryAccess -> registryAccess.registryOrThrow(Registries.INSTRUMENT)
                        .getKey(internal));
    }
    
    /**
     * Gets the command string of the Instrument.
     *
     * @return The command string of the Instrument.
     */
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(Instrument internal) {
        
        return "<instrument:" + Services.REGISTRY.keyOrThrow(Registries.INSTRUMENT, internal) + ">";
    }
    
}
