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

@ZenRegister
@Document("vanilla/api/item/component/Tool")
@NativeTypeRegistration(value = Tool.class, zenCodeName = "crafttweaker.api.item.component.Tool")
public class ExpandTool {
    
    @ZenCodeType.StaticExpansionMethod
    public static Tool of(List<Tool.Rule> rules, float defaultMiningSpeed, int damagePerBlock) {
        
        return new Tool(rules, defaultMiningSpeed, damagePerBlock);
    }
    
    @ZenCodeType.Method
    public static float getMiningSpeed(Tool internal, BlockState state) {
        
        return internal.getMiningSpeed(state);
    }
    
    @ZenCodeType.Getter("rules")
    public static List<Tool.Rule> rules(Tool internal) {
        
        return internal.rules();
    }
    
    @ZenCodeType.Method
    public static boolean isCorrectForDrops(Tool internal, BlockState state) {
        
        return internal.isCorrectForDrops(state);
    }
    
    @ZenCodeType.Getter("damagePerBlock")
    public static int damagePerBlock(Tool internal) {
        
        return internal.damagePerBlock();
    }
    
    @ZenCodeType.Getter("defaultMiningSpeed")
    public static float defaultMiningSpeed(Tool internal) {
        
        return internal.defaultMiningSpeed();
    }
    
    @ZenRegister
    @Document("vanilla/api/item/component/ToolRule")
    @NativeTypeRegistration(value = Tool.Rule.class, zenCodeName = "crafttweaker.api.item.component.ToolRule")
    public static class ExpandToolRule {
        
        @ZenCodeType.Getter("hasCorrectToolForDrops")
        public static boolean hasCorrectToolForDrops(Tool.Rule internal) {
            
            return internal.correctForDrops().isPresent();
        }
        
        @ZenCodeType.Getter("correctToolForDrops")
        public static boolean correctForDrops(Tool.Rule internal) {
            
            return internal.correctForDrops()
                    .orElseThrow(() -> new IllegalStateException("Rule does not have a value for 'correctToolForDrops'! Be sure to check 'hasCorrectToolForDrops' first!"));
        }
        
        @ZenCodeType.Nullable
        @ZenCodeType.Getter("speed")
        public static Float speed(Tool.Rule internal) {
            
            return internal.speed().orElse(null);
        }
        
        @ZenCodeType.Getter("blocks")
        public static List<Block> blocks(Tool.Rule internal) {
            
            return internal.blocks().stream().map(Holder::value).toList();
        }
        
        @ZenCodeType.StaticExpansionMethod
        public static Tool.Rule overrideSpeed(List<Block> blocks, float speed) {
            
            return Tool.Rule.overrideSpeed(blocks, speed);
        }
        
        @ZenCodeType.StaticExpansionMethod
        public static Tool.Rule overrideSpeed(KnownTag<Block> tag, float speed) {
            
            return Tool.Rule.overrideSpeed(tag.<TagKey<Block>> getTagKey(), speed);
        }
        
        @ZenCodeType.StaticExpansionMethod
        public static Tool.Rule deniesDrops(KnownTag<Block> tag) {
            
            return Tool.Rule.deniesDrops(tag.getTagKey());
        }
        
        @ZenCodeType.StaticExpansionMethod
        public static Tool.Rule minesAndDrops(KnownTag<Block> tag, float speed) {
            
            return Tool.Rule.minesAndDrops(tag.<TagKey<Block>> getTagKey(), speed);
        }
        
        @ZenCodeType.StaticExpansionMethod
        public static Tool.Rule minesAndDrops(List<Block> blocks, float speed) {
            
            return Tool.Rule.minesAndDrops(blocks, speed);
        }
        
    }
    
}
