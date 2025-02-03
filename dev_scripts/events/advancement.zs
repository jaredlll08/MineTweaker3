#modloader neoforge
import crafttweaker.neoforge.api.event.advancement.AdvancementEarnEvent;
import crafttweaker.api.entity.type.player.ServerPlayer;
import crafttweaker.api.world.ServerLevel;

events.register<AdvancementEarnEvent>(event => {
    val player = event.entity;
    val level = player.level;
    if player is ServerPlayer && level is ServerLevel {
        val sp = player as ServerPlayer;
        val sl = level as ServerLevel;
        val server = sl.server;
        println(sp.advancements.getOrStartProgress(server.advancements[<resource:minecraft:story/mine_stone>]).done);
        // Get all the advancements and check them here
    }
});