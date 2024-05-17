package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.component.DebugStickState;

@ZenRegister
@Document("vanilla/api/item/component/DebugStickState")
@NativeTypeRegistration(value = DebugStickState.class, zenCodeName = "crafttweaker.api.item.component.DebugStickState")
public class ExpandDebugStickState {
    // TODO we don't expose Property, so this is kinda useless
}
