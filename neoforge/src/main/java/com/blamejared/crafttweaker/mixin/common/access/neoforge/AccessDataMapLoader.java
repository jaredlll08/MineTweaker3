package com.blamejared.crafttweaker.mixin.common.access.neoforge;

import com.blamejared.crafttweaker.impl.datamap.LoadResultGetter;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DataMapLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(value = DataMapLoader.class, remap = false)
public interface AccessDataMapLoader {
    
    @Accessor("results")
    Map<ResourceKey<? extends Registry<?>>, LoadResultGetter> results();
    
}
