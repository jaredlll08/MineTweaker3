/*
    When an arrow is used to interact with an empty cauldron, run a sequence.
*/
cauldron.addEmptyInteraction(<item:minecraft:arrow>, (blockState, level, pos, player, hand, stack) => {

    if !level.isClientSide {
            var level = level;
            var pos = pos;
            var blockState = blockState;
            level.sequence({version: "1.0.0"})
                // Runs a task immediately
                .run((level, data) => {
                    level.setBlockAndUpdate(pos, <blockstate:minecraft:grass_block>);
                    println(data.data["version"] as string);
                })
                // Waits 40 ticks
                .sleep(40)
                // Runs a task immediately (alias for 'run')
                .then((level) => {
                    level.setBlockAndUpdate(pos, <blockstate:minecraft:redstone_block>);
                })
                // Waits 40 ticks
                .sleep(40)
                // Runs a task immediately (alias for 'run')
                .then((level, data) => {
                    level.setBlockAndUpdate(pos, <blockstate:minecraft:bedrock>);
                    println(data.data["version"] as string);
                })
                // Waits 30 ticks
                .sleep(30)
                // Runs a task immediately (alias for 'run')
                .then((level) => {
                    level.setBlockAndUpdate(pos, blockState);
                })
                // Build and register the schedule
                .start();
    }
    return crafttweaker.api.world.ItemInteractionResult.sidedSuccess(level.isClientSide);
});