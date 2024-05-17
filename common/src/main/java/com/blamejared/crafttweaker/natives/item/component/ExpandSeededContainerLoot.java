package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.component.SeededContainerLoot;
import net.minecraft.world.level.storage.loot.LootTable;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/component/SeededContainerLoot")
@NativeTypeRegistration(value = SeededContainerLoot.class, zenCodeName = "crafttweaker.api.item.component.SeededContainerLoot")
public class ExpandSeededContainerLoot {
    
    @ZenCodeType.StaticExpansionMethod
    public static SeededContainerLoot of(ResourceKey<LootTable> lootTable, long seed) {
        
        return new SeededContainerLoot(lootTable, seed);
    }
    
    @ZenCodeType.Getter("lootTable")
    public static ResourceKey<LootTable> lootTable(SeededContainerLoot internal) {
        
        return internal.lootTable();
    }
    
    @ZenCodeType.Getter("seed")
    public static long seed(SeededContainerLoot internal) {
        
        return internal.seed();
    }
    
}
