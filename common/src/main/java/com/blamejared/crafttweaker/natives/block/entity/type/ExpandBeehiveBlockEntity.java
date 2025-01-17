package com.blamejared.crafttweaker.natives.block.entity.type;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/block/entity/type/BeehiveBlockEntity")
@NativeTypeRegistration(value = BeehiveBlockEntity.class, zenCodeName = "crafttweaker.api.block.entity.type.BeehiveBlockEntity")
public class ExpandBeehiveBlockEntity {
    
    @ZenRegister
    @Document("vanilla/api/block/entity/type/BeehiveBlockEntityOccupant")
    @NativeTypeRegistration(value = BeehiveBlockEntity.Occupant.class, zenCodeName = "crafttweaker.api.block.entity.type.BeehiveBlockEntityOccupant")
    public static class ExpandBeehiveBlockEntityOccupant {
        
        /**
         * Creates a new occupant for a beehive.
         *
         * @param entity The entity to create the occupant for.
         *
         * @return The new occupant.
         */
        @ZenCodeType.StaticExpansionMethod
        public static BeehiveBlockEntity.Occupant of(Entity entity) {
            
            return BeehiveBlockEntity.Occupant.of(entity);
        }
        
        /**
         * Creates a new occupant for a beehive.
         *
         * @param ticksInHive The number of ticks the occupant has been in the hive.
         *
         * @return The new occupant.
         */
        @ZenCodeType.StaticExpansionMethod
        public static BeehiveBlockEntity.Occupant create(int ticksInHive) {
            
            return BeehiveBlockEntity.Occupant.create(ticksInHive);
        }
        
        /**
         * Creates an entity for the occupant.
         *
         * @param level The level to create the entity in.
         * @param pos   The position to create the entity at.
         *
         * @return The new entity.
         */
        @ZenCodeType.Nullable
        @ZenCodeType.Method
        public static Entity createEntity(BeehiveBlockEntity.Occupant internal, Level level, BlockPos pos) {
            
            return internal.createEntity(level, pos);
        }
        
        /**
         * Gets the entity data for the occupant.
         *
         * @return The entity data for the occupant.
         */
        @ZenCodeType.Getter("entityData")
        public static CustomData entityData(BeehiveBlockEntity.Occupant internal) {
            
            return internal.entityData();
        }
        
        /**
         * Gets the number of ticks the occupant has been in the hive.
         *
         * @return The number of ticks the occupant has been in the hive.
         */
        @ZenCodeType.Getter("ticksInHive")
        public static int ticksInHive(BeehiveBlockEntity.Occupant internal) {
            
            return internal.ticksInHive();
        }
        
        /**
         * Gets the minimum number of ticks the occupant has been in the hive.
         *
         * @return The minimum number of ticks the occupant has been in the hive.
         */
        @ZenCodeType.Getter("minTicksInHive")
        public static int minTicksInHive(BeehiveBlockEntity.Occupant internal) {
            
            return internal.minTicksInHive();
        }
        
    }
    
}
