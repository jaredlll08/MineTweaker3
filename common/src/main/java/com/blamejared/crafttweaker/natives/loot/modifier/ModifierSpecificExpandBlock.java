package com.blamejared.crafttweaker.natives.loot.modifier;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.loot.LootManager;
import com.blamejared.crafttweaker.api.loot.condition.LootConditions;
import com.blamejared.crafttweaker.api.loot.modifier.ILootModifier;
import com.blamejared.crafttweaker.natives.loot.condition.ExpandInvertedLootItemCondition;
import com.blamejared.crafttweaker.natives.loot.condition.ExpandLootItemBlockStatePropertyCondition;
import com.blamejared.crafttweaker.natives.loot.condition.ExpandMatchTool;
import com.blamejared.crafttweaker.natives.predicate.ExpandEnchantmentPredicate;
import com.blamejared.crafttweaker.natives.predicate.ExpandItemPredicate;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.advancements.critereon.ItemEnchantmentsPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemSubPredicates;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

/**
 * Additional methods for easier modification of block-related loot tables.
 */
@Document("vanilla/api/loot/modifier/BlockLootModifiers")
@ZenCodeType.Expansion("crafttweaker.api.block.Block")
@ZenRegister
public final class ModifierSpecificExpandBlock {
    
    /**
     * Adds an {@link ILootModifier} to this block, with the given name.
     *
     * @param internal The block to add the loot modifier to.
     * @param name     The name of the loot modifier to add.
     * @param modifier The loot modifier to add.
     */
    @ZenCodeType.Method
    public static void addLootModifier(final Block internal, final String name, final ILootModifier modifier) {
        
        LootManager.INSTANCE.getModifierManager().register(
                name,
                LootConditions.only(ExpandLootItemBlockStatePropertyCondition.create(internal)),
                modifier
        );
    }
    
    /**
     * Adds an {@link ILootModifier} to this block, with the given name, only if it is not harvested with the silk touch enchantment.
     *
     * @param internal The block to add the loot modifier to.
     * @param name     The name of the loot modifier to add.
     * @param modifier The loot modifier to add.
     */
    @ZenCodeType.Method
    public static void addNoSilkTouchLootModifier(final Block internal, final String name, final ILootModifier modifier) {
        
        LootManager.INSTANCE.getModifierManager().register(
                name,
                LootConditions.allOf(
                        ExpandLootItemBlockStatePropertyCondition.create(internal),
                        ExpandInvertedLootItemCondition.create(ExpandMatchTool.create(ExpandItemPredicate.create()
                                .withSubPredicate(ItemSubPredicates.ENCHANTMENTS,
                                        ItemEnchantmentsPredicate.enchantments(List.of(
                                                ExpandEnchantmentPredicate.create(
                                                        Services.REGISTRY.getOrThrow(Registries.ENCHANTMENT, Enchantments.SILK_TOUCH), MinMaxBounds.Ints.atLeast(1)))))))),
                
                modifier
        );
    }
    
    /**
     * Adds an {@link ILootModifier} to this block, firing only if it matches the state outlined in the
     * {@link StatePropertiesPredicate}.
     *
     * @param internal       The block to add the loot modifier to.
     * @param name           The name of the loot modifier to add.
     * @param statePredicate A consumer to configure the {@link StatePropertiesPredicate} to identify the target state.
     * @param modifier       The loot modifier to add.
     */
    @ZenCodeType.Method
    public static void addStateLootModifier(final Block internal, final String name, final StatePropertiesPredicate.Builder statePredicate, final ILootModifier modifier) {
        
        LootManager.INSTANCE.getModifierManager().register(
                name,
                LootConditions.only(ExpandLootItemBlockStatePropertyCondition.create(internal)
                        .setProperties(statePredicate)),
                modifier
        );
    }
    
    /**
     * Adds an {@link ILootModifier} that fires if this block gets broken with the given tool.
     *
     * <p>Parameters that may be attached to the tool such as count, damage, or NBT data are ignored.</p>
     *
     * @param internal The block to add the loot modifier to.
     * @param name     The name of the loot modifier.
     * @param tool     The tool the block was broken with.
     * @param modifier The loot modifier to add to the block.
     */
    @ZenCodeType.Method
    public static void addToolLootModifier(final Block internal, final String name, final IItemStack tool, final ILootModifier modifier) {
        
        addToolLootModifier(internal, name, tool, false, modifier);
    }
    
    /**
     * Adds an {@link ILootModifier} that fires if this block gets broken with the given tool, optionally considering
     * its damage or NBT.
     *
     * <p>Additional parameters that may be attached to the tool, such as count, are ignored.</p>
     *
     * @param internal        The block to add the loot modifier to.
     * @param name            The name of the loot modifier.
     * @param tool            The tool the block was broken with.
     * @param matchComponents Whether to consider components or not when trying to match the tool.
     * @param modifier        The loot modifier to add to the block.
     */
    @ZenCodeType.Method
    public static void addToolLootModifier(final Block internal, final String name, final IItemStack tool, final boolean matchComponents, final ILootModifier modifier) {
        
        final ItemPredicate.Builder predicateBuilder = ExpandItemPredicate.create(tool);
        
        if(matchComponents) {
            predicateBuilder.hasComponents(DataComponentPredicate.allOf(tool.getComponents()));
        }
        
        LootManager.INSTANCE.getModifierManager().register(
                name,
                LootConditions.allOf(
                        ExpandLootItemBlockStatePropertyCondition.create(internal),
                        ExpandMatchTool.create(predicateBuilder)
                ),
                modifier
        );
    }
    
}
