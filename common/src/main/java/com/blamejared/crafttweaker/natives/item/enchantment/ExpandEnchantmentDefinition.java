package com.blamejared.crafttweaker.natives.item.enchantment;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Collections;
import java.util.List;

@ZenRegister
@Document("vanilla/api/item/enchantment/EnchantmentDefinition")
@NativeTypeRegistration(value = Enchantment.EnchantmentDefinition.class, zenCodeName = "crafttweaker.api.item.enchantment.EnchantmentDefinition")
public class ExpandEnchantmentDefinition {
    
    @ZenCodeType.Getter("supportedItems")
    public static Item[] supportedItems(Enchantment.EnchantmentDefinition internal) {
        
        return internal.supportedItems().stream().map(Holder::value).toArray(Item[]::new);
    }
    
    @ZenCodeType.Getter("maxCost")
    public static Enchantment.Cost maxCost(Enchantment.EnchantmentDefinition internal) {
        
        return internal.maxCost();
    }
    
    @ZenCodeType.Getter("weight")
    public static int weight(Enchantment.EnchantmentDefinition internal) {
        
        return internal.weight();
    }
    
    @ZenCodeType.Getter("maxLevel")
    public static int maxLevel(Enchantment.EnchantmentDefinition internal) {
        
        return internal.maxLevel();
    }
    
    @ZenCodeType.Getter("anvilCost")
    public static int anvilCost(Enchantment.EnchantmentDefinition internal) {
        
        return internal.anvilCost();
    }
    
    @ZenCodeType.Getter("slots")
    public static List<EquipmentSlotGroup> slots(Enchantment.EnchantmentDefinition internal) {
        
        return internal.slots();
    }
    
    @ZenCodeType.Getter("minCost")
    public static Enchantment.Cost minCost(Enchantment.EnchantmentDefinition internal) {
        
        return internal.minCost();
    }
    
    @ZenCodeType.Getter("primaryItems")
    public static Item[] primaryItems(Enchantment.EnchantmentDefinition internal) {
        
        if(internal.primaryItems().isEmpty()) {
            return new Item[0];
        }
        return internal.primaryItems()
                .map(holders -> holders.stream().map(Holder::value).toList())
                .orElseGet(Collections::emptyList)
                .toArray(Item[]::new);
    }
    
}
