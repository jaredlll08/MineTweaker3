package com.blamejared.crafttweaker.mixin.common.access.brewing;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(PotionBrewing.class)
public interface AccessPotionBrewing {
    
    @Accessor("potionMixes")
    List<PotionBrewing.Mix<Potion>> crafttweaker$getPotionMixes();
    
    @Mutable
    @Accessor("potionMixes")
    void crafttweaker$setPotionMixes(List<PotionBrewing.Mix<Potion>> potionMixes);
    
}
