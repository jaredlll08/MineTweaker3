#modloader neoforge
import crafttweaker.api.entity.type.player.Player;
import crafttweaker.api.ingredient.IIngredient;
import crafttweaker.api.item.IItemStack;
import stdlib.List;
import crafttweaker.neoforge.api.event.entity.use.LivingEntityUseItemFinishEvent;
import crafttweaker.api.entity.effect.MobEffectInstance;

public class OnEat {
    public static var EATS = new List<OnEat>();
    public var toEat as IIngredient;
    public var onEaten as function(player as Player) as void;

    public this(toEat as IIngredient, onEaten as function(player as Player) as void) {
        this.toEat = toEat;
        this.onEaten = onEaten;
    }

    public static register(toEat as IIngredient, itemToGive as IItemStack) as void {
        OnEat.EATS.add(new OnEat(toEat, player => player.give(itemToGive)));
    }

    public static register(toEat as IIngredient, onEaten as function(player as Player) as void) as void {
        OnEat.EATS.add(new OnEat(toEat, onEaten));
    }

    public apply(player as Player) as void {
        var on as function(player as Player) as void = this.onEaten;
        on(player);
    }

    public static listen() as void {
        events.register<LivingEntityUseItemFinishEvent>(event => {
            val entity = event.entity;
            val level = entity.level;
            if level.isClientSide || !(entity is Player) {
                return;
            }
            val player = entity as Player;
            for eat in OnEat.EATS {
                if eat.toEat.matches(event.item) {
                    // A bug requires this
                    var onEat as function(player as Player) as void = eat.onEaten;
                    onEat(player);
                }
            }
        });
    }
}

OnEat.listen();
OnEat.register(<item:minecraft:potion>.withTag({Potion: "minecraft:water"}), <item:minecraft:blue_dye>);
OnEat.register(<item:minecraft:carrot>, (player) => {
    var effect = MobEffectInstance.of(<mobeffect:minecraft:night_vision>, 2000, 2000);
    player.addEffect(effect);
});