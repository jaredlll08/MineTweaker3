package com.blamejared.crafttweaker.natives.loot.condition;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.loot.CanItemPerformAbility;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("neoforge/api/loot/condition/CanItemPerformAbilityLootCondition")
@NativeTypeRegistration(value = CanItemPerformAbility.class, zenCodeName = "crafttweaker.api.loot.condition.CanItemPerformAbilityLootCondition")
public final class ExpandCanToolPerformAction {
    
    @ZenCodeType.StaticExpansionMethod
    public static LootItemCondition.Builder create(final ItemAbility action) {
        
        return CanItemPerformAbility.canItemPerformAbility(action);
    }
    
}

