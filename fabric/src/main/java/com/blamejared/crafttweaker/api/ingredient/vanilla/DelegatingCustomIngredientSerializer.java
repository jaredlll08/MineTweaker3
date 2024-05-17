package com.blamejared.crafttweaker.api.ingredient.vanilla;

import com.blamejared.crafttweaker.api.ingredient.vanilla.serializer.CraftTweakerVanillaIngredientSerializer;
import com.blamejared.crafttweaker.api.ingredient.vanilla.type.CraftTweakerVanillaIngredient;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredientSerializer;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

record DelegatingCustomIngredientSerializer<T extends CraftTweakerVanillaIngredient>(
        CraftTweakerVanillaIngredientSerializer<T> internal) implements CustomIngredientSerializer<DelegatingCustomIngredient<T>> {
    
    private static final Map<MapCodec<? extends CraftTweakerVanillaIngredient>, MapCodec<DelegatingCustomIngredient<?>>> CODEC_CACHE = new HashMap<>();
    private static final Map<StreamCodec<RegistryFriendlyByteBuf, ? extends CraftTweakerVanillaIngredient>, StreamCodec<RegistryFriendlyByteBuf, DelegatingCustomIngredient<?>>> STREAM_CODEC_CACHE = new HashMap<>();
    
    @Override
    public ResourceLocation getIdentifier() {
        
        return internal.getId();
    }
    
    @Override
    public MapCodec<DelegatingCustomIngredient<T>> getCodec(boolean allowEmpty) {
        
        MapCodec<DelegatingCustomIngredient<?>> codec = CODEC_CACHE.computeIfAbsent(internal.codec(), c -> c.xmap(CraftTweakerIngredients.Ingredients::of, GenericUtil.uncheckFunc(DelegatingCustomIngredient::internal)));
        return GenericUtil.uncheck(codec);
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, DelegatingCustomIngredient<T>> getPacketCodec() {
        
        StreamCodec<RegistryFriendlyByteBuf, DelegatingCustomIngredient<?>> codec = STREAM_CODEC_CACHE.computeIfAbsent(internal.streamCodec(), c -> c.map(CraftTweakerIngredients.Ingredients::of, delegatingCustomIngredient -> GenericUtil.uncheck(delegatingCustomIngredient.internal())));
        return GenericUtil.uncheck(codec);
    }
    
}
