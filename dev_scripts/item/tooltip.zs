import crafttweaker.api.text.Component;

<item:minecraft:dirt>.addTooltip("This is line 1!");
<item:minecraft:dirt>.addTooltip("This is line 2!");
<item:minecraft:dirt>.addShiftTooltip("This is hidden content", "what is this?");

<item:minecraft:dirt>.modifyTooltip((stack, tooltip, context, flag) => {
    tooltip.insert(1, Component.literal("Your game will *not* crash"));
});