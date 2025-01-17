package com.blamejared.crafttweaker.natives.block.type.falling;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Fallable;
import net.minecraft.world.level.block.state.BlockState;
import org.openzen.zencode.java.ZenCodeType;

/**
 * @docParam this (<block:minecraft:sand> as Fallable)
 */
@ZenRegister
@Document("vanilla/api/block/type/falling/Fallable")
@NativeTypeRegistration(value = Fallable.class, zenCodeName = "crafttweaker.api.block.type.falling.Fallable")
public class ExpandFallable {
    
    /**
     * Called when a falling block lands on a block.
     *
     * @param level         The level the block is falling in.
     * @param pos           The position of the block that is falling.
     * @param fallingState  The state of the block that is falling.
     * @param placeState    The state of the block that the falling block is landing on.
     * @param fallingEntity The entity that is falling.
     */
    @ZenCodeType.Method
    public static void onLand(Fallable internal, Level level, BlockPos pos, BlockState fallingState, BlockState placeState, FallingBlockEntity fallingEntity) {
        
        internal.onLand(level, pos, fallingState, placeState, fallingEntity);
    }
    
    /**
     * Called when a falling block breaks after falling.
     *
     * @param level         The level the block is falling in.
     * @param pos           The position of the block that is falling.
     * @param fallingEntity The entity that is falling.
     */
    @ZenCodeType.Method
    public static void onBrokenAfterFall(Fallable internal, Level level, BlockPos pos, FallingBlockEntity fallingEntity) {
        
        internal.onBrokenAfterFall(level, pos, fallingEntity);
    }
    
    /**
     * Gets the damage source used when this block falls on an entity.
     *
     * @return The damage source used when this block falls on an entity.
     */
    @ZenCodeType.Method
    public static DamageSource getFallDamageSource(Fallable internal, Entity entity) {
        
        return internal.getFallDamageSource(entity);
    }
    
}
