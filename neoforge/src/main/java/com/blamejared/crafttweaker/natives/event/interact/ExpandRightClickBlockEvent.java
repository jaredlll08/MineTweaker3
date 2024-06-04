package com.blamejared.crafttweaker.natives.event.interact;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.NeoForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.openzen.zencode.java.ZenCodeType;

/**
 * This event is fired on both sides whenever the player right clicks while targeting a block.
 * This event controls which of Item.onItemUseFirst, Block.onBlockActivated, and Item.onItemUse will be called.
 *
 * @docEvent canceled will cause none of the above three to be called.
 */
@ZenRegister
@ZenEvent
@Document("neoforge/api/event/interact/RightClickBlockEvent")
@NativeTypeRegistration(value = PlayerInteractEvent.RightClickBlock.class, zenCodeName = "crafttweaker.neoforge.api.event.interact.RightClickBlockEvent")
public class ExpandRightClickBlockEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<PlayerInteractEvent.RightClickBlock> BUS = IEventBus.cancelable(
            PlayerInteractEvent.RightClickBlock.class,
            NeoForgeEventBusWire.of(),
            NeoForgeEventCancellationCarrier.of()
    );
    
    @ZenCodeType.Setter("cancellationResult")
    public static void setCancellationResult(PlayerInteractEvent.RightClickBlock internal, InteractionResult result) {
        
        internal.setCancellationResult(result);
    }
    
    @ZenCodeType.Getter("cancellationResult")
    public static InteractionResult getCancellationResult(PlayerInteractEvent.RightClickBlock internal) {
        
        return internal.getCancellationResult();
    }
    
    @ZenCodeType.Setter("canceled")
    public static void setCanceled(PlayerInteractEvent.RightClickBlock internal, boolean canceled) {
        
        internal.setCanceled(canceled);
    }
    
    @ZenCodeType.Setter("useItem")
    public static void setUseItem(PlayerInteractEvent.RightClickBlock internal, TriState triggerItem) {
        
        internal.setUseItem(triggerItem);
    }
    
    @ZenCodeType.Setter("useBlock")
    public static void setUseBlock(PlayerInteractEvent.RightClickBlock internal, TriState triggerBlock) {
        
        internal.setUseBlock(triggerBlock);
    }
    
    @ZenCodeType.Getter("hitVec")
    public static BlockHitResult getHitVec(PlayerInteractEvent.RightClickBlock internal) {
        
        return internal.getHitVec();
    }
    
    @ZenCodeType.Getter("useItem")
    public static TriState getUseItem(PlayerInteractEvent.RightClickBlock internal) {
        
        return internal.getUseItem();
    }
    
    @ZenCodeType.Getter("useBlock")
    public static TriState getUseBlock(PlayerInteractEvent.RightClickBlock internal) {
        
        return internal.getUseBlock();
    }
    
}
