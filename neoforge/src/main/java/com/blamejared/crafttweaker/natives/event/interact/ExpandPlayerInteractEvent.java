package com.blamejared.crafttweaker.natives.event.interact;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.item.MCItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.openzen.zencode.java.ZenCodeType;


/**
 * This event is the superclass of all other PlayerInteract events.
 * Generally, you want to use the subtypes of this event.
 */
@ZenRegister
@Document("neoforge/api/event/interact/PlayerInteractEvent")
@NativeTypeRegistration(value = PlayerInteractEvent.class, zenCodeName = "crafttweaker.neoforge.api.interact.PlayerInteractEvent")
public class ExpandPlayerInteractEvent {
    
    /**
     * If the interaction was on an entity, will be a BlockPos centered on the entity.
     * If the interaction was on a block, will be the position of that block.
     * Otherwise, will be a BlockPos centered on the player.
     */
    @ZenCodeType.Getter("blockPos")
    public static BlockPos getBlockPos(PlayerInteractEvent internal) {
        
        return internal.getPos();
    }
    
    /**
     * The stack involved in this interaction.
     * May be empty, but will never be null.
     */
    @ZenCodeType.Getter("itemStack")
    public static IItemStack getItemStack(PlayerInteractEvent internal) {
        
        return new MCItemStack(internal.getItemStack());
    }
    
    /**
     * The face involved in this interaction.
     * For all non-block interactions, this will return null
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("face")
    public static Direction getFace(PlayerInteractEvent internal) {
        
        return internal.getFace();
    }
    
    @ZenCodeType.Getter("hand")
    public static InteractionHand getHand(PlayerInteractEvent internal) {
        
        return internal.getHand();
    }
    
}
