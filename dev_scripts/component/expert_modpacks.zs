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

var top = <item:minecraft:shulker_box>.withContainer(topContents);
var middle = <item:minecraft:shulker_box>.withContainer(middleContents);
var bottom = <item:minecraft:shulker_box>.withContainer(bottomContents);

craftingTable.addShapeless("furnace_expert", <item:minecraft:shulker_box>.withContainer([top, middle, bottom]), [<item:minecraft:furnace>]);
craftingTable.addShaped("expert_furnace", <item:minecraft:furnace>, [
    [top],
    [middle],
    [bottom]
]);