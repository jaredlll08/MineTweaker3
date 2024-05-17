package com.blamejared.crafttweaker.api.ingredient.vanilla.serializer;

import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.ingredient.vanilla.type.IngredientCraftTweaker;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public class IngredientCraftTweakerSerializer implements CraftTweakerVanillaIngredientSerializer<IngredientCraftTweaker<? extends IIngredient>> {
    
    public static final IngredientCraftTweakerSerializer INSTANCE = new IngredientCraftTweakerSerializer();
    //TODO 1.20.5 I don't know if this makes sense or works
    public static final MapCodec<IngredientCraftTweaker<? extends IIngredient>> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(IIngredient.CODEC.fieldOf("ingredient")
                    .forGetter(IngredientCraftTweaker::getCrTIngredient)
            ).apply(instance, IngredientCraftTweaker::of)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, IngredientCraftTweaker<? extends IIngredient>> STREAM_CODEC = IIngredient.STREAM_CODEC.map(IngredientCraftTweaker::of, IngredientCraftTweaker::getCrTIngredient);
    
    private IngredientCraftTweakerSerializer() {}
    
    @Override
    public ResourceLocation getId() {
        
        return IIngredient.INGREDIENT_ID;
    }
    
    @Override
    public MapCodec<IngredientCraftTweaker<?>> codec() {
        
        return CODEC;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, IngredientCraftTweaker<?>> streamCodec() {
        
        return STREAM_CODEC;
    }
    
}
