package com.blamejared.crafttweaker.natives.item.type.sword;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.SwordItem;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/type/sword/SwordItem")
@NativeTypeRegistration(value = SwordItem.class, zenCodeName = "crafttweaker.api.item.type.sword.SwordItem")
public class ExpandSwordItem {
    
}
