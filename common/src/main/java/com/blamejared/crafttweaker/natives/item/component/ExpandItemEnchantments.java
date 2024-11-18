package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ZenRegister
@Document("vanilla/api/item/component/ItemEnchantments")
@NativeTypeRegistration(value = ItemEnchantments.class, zenCodeName = "crafttweaker.api.item.component.ItemEnchantments")
public class ExpandItemEnchantments {
    
    @ZenCodeType.StaticExpansionMethod
    public static ItemEnchantments empty() {
        
        return ItemEnchantments.EMPTY;
    }
    
    @ZenCodeType.Method
    public static int getLevel(ItemEnchantments internal, Enchantment enchantment) {
        
        return internal.getLevel(Services.REGISTRY.holderOrThrow(Registries.ENCHANTMENT, enchantment));
    }
    
    @ZenCodeType.Getter("entries")
    public static Map<Enchantment, Integer> entries(ItemEnchantments internal) {
        
        return internal.entrySet()
                .stream()
                .map(holderEntry -> Map.entry(holderEntry.getKey().value(), holderEntry.getIntValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (enchantment, enchantment2) -> enchantment));
    }
    
    @ZenCodeType.Method
    public static ItemEnchantments withTooltip(ItemEnchantments internal, boolean withTooltip) {
        
        return internal.withTooltip(withTooltip);
    }
    
    @ZenCodeType.Getter("size")
    public static int size(ItemEnchantments internal) {
        
        return internal.size();
    }
    
    @ZenCodeType.Getter("keySet")
    public static Set<Enchantment> keySet(ItemEnchantments internal) {
        
        return internal.keySet().stream().map(Holder::value).collect(Collectors.toSet());
    }
    
    @ZenCodeType.Getter("isEmpty")
    public static boolean isEmpty(ItemEnchantments internal) {
        
        return internal.isEmpty();
    }
    
    @ZenRegister
    @Document("vanilla/api/item/component/ItemEnchantmentsMutable")
    @NativeTypeRegistration(value = ItemEnchantments.Mutable.class, zenCodeName = "crafttweaker.api.item.component.ItemEnchantmentsMutable")
    public static class ExpandItemEnchantmentsMutable {
        
        @ZenCodeType.StaticExpansionMethod
        public static ItemEnchantments.Mutable of(@ZenCodeType.Optional ItemEnchantments enchantments) {
            
            if(enchantments == null) {
                enchantments = ItemEnchantments.EMPTY;
            }
            return new ItemEnchantments.Mutable(enchantments);
        }
        
        @ZenCodeType.Method
        public static void setEnchantment(ItemEnchantments.Mutable internal, Enchantment enchantment, int level) {
            
            internal.set(Services.REGISTRY.holderOrThrow(Registries.ENCHANTMENT, enchantment), level);
        }
        
        @ZenCodeType.Getter("keySet")
        public static Set<Enchantment> keySet(ItemEnchantments.Mutable internal) {
            
            return internal.keySet().stream().map(Holder::value).collect(Collectors.toSet());
        }
        
        @ZenCodeType.Method
        public static void removeIf(ItemEnchantments.Mutable internal, Predicate<Enchantment> predicate) {
            
            internal.removeIf(enchantmentHolder -> predicate.test(enchantmentHolder.value()));
        }
        
        @ZenCodeType.Method
        public static ItemEnchantments toImmutable(ItemEnchantments.Mutable internal) {
            
            return internal.toImmutable();
        }
        
        @ZenCodeType.Method
        public static void upgrade(ItemEnchantments.Mutable internal, Enchantment enchantment, int level) {
            
            internal.upgrade(Services.REGISTRY.holderOrThrow(Registries.ENCHANTMENT, enchantment), level);
        }
        
        @ZenCodeType.Method
        public static int getLevel(ItemEnchantments.Mutable internal, Enchantment enchantment) {
            
            return internal.getLevel(Services.REGISTRY.holderOrThrow(Registries.ENCHANTMENT, enchantment));
        }
        
    }
    
}
