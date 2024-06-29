package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;
import java.util.Optional;

@ZenRegister
@Document("vanilla/api/item/component/PotionContents")
@NativeTypeRegistration(value = PotionContents.class, zenCodeName = "crafttweaker.api.item.component.PotionContents")
public class ExpandPotionContents {
    
    @ZenCodeType.StaticExpansionMethod
    public static PotionContents of(Potion potion, int customColour, List<MobEffectInstance> customEffects) {
        return new PotionContents(Optional.of(Holder.direct(potion)), Optional.of(customColour), customEffects);
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static PotionContents of(Potion potion) {
        return new PotionContents(Holder.direct(potion));
    }
    
    @ZenCodeType.Getter("color")
    public static int getColor(PotionContents internal) {
        return internal.getColor();
    }
    
    @ZenCodeType.Getter("hasEffects")
    public static boolean hasEffects(PotionContents internal) {
        return internal.hasEffects();
    }
    
    @ZenCodeType.Getter("customEffects")
    public static List<MobEffectInstance> getCustomEffects(PotionContents internal) {
        return internal.customEffects();
    }
    
    @ZenCodeType.Method
    public static PotionContents withEffectAdded(PotionContents internal, MobEffectInstance effectInstance) {
        return new PotionContents(internal.potion(), internal.customColor(), Util.copyAndAdd(internal.customEffects(), effectInstance));
    }
}
