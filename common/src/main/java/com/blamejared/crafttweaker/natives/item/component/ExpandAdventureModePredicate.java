package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.tag.type.KnownTag;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.google.common.collect.Lists;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.AdventureModePredicate;
import net.minecraft.world.level.block.Block;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/item/component/AdventureModePredicate")
@NativeTypeRegistration(value = AdventureModePredicate.class, zenCodeName = "crafttweaker.api.item.component.AdventureModePredicate")
public class ExpandAdventureModePredicate {
    
    /**
     * Creates a new AdventureModePredicate with the given predicates and showInTooltip flag.
     *
     * @param predicates    The list of block predicates.
     * @param showInTooltip Whether the predicate should show in the tooltip.
     *
     * @return The new AdventureModePredicate.
     */
    @ZenCodeType.StaticExpansionMethod
    public static AdventureModePredicate of(List<BlockPredicate> predicates, boolean showInTooltip) {
        
        return new AdventureModePredicate(predicates, showInTooltip);
    }
    
    /**
     * Creates a new AdventureModePredicate with the given blocks and showInTooltip flag.
     *
     * @param predicates    The list of blocks.
     * @param showInTooltip Whether the predicate should show in the tooltip.
     *
     * @return The new AdventureModePredicate.
     */
    @ZenCodeType.StaticExpansionMethod
    public static AdventureModePredicate ofBlocks(List<Block> predicates, boolean showInTooltip) {
        
        return new AdventureModePredicate(Lists.transform(predicates, input -> BlockPredicate.Builder.block()
                .of(input)
                .build()), showInTooltip);
    }
    
    /**
     * Creates a new AdventureModePredicate with the given tags and showInTooltip flag.
     *
     * @param predicates    The list of tags.
     * @param showInTooltip Whether the predicate should show in the tooltip.
     *
     * @return The new AdventureModePredicate.
     */
    @ZenCodeType.StaticExpansionMethod
    public static AdventureModePredicate ofTags(List<KnownTag<Block>> predicates, boolean showInTooltip) {
        
        return new AdventureModePredicate(Lists.transform(predicates, input -> BlockPredicate.Builder.block()
                .of(input.<TagKey<Block>> getTagKey())
                .build()), showInTooltip);
    }
    
    /**
     * Gets whether the predicate should show in the tooltip.
     *
     * @return Whether the predicate should show in the tooltip.
     */
    @ZenCodeType.Getter("showInTooltip")
    public static boolean showInTooltip(AdventureModePredicate internal) {
        
        return internal.showInTooltip();
    }
    
    /**
     * Sets whether the predicate should show in the tooltip.
     *
     * @param tooltip Whether the predicate should show in the tooltip.
     *
     * @return The new AdventureModePredicate.
     */
    @ZenCodeType.Method
    public static AdventureModePredicate withTooltip(AdventureModePredicate internal, boolean tooltip) {
        
        return internal.withTooltip(tooltip);
    }
    
}
