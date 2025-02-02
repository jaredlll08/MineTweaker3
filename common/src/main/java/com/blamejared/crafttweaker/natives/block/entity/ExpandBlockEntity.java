package com.blamejared.crafttweaker.natives.block.entity;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.IData;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/block/entity/BlockEntity")
@NativeTypeRegistration(value = BlockEntity.class, zenCodeName = "crafttweaker.api.block.entity.BlockEntity")
public class ExpandBlockEntity {
    
    /**
     * Gets the {@link Level} of the block entity.
     *
     * @return The {@link Level} of the block entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("level")
    @ZenCodeType.Nullable
    public static Level getLevel(BlockEntity internal) {
        
        return internal.getLevel();
    }
    
    /**
     * Checks if the block entity has a level.
     *
     * @return Whether the block entity has a level.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("hasLevel")
    public static boolean hasLevel(BlockEntity internal) {
        
        return internal.hasLevel();
    }
    
    /**
     * Gets the data of the block entity.
     *
     * @return The data of the block entity.
     */
    @ZenCodeType.Getter("data")
    public static IData getData(BlockEntity internal) {
        
        return new MapData(CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess(internal::saveWithoutMetadata));
    }
    
    /**
     * Sets the data of the block entity.
     *
     * @param data The data to set.
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("data")
    public static void setData(BlockEntity internal, IData data) {
        
        if(data instanceof MapData map) {
            internal.loadWithComponents(map.getInternal(), CraftTweakerAPI.getAccessibleElementsProvider()
                    .registryAccess());
        } else {
            throw new IllegalArgumentException("Invalid data provided, expected MapData, received: '%s'".formatted(data));
        }
    }
    
    /**
     * Updates the data of the block entity, merging the provided data with the existing data.
     *
     * @param data The data to update.
     */
    @ZenCodeType.Method
    public static void updateData(BlockEntity internal, IData data) {
        
        if(data instanceof MapData) {
            MapData mergedData = (MapData) getData(internal).merge(data);
            internal.loadWithComponents(mergedData.getInternal(), CraftTweakerAPI.getAccessibleElementsProvider()
                    .registryAccess());
        } else {
            throw new IllegalArgumentException("Invalid data provided, expected MapData, received: '%s'".formatted(data));
        }
    }
    
    /**
     * Gets the {@link BlockPos} of the block entity.
     *
     * @return The {@link BlockPos} of the block entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("blockPos")
    public static BlockPos getBlockPos(BlockEntity internal) {
        
        return internal.getBlockPos();
    }
    
    /**
     * Gets the {@link BlockState} of the block entity.
     *
     * @return The {@link BlockState} of the block entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("blockState")
    public static BlockState getBlockState(BlockEntity internal) {
        
        return internal.getBlockState();
    }
    
    /**
     * Gets the {@link ResourceLocation} of the block entity type.
     *
     * @return The {@link ResourceLocation} of the block entity type.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("registryName")
    public static ResourceLocation getRegistryName(BlockEntity internal) {
        
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(internal.getType());
    }
    
}
