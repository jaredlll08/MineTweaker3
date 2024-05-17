package com.blamejared.crafttweaker.mixin.common.access.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagNetworkSerialization;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TagNetworkSerialization.class)
public interface AccessTagNetworkSerialization {
    
    @Invoker("deserializeTagsFromNetwork")
    static <T> void crafttweaker$$callDeserializeTagsFromNetwork(
            ResourceKey<? extends Registry<T>> $$0, Registry<T> $$1, TagNetworkSerialization.NetworkPayload $$2, TagNetworkSerialization.TagOutput<T> $$3
    ) {}
    
}
