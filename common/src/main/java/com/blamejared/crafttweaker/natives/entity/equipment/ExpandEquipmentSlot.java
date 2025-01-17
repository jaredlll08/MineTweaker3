package com.blamejared.crafttweaker.natives.entity.equipment;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.EquipmentSlot;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/equipment/EquipmentSlot")
@NativeTypeRegistration(value = EquipmentSlot.class, zenCodeName = "crafttweaker.api.entity.equipment.EquipmentSlot")
@BracketEnum("minecraft:equipmentslot")
public class ExpandEquipmentSlot {
    
    /**
     * Gets the type of the {@link EquipmentSlot}.
     *
     * @return The type of the {@link EquipmentSlot}.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("type")
    public static EquipmentSlot.Type getType(EquipmentSlot internal) {
        
        return internal.getType();
    }
    
    /**
     * Gets the index of the {@link EquipmentSlot}.
     *
     * @return The index of the {@link EquipmentSlot}.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("index")
    public static int getIndex(EquipmentSlot internal) {
        
        return internal.getIndex();
    }
    
    /**
     * Gets the name of the {@link EquipmentSlot}.
     *
     * @return The name of the {@link EquipmentSlot}.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("name")
    public static String getName(EquipmentSlot internal) {
        
        return internal.getName();
    }
    
    /**
     * Gets the command string of the {@link EquipmentSlot}.
     *
     * @return The command string of the {@link EquipmentSlot}.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(EquipmentSlot internal) {
        
        return "<constant:minecraft:equipmentslot:" + internal.getName() + ">";
    }
    
}
