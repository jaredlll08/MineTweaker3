package com.blamejared.crafttweaker.api.ingredient.condition;

import com.blamejared.crafttweaker.CraftTweakerRegistries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public interface IIngredientConditionSerializer<T extends IIngredientCondition> {
    
    Codec<IIngredientConditionSerializer<? extends IIngredientCondition>> CODEC = CraftTweakerRegistries.CONDITIONER_SERIALIZER.byNameCodec();
    
    MapCodec<T> codec();
    
    StreamCodec<RegistryFriendlyByteBuf, T> streamCodec();
    
    ResourceLocation getType();
    
}
