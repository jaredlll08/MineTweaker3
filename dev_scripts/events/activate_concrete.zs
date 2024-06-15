#modloader neoforge
import crafttweaker.neoforge.api.event.interact.RightClickBlockEvent;
import crafttweaker.api.block.Block;
import crafttweaker.api.world.ServerLevel;

val COLOR_MAP = {
    <block:minecraft:red_concrete>: 0xff0000,
    <block:minecraft:green_concrete>: 0x00ff00,
    <block:minecraft:blue_concrete>: 0x0000ff,
} as int[Block];

events.register<RightClickBlockEvent>(event => {
    val pos = event.blockPos;
    val player = event.entity;
    val level = player.level;
    if level.isClientSide || !(level is ServerLevel) {
        return;
    }
    val sl = level as ServerLevel;
    val state = level.getBlockState(pos);
    if state.block == <block:minecraft:lever> {
        val facing = state.getPropertyValue("facing");
        val face = state.getPropertyValue("face");

        var direction = <constant:minecraft:direction:down>;
        if face == "CEILING" {
            direction = <constant:minecraft:direction:up>;
        } else if face == "FLOOR" {
            direction = <constant:minecraft:direction:down>;
        } else {
            switch facing {
                case "west":
                    direction = <constant:minecraft:direction:east>;
                    break;
                case "east":
                    direction = <constant:minecraft:direction:west>;
                    break;
                case "up":
                    direction = <constant:minecraft:direction:down>;
                    break;
                case "down":
                    direction = <constant:minecraft:direction:up>;
                    break;
                case "north":
                    direction = <constant:minecraft:direction:south>;
                    break;
                case "south":
                    direction = <constant:minecraft:direction:north>;
                    break;
                }
        }

        val onBlock = level.getBlockState(pos.relative(direction)).block;
        if onBlock in COLOR_MAP {
            sl.server.executeCommand("time set " + COLOR_MAP[onBlock], player, true);
        }

    }
});
