// https://github.com/craftTweaker/CraftTweaker/issues/1554

#modloader neoforge
import crafttweaker.neoforge.api.event.block.BlockToolModificationEvent;

events.register<BlockToolModificationEvent>((event) => {
    //some code
    println("called");
    event.cancel();
});