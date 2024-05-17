package com.blamejared.crafttweaker.api.ingredient.transformer;

import com.blamejared.crafttweaker.CraftTweakerRegistries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public interface IIngredientTransformerSerializer<T extends IIngredientTransformer> {
    
    Codec<IIngredientTransformerSerializer<? extends IIngredientTransformer>> CODEC = CraftTweakerRegistries.TRANSFORMER_SERIALIZER.byNameCodec();
    
    MapCodec<T> codec();
    
    StreamCodec<RegistryFriendlyByteBuf, T> streamCodec();
    
    ResourceLocation getType();
    
}
