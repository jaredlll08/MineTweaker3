package com.blamejared.crafttweaker.natives.event.interact;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.NeoForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.openzen.zencode.java.ZenCodeType;

/**
 * This event is fired on both sides whenever the player interacts with an entity.
 *
 * @docEvent canceled will cause the entity to not be interacted with
 */
@ZenRegister
@ZenEvent
@Document("neoforge/api/event/interact/EntityInteractEvent")
@NativeTypeRegistration(value = PlayerInteractEvent.EntityInteract.class, zenCodeName = "crafttweaker.neoforge.api.event.interact.EntityInteractEvent")
public class ExpandPlayerEntityInteractEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<PlayerInteractEvent.EntityInteract> BUS = IEventBus.cancelable(
            PlayerInteractEvent.EntityInteract.class,
            NeoForgeEventBusWire.of(),
            NeoForgeEventCancellationCarrier.of()
    );
    
    @ZenCodeType.Getter("target")
    public static Entity getTarget(PlayerInteractEvent.EntityInteract internal) {
        
        return internal.getTarget();
    }
    
    /**
     * @return The {@link InteractionResult} that will be returned to vanilla if the event is cancelled, instead of calling the relevant
     * method of the event. By default, this is PASS, meaning cancelled events will cause
     * the client to keep trying more interactions until something works.
     */
    @ZenCodeType.Getter("cancellationResult")
    public static InteractionResult getCancellationResult(PlayerInteractEvent.EntityInteract internal) {
        
        return internal.getCancellationResult();
    }
    
    /**
     * Set the {@link InteractionResult} that will be returned to vanilla if the event is cancelled, instead of calling the relevant method of the event.
     *
     * Note that this only has an effect on RightClickBlockEvent, RightClickItemEvent, EntityInteractEvent.
     */
    @ZenCodeType.Setter("cancellationResult")
    public static void setCancellationResult(PlayerInteractEvent.EntityInteract internal, InteractionResult result) {
        
        internal.setCancellationResult(result);
    }
    
}
