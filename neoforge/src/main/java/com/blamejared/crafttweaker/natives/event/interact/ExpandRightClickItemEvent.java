package com.blamejared.crafttweaker.natives.event.interact;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.NeoForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.InteractionResult;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.openzen.zencode.java.ZenCodeType;

/**
 * The rightClickItem event is fired whenever the player right clicks with an item in their hand.
 * It does not offer any special getters, but you can still access all members from {@link PlayerInteractEvent}
 *
 * @docEvent canceled Item#onItemRightClick will not be called
 */
@ZenRegister
@ZenEvent
@Document("neoforge/api/event/interact/RightClickItemEvent")
@NativeTypeRegistration(value = PlayerInteractEvent.RightClickItem.class, zenCodeName = "crafttweaker.neoforge.api.event.interact.RightClickItemEvent")
public class ExpandRightClickItemEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<PlayerInteractEvent.RightClickItem> BUS = IEventBus.cancelable(
            PlayerInteractEvent.RightClickItem.class,
            NeoForgeEventBusWire.of(),
            NeoForgeEventCancellationCarrier.of()
    );
    
    /**
     * @return The {@link InteractionResult} that will be returned to vanilla if the event is cancelled, instead of calling the relevant
     * method of the event. By default, this is PASS, meaning cancelled events will cause
     * the client to keep trying more interactions until something works.
     */
    @ZenCodeType.Getter("cancellationResult")
    public static InteractionResult getCancellationResult(PlayerInteractEvent.RightClickItem internal) {
        
        return internal.getCancellationResult();
    }
    
    /**
     * Set the {@link InteractionResult} that will be returned to vanilla if the event is cancelled, instead of calling the relevant method of the event.
     *
     * Note that this only has an effect on RightClickBlockEvent, RightClickItemEvent, EntityInteractEvent.
     */
    @ZenCodeType.Setter("cancellationResult")
    public static void setCancellationResult(PlayerInteractEvent.RightClickItem internal, InteractionResult result) {
        
        internal.setCancellationResult(result);
    }
}
