package com.blamejared.crafttweaker.impl.network.packet;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.command.CommandUtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ClientBoundSendOpenFileMessagePacket(Component chatComponent, Component hoverComponent,
                                                   String path) implements CraftTweakerPacket {
    
    public static final ResourceLocation ID = CraftTweakerConstants.rl("open_file");
    public static final StreamCodec<RegistryFriendlyByteBuf, ClientBoundSendOpenFileMessagePacket> STREAM_CODEC = StreamCodec.composite(
            ComponentSerialization.STREAM_CODEC,
            ClientBoundSendOpenFileMessagePacket::chatComponent,
            ComponentSerialization.STREAM_CODEC,
            ClientBoundSendOpenFileMessagePacket::hoverComponent,
            ByteBufCodecs.STRING_UTF8,
            ClientBoundSendOpenFileMessagePacket::path,
            ClientBoundSendOpenFileMessagePacket::new
    );
    
    @Override
    public void handle() {
        // this.component is what is printed in chat and what is
        
        MutableComponent component = this.chatComponent().copy()
                .withStyle(style -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, this.hoverComponent()
                        .copy())))
                .withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, this.path())));
        
        Minecraft.getInstance().player.sendSystemMessage(component);
        if(!component.getString().isBlank()) {
            CommandUtilities.COMMAND_LOGGER.info(component.getString());
        }
    }
    
    @Override
    public Type<? extends CustomPacketPayload> type() {
        
        return ClientBoundPackets.OPEN_FILE.type();
    }
    
}
