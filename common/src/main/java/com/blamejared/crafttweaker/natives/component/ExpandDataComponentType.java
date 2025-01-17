package com.blamejared.crafttweaker.natives.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

/**
 * Represents a type of data that game objects can have.
 *
 * Some examples are:
 * {@code
 * <componenttype:minecraft:stack_size>
 * <componenttype:minecraft:durability>
 * <componenttype:minecraft:enchantment:minecraft:damage>
 * }
 * 
 * Data components can be stored on any type that implements {@link DataComponentHolder}, such as {@link net.minecraft.world.item.ItemStack}.
 */
@ZenRegister
@Document("vanilla/api/component/DataComponentType")
@NativeTypeRegistration(value = DataComponentType.class, zenCodeName = "crafttweaker.api.component.DataComponentType")
public class ExpandDataComponentType {
    
    //TODO 1.21.x rework data components into something akin to tags, with <component:item:minecraft:damage> / <component:enchantment:minecraft:damage>
    
    /**
     * Checks if the data component type is transient, meaning it is not saved.
     *
     * @return True if the data component type is transient, false otherwise.
     */
    @ZenCodeType.Getter("isTransient")
    public static boolean isTransient(DataComponentType internal) {
        
        return internal.isTransient();
    }
    
    /**
     * Gets the registry name of the data component type.
     *
     * @return The registry name of the data component type.
     */
    public static ResourceLocation getRegistryName(DataComponentType internal) {
        
        return Services.REGISTRY.keyOrThrow(Registries.DATA_COMPONENT_TYPE, internal);
    }
    
    /**
     * Gets the command string representation of the data component type.
     *
     * @return The command string representation of the data component type.
     */
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(DataComponentType internal) {
        
        return "<componenttype:" + getRegistryName(internal) + ">";
    }
    
}
