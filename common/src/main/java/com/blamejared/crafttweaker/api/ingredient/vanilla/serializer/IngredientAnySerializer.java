package com.blamejared.crafttweaker.api.ingredient.vanilla.serializer;

import com.blamejared.crafttweaker.api.ingredient.type.IIngredientAny;
import com.blamejared.crafttweaker.api.ingredient.vanilla.type.IngredientAny;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public class IngredientAnySerializer implements CraftTweakerVanillaIngredientSerializer<IngredientAny> {
    
    public static final MapCodec<IngredientAny> CODEC = MapCodec.unit(IngredientAny.of());
    public static final StreamCodec<RegistryFriendlyByteBuf, IngredientAny> STREAM_CODEC = StreamCodec.unit(IngredientAny.of());
    public static IngredientAnySerializer INSTANCE = new IngredientAnySerializer();
    
    private IngredientAnySerializer() {}
    
    @Override
    public ResourceLocation getId() {
        
        return IIngredientAny.ID;
    }
    
    @Override
    public MapCodec<IngredientAny> codec() {
        
        return CODEC;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, IngredientAny> streamCodec() {
        
        return STREAM_CODEC;
    }
    
}
