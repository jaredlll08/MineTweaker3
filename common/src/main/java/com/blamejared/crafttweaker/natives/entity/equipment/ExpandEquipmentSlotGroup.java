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
    
    @ZenCodeType.StaticExpansionMethod
    public static EquipmentSlotGroup bySlot(EquipmentSlot slot) {
        
        return EquipmentSlotGroup.bySlot(slot);
    }
    
    @ZenCodeType.Method
    public static boolean test(EquipmentSlotGroup internal, EquipmentSlot slot) {
        
        return internal.test(slot);
    }
    
}
