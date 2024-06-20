#modloader neoforge
import crafttweaker.neoforge.api.event.entity.EntityJoinLevelEvent;
import crafttweaker.neoforge.api.event.entity.EntityStruckByLightningEvent;
import crafttweaker.api.util.math.BlockPos;
import crafttweaker.api.world.ServerLevel;
import crafttweaker.api.entity.MobSpawnType;

public expand BlockPos {
    public const runForRadius(radius as int, func as function(pos as BlockPos) as void) as void {
        // Using a mutable pos is better for memory for something like this, instead of
        // creating *a lot* of BlockPos objects, only a single one is created and moved around.
        val mutableThis = this.asMutable();
        for x in (-radius) .. (radius + 1) {
            for y in (-radius) .. (radius + 1) {
                for z in (-radius) .. (radius + 1) {
                    func(mutableThis.offset(x, y, z));
                }
            }
        }
    }
}

events.register<EntityJoinLevelEvent>(event => {
    val entity = event.entity;
    if entity.level.isClientSide && !(entity.level is ServerLevel) {
        return;
    }

    val level = entity.level as ServerLevel;
    if entity.registryName == <resource:minecraft:lightning_bolt> {
       val entityPos = entity.blockPosition;
       entityPos.runForRadius(1, pos => {
            val state = level.getBlockState(pos);
            if state.block == <block:minecraft:emerald_block> {
                val entity = <entitytype:minecraft:wandering_trader>.spawn(level, pos.above(), MobSpawnType.MOB_SUMMONED);
                val data = entity.customData;
                data["c:summoned"] = true;
                entity.updateData(data);
            }
        });
    }
});

events.register<EntityStruckByLightningEvent>(event => {
    val entity = event.entity;
    if entity.registryName == <entitytype:minecraft:wandering_trader> && "c:summoned" in entity.customData && entity.customData["c:summoned"].asBool() {
        event.cancel();
    }
});
