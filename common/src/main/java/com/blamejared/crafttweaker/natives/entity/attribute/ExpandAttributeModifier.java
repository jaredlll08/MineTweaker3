package com.blamejared.crafttweaker.natives.entity.attribute;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/attribute/AttributeModifier")
@NativeTypeRegistration(value = AttributeModifier.class, zenCodeName = "crafttweaker.api.entity.attribute.AttributeModifier")
public class ExpandAttributeModifier {
    
    /**
     * Creates a new AttributeModifier
     *
     * @param id        the id of this attribute modifier
     * @param amount    the amount of this attribute modifier
     * @param operation the operation of this attribute modifier.
     *
     * @return the new attribute modifier
     *
     * @docParam id <resource:crafttweaker:generic.scripting>
     * @docParam amount 11.4
     * @docParam operation AttributeOperation.ADDITION
     */
    @ZenCodeType.StaticExpansionMethod
    public static AttributeModifier create(ResourceLocation id, double amount, AttributeModifier.Operation operation) {
        
        return new AttributeModifier(id, amount, operation);
    }
    
    /**
     * Gets the ID of this AttributeModifier.
     *
     * @return The ID of this AttributeModifier
     */
    @ZenCodeType.Getter("id")
    public static ResourceLocation id(AttributeModifier internal) {
        
        return internal.id();
    }
    
    /**
     * Gets the amount of this AttributeModifier.
     *
     * @return The amount of this AttributeModifier.
     */
    @ZenCodeType.Getter("amount")
    public static double amount(AttributeModifier internal) {
        
        return internal.amount();
    }
    
    /**
     * Gets the operation of this AttributeModifier.
     *
     * @return The operation of this AttributeModifier.
     */
    @ZenCodeType.Getter("operation")
    public static AttributeModifier.Operation operation(AttributeModifier internal) {
        
        return internal.operation();
    }
    
}
