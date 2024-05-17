import crafttweaker.api.villager.trade.type.SuspiciousStewForEmerald;
import crafttweaker.api.item.component.SuspiciousStewEffects;
import crafttweaker.api.item.component.SuspiciousStewEffectsEntry;
villagerTrades.addTrade(<profession:minecraft:farmer>, 1, SuspiciousStewForEmerald.of(SuspiciousStewEffects.of([SuspiciousStewEffectsEntry.of(<mobeffect:minecraft:haste>, 300)]), 1, 0.05));