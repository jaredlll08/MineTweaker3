#modloader neoforge
import crafttweaker.neoforge.api.event.interact.EntityInteractEvent;
import crafttweaker.api.entity.type.item.ItemEntity;

events.register<EntityInteractEvent>(event => {
    val target = event.target;
    val level = target.level;
    val data = target.data;
    val random = level.random;
    if !level.isClientSide && target.type == <entitytype:minecraft:sheep> {
        event.cancel();
        if "Sheared" in data && !(data["Sheared"] as bool) {
            val newData = target.data;
            newData["Sheared"] = true;
            target.updateData(newData);
            event.cancellationResult = <constant:minecraft:world/interactionresult:SUCCESS>;
            level.playSound(null, target.onPos, <soundevent:minecraft:entity.sheep.shear>, <constant:minecraft:sound/source:players>);
            val item = new ItemEntity(level, target.x, target.y +1, target.z, <item:minecraft:diamond>);
            item.deltaMovement = item.deltaMovement.add(
                (random.nextFloat() - random.nextFloat()) * 0.1f,
                random.nextFloat() * 0.05f,
                (random.nextFloat() - random.nextFloat()) * 0.1f
            );
            level.addFreshEntity(item);
        }
    }
});