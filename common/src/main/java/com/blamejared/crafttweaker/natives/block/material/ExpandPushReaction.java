package com.blamejared.crafttweaker.natives.block.material;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.level.material.PushReaction;

/**
 * Represents the push reaction of a block.
 * This is used by pistons to determine how they should push blocks.
 */
@ZenRegister
@Document("vanilla/api/block/material/PushReaction")
@NativeTypeRegistration(value = PushReaction.class, zenCodeName = "crafttweaker.api.block.material.PushReaction")
@BracketEnum("minecraft:material/pushreaction")
public class ExpandPushReaction {

}
