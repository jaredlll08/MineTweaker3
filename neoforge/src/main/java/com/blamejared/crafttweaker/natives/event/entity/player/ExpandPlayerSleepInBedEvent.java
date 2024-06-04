package com.blamejared.crafttweaker.natives.event.entity.player;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenEvent
@Document("neoforge/api/event/entity/player/CanPlayerSleepEvent")
@NativeTypeRegistration(value = CanPlayerSleepEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.entity.player.CanPlayerSleepEvent")
public class ExpandPlayerSleepInBedEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<CanPlayerSleepEvent> BUS = IEventBus.direct(
            CanPlayerSleepEvent.class,
            NeoForgeEventBusWire.of()
    );
    
    @ZenCodeType.Getter("entity")
    public static ServerPlayer getEntity(CanPlayerSleepEvent internal) {
        
        return internal.getEntity();
    }
    
    @ZenCodeType.Getter("problem")
    public static Player.@ZenCodeType.Nullable BedSleepingProblem getProblem(CanPlayerSleepEvent internal) {
        
        return internal.getProblem();
    }
    
    @ZenCodeType.Getter("vanillaProblem")
    public static Player.@ZenCodeType.Nullable BedSleepingProblem getVanillaProblem(CanPlayerSleepEvent internal) {
        
        return internal.getVanillaProblem();
    }
    
    @ZenCodeType.Getter("state")
    public static BlockState getState(CanPlayerSleepEvent internal) {
        
        return internal.getState();
    }
    
    @ZenCodeType.Getter("pos")
    public static BlockPos getPos(CanPlayerSleepEvent internal) {
        
        return internal.getPos();
    }
    
    @ZenCodeType.Setter("problem")
    public static void setProblem(CanPlayerSleepEvent internal, Player.@ZenCodeType.Nullable BedSleepingProblem problem) {
        
        internal.setProblem(problem);
    }
    
    @ZenCodeType.Getter("level")
    public static Level getLevel(CanPlayerSleepEvent internal) {
        
        return internal.getLevel();
    }
    
}
