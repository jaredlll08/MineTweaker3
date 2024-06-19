package com.blamejared.crafttweaker.mixin.common.access.item;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;


@Mixin(Item.class)
public interface AccessItem {
    
    @Accessor("components")
    DataComponentMap crafttweaker$getComponents();
    
    @Mutable
    @Accessor("components")
    void crafttweaker$setComponents(DataComponentMap components);
    
}
