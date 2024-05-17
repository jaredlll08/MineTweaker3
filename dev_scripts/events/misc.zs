#modloader neoforge
import crafttweaker.neoforge.api.event.brewing.PotionBrewEventPre;
import crafttweaker.neoforge.api.event.brewing.PotionBrewEventPost;
import crafttweaker.neoforge.api.event.conversion.LivingConversionPreEvent;
import crafttweaker.neoforge.api.event.entity.arrow.ArrowNockEvent;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.world.InteractionResultHolder;
import crafttweaker.neoforge.api.event.item.ItemTossEvent;
import crafttweaker.neoforge.api.event.interact.RightClickBlockEvent;

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