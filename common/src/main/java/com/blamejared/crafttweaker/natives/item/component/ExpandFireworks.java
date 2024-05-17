package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/item/component/Fireworks")
@NativeTypeRegistration(value = Fireworks.class, zenCodeName = "crafttweaker.api.item.component.Fireworks")
public class ExpandFireworks {
    
    @ZenCodeType.StaticExpansionMethod
    public static Fireworks of(int flightDuration, List<FireworkExplosion> explosions) {
        
        return new Fireworks(flightDuration, explosions);
    }
    
    @ZenCodeType.Getter("explosion")
    public static List<FireworkExplosion> explosions(Fireworks internal) {
        
        return internal.explosions();
    }
    
    @ZenCodeType.Getter("flightDuration")
    public static int flightDuration(Fireworks internal) {
        
        return internal.flightDuration();
    }
    
}
