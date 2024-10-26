#modloader neoforge
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.world.InteractionResultHolder;
import crafttweaker.neoforge.api.event.interact.RightClickBlockEvent;
import crafttweaker.api.block.entity.BlockEntity;
import crafttweaker.api.block.entity.type.DecoratedPotBlockEntity;

events.register<RightClickBlockEvent>(event => {
    val pos = event.blockPos;
    val level = event.entity.level;
    val entity = event.entity;
    if !level.isClientSide && level.getBlockState(pos).block == <block:minecraft:decorated_pot> {
        val maybeBe = level.getBlockEntity(pos);
        if maybeBe is BlockEntity && (maybeBe as BlockEntity) is DecoratedPotBlockEntity {
            val be = maybeBe as BlockEntity as DecoratedPotBlockEntity;
            (be.decorations.ordered as crafttweaker.api.item.ItemDefinition[]).each(item => {
                if <item:minecraft:burn_pottery_sherd>.matches(item) {
                    entity.drop(be.getPotAsItem(), false);
                }
                println(item.commandString);
            });
        }
    }
});