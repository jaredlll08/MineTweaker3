package com.blamejared.crafttweaker.natives.entity;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.entity.MobSpawnType;

@ZenRegister
@Document("vanilla/api/entity/MobSpawnType")
@NativeTypeRegistration(value = MobSpawnType.class, zenCodeName = "crafttweaker.api.entity.MobSpawnType")
@BracketEnum("minecraft:mob_spawn_type")
public class ExpandMobSpawnType {

}
