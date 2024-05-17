package com.blamejared.crafttweaker.natives.item.type.armor;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/type/armor/ArmorMaterial")
@NativeTypeRegistration(value = ArmorMaterial.class, zenCodeName = "crafttweaker.api.item.type.armor.ArmorMaterial")
public class ExpandArmorMaterial {
    
    @ZenCodeType.Method
    public static int getDefense(ArmorMaterial internal, ArmorItem.Type type) {
        
        return internal.getDefense(type);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("enchantmentValue")
    public static int enchantmentValue(ArmorMaterial internal) {
        
        return internal.enchantmentValue();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("equipSound")
    public static SoundEvent equipSound(ArmorMaterial internal) {
        
        return internal.equipSound().value();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("repairIngredient")
    public static IIngredient repairIngredient(ArmorMaterial internal) {
        
        return IIngredient.fromIngredient(internal.repairIngredient().get());
    }
    
    @ZenCodeType.Getter("registryName")
    public static ResourceLocation getName(ArmorMaterial internal) {
        
        return BuiltInRegistries.ARMOR_MATERIAL.getKey(internal);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("toughness")
    public static float toughness(ArmorMaterial internal) {
        
        return internal.toughness();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("knockbackResistance")
    public static float knockbackResistance(ArmorMaterial internal) {
        
        return internal.knockbackResistance();
    }
    
}
