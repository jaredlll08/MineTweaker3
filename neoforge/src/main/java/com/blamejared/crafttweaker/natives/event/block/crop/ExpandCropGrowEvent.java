package com.blamejared.crafttweaker.natives.event.block.crop;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.NeoForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.level.block.CropGrowEvent;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("neoforge/api/event/block/crop/CropGrowEvent")
@NativeTypeRegistration(value = CropGrowEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.block.crop.CropGrowEvent")
public class ExpandCropGrowEvent {
    
    @ZenRegister
    @ZenEvent
    @Document("neoforge/api/event/block/crop/PreCropGrowEvent")
    @NativeTypeRegistration(value = CropGrowEvent.Pre.class, zenCodeName = "crafttweaker.neoforge.api.event.block.crop.PreCropGrowEvent")
    public static class ExpandCropGrowPreEvent {
        
        @ZenEvent.Bus
        public static final IEventBus<CropGrowEvent.Pre> BUS = IEventBus.cancelable(
                CropGrowEvent.Pre.class,
                NeoForgeEventBusWire.of(),
                NeoForgeEventCancellationCarrier.of()
        );
        
    }
    
    @ZenRegister
    @ZenEvent
    @Document("neoforge/api/event/block/crop/PostCropGrowEvent")
    @NativeTypeRegistration(value = CropGrowEvent.Post.class, zenCodeName = "crafttweaker.neoforge.api.event.block.crop.PostCropGrowEvent")
    public static class ExpandCropGrowPostEvent {
        
        @ZenEvent.Bus
        public static final IEventBus<CropGrowEvent.Post> BUS = IEventBus.direct(
                CropGrowEvent.Post.class,
                NeoForgeEventBusWire.of()
        );
        
        @ZenCodeType.Getter("originalState")
        public static BlockState getOriginalState(CropGrowEvent.Post internal) {
            
            return internal.getOriginalState();
        }
        
    }
    
}
