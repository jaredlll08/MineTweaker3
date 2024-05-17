package com.blamejared.crafttweaker.natives.tooltip;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

@ZenRegister
@Document("vanilla/api/tooltip/TooltipComponent")
@NativeTypeRegistration(value = TooltipComponent.class, zenCodeName = "crafttweaker.api.tooltip.TooltipComponent")
public class ExpandTooltipComponent {

}
