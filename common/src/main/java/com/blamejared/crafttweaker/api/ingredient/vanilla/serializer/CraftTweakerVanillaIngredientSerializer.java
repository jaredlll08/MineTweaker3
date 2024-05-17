package com.blamejared.crafttweaker.api.ingredient.vanilla.serializer;

import com.blamejared.crafttweaker.api.ingredient.vanilla.type.CraftTweakerVanillaIngredient;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public interface CraftTweakerVanillaIngredientSerializer<T extends CraftTweakerVanillaIngredient> {
    
    ResourceLocation getId();
    
    MapCodec<T> codec();
    
    StreamCodec<RegistryFriendlyByteBuf, T> streamCodec();
    
}
