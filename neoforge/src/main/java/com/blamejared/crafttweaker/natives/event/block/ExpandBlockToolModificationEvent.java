package com.blamejared.crafttweaker.natives.event.block;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.NeoForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenEvent
@Document("neoforge/api/event/block/BlockToolModificationEvent")
@NativeTypeRegistration(value = BlockEvent.BlockToolModificationEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.block.BlockToolModificationEvent")
public class ExpandBlockToolModificationEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<BlockEvent.BlockToolModificationEvent> BUS = IEventBus.cancelable(
            BlockEvent.BlockToolModificationEvent.class,
            NeoForgeEventBusWire.of(),
            NeoForgeEventCancellationCarrier.of()
    );
    
    @ZenCodeType.Getter("player")
    public static Player getPlayer(BlockEvent.BlockToolModificationEvent internal) {
        
        return internal.getPlayer();
    }
    
    @ZenCodeType.Getter("heldItemStack")
    public static IItemStack getHeldItemStack(BlockEvent.BlockToolModificationEvent internal) {
        
        return IItemStack.of(internal.getHeldItemStack());
    }
    
    @ZenCodeType.Getter("itemAbility")
    public static ItemAbility getItemAbility(BlockEvent.BlockToolModificationEvent internal) {
        
        return internal.getItemAbility();
    }
    
    @ZenCodeType.Getter("isSimulated")
    public static boolean isSimulated(BlockEvent.BlockToolModificationEvent internal) {
        
        return internal.isSimulated();
    }
    
    @ZenCodeType.Getter("context")
    public static UseOnContext getContext(BlockEvent.BlockToolModificationEvent internal) {
        
        return internal.getContext();
    }
    
    /**
     * Sets the transformed state after tool use.
     * If not set, will return the original state.
     * This will be bypassed if canceled, returning null instead.
     */
    @ZenCodeType.Setter("finalState")
    public static void setFinalState(BlockEvent.BlockToolModificationEvent internal, BlockState state) {
        
        internal.setFinalState(state);
    }
    
    /**
     * Gets the transformed state after tool use.
     * If setFinalState is not called, it will return the original state.
     * This will be bypassed if canceled, returning null instead.
     */
    @ZenCodeType.Getter("finalState")
    @ZenCodeType.Nullable
    public static BlockState getFinalState(BlockEvent.BlockToolModificationEvent internal) {
        
        return internal.getFinalState();
    }
    
}
