package com.blamejared.crafttweaker.natives.loot.modifier;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.loot.LootManager;
import com.blamejared.crafttweaker.api.loot.condition.LootConditions;
import com.blamejared.crafttweaker.api.loot.modifier.ILootModifier;
import com.blamejared.crafttweaker.natives.loot.condition.ExpandLootItemEntityPropertyCondition;
import com.blamejared.crafttweaker.natives.loot.condition.ExpandLootItemKilledByPlayerCondition;
import com.blamejared.crafttweaker.natives.predicate.ExpandEntityEquipmentPredicate;
import com.blamejared.crafttweaker.natives.predicate.ExpandEntityPredicate;
import com.blamejared.crafttweaker.natives.predicate.ExpandItemPredicate;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.openzen.zencode.java.ZenCodeType;

/**
 * Additional methods for easier modification of entity-related loot tables.
 */
@Document("vanilla/api/loot/modifier/EntityLootModifiers")
@ZenCodeType.Expansion("crafttweaker.api.entity.EntityType<crafttweaker.api.entity.Entity>")
@ZenRegister
public class ModifierSpecificExpandEntityType {
    
    /**
     * Adds an {@link ILootModifier} to the current entity.
     *
     * @param internal The entity to add the loot modifier to.
     * @param name     The name of the loot modifier.
     * @param modifier The loot modifier to add to the entity.
     */
    @ZenCodeType.Method
    public static void addLootModifier(final EntityType<Entity> internal, final String name, final ILootModifier modifier) {
        
        LootManager.INSTANCE.getModifierManager().register(
                name,
                LootConditions.only(makeForType(internal)),
                modifier
        );
    }
    
    /**
     * Adds an {@link ILootModifier} to the current entity that fires only if the entity was killed by a player.
     *
     * @param internal The entity to add the loot modifier to.
     * @param name     The name of the loot modifier.
     * @param modifier The loot modifier to add to the entity.
     */
    @ZenCodeType.Method
    public static void addPlayerOnlyLootModifier(final EntityType<Entity> internal, final String name, final ILootModifier modifier) {
        
        LootManager.INSTANCE.getModifierManager().register(
                name,
                LootConditions.allOf(makeForType(internal), ExpandLootItemKilledByPlayerCondition.create()),
                modifier
        );
    }
    
    /**
     * Adds an {@link ILootModifier} to the current entity that fires only if the entity was killed with the given
     * weapon.
     *
     * <p>Additional parameters that further specify the weapon, such as NBT, count, or damage, are ignored.</p>
     *
     * @param internal The entity to add the loot modifier to.
     * @param name     The name of the loot modifier.
     * @param weapon   The weapon that needs to be used to kill the entity.
     * @param modifier The loot modifier to add to the entity.
     */
    @ZenCodeType.Method
    public static void addWeaponOnlyLootModifier(final EntityType<Entity> internal, final String name, final IItemStack weapon, final ILootModifier modifier) {
        
        addWeaponOnlyLootModifier(internal, name, weapon, false, modifier);
    }
    
    /**
     * Adds an {@link ILootModifier} to the current entity that fires only if the entity was killed with the given
     * weapon, optionally considering its damage and NBT data.
     *
     * <p>Additional parameters that further specify the weapon, such as count, are ignored.</p>
     *
     * @param internal        The entity to add the loot modifier to.
     * @param name            The name of the loot modifier.
     * @param weapon          The weapon that needs to be used to kill the entity.
     * @param matchComponents Whether to consider components or not when identifying the weapon.
     * @param modifier        The loot modifier to add to the entity.
     */
    @ZenCodeType.Method
    public static void addWeaponOnlyLootModifier(final EntityType<Entity> internal, final String name, final IItemStack weapon,
                                                 final boolean matchComponents, final ILootModifier modifier) {
        
        LootManager.INSTANCE.getModifierManager().register(
                name,
                LootConditions.allOf(makeForType(internal), makeForWeapon(weapon, matchComponents)),
                modifier
        );
    }
    
    /**
     * Adds an {@link ILootModifier} to the current entity that fires only if the entity was killed by a player with the
     * given weapon.
     *
     * <p>Additional parameters that further specify the weapon, such as NBT, count, or damage, are ignored.</p>
     *
     * @param internal The entity to add the loot modifier to.
     * @param name     The name of the loot modifier.
     * @param weapon   The weapon that needs to be used to kill the entity.
     * @param modifier The loot modifier to add to the entity.
     */
    @ZenCodeType.Method
    public static void addWeaponAndPlayerOnlyLootModifier(final EntityType<Entity> internal, final String name, final IItemStack weapon, final ILootModifier modifier) {
        
        addWeaponAndPlayerOnlyLootModifier(internal, name, weapon, false, modifier);
    }
    
    /**
     * Adds an {@link ILootModifier} to the current entity that fires only if the entity was killed by a player with the
     * given weapon, optionally considering its damage and NBT data.
     *
     * <p>Additional parameters that further specify the weapon, such as count, are ignored.</p>
     *
     * @param internal        The entity to add the loot modifier to.
     * @param name            The name of the loot modifier.
     * @param weapon          The weapon that needs to be used to kill the entity.
     * @param matchComponents Whether to consider components or not when identifying the weapon.
     * @param modifier        The loot modifier to add to the entity.
     */
    @ZenCodeType.Method
    public static void addWeaponAndPlayerOnlyLootModifier(final EntityType<Entity> internal, final String name, final IItemStack weapon,
                                                          final boolean matchComponents, final ILootModifier modifier) {
        
        LootManager.INSTANCE.getModifierManager().register(
                name,
                LootConditions.allOf(
                        makeForType(internal),
                        makeForWeapon(weapon, matchComponents),
                        ExpandLootItemKilledByPlayerCondition.create()
                ),
                modifier
        );
    }
    
    private static LootItemCondition.Builder makeForType(final EntityType<Entity> type) {
        
        return ExpandLootItemEntityPropertyCondition.create(
                LootContext.EntityTarget.THIS,
                ExpandEntityPredicate.create(type)
        );
    }
    
    private static LootItemCondition.Builder makeForWeapon(final IItemStack weapon, final boolean matchComponents) {
        
        final ItemPredicate.Builder item = ExpandItemPredicate.create(weapon);
        
        if(matchComponents) {
            item.hasComponents(DataComponentPredicate.allOf(weapon.getComponents()));
        }
        
        
        return ExpandLootItemEntityPropertyCondition.create(
                LootContext.EntityTarget.ATTACKER,
                ExpandEntityPredicate.create()
                        .equipment(ExpandEntityEquipmentPredicate.create().mainhand(item).build())
        );
    }
    
}
