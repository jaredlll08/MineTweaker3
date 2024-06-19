package com.blamejared.crafttweaker.natives.entity.type.projectile.fireball;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeConstructor;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

@ZenRegister
@Document("vanilla/api/entity/type/projectile/LargeFireball")
@NativeTypeRegistration(value = LargeFireball.class, zenCodeName = "crafttweaker.api.entity.type.projectile.LargeFireball",
        constructors = {
                @NativeConstructor(value = {
                        @NativeConstructor.ConstructorParameter(name = "level", type = Level.class, description = "The level the entity is in.", examples = "level"),
                        @NativeConstructor.ConstructorParameter(name = "shooter", type = LivingEntity.class, description = "The entity that created the fireball, used to get the position.", examples = "shooter"),
                        @NativeConstructor.ConstructorParameter(name = "direction", type = Vec3.class, description = "The direction that the skull is moving in", examples = "new Vec3(1, 2, 3)"),
                        @NativeConstructor.ConstructorParameter(name = "explosionPower", type = int.class, description = "The explosion power of this fireball", examples = "1")
                })
        })
public class ExpandLargeFireball {

}
