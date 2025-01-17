package com.blamejared.crafttweaker.natives.command;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.commands.CommandSource;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.Entity;
import org.openzen.zencode.java.ZenCodeType;

/**
 * Represents a source of a command execution, such as an {@link Entity} or the {@link MinecraftServer}.
 */
@ZenRegister
@Document("vanilla/api/command/CommandSource")
@NativeTypeRegistration(value = CommandSource.class, zenCodeName = "crafttweaker.api.command.CommandSource")
public class ExpandCommandSource {
    
    /**
     * Sends a message to the command source.
     *
     * @param component The message to send.
     */
    @ZenCodeType.Method
    public static void sendMessage(CommandSource internal, Component component) {
        
        internal.sendSystemMessage(component);
    }
    
    /**
     * Checks if the commands from this source should return successful messages
     *
     * @return True if the commands from this source should return successful messages, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("acceptsSuccess")
    public static boolean acceptsSuccess(CommandSource internal) {
        
        return internal.acceptsSuccess();
    }
    
    /**
     * Checks if the commands from this source should return failure messages
     *
     * @return True if the commands from this source should return failure messages, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("acceptsFailure")
    public static boolean acceptsFailure(CommandSource internal) {
        
        return internal.acceptsFailure();
    }
    
    /**
     * Checks if the commands from this source should inform admins.
     *
     * @return True if the commands from this source should inform admins, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("shouldInformAdmins")
    public static boolean shouldInformAdmins(CommandSource internal) {
        
        return internal.shouldInformAdmins();
    }
    
    /**
     * Checks if the command source always accepts.
     *
     * @return True if the command source always accepts, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("alwaysAccepts")
    public static boolean alwaysAccepts(CommandSource internal) {
        
        return internal.alwaysAccepts();
    }
    
}
