package com.blamejared.crafttweaker.natives.game;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.level.CraftTweakerSavedData;
import com.blamejared.crafttweaker.api.level.CraftTweakerSavedDataHolder;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/game/Server")
@NativeTypeRegistration(value = MinecraftServer.class, zenCodeName = "crafttweaker.api.game.Server")
public class ExpandServer {
    
    /**
     * Gets the advancements manager.
     *
     * @return The advancements manager.
     */
    @ZenCodeType.Getter("advancements")
    public static ServerAdvancementManager getAdvancements(MinecraftServer internal) {
        
        return internal.getAdvancements();
    }
    
    /**
     * Gets the player list.
     *
     * @return The player list.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("playerList")
    public static PlayerList getPlayerList(MinecraftServer internal) {
        
        return internal.getPlayerList();
    }
    
    /**
     * Gets the custom data of the overworld.
     *
     * <p>The overworld is always loaded, so this can be used to store and access data no matter what level a player may be in.</p>
     *
     * @return The overworld's custom data.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("overworldData")
    public static CraftTweakerSavedData getOverworldData(MinecraftServer internal) {
        
        return ((CraftTweakerSavedDataHolder) internal.overworld()).crafttweaker$getSavedData();
    }
    
    /**
     * Gets the default game type.
     *
     * @return The default game type.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("defaultGameTime")
    public static GameType getDefaultGameType(MinecraftServer internal) {
        
        return internal.getDefaultGameType();
    }
    
    /**
     * Checks if the server is hardcore.
     *
     * @return True if the server is hardcore, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isHardcore")
    public static boolean isHardcore(MinecraftServer internal) {
        
        return internal.isHardcore();
    }
    
    /**
     * Gets the operator user permission level.
     *
     * @return The operator user permission level.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("operatorUserPermissionLevel")
    public static int getOperatorUserPermissionLevel(MinecraftServer internal) {
        
        return internal.getOperatorUserPermissionLevel();
    }
    
    /**
     * Checks if the server is running.
     *
     * @return True if the server is running, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isRunning")
    public static boolean isRunning(MinecraftServer internal) {
        
        return internal.isRunning();
    }
    
    /**
     * Checks if the server is shutting down.
     *
     * @return True if the server is shutting down, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isShutdown")
    public static boolean isShutdown(MinecraftServer internal) {
        
        return internal.isShutdown();
    }
    
    /**
     * Gets the overworld.
     *
     * @return The overworld.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("overworld")
    public static ServerLevel overworld(MinecraftServer internal) {
        
        return internal.overworld();
    }
    
    /**
     * Gets a level by its key.
     *
     * @param location The key of the level.
     *
     * @return The level.
     */
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static ServerLevel getLevel(MinecraftServer internal, ResourceLocation location) {
        
        return internal.getLevel(ResourceKey.create(Registries.DIMENSION, location));
    }
    
    /**
     * Gets the keys of all levels.
     *
     * @return The keys of all levels.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("levelKeys")
    public static List<ResourceLocation> levelKeys(MinecraftServer internal) {
        
        return internal.levelKeys().stream().map(ResourceKey::location).toList();
    }
    
    /**
     * Gets all levels.
     *
     * @return All levels.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("allLevels")
    public static Iterable<ServerLevel> getAllLevels(MinecraftServer internal) {
        
        return internal.getAllLevels();
    }
    
    /**
     * Gets the server version.
     *
     * @return The server version.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("serverVersion")
    public static String getServerVersion(MinecraftServer internal) {
        
        return internal.getServerVersion();
    }
    
    /**
     * Gets the player count.
     *
     * @return The player count.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("playerCount")
    public static int getPlayerCount(MinecraftServer internal) {
        
        return internal.getPlayerCount();
    }
    
    /**
     * Gets the maximum number of players.
     *
     * @return The maximum number of players.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("maxPlayer")
    public static int getMaxPlayers(MinecraftServer internal) {
        
        return internal.getMaxPlayers();
    }
    
    /**
     * Gets the player names.
     *
     * @return The player names.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("playerNames")
    public static String[] getPlayerNames(MinecraftServer internal) {
        
        return internal.getPlayerNames();
    }
    
    /**
     * Gets the server mod name.
     *
     * @return The server mod name.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("serverModName")
    public static String getServerModName(MinecraftServer internal) {
        
        return internal.getServerModName();
    }
    
    /**
     * Gets the modded status.
     *
     * @return The modded status.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    @ZenCodeType.Getter("moddeStatus") //TODO 1.21.2 fix the type
    public static String getModdedStatus(MinecraftServer internal) {
        
        return internal.getModdedStatus().fullDescription();
    }
    
    /**
     * Checks if the server is singleplayer.
     *
     * @return True if the server is singleplayer, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isSingleplayer")
    public static boolean isSingleplayer(MinecraftServer internal) {
        
        return internal.isSingleplayer();
    }
    
    /**
     * Sets the difficulty of the server.
     *
     * @param difficulty The difficulty to set.
     * @param force      Whether to force the difficulty.
     */
    @ZenCodeType.Method
    public static void setDifficulty(MinecraftServer internal, Difficulty difficulty, boolean force) {
        
        internal.setDifficulty(difficulty, force);
    }
    
    /**
     * Sets if the difficulty should be locked.
     *
     * @param locked Whether to lock the difficulty.
     */
    @ZenCodeType.Method
    public static void setDifficultyLocked(MinecraftServer internal, boolean locked) {
        
        internal.setDifficultyLocked(locked);
    }
    
    /**
     * Checks if monsters are spawning.
     *
     * @return True if monsters are spawning, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isSpawningMonsters")
    public static boolean isSpawningMonsters(MinecraftServer internal) {
        
        return internal.isSpawningMonsters();
    }
    
    /**
     * Checks if the server is dedicated.
     *
     * @return True if the server is dedicated, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isDedicatedServer")
    public static boolean isDedicatedServer(MinecraftServer internal) {
        
        return internal.isDedicatedServer();
    }
    
    /**
     * Checks if animals are spawning.
     *
     * @return True if animals are spawning, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isSpawningAnimals")
    public static boolean isSpawningAnimals(MinecraftServer internal) {
        
        return internal.isSpawningAnimals();
    }
    
    /**
     * Checks if NPCs are enabled.
     *
     * @return True if NPCs are enabled, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("areNpcsEnabled")
    public static boolean areNpcsEnabled(MinecraftServer internal) {
        
        return internal.areNpcsEnabled();
    }
    
    /**
     * Checks if PvP is allowed.
     *
     * @return True if PvP is allowed, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isPvpAllowed")
    public static boolean isPvpAllowed(MinecraftServer internal) {
        
        return internal.isPvpAllowed();
    }
    
    /**
     * Sets if PvP is allowed.
     *
     * @param pvpAllowed Whether to allow PvP.
     */
    @ZenCodeType.Method
    public static void setPvpAllowed(MinecraftServer internal, boolean pvpAllowed) {
        
        internal.setPvpAllowed(pvpAllowed);
    }
    
    /**
     * Checks if flight is allowed.
     *
     * @return True if flight is allowed, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isFlightAllowed")
    public static boolean isFlightAllowed(MinecraftServer internal) {
        
        return internal.isFlightAllowed();
    }
    
    /**
     * Sets if flight is allowed.
     *
     * @param flightAllowed Whether to allow flight.
     */
    @ZenCodeType.Method
    public static void setFlightAllowed(MinecraftServer internal, boolean flightAllowed) {
        
        internal.setFlightAllowed(flightAllowed);
    }
    
    /**
     * Checks if command blocks are enabled.
     *
     * @return True if command blocks are enabled, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isCommandBlockEnabled")
    public static boolean isCommandBlockEnabled(MinecraftServer internal) {
        
        return internal.isCommandBlockEnabled();
    }
    
    /**
     * Gets the message of the day.
     *
     * @return The message of the day.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("motd")
    public static String getMotd(MinecraftServer internal) {
        
        return internal.getMotd();
    }
    
    /**
     * Sets the message of the day.
     *
     * @param motd The message of the day.
     */
    @ZenCodeType.Method
    public static void setMotd(MinecraftServer internal, String motd) {
        
        internal.setMotd(motd);
    }
    
    /**
     * Checks if the server is stopped.
     *
     * @return True if the server is stopped, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isStopped")
    public static boolean isStopped(MinecraftServer internal) {
        
        return internal.isStopped();
    }
    
    /**
     * Sets the default game type.
     *
     * @param gameType The game type to set.
     */
    @ZenCodeType.Method
    public static void setDefaultGameType(MinecraftServer internal, GameType gameType) {
        
        internal.setDefaultGameType(gameType);
    }
    
    /**
     * Checks if the server is ready.
     *
     * @return True if the server is ready, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isReady")
    public static boolean isReady(MinecraftServer internal) {
        
        return internal.isReady();
    }
    
    /**
     * Gets the tick count.
     *
     * @return The tick count.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("tickCount")
    public static int getTickCount(MinecraftServer internal) {
        
        return internal.getTickCount();
    }
    
    /**
     * Gets the spawn protection radius.
     *
     * @return The spawn protection radius.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("spawnProtectionRadius")
    public static int getSpawnProtectionRadius(MinecraftServer internal) {
        
        return internal.getSpawnProtectionRadius();
    }
    
    /**
     * Checks if a position is under spawn protection.
     *
     * @param level  The level.
     * @param pos    The position.
     * @param player The player.
     *
     * @return True if the position is under spawn protection, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean isUnderSpawnProtection(MinecraftServer internal, ServerLevel level, BlockPos pos, Player player) {
        
        return internal.isUnderSpawnProtection(level, pos, player);
    }
    
    /**
     * Gets the absolute maximum world size.
     *
     * @return The absolute maximum world size.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("absoluteMaxWorldSize")
    public static int getAbsoluteMaxWorldSize(MinecraftServer internal) {
        
        return internal.getAbsoluteMaxWorldSize();
    }
    
    /**
     * Gets the spawn radius.
     *
     * @param level The level.
     *
     * @return The spawn radius.
     */
    @ZenCodeType.Method
    public static int getSpawnRadius(MinecraftServer internal, @ZenCodeType.Nullable ServerLevel level) {
        
        return internal.getSpawnRadius(level);
    }
    
    /**
     * Checks if the whitelist is enforced.
     *
     * @return True if the whitelist is enforced, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isEnforceWhitelist")
    public static boolean isEnforceWhitelist(MinecraftServer internal) {
        
        return internal.isEnforceWhitelist();
    }
    
    /**
     * Sets if the whitelist is enforced.
     *
     * @param enforceWhitelist Whether to enforce the whitelist.
     */
    @ZenCodeType.Method
    public static void setEnforceWhitelist(MinecraftServer internal, boolean enforceWhitelist) {
        
        internal.setEnforceWhitelist(enforceWhitelist);
    }
    
    /**
     * Gets the current smoothed tick time.
     *
     * @return The current smoothed tick time.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("currentSmoothedTickTime")
    public static float getCurrentSmoothedTickTime(MinecraftServer internal) {
        
        return internal.getCurrentSmoothedTickTime();
    }
    
    /**
     * Gets the forced game type.
     *
     * @return The forced game type.
     */
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("forcedGameType")
    public static GameType getForcedGameType(MinecraftServer internal) {
        
        return internal.getForcedGameType();
    }
    
    /**
     * Runs a command, if silent is true, the output is hidden.
     *
     * @docParam command "time set day"
     * @docParam silent true
     */
    @ZenCodeType.Method
    public static void executeCommand(MinecraftServer internal, String command, @ZenCodeType.OptionalBoolean boolean silent) {
        
        CommandSourceStack source = internal.createCommandSourceStack();
        executeCommandInternal(internal, command, silent ? source.withSuppressedOutput() : source);
    }
    
    /**
     * let a player send a command, if silent is true, the output is hidden.
     *
     * @docParam command "time set day"
     * @docParam player player
     * @docParam silent true
     */
    @ZenCodeType.Method
    public static void executeCommand(MinecraftServer internal, String command, Player player, @ZenCodeType.OptionalBoolean boolean silent) {
        
        CommandSourceStack source = player.createCommandSourceStack();
        executeCommandInternal(internal, command, silent ? source.withSuppressedOutput() : source);
    }
    
    private static void executeCommandInternal(MinecraftServer internal, String command, CommandSourceStack source) {
        
        ParseResults<CommandSourceStack> parsedResults = internal.getCommands().getDispatcher().parse(command, source);
        internal.getCommands().performCommand(parsedResults, command);
    }
    
}
