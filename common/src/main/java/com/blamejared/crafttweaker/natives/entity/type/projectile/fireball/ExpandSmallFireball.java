package com.blamejared.crafttweaker.natives.entity.type.projectile.fireball;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeConstructor;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

@ZenRegister
@Document("vanilla/api/entity/type/projectile/SmallFireball")
@NativeTypeRegistration(value = SmallFireball.class, zenCodeName = "crafttweaker.api.entity.type.projectile.SmallFireball",
        constructors = {
                @NativeConstructor(value = {
                        @NativeConstructor.ConstructorParameter(name = "level", type = Level.class, description = "The level this entity is in.", examples = "level"),
                        @NativeConstructor.ConstructorParameter(name = "shooter", type = LivingEntity.class, description = "The entity that created the fireball, used to get the position.", examples = "shooter"),
                        @NativeConstructor.ConstructorParameter(name = "direction", type = Vec3.class, description = "The direction that the skull is moving in", examples = "new Vec3(1, 2, 3)")
                }),
                @NativeConstructor(value = {
                        @NativeConstructor.ConstructorParameter(name = "level", type = Level.class, description = "The level this entity is in.", examples = "level"),
                        @NativeConstructor.ConstructorParameter(name = "x", type = double.class, description = "The x position of the entity.", examples = "0"),
                        @NativeConstructor.ConstructorParameter(name = "y", type = double.class, description = "The y position of the entity.", examples = "1"),
                        @NativeConstructor.ConstructorParameter(name = "z", type = double.class, description = "The z position of the entity.", examples = "2"),
                        @NativeConstructor.ConstructorParameter(name = "direction", type = Vec3.class, description = "The direction that the skull is moving in", examples = "new Vec3(1, 2, 3)")
                })
        })
public class ExpandSmallFireball {

}
