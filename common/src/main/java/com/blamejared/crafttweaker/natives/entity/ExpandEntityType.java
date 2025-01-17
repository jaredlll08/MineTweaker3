package com.blamejared.crafttweaker.natives.entity;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.entity.CTEntityIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.tag.type.KnownTag;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.blamejared.crafttweaker_annotations.annotations.TaggableElement;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@ZenRegister
@Document("vanilla/api/entity/EntityType")
@NativeTypeRegistration(value = EntityType.class, zenCodeName = "crafttweaker.api.entity.EntityType")
@TaggableElement(value = "minecraft:entity_type")
public class ExpandEntityType {
    
    /**
     * Spawns an entity.
     *
     * @param level          The level to spawn the entity in.
     * @param spawnStack     The stack to spawn the entity with.
     * @param spawningPlayer The player that is spawning the entity.
     * @param position       The position to spawn the entity at.
     * @param spawnType      The type of spawn to use.
     * @param alignPosition  Whether to align the position of the entity.
     * @param invertY        Whether to offset the y position of the entity.
     *
     * @return The spawned entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static Entity spawn(EntityType<Entity> internal, ServerLevel level, @ZenCodeType.Nullable IItemStack spawnStack, @ZenCodeType.Nullable Player spawningPlayer, BlockPos position, MobSpawnType spawnType, boolean alignPosition, boolean invertY) {
        
        return internal.spawn(level, spawnStack == null ? null : spawnStack.getInternal(), spawningPlayer, position, spawnType, alignPosition, invertY);
    }
    
    /**
     * Spawns an entity.
     *
     * @param level     The level to spawn the entity in.
     * @param position  The position to spawn the entity at.
     * @param spawnType The type of spawn to use.
     *
     * @return The spawned entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static Entity spawn(EntityType<Entity> internal, ServerLevel level, BlockPos position, MobSpawnType spawnType) {
        
        return internal.spawn(level, position, spawnType);
    }
    
    /**
     * Spawns an entity.
     *
     * @param level         The level to spawn the entity in.
     * @param onSpawn       The consumer to call when the entity is spawned.
     * @param position      The position to spawn the entity at.
     * @param spawnType     The type of spawn to use.
     * @param alignPosition Whether to align the position of the entity.
     * @param invertY       Whether to offset the y position of the entity.
     *
     * @return The spawned entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static Entity spawn(EntityType<Entity> internal, ServerLevel level, @ZenCodeType.Nullable Consumer<Entity> onSpawn, BlockPos position, MobSpawnType spawnType, boolean alignPosition, boolean invertY) {
        
        return internal.spawn(level, onSpawn, position, spawnType, alignPosition, invertY);
    }
    
    /**
     * Checks if the entity can be summoned.
     *
     * @return True if the entity can be summoned.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("canSummon")
    public static boolean canSummon(EntityType<Entity> internal) {
        
        return internal.canSummon();
    }
    
    /**
     * Checks if the entity is fire immune.
     *
     * @param internal The entity type to check.
     *
     * @return True if the entity is fire immune.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("fireImmune")
    public static boolean fireImmune(EntityType<Entity> internal) {
        
        return internal.fireImmune();
    }
    
    /**
     * Checks if the entity can spawn far from the player.
     *
     * @return True if the entity can spawn far from the player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("canSpawnFarFromPlayer")
    public static boolean canSpawnFarFromPlayer(EntityType<Entity> internal) {
        
        return internal.canSpawnFarFromPlayer();
    }
    
    /**
     * Gets the category of the entity.
     *
     * @return The category of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("category")
    public static MobCategory getCategory(EntityType<Entity> internal) {
        
        return internal.getCategory();
    }
    
    /**
     * Gets the description ID of the entity.
     *
     * @return The description ID of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("descriptionId")
    public static String getDescriptionId(EntityType<Entity> internal) {
        
        return internal.getDescriptionId();
    }
    
    /**
     * Gets the description of the entity.
     *
     * @return The description of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("description")
    public static Component getDescription(EntityType<Entity> internal) {
        
        return internal.getDescription();
    }
    
    /**
     * Gets the short string representation of the entity.
     *
     * @return The short string representation of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("toShortString")
    public static String toShortString(EntityType<Entity> internal) {
        
        return internal.toShortString();
    }
    
    /**
     * Gets the default loot table of the entity.
     *
     * @return The default loot table of the entity.
     */
    @ZenCodeType.Getter("defaultLootTable")
    public static ResourceLocation getDefaultLootTable(EntityType<Entity> internal) {
        
        return internal.getDefaultLootTable().location();
    }
    
    /**
     * Gets the width of the entity.
     *
     * @return The width of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("width")
    public static float getWidth(EntityType<Entity> internal) {
        
        return internal.getWidth();
    }
    
    /**
     * Gets the height of the entity.
     *
     * @return The height of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("height")
    public static float getHeight(EntityType<Entity> internal) {
        
        return internal.getHeight();
    }
    
    /**
     * Creates an entity.
     *
     * @param level The level to create the entity in.
     *
     * @return The created entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static Entity create(EntityType<Entity> internal, Level level) {
        
        return internal.create(level);
    }
    
    /**
     * Checks if the entity is dangerous to blocks.
     *
     * @param state The state of the block to check.
     *
     * @return True if the entity is dangerous to blocks.
     */
    @ZenCodeType.Method
    public static boolean isBlockDangerous(EntityType<Entity> internal, BlockState state) {
        
        return internal.isBlockDangerous(state);
    }
    
    /**
     * Gets the dimensions of the entity.
     *
     * @return The dimensions of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("dimensions")
    public static EntityDimensions getDimensions(EntityType<Entity> internal) {
        
        return internal.getDimensions();
    }
    
    /**
     * Checks if the entity is in a tag.
     *
     * @param tag The tag to check.
     *
     * @return True if the entity is in the tag.
     */
    @ZenCodeType.Method
    public static boolean isIn(EntityType<Entity> internal, KnownTag<EntityType<Entity>> tag) {
        
        return internal.is(tag.<TagKey<EntityType<?>>> getTagKey());
    }
    
    /**
     * Gets the registry name of the entity.
     *
     * @return The registry name of the entity.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("registryName")
    public static ResourceLocation getRegistryName(EntityType<Entity> internal) {
        
        return BuiltInRegistries.ENTITY_TYPE.getKey(internal);
    }
    
    /**
     * Gets the command string of the entity.
     *
     * @return The command string of the entity.
     */
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(EntityType<Entity> internal) {
        
        return rawGetCommandString(internal);
    }
    
    /**
     * Gets the raw command string of the entity.
     *
     * @param internal The entity type to get the raw command string of.
     *
     * @return The raw command string of the entity.
     */
    public static String rawGetCommandString(EntityType<?> internal) {
        
        return "<entitytype:" + BuiltInRegistries.ENTITY_TYPE.getKey(internal) + ">";
    }
    
    /**
     * Casts the entity type to an entity ingredient.
     *
     * @return The entity ingredient.
     */
    @ZenCodeType.Caster(implicit = true)
    public static CTEntityIngredient asEntityIngredient(EntityType<Entity> internal) {
        
        return new CTEntityIngredient.EntityTypeIngredient(internal);
    }
    
    /**
     * Combines two entity ingredients.
     *
     * @param other The second entity ingredient.
     *
     * @return The combined entity ingredient.
     */
    @ZenCodeType.Operator(ZenCodeType.OperatorType.OR)
    public static CTEntityIngredient asList(EntityType<Entity> internal, CTEntityIngredient other) {
        
        List<CTEntityIngredient> elements = new ArrayList<>();
        elements.add(asEntityIngredient(internal));
        elements.add(other);
        return new CTEntityIngredient.CompoundEntityIngredient(elements);
    }
    
}
