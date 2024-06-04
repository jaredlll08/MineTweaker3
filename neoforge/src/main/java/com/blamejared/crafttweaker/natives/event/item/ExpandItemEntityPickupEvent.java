package com.blamejared.crafttweaker.natives.event.item;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("neoforge/api/event/item/ItemEntityPickupEvent")
@NativeTypeRegistration(value = ItemEntityPickupEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.item.ItemEntityPickupEvent")
public class ExpandItemEntityPickupEvent {
    
    @ZenCodeType.Getter("itemEntity")
    public static ItemEntity getItemEntity(ItemEntityPickupEvent internal) {
        
        return internal.getItemEntity();
    }
    
    @ZenRegister
    @ZenEvent
    @Document("neoforge/api/event/item/ItemEntityPickupPreEvent")
    @NativeTypeRegistration(value = ItemEntityPickupEvent.Pre.class, zenCodeName = "crafttweaker.neoforge.api.event.item.ItemEntityPickupPreEvent")
    public static class ExpandItemEntityPickupEventPre {
        
        @ZenEvent.Bus
        public static final IEventBus<ItemEntityPickupEvent.Pre> BUS = IEventBus.direct(
                ItemEntityPickupEvent.Pre.class,
                NeoForgeEventBusWire.of()
        );
        
        
        @ZenCodeType.Getter("canPickup")
        public static TriState canPickup(ItemEntityPickupEvent.Pre internal) {
            
            return internal.canPickup();
        }
        
        @ZenCodeType.Setter("canPickup")
        public static void setCanPickup(ItemEntityPickupEvent.Pre internal, TriState state) {
            
            internal.setCanPickup(state);
        }
        
    }
    
    @ZenRegister
    @ZenEvent
    @Document("neoforge/api/event/item/ItemEntityPickupPostEvent")
    @NativeTypeRegistration(value = ItemEntityPickupEvent.Post.class, zenCodeName = "crafttweaker.neoforge.api.event.item.ItemEntityPickupPostEvent")
    public static class ExpandItemEntityPickupEventPost {
        
        @ZenEvent.Bus
        public static final IEventBus<ItemEntityPickupEvent.Post> BUS = IEventBus.direct(
                ItemEntityPickupEvent.Post.class,
                NeoForgeEventBusWire.of()
        );
        
        
        @ZenCodeType.Getter("currentStack")
        public static ItemStack getCurrentStack(ItemEntityPickupEvent.Post internal) {
            
            return internal.getCurrentStack();
        }
        
        @ZenCodeType.Getter("originalStack")
        public static ItemStack getOriginalStack(ItemEntityPickupEvent.Post internal) {
            
            return internal.getOriginalStack();
        }
        
    }
    
}