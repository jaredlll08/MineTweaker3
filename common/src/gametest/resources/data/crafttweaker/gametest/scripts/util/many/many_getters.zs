import crafttweaker.api.util.Many;
import crafttweaker.api.tag.MCTag;

var many = <tag:item:minecraft:wool> * 2;
println(many.amount == 2);
println(many.data == (<tag:item:minecraft:wool> as MCTag)); // Stupid ZC bug requires the cast