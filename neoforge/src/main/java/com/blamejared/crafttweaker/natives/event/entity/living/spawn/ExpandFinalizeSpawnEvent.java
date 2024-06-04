package com.blamejared.crafttweaker.natives.event.entity.living.spawn;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.event.NeoForgeEventCancellationCarrier;
import com.blamejared.crafttweaker.api.event.ZenEvent;
import com.blamejared.crafttweaker.api.event.bus.IEventBus;
import com.blamejared.crafttweaker.api.event.bus.NeoForgeEventBusWire;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenEvent
@Document("neoforge/api/event/entity/living/spawn/FinalizeMobSpawnEvent")
@NativeTypeRegistration(value = FinalizeSpawnEvent.class, zenCodeName = "crafttweaker.neoforge.api.event.entity.living.spawn.FinalizeMobSpawnEvent")
public class ExpandFinalizeSpawnEvent {
    
    @ZenEvent.Bus
    public static final IEventBus<FinalizeSpawnEvent> BUS = IEventBus.cancelable(
            FinalizeSpawnEvent.class,
            NeoForgeEventBusWire.of(),
            NeoForgeEventCancellationCarrier.of()
    );
    
    @ZenCodeType.Getter("difficulty")
    public static DifficultyInstance getDifficulty(FinalizeSpawnEvent internal) {
        
        return internal.getDifficulty();
    }
    
    @ZenCodeType.Setter("difficulty")
    public static void setDifficulty(FinalizeSpawnEvent internal, DifficultyInstance inst) {
        
        internal.setDifficulty(inst);
    }
    
    @ZenCodeType.Getter("spawnType")
    public static MobSpawnType getSpawnType(FinalizeSpawnEvent internal) {
        
        return internal.getSpawnType();
    }
    //TODO expose SpawnGroupData
    //    public static @Nullable SpawnGroupData getSpawnData(FinalizeSpawnEvent internal) {
    //
    //        return internal.getSpawnData();
    //    }
    //
    //    public static void setSpawnData(FinalizeSpawnEvent internal, @Nullable SpawnGroupData data) {
    //
    //        internal.setSpawnData(data);
    //    }
    
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("blockEntitySpawner")
    public static BlockEntity getBlockEntitySpawner(FinalizeSpawnEvent internal) {
        
        if(internal.getSpawner() == null) {
            return null;
        }
        return internal.getSpawner().left().orElse(null);
    }
    
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("entitySpawner")
    public static Entity getEntitySpawner(FinalizeSpawnEvent internal) {
        
        if(internal.getSpawner() == null) {
            return null;
        }
        return internal.getSpawner().right().orElse(null);
    }
    
    @ZenCodeType.Setter("spawnCancelled")
    public static void setSpawnCancelled(FinalizeSpawnEvent internal, boolean cancel) {
        
        internal.setSpawnCancelled(cancel);
    }
    
    @ZenCodeType.Getter("isSpawnCancelled")
    public static boolean isSpawnCancelled(FinalizeSpawnEvent internal) {
        
        return internal.isSpawnCancelled();
    }
    
}
