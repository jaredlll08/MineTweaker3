package com.blamejared.crafttweaker.mixin.common.access.neoforge;

import com.blamejared.crafttweaker.impl.datamap.LoadResultGetter;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "net.neoforged.neoforge.registries.DataMapLoader$LoadResult", remap = false)
public interface AccessDataMapLoaderResult extends LoadResultGetter {
}
