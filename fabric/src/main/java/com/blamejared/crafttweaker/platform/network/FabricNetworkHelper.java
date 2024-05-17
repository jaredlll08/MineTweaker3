package com.blamejared.crafttweaker.platform.network;

import com.blamejared.crafttweaker.impl.network.packet.CraftTweakerPacket;
import com.blamejared.crafttweaker.platform.services.INetworkHelper;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;

public class FabricNetworkHelper implements INetworkHelper {
    
    @Override
    public <T extends CraftTweakerPacket> void sendPacket(ServerPlayer target, T packet) {
        
        ServerPlayNetworking.send(target, packet);
    }
    
}
