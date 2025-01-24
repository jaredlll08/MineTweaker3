package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.tag.type.KnownTag;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

/**
 * Represents the tool properties of an item, including mining speed, damage per block, and rules for specific blocks.
 * This class is used to define how a tool interacts with blocks in the game.
 */
@ZenRegister
@Document("vanilla/api/item/component/Tool")
@NativeTypeRegistration(value = Tool.class, zenCodeName = "crafttweaker.api.item.component.Tool")
public class ExpandTool {
    
    /**
     * Creates a new Tool instance with the specified rules, default mining speed, and damage per block.
     *
     * @param rules              The list of rules that define how the tool interacts with specific blocks.
     * @param defaultMiningSpeed The default mining speed of the tool when no specific rule applies.
     * @param damagePerBlock     The amount of damage the tool takes per block mined.
     *
     * @return A new Tool instance.
     */
    @ZenCodeType.StaticExpansionMethod
    public static Tool of(List<Tool.Rule> rules, float defaultMiningSpeed, int damagePerBlock) {
        
        return new Tool(rules, defaultMiningSpeed, damagePerBlock);
    }
    
    /**
     * Retrieves the mining speed of the tool for the given block state.
     *
     * @param state The block state to check the mining speed for.
     *
     * @return The mining speed of the tool for the given block state.
     */
    @ZenCodeType.Method
    public static float getMiningSpeed(Tool internal, BlockState state) {
        
        return internal.getMiningSpeed(state);
    }
    
    /**
     * Retrieves the list of rules that define how the tool interacts with specific blocks.
     *
     * @return The list of rules for this tool.
     */
    @ZenCodeType.Getter("rules")
    public static List<Tool.Rule> rules(Tool internal) {
        
        return internal.rules();
    }
    
    /**
     * Checks if the tool is correct for harvesting drops from the given block state.
     *
     * @param state The block state to check.
     *
     * @return True if the tool is correct for harvesting drops, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean isCorrectForDrops(Tool internal, BlockState state) {
        
        return internal.isCorrectForDrops(state);
    }
    
    /**
     * Retrieves the amount of damage the tool takes per block mined.
     *
     * @return The damage per block value.
     */
    @ZenCodeType.Getter("damagePerBlock")
    public static int damagePerBlock(Tool internal) {
        
        return internal.damagePerBlock();
    }
    
    /**
     * Retrieves the default mining speed of the tool when no specific rule applies.
     *
     * @return The default mining speed.
     */
    @ZenCodeType.Getter("defaultMiningSpeed")
    public static float defaultMiningSpeed(Tool internal) {
        
        return internal.defaultMiningSpeed();
    }
    
    /**
     * Represents a rule that defines how a tool interacts with specific blocks.
     * Rules can override mining speed, determine if the tool is correct for harvesting drops, and more.
     */
    @ZenRegister
    @Document("vanilla/api/item/component/ToolRule")
    @NativeTypeRegistration(value = Tool.Rule.class, zenCodeName = "crafttweaker.api.item.component.ToolRule")
    public static class ExpandToolRule {
        
        /**
         * Checks if this rule defines whether the tool is correct for harvesting drops.
         *
         * @return True if the rule has a value for 'correctToolForDrops', false otherwise.
         */
        @ZenCodeType.Getter("hasCorrectToolForDrops")
        public static boolean hasCorrectToolForDrops(Tool.Rule internal) {
            
            return internal.correctForDrops().isPresent();
        }
        
        /**
         * Retrieves whether the tool is correct for harvesting drops according to this rule.
         * Throws an exception if the rule does not define this value.
         *
         * @return True if the tool is correct for harvesting drops, false otherwise.
         *
         * @throws IllegalStateException If the rule does not have a value for 'correctToolForDrops'.
         */
        @ZenCodeType.Getter("correctToolForDrops")
        public static boolean correctForDrops(Tool.Rule internal) {
            
            return internal.correctForDrops()
                    .orElseThrow(() -> new IllegalStateException("Rule does not have a value for 'correctToolForDrops'! Be sure to check 'hasCorrectToolForDrops' first!"));
        }
        
        /**
         * Retrieves the mining speed override defined by this rule, if any.
         *
         * @return The mining speed override, or null if not defined.
         */
        @ZenCodeType.Nullable
        @ZenCodeType.Getter("speed")
        public static Float speed(Tool.Rule internal) {
            
            return internal.speed().orElse(null);
        }
        
        /**
         * Retrieves the list of blocks that this rule applies to.
         *
         * @return The list of blocks.
         */
        @ZenCodeType.Getter("blocks")
        public static List<Block> blocks(Tool.Rule internal) {
            
            return internal.blocks().stream().map(Holder::value).toList();
        }
        
        /**
         * Creates a new rule that overrides the mining speed for the specified blocks.
         *
         * @param blocks The list of blocks this rule applies to.
         * @param speed  The mining speed override.
         *
         * @return A new {@link Tool.Rule} instance.
         */
        @ZenCodeType.StaticExpansionMethod
        public static Tool.Rule overrideSpeed(List<Block> blocks, float speed) {
            
            return Tool.Rule.overrideSpeed(blocks, speed);
        }
        
        /**
         * Creates a new rule that overrides the mining speed for blocks matching the specified tag.
         *
         * @param tag   The tag identifying the blocks this rule applies to.
         * @param speed The mining speed override.
         *
         * @return A new {@link Tool.Rule} instance.
         */
        @ZenCodeType.StaticExpansionMethod
        public static Tool.Rule overrideSpeed(KnownTag<Block> tag, float speed) {
            
            return Tool.Rule.overrideSpeed(tag.<TagKey<Block>> getTagKey(), speed);
        }
        
        /**
         * Creates a new rule that denies drops for blocks matching the specified tag.
         *
         * @param tag The tag identifying the blocks this rule applies to.
         *
         * @return A new {@link Tool.Rule} instance.
         */
        @ZenCodeType.StaticExpansionMethod
        public static Tool.Rule deniesDrops(KnownTag<Block> tag) {
            
            return Tool.Rule.deniesDrops(tag.getTagKey());
        }
        
        /**
         * Creates a new rule that allows mining and harvesting drops for blocks matching the specified tag.
         *
         * @param tag   The tag identifying the blocks this rule applies to.
         * @param speed The mining speed override.
         *
         * @return A new {@link Tool.Rule} instance.
         */
        @ZenCodeType.StaticExpansionMethod
        public static Tool.Rule minesAndDrops(KnownTag<Block> tag, float speed) {
            
            return Tool.Rule.minesAndDrops(tag.<TagKey<Block>> getTagKey(), speed);
        }
        
        /**
         * Creates a new rule that allows mining and harvesting drops for the specified blocks.
         *
         * @param blocks The list of blocks this rule applies to.
         * @param speed  The mining speed override.
         *
         * @return A new {@link Tool.Rule} instance.
         */
        @ZenCodeType.StaticExpansionMethod
        public static Tool.Rule minesAndDrops(List<Block> blocks, float speed) {
            
            return Tool.Rule.minesAndDrops(blocks, speed);
        }
        
    }
    
}