package com.blamejared.crafttweaker.natives.item.enchantment.provider;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.enchantment.providers.EnchantmentProvider;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/enchantment/provider/EnchantmentProvider")
@NativeTypeRegistration(value = EnchantmentProvider.class, zenCodeName = "crafttweaker.api.item.enchantment.provider.EnchantmentProvider")
public class ExpandEnchantmentProvider {
    
    @ZenCodeType.Method
    public static void enchant(EnchantmentProvider internal, ItemStack stack, ItemEnchantments.Mutable enchantments, RandomSource random, DifficultyInstance difficulty) {
        
        internal.enchant(stack, enchantments, random, difficulty);
    }
    
    
}
