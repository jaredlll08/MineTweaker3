package com.blamejared.crafttweaker.natives.event.interact;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.NeoForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.openzen.zencode.java.ZenCodeType;

/**
 * This event is fired when a player left clicks while targeting a block.
 * This event controls which of {@link net.minecraft.world.level.block.Block#attack(BlockState, Level, BlockPos, Player)} and/or the item harvesting methods will be called.
 *
 * Note that if the event is canceled and the player holds down left mouse, the event will continue to fire.
 * This is due to how vanilla calls the left click handler methods.
 *
 * Also note that creative mode directly breaks the block without running any other logic.
 *
 * @docEvent canceled none of the above noted methods to be called.
 */
@ZenRegister
@ZenEvent
@Document("neoforge/api/event/interact/LeftClickBlockEvent")
@NativeTypeRegistration(value = PlayerInteractEvent.LeftClickBlock.class, zenCodeName = "crafttweaker.neoforge.api.event.interact.LeftClickBlockEvent")
public class ExpandLeftClickBlockEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<PlayerInteractEvent.LeftClickBlock> BUS = IEventBus.cancelable(
            PlayerInteractEvent.LeftClickBlock.class,
            NeoForgeEventBusWire.of(),
            NeoForgeEventCancellationCarrier.of()
    );
    
    @ZenCodeType.Setter("canceled")
    public static void setCanceled(PlayerInteractEvent.LeftClickBlock internal, boolean canceled) {
        
        internal.setCanceled(canceled);
    }
    
    @ZenCodeType.Setter("useItem")
    public static void setUseItem(PlayerInteractEvent.LeftClickBlock internal, TriState triggerItem) {
        
        internal.setUseItem(triggerItem);
    }
    
    @ZenCodeType.Setter("useBlock")
    public static void setUseBlock(PlayerInteractEvent.LeftClickBlock internal, TriState triggerBlock) {
        
        internal.setUseBlock(triggerBlock);
    }
    
    @ZenCodeType.Getter("action")
    public static PlayerInteractEvent.LeftClickBlock.Action getAction(PlayerInteractEvent.LeftClickBlock internal) {
        
        return internal.getAction();
    }
    
    @ZenCodeType.Getter("useItem")
    public static TriState getUseItem(PlayerInteractEvent.LeftClickBlock internal) {
        
        return internal.getUseItem();
    }
    
    @ZenCodeType.Getter("useBlock")
    public static TriState getUseBlock(PlayerInteractEvent.LeftClickBlock internal) {
        
        return internal.getUseBlock();
    }
    
    @ZenRegister
    @Document("neoforge/api/event/interact/LeftClickBlockEventAction")
    @NativeTypeRegistration(value = PlayerInteractEvent.LeftClickBlock.Action.class, zenCodeName = "crafttweaker.neoforge.api.event.interact.LeftClickBlockEventAction")
    @BracketEnum("neoforge:events/leftclickblock/action")
    public static class ExpandLeftClickBlockEventAction {
    
    }
    
}
