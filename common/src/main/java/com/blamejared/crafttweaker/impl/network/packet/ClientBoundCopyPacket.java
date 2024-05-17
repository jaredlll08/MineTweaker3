package com.blamejared.crafttweaker.impl.network.packet;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ClientBoundCopyPacket(String toCopy) implements CraftTweakerPacket {
    
    public static final ResourceLocation ID = CraftTweakerConstants.rl("copy");
    public static final StreamCodec<RegistryFriendlyByteBuf, ClientBoundCopyPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            ClientBoundCopyPacket::toCopy,
            ClientBoundCopyPacket::new
    );
    
    @Override
    public void handle() {
        
        Minecraft.getInstance().keyboardHandler.setClipboard(toCopy());
    }
    
    @Override
    public Type<? extends CustomPacketPayload> type() {
        
        return ClientBoundPackets.COPY.type();
    }
    
    
}
