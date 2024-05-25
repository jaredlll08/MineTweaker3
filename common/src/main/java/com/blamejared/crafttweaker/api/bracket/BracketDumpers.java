package com.blamejared.crafttweaker.api.bracket;


import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.BracketDumper;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.api.util.ItemStackUtil;
import com.blamejared.crafttweaker.natives.block.ExpandBlock;
import com.blamejared.crafttweaker.natives.block.entity.ExpandBannerPattern;
import com.blamejared.crafttweaker.natives.component.ExpandDataComponentType;
import com.blamejared.crafttweaker.natives.entity.ExpandEntityType;
import com.blamejared.crafttweaker.natives.entity.attribute.ExpandAttribute;
import com.blamejared.crafttweaker.natives.entity.effect.ExpandMobEffect;
import com.blamejared.crafttweaker.natives.item.alchemy.ExpandPotion;
import com.blamejared.crafttweaker.natives.item.armortrim.ExpandTrimMaterial;
import com.blamejared.crafttweaker.natives.item.armortrim.ExpandTrimPattern;
import com.blamejared.crafttweaker.natives.item.component.ExpandInstrument;
import com.blamejared.crafttweaker.natives.item.enchantment.ExpandEnchantment;
import com.blamejared.crafttweaker.natives.sound.ExpandSoundEvent;
import com.blamejared.crafttweaker.natives.villager.ExpandVillagerProfession;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Collection;
import java.util.stream.Collectors;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.BracketDumpers")
@Document("vanilla/api/BracketDumpers")
public class BracketDumpers {
    
    @ZenCodeType.Method
    @BracketDumper("attribute")
    public static Collection<String> getAttributeDump() {
        
        return BuiltInRegistries.ATTRIBUTE
                .stream()
                .map(ExpandAttribute::getCommandString)
                .collect(Collectors.toSet());
    }
    
    @ZenCodeType.Method
    @BracketDumper("block")
    public static Collection<String> getBlockDump() {
        
        return BuiltInRegistries.BLOCK
                .stream()
                .map(ExpandBlock::getCommandString)
                .collect(Collectors.toSet());
    }
    
    @ZenCodeType.StaticExpansionMethod
    @BracketDumper("fluid")
    public static Collection<String> getFluidStackDump() {
        
        return BuiltInRegistries.FLUID.stream()
                .map(fluid -> IFluidStack.of(fluid, 1).getCommandString())
                .collect(Collectors.toList());
    }
    
    @ZenCodeType.Method
    @BracketDumper("mobeffect")
    public static Collection<String> getEffectDump() {
        
        return BuiltInRegistries.MOB_EFFECT
                .stream()
                .map(ExpandMobEffect::getCommandString)
                .collect(Collectors.toSet());
    }
    
    @ZenCodeType.Method
    @BracketDumper("enchantment")
    public static Collection<String> getEnchantmentDump() {
        
        return BuiltInRegistries.ENCHANTMENT
                .stream()
                .map(ExpandEnchantment::getCommandString)
                .collect(Collectors.toList());
    }
    
    @ZenCodeType.Method
    @BracketDumper("entitytype")
    public static Collection<String> getEntityTypeDump() {
        
        return BuiltInRegistries.ENTITY_TYPE
                .stream()
                .map(internal -> ExpandEntityType.getCommandString(GenericUtil.uncheck(internal)))
                .collect(Collectors.toList());
    }
    
    @ZenCodeType.Method
    @BracketDumper("item")
    public static Collection<String> getItemBracketDump() {
        
        return BuiltInRegistries.ITEM
                .stream()
                .map(Item::getDefaultInstance)
                .map(ItemStackUtil::getCommandString)
                .collect(Collectors.toSet());
    }
    
    @ZenCodeType.Method
    @BracketDumper("potion")
    public static Collection<String> getPotionTypeDump() {
        
        return BuiltInRegistries.POTION
                .stream()
                .map(ExpandPotion::getCommandString)
                .collect(Collectors.toList());
    }
    
    @ZenCodeType.Method
    @BracketDumper("profession")
    public static Collection<String> getProfessionDump() {
        
        return BuiltInRegistries.VILLAGER_PROFESSION
                .stream()
                .map(ExpandVillagerProfession::getCommandString)
                .collect(Collectors.toList());
    }
    
    @ZenCodeType.Method
    @BracketDumper("soundevent")
    public static Collection<String> getSoundEventDump() {
        
        return BuiltInRegistries.SOUND_EVENT
                .stream()
                .map(ExpandSoundEvent::getCommandString)
                .collect(Collectors.toList());
    }
    
    @ZenCodeType.Method
    @BracketDumper("targetingstrategy")
    public static Collection<String> getTargetingStrategyDump() {
        
        return CraftTweakerAPI.getRegistry()
                .getReplacerRegistry()
                .allStrategyNames()
                .stream()
                .map("<targetingstrategy:%s>"::formatted)
                .collect(Collectors.toList());
    }
    
    @BracketDumper("componenttype")
    public static Collection<String> getComponentTypes() {
        
        return BuiltInRegistries.DATA_COMPONENT_TYPE
                .stream()
                .map(ExpandDataComponentType::getCommandString)
                .collect(Collectors.toList());
    }
    
    @BracketDumper("bannerpattern")
    public static Collection<String> getBannerPatterns() {
        
        return Services.REGISTRY.registryOrThrow(Registries.BANNER_PATTERN)
                .stream()
                .map(ExpandBannerPattern::getCommandString)
                .collect(Collectors.toList());
    }
    
    @BracketDumper("instrument")
    public static Collection<String> getInstruments() {
        
        return Services.REGISTRY.registryOrThrow(Registries.INSTRUMENT)
                .stream()
                .map(ExpandInstrument::getCommandString)
                .collect(Collectors.toList());
    }
    
    @BracketDumper("trimpattern")
    public static Collection<String> getTrimpatterns() {
        
        return Services.REGISTRY.registryOrThrow(Registries.TRIM_PATTERN)
                .stream()
                .map(ExpandTrimPattern::getCommandString)
                .collect(Collectors.toList());
    }
    
    @BracketDumper("trimmaterial")
    public static Collection<String> getTrimMaterials() {
        
        return Services.REGISTRY.registryOrThrow(Registries.TRIM_MATERIAL)
                .stream()
                .map(ExpandTrimMaterial::getCommandString)
                .collect(Collectors.toList());
    }
    
}
