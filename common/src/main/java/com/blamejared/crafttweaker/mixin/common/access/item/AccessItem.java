package com.blamejared.crafttweaker.mixin.common.access.item;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.UUID;

@Mixin(Item.class)
public interface AccessItem {
    
    @Accessor("BASE_ATTACK_DAMAGE_UUID")
    static UUID crafttweaker$getBASE_ATTACK_DAMAGE_UUID() {throw new UnsupportedOperationException();}
    
    @Accessor("BASE_ATTACK_SPEED_UUID")
    static UUID crafttweaker$getBASE_ATTACK_SPEED_UUID() {throw new UnsupportedOperationException();}
    
    @Accessor("components")
    DataComponentMap crafttweaker$getComponents();
    
    @Mutable
    @Accessor("components")
    void crafttweaker$setComponents(DataComponentMap components);
    
}
