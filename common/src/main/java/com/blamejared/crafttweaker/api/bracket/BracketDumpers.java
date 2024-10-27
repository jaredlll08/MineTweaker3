package com.blamejared.crafttweaker.api.bracket;


import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.BracketDumper;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.util.ItemStackUtil;
import com.blamejared.crafttweaker.natives.block.ExpandBlock;
import com.blamejared.crafttweaker.natives.block.entity.ExpandBannerPattern;
import com.blamejared.crafttweaker.natives.block.entity.ExpandDecoratedPotPattern;
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
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.BracketDumpers")
@Document("vanilla/api/BracketDumpers")
public class BracketDumpers {
    
    public static <T> Collection<String> dumpRegistry(ResourceKey<Registry<T>> key, Function<T, String> toString) {
        
        return Services.REGISTRY.registryOrThrow(key).stream().map(toString).collect(Collectors.toSet());
    }
    
    @ZenCodeType.Method
    @BracketDumper("attribute")
    public static Collection<String> getAttributeDump() {
        
        return dumpRegistry(Registries.ATTRIBUTE, ExpandAttribute::getCommandString);
    }
    
    @ZenCodeType.Method
    @BracketDumper("block")
    public static Collection<String> getBlockDump() {
        
        return dumpRegistry(Registries.BLOCK, ExpandBlock::getCommandString);
    }
    
    @ZenCodeType.Method
    @BracketDumper("fluid")
    public static Collection<String> getFluidStackDump() {
        
        return dumpRegistry(Registries.FLUID, fluid -> IFluidStack.of(fluid, 1).getCommandString());
    }
    
    @ZenCodeType.Method
    @BracketDumper("effect")
    public static Collection<String> getEffectDump() {
        
        return dumpRegistry(Registries.MOB_EFFECT, ExpandMobEffect::getCommandString);
    }
    
    @ZenCodeType.Method
    @BracketDumper("enchantment")
    public static Collection<String> getEnchantmentDump() {
        
        return dumpRegistry(Registries.ENCHANTMENT, ExpandEnchantment::getCommandString);
    }
    
    @ZenCodeType.Method
    @BracketDumper("entitytype")
    public static Collection<String> getEntityTypeDump() {
        
        return dumpRegistry(Registries.ENTITY_TYPE, ExpandEntityType::rawGetCommandString);
    }
    
    @ZenCodeType.Method
    @BracketDumper("item")
    public static Collection<String> getItemBracketDump() {
        
        return dumpRegistry(Registries.ITEM, ItemStackUtil::getCommandString);
    }
    
    @ZenCodeType.Method
    @BracketDumper("potion")
    public static Collection<String> getPotionTypeDump() {
        
        return dumpRegistry(Registries.POTION, ExpandPotion::getCommandString);
    }
    
    @ZenCodeType.Method
    @BracketDumper("profession")
    public static Collection<String> getProfessionDump() {
        
        return dumpRegistry(Registries.VILLAGER_PROFESSION, ExpandVillagerProfession::getCommandString);
    }
    
    @ZenCodeType.Method
    @BracketDumper("soundevent")
    public static Collection<String> getSoundEventDump() {
        
        return dumpRegistry(Registries.SOUND_EVENT, ExpandSoundEvent::getCommandString);
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
    
    @ZenCodeType.Method
    @BracketDumper("componenttype")
    public static Collection<String> getComponentTypes() {
        
        return dumpRegistry(Registries.DATA_COMPONENT_TYPE, ExpandDataComponentType::getCommandString);
    }
    
    @ZenCodeType.Method
    @BracketDumper("bannerpattern")
    public static Collection<String> getBannerPatterns() {
        
        return dumpRegistry(Registries.BANNER_PATTERN, ExpandBannerPattern::getCommandString);
    }
    
    @ZenCodeType.Method
    @BracketDumper("instrument")
    public static Collection<String> getInstruments() {
        
        return dumpRegistry(Registries.INSTRUMENT, ExpandInstrument::getCommandString);
    }
    
    @ZenCodeType.Method
    @BracketDumper("trimpattern")
    public static Collection<String> getTrimPatterns() {
        
        return dumpRegistry(Registries.TRIM_PATTERN, ExpandTrimPattern::getCommandString);
    }
    
    @ZenCodeType.Method
    @BracketDumper("trimmaterial")
    public static Collection<String> getTrimMaterials() {
        
        return dumpRegistry(Registries.TRIM_MATERIAL, ExpandTrimMaterial::getCommandString);
    }
    
    @ZenCodeType.Method
    @BracketDumper("decoratedpotpattern")
    public static Collection<String> getDecoratedPotPatterns() {
        
        return dumpRegistry(Registries.DECORATED_POT_PATTERN, ExpandDecoratedPotPattern::getCommandString);
    }
    
}
