package com.blamejared.crafttweaker.platform.network;

import com.blamejared.crafttweaker.impl.network.packet.CraftTweakerPacket;
import com.blamejared.crafttweaker.platform.services.INetworkHelper;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;

public class NeoForgeNetworkHelper implements INetworkHelper {
    
    @Override
    public <T extends CraftTweakerPacket> void sendPacket(ServerPlayer target, T packet) {
        
        PacketDistributor.sendToPlayer(target, packet);
    }
    
}
