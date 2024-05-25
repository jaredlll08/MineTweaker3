package com.blamejared.crafttweaker.natives.event.interact;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.NeoForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.openzen.zencode.java.ZenCodeType;

/**
 * This event is fired on both sides whenever a player right-clicks an entity.
 *
 * "Interact at" is an interact where the local vector (which part of the entity you clicked) is known.
 * The state of this event affects whether {@link Entity#interactAt(Player, Vec3, InteractionHand)} is called.
 *
 * @docEvent canceled will cause the entity to not be interacted with
 */
@ZenRegister
@ZenEvent
@Document("neoforge/api/event/interact/EntityInteractSpecificEvent")
@NativeTypeRegistration(value = PlayerInteractEvent.EntityInteractSpecific.class, zenCodeName = "crafttweaker.neoforge.api.event.interact.EntityInteractSpecificEvent")
public class ExpandPlayerEntityInteractSpecificEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<PlayerInteractEvent.EntityInteractSpecific> BUS = IEventBus.cancelable(
            PlayerInteractEvent.EntityInteractSpecific.class,
            NeoForgeEventBusWire.of(),
            NeoForgeEventCancellationCarrier.of()
    );
    
    @ZenCodeType.Getter("target")
    public static Entity getTarget(PlayerInteractEvent.EntityInteractSpecific internal) {
        
        return internal.getTarget();
    }
    
    @ZenCodeType.Getter("localPos")
    public static Vec3 getLocalPos(PlayerInteractEvent.EntityInteractSpecific internal) {
        
        return internal.getLocalPos();
    }
    
    /**
     * @return The {@link InteractionResult} that will be returned to vanilla if the event is cancelled, instead of calling the relevant
     * method of the event. By default, this is PASS, meaning cancelled events will cause
     * the client to keep trying more interactions until something works.
     */
    @ZenCodeType.Getter("cancellationResult")
    public static InteractionResult getCancellationResult(PlayerInteractEvent.EntityInteractSpecific internal) {
        
        return internal.getCancellationResult();
    }
    
    /**
     * Set the {@link InteractionResult} that will be returned to vanilla if the event is cancelled, instead of calling the relevant method of the event.
     *
     * Note that this only has an effect on RightClickBlockEvent, RightClickItemEvent, EntityInteractEvent.
     */
    @ZenCodeType.Setter("cancellationResult")
    public static void setCancellationResult(PlayerInteractEvent.EntityInteractSpecific internal, InteractionResult result) {
        
        internal.setCancellationResult(result);
    }
    
}
