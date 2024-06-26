package com.blamejared.crafttweaker.natives.item;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.neoforged.neoforge.common.ItemAbility;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("neoforge/api/item/ItemAbility")
@NativeTypeRegistration(value = ItemAbility.class, zenCodeName = "crafttweaker.neoforge.api.item.ItemAbility")
public class ExpandToolAction {
    
    @ZenCodeType.Getter("name")
    public static String name(ItemAbility internal) {
        
        return internal.name();
    }
    
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(ItemAbility internal) {
        
        return "<item_ability:" + name(internal) + ">";
    }
    
}
