package com.blamejared.crafttweaker.impl.network.packet;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public interface CraftTweakerPacket extends CustomPacketPayload {
    
    void handle();
    
    Type<? extends CustomPacketPayload> type();
    
}
