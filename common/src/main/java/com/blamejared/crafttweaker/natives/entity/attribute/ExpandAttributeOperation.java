package com.blamejared.crafttweaker.natives.entity.attribute;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/attribute/AttributeOperation")
@NativeTypeRegistration(value = AttributeModifier.Operation.class, zenCodeName = "crafttweaker.api.entity.attribute.AttributeOperation")
@BracketEnum("minecraft:attribute/operation")
public class ExpandAttributeOperation {
    
    /**
     * Gets the id of this operation.
     *
     * @return The id of this operation.
     */
    @ZenCodeType.Getter("id")
    public static int id(AttributeModifier.Operation internal) {
        
        return internal.id();
    }
    
}
