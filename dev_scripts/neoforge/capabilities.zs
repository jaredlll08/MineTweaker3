#modloader neoforge

import crafttweaker.neoforge.api.event.interact.RightClickBlockEvent;
import crafttweaker.neoforge.api.event.interact.RightClickItemEvent;
import crafttweaker.neoforge.api.capability.IItemHandler;
import crafttweaker.api.capability.ItemHandlerCaps;
import crafttweaker.api.util.Direction;
import crafttweaker.api.util.math.BlockPos;
import crafttweaker.api.data.IData;

/*
    Listens to the right click block event for when a player right clicks a block with an item handler capability, and inserts a diamond into the 2nd slot.
*/
events.register<RightClickBlockEvent>((event) => {
    if event.entity.level.isClientSide || event.hand != <constant:minecraft:interactionhand:main_hand> { return; }

    var player = event.entity;
    var level = player.level;
    var tile = level.getBlockEntity(event.blockPos);

    if tile != null {
        var items = level.getCapability<IItemHandler, Direction?>(ItemHandlerCaps.BLOCK, event.blockPos, null);
        if items != null {
            items.insertItem(2, <item:minecraft:diamond>, false);
        }
    }
});
