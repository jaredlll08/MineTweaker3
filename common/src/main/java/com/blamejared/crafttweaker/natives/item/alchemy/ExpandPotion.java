package com.blamejared.crafttweaker.natives.item.alchemy;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.blamejared.crafttweaker_annotations.annotations.TaggableElement;
import net.minecraft.Optionull;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;
import java.util.Optional;

@ZenRegister
@Document("vanilla/api/item/alchemy/Potion")
@NativeTypeRegistration(value = Potion.class, zenCodeName = "crafttweaker.api.item.alchemy.Potion")
@TaggableElement("minecraft:potion")
public class ExpandPotion {
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("registryName")
    public static ResourceLocation getRegistryName(Potion internal){
        return BuiltInRegistries.POTION.getKey(internal);
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static String getName(String prefix, @ZenCodeType.Optional @ZenCodeType.Nullable Potion potion) {
        
        return Potion.getName(Optional.ofNullable(Optionull.map(potion, potion1 -> Services.REGISTRY.holderOrThrow(Registries.POTION, potion1))), prefix);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("effects")
    public static List<MobEffectInstance> getEffects(Potion internal) {
        
        return internal.getEffects();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("hasInstantEffects")
    public static boolean hasInstantEffects(Potion internal) {
        
        return internal.hasInstantEffects();
    }
    
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(Potion internal) {
        
        return "<potion:" + BuiltInRegistries.POTION.getKey(internal) + ">";
    }
    
}
