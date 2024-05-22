import crafttweaker.api.item.component.ItemContainerContents;

var c = <item:minecraft:cobblestone> * 64;
var a = <item:minecraft:air>;

var topContents = ItemContainerContents.of([
c,c,c,c,c,c,c,c,c,
c,a,a,a,a,a,a,a,c,
c,a,a,a,a,a,a,a,c
]);

var middleContents = ItemContainerContents.of([
c,a,a,a,a,a,a,a,c,
c,a,a,a,a,a,a,a,c,
c,a,a,a,a,a,a,a,c
]);

var bottomContents = ItemContainerContents.of([
c,a,a,a,a,a,a,a,c,
c,a,a,a,a,a,a,a,c,
c,c,c,c,c,c,c,c,c
]);

craftingTable.addShaped("expert_furnace", <item:minecraft:furnace>, [
    [<item:minecraft:shulker_box>.withContainer(topContents)],
    [<item:minecraft:shulker_box>.withContainer(middleContents)],
    [<item:minecraft:shulker_box>.withContainer(bottomContents)]
]);