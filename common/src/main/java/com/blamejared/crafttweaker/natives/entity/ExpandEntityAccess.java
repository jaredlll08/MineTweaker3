package com.blamejared.crafttweaker.natives.entity;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraft.world.phys.AABB;
import org.openzen.zencode.java.ZenCodeType;

import java.util.UUID;

@ZenRegister
@Document("vanilla/api/entity/EntityAccess")
@NativeTypeRegistration(value = EntityAccess.class, zenCodeName = "crafttweaker.api.entity.EntityAccess")
public class ExpandEntityAccess {
    
    /**
     * Gets the ID of the entity.
     *
     * @return The ID of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("id")
    public static int getId(EntityAccess internal) {
        
        return internal.getId();
    }
    
    /**
     * Gets the UUID of the entity.
     *
     * @return The UUID of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("uuid")
    public static UUID getUUID(EntityAccess internal) {
        
        return internal.getUUID();
    }
    
    /**
     * Gets the {@link BlockPos} of the entity.
     *
     * @return The {@link BlockPos} of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("blockPosiion")
    public static BlockPos blockPosition(EntityAccess internal) {
        
        return internal.blockPosition();
    }
    
    /**
     * Gets the bounding box of the entity.
     *
     * @return The bounding box of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("boundingBox")
    public static AABB getBoundingBox(EntityAccess internal) {
        
        return internal.getBoundingBox();
    }
    
    /**
     * Removes this entity with the given {@link Entity.RemovalReason removal reason}.
     *
     * @param var1 The removal reason to set.
     */
    @ZenCodeType.Method
    public static void setRemoved(EntityAccess internal, Entity.RemovalReason var1) {
        
        internal.setRemoved(var1);
    }
    
    /**
     * Checks if the entity should be saved.
     *
     * @return true if the entity should be saved.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("shouldBeSaved")
    public static boolean shouldBeSaved(EntityAccess internal) {
        
        return internal.shouldBeSaved();
    }
    
    /**
     * Checks if the entity is always ticking.
     *
     * @return true if the entity is always ticking.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isAlwaysTicking")
    public static boolean isAlwaysTicking(EntityAccess internal) {
        
        return internal.isAlwaysTicking();
    }
    
}
