// https://github.com/craftTweaker/CraftTweaker/issues/1683

import crafttweaker.api.loot.modifier.CommonLootModifiers;

<entitytype:minecraft:blaze>.addLootModifier("blaze_no_killed_by_player_condition", CommonLootModifiers.add(<item:minecraft:arrow>));