package com.blamejared.crafttweaker.natives.world;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/world/BlockContainerSingleItem")
@NativeTypeRegistration(value = ContainerSingleItem.BlockContainerSingleItem.class, zenCodeName = "crafttweaker.api.world.BlockContainerSingleItem")
public class ExpandBlockContainerSingleItem {
    
    /**
     * Gets the BlockEntity that holds the container.
     *
     * @return The BlockEntity that holds the container.
     */
    @ZenCodeType.Method
    public static BlockEntity getContainerBlockEntity(ContainerSingleItem.BlockContainerSingleItem internal) {
        
        return internal.getContainerBlockEntity();
    }
    
    /**
     * Checks if the player container is still valid for a player to interact with.
     *
     * @param player The player interacting with the container.
     *
     * @return Whether the player can still access the container.
     */
    @ZenCodeType.Method
    public static boolean stillValid(ContainerSingleItem internal, Player player) {
        
        return internal.stillValid(player);
    }
    
}