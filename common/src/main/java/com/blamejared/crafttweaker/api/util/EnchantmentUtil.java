package com.blamejared.crafttweaker.api.util;


import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.Map;

public class EnchantmentUtil {
    //TODO 1.20.5 stop using this method?
    public static void setEnchantments(Map<Enchantment, Integer> enchMap, ItemStack stack) {
        
        DataComponentType<ItemEnchantments> component = stack.getItem() == Items.ENCHANTED_BOOK ? DataComponents.STORED_ENCHANTMENTS : DataComponents.ENCHANTMENTS;
        if(!enchMap.isEmpty()) {
            ItemEnchantments.Mutable enchants = new ItemEnchantments.Mutable(stack.getOrDefault(component, ItemEnchantments.EMPTY));
            enchMap.forEach(enchants::set);
            stack.set(component, enchants.toImmutable());
        } else {
            stack.remove(component);
        }
    }
    
}
