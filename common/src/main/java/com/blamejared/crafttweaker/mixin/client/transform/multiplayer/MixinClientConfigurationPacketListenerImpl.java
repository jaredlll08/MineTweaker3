package com.blamejared.crafttweaker.mixin.client.transform.multiplayer;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.tag.CraftTweakerTagRegistry;
import com.blamejared.crafttweaker.impl.script.RecipeManagerScriptLoader;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.multiplayer.ClientConfigurationPacketListenerImpl;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.protocol.common.ClientboundUpdateTagsPacket;
import net.minecraft.network.protocol.configuration.ClientboundFinishConfigurationPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientConfigurationPacketListenerImpl.class)
public abstract class MixinClientConfigurationPacketListenerImpl {
    
    
    @Inject(method = "handleUpdateTags", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/protocol/common/ClientboundUpdateTagsPacket;getTags()Ljava/util/Map;"))
    private void crafttweaker$handleUpdateTags(ClientboundUpdateTagsPacket packet, CallbackInfo ci) {
        
        //We don't collect the registry access here as it doesn't contain all the registries
        CraftTweakerTagRegistry.INSTANCE.bind(packet.getTags());
        
        RecipeManagerScriptLoader.updateState(RecipeManagerScriptLoader.UpdatedState.TAGS, null);
    }
    
    @Inject(method = "handleConfigurationFinished", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/Connection;setupInboundProtocol(Lnet/minecraft/network/ProtocolInfo;Lnet/minecraft/network/PacketListener;)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private void crafttweaker$finish(ClientboundFinishConfigurationPacket $$0, CallbackInfo ci, @Local(index = 2) RegistryAccess.Frozen receivedRegistries) {
        
        CraftTweakerAPI.getAccessibleElementsProvider().client().registryAccess(receivedRegistries);
    }
    
}
