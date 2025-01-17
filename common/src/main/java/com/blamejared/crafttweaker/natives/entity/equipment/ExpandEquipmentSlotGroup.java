package com.blamejared.crafttweaker.natives.entity.equipment;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/equipment/EquipmentSlotGroup")
@NativeTypeRegistration(value = EquipmentSlotGroup.class, zenCodeName = "crafttweaker.api.entity.equipment.EquipmentSlotGroup")
@BracketEnum("minecraft:equipmentslot/group")
public class ExpandEquipmentSlotGroup {
    
    /**
     * Gets the {@link EquipmentSlotGroup} by the {@link EquipmentSlot}.
     *
     * @param slot The {@link EquipmentSlot} to get the {@link EquipmentSlotGroup} of.
     *
     * @return The {@link EquipmentSlotGroup} of the {@link EquipmentSlot}.
     */
    @ZenCodeType.StaticExpansionMethod
    public static EquipmentSlotGroup bySlot(EquipmentSlot slot) {
        
        return EquipmentSlotGroup.bySlot(slot);
    }
    
    /**
     * Checks if the {@link EquipmentSlotGroup} contains the {@link EquipmentSlot}.
     *
     * @param slot The {@link EquipmentSlot} to check.
     *
     * @return True if the {@link EquipmentSlotGroup} contains the {@link EquipmentSlot}, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean test(EquipmentSlotGroup internal, EquipmentSlot slot) {
        
        return internal.test(slot);
    }
    
}
