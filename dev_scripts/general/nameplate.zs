#modloader neoforge
import crafttweaker.api.text.Component;

<entitytype:minecraft:pig>.setNameTag((entity, result) => {
 result.alwaysRender();

 result.content = Component.literal("test");
});