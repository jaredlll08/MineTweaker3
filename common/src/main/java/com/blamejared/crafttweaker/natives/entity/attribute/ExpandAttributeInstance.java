package com.blamejared.crafttweaker.natives.entity.attribute;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@Document("vanilla/api/entity/attribute/AttributeInstance")
@NativeTypeRegistration(value = AttributeInstance.class, zenCodeName = "crafttweaker.api.entity.attribute.AttributeInstance")
public class ExpandAttributeInstance {
    
    /**
     * Gets the base value of the attribute.
     *
     * @return The base value of the attribute.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("baseValue")
    public static double getBaseValue(AttributeInstance internal) {
        
        return internal.getBaseValue();
    }
    
    /**
     * Sets the base value of the attribute.
     *
     * @param value The new base value of the attribute.
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("baseValue")
    public static void setBaseValue(AttributeInstance internal, double value) {
        
        internal.setBaseValue(value);
    }
    
    /**
     * Gets the current value of the attribute.
     *
     * @return The current value of the attribute.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("value")
    public static double getValue(AttributeInstance internal) {
        
        return internal.getValue();
    }
    
    /**
     * Gets the {@link AttributeModifier} of the attribute.
     *
     * @return The modifiers of the attribute.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("modifiers")
    public static List<AttributeModifier> getModifiers(AttributeInstance internal) {
        
        return new ArrayList<>(internal.getModifiers());
    }
    
    /**
     * Adds a transient {@link AttributeModifier} to the attribute.
     *
     * @param modifier The modifier to add.
     */
    @ZenCodeType.Method
    public static void addTransientModifier(AttributeInstance internal, AttributeModifier modifier) {
        
        internal.addTransientModifier(modifier);
    }
    
    /**
     * Adds a permanent {@link AttributeModifier} to the attribute.
     *
     * @param modifier The modifier to add.
     */
    @ZenCodeType.Method
    public static void addPermanentModifier(AttributeInstance internal, AttributeModifier modifier) {
        
        internal.addPermanentModifier(modifier);
    }
    
    /**
     * Checks if the attribute has a modifier with the given ID.
     *
     * @param id The ID of the modifier to check for.
     *
     * @return True if the attribute has a modifier with the given ID, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean hasModifier(AttributeInstance internal, ResourceLocation id) {
        
        return internal.hasModifier(id);
    }
    
    /**
     * Gets the {@link AttributeModifier} with the given ID.
     *
     * @param id The ID of the modifier to get.
     *
     * @return The {@link AttributeModifier} with the given ID, or null if it does not exist.
     */
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static AttributeModifier getModifier(AttributeInstance internal, ResourceLocation id) {
        
        return internal.getModifier(id);
    }
    
    /**
     * Removes the {@link AttributeModifier} with the given ID.
     *
     * @param id The ID of the modifier to remove.
     */
    @ZenCodeType.Method
    public static void removeModifier(AttributeInstance internal, ResourceLocation id) {
        
        internal.removeModifier(id);
    }
    
}
