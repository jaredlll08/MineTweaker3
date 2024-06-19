package com.blamejared.crafttweaker.natives.item.enchantment;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.blamejared.crafttweaker_annotations.annotations.TaggableElement;
import com.google.common.collect.Maps;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Map;

@ZenRegister
@Document("vanilla/api/item/enchantment/Enchantment")
@NativeTypeRegistration(value = Enchantment.class, zenCodeName = "crafttweaker.api.item.enchantment.Enchantment")
@TaggableElement("minecraft:enchantment")
public class ExpandEnchantment {
    
    @ZenCodeType.Method
    public static int getMinCost(Enchantment internal, int level) {
        
        return internal.getMinCost(level);
    }
    
    @ZenCodeType.Getter("definition")
    public static Enchantment.EnchantmentDefinition definition(Enchantment internal) {
        
        return internal.definition();
    }
    
    @ZenCodeType.Getter
    public static int getMaxLevel(Enchantment internal) {
        
        return internal.getMaxLevel();
    }
    
    @ZenCodeType.Getter("description")
    public static Component description(Enchantment internal) {
        
        return internal.description();
    }
    
    @ZenCodeType.Method
    public static int getMaxCost(Enchantment internal, int level) {
        
        return internal.getMaxCost(level);
    }
    
    @ZenCodeType.Method
    public static Map<EquipmentSlot, IItemStack> getSlotItems(Enchantment internal, LivingEntity entity) {
        
        return Maps.transformValues(internal.getSlotItems(entity), IItemStack::of);
    }
    
    @ZenCodeType.Method
    public static boolean matchingSlot(Enchantment internal, EquipmentSlot slot) {
        
        return internal.matchingSlot(slot);
    }
    
    //TODO 1.21.x when we have non item data component types
    //    @ZenCodeType.Getter("effects")
    //    public static DataComponentMap effects(Enchantment internal) {
    //
    //        return internal.effects();
    //    }
    //
    //    public static <T> List<T> getEffects(Enchantment internal, DataComponentType<List<T>> $$0) {
    //        return internal.getEffects($$0);
    //    }
    
    @ZenCodeType.Getter("supportedItems")
    public static Item[] getSupportedItems(Enchantment internal) {
        
        return internal.getSupportedItems().stream().map(Holder::value).toArray(Item[]::new);
    }
    
    @ZenCodeType.Getter("exclusiveSet")
    public static Enchantment[] exclusiveSet(Enchantment internal) {
        
        return internal.exclusiveSet()
                .stream()
                .map(Holder::value)
                .toArray(Enchantment[]::new);
    }
    
    @ZenCodeType.Method
    public static boolean isPrimaryItem(Enchantment internal, IItemStack stack) {
        
        return internal.isPrimaryItem(stack.getInternal());
    }
    
    @ZenCodeType.Method
    public static boolean canEnchant(Enchantment internal, IItemStack stack) {
        
        return internal.canEnchant(stack.getInternal());
    }
    
    @ZenCodeType.Method
    public static boolean isSupportedItem(Enchantment internal, IItemStack stack) {
        
        return internal.isSupportedItem(stack.getInternal());
    }
    
    @ZenCodeType.Getter("anvilCost")
    public static int getAnvilCost(Enchantment internal) {
        
        return internal.getAnvilCost();
    }
    
    
    @ZenCodeType.Getter("weight")
    public static int getWeight(Enchantment internal) {
        
        return internal.getWeight();
    }
    
    @ZenCodeType.Getter("minLevel")
    public static int getMinLevel(Enchantment internal) {
        
        return internal.getMinLevel();
    }
    
    @ZenCodeType.Getter("registryName")
    public static ResourceLocation getRegistryName(Enchantment internal) {
        
        return Services.REGISTRY.keyOrThrow(Registries.ENCHANTMENT, internal);
    }
    
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(Enchantment internal) {
        
        return "<enchantment:" + getRegistryName(internal) + ">";
    }
    
}
