package com.blamejared.crafttweaker.natives.entity;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.MobCategory;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/MobCategory")
@NativeTypeRegistration(value = MobCategory.class, zenCodeName = "crafttweaker.api.entity.MobCategory")
@BracketEnum("minecraft:mobcategory")
public class ExpandMobCategory {
    
    /**
     * Gets the name of the mob category.
     *
     * @return The name of the mob category.
     */
    @ZenCodeType.Method
    public static String getName(MobCategory internal) {
        
        return internal.getName();
    }
    
    /**
     * Gets the maximum number of instances per chunk.
     *
     * @return The maximum number of instances per chunk.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("maxInstancesPerChunk")
    public static int getMaxInstancesPerChunk(MobCategory internal) {
        
        return internal.getMaxInstancesPerChunk();
    }
    
    /**
     * Checks if the mob category is friendly.
     *
     * @return True if the mob category is friendly.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("friendly")
    public static boolean isFriendly(MobCategory internal) {
        
        return internal.isFriendly();
    }
    
    /**
     * Checks if the mob category is persistent.
     *
     * @return True if the mob category is persistent.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("persistent")
    public static boolean isPersistent(MobCategory internal) {
        
        return internal.isPersistent();
    }
    
    /**
     * Gets the despawn distance of the mob category.
     *
     * @return The despawn distance of the mob category.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("despawnDistance")
    public static int getDespawnDistance(MobCategory internal) {
        
        return internal.getDespawnDistance();
    }
    
    /**
     * Gets the no despawn distance of the mob category.
     *
     * @return The no despawn distance of the mob category.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("noDespawnDistance")
    public static int getNoDespawnDistance(MobCategory internal) {
        
        return internal.getNoDespawnDistance();
    }
    
}
