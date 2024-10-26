#modloader neoforge
import crafttweaker.neoforge.api.event.brewing.PotionBrewEventPre;
import crafttweaker.neoforge.api.event.brewing.PotionBrewEventPost;
import crafttweaker.neoforge.api.event.conversion.LivingConversionPreEvent;
import crafttweaker.neoforge.api.event.entity.arrow.ArrowNockEvent;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.world.InteractionResultHolder;
import crafttweaker.neoforge.api.event.item.ItemTossEvent;
import crafttweaker.neoforge.api.event.interact.RightClickBlockEvent;
import crafttweaker.api.world.RandomizableContainer;
import crafttweaker.api.block.entity.BlockEntity;

events.register<PotionBrewEventPre>(event => {
    println("PotionBrewEventPre fired");
    var first = event.getItem(0);
    println(first.commandString);
});

events.register<PotionBrewEventPost>(event => {
    println("PotionBrewEventPost fired");
    var first = event.getItem(0);
    println(first.commandString);
});

events.register<ItemTossEvent>(event => {
    println("threw item");
    println(event.entity.item.commandString);
});

events.register<LivingConversionPreEvent>(event => {
    println(event.outcome.registryName);
    println(event.entity.type.registryName);
});

events.register<ArrowNockEvent>(event => {
    event.action = InteractionResultHolder<IItemStack>.success<IItemStack>(<item:minecraft:dirt>);
    println(event.action.result);
});

events.register<RightClickBlockEvent>(event => {
    println("called thing");
});

events.register<RightClickBlockEvent>(event => {
    val pos = event.blockPos;
    val level = event.entity.level;
    if !level.isClientSide && level.getBlockState(pos).block == <block:minecraft:chest> {
        val maybeBe = level.getBlockEntity(pos);
        if maybeBe is BlockEntity && (maybeBe as BlockEntity) is RandomizableContainer {
            val be as RandomizableContainer = maybeBe as BlockEntity;
            if <item:minecraft:book>.matches(event.entity.getMainHandItem()) {
                be.setLootTable(<resource:minecraft:blocks/anvil>);
            }
            if be.lootTable != null {
                println(be.lootTable.location());
            }
        }
    }
});