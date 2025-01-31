package com.blamejared.crafttweaker.natives.loot;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootContext;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Expansion("crafttweaker.api.loot.LootContext")
public class ExpandForgeLootContext {
    
    /**
     * Gets the queried LootTable id
     *
     * @return The id of the loot table
     */
    @ZenCodeType.Getter("queriedLootTableId")
    public static ResourceLocation getQueriedLootTableId(LootContext internal) {
        
        return internal.getQueriedLootTableId();
    }
    
}
