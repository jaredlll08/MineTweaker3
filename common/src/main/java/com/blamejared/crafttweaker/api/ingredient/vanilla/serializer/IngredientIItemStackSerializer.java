package com.blamejared.crafttweaker.api.ingredient.vanilla.serializer;

import com.blamejared.crafttweaker.api.ingredient.vanilla.type.IngredientIItemStack;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public class IngredientIItemStackSerializer implements CraftTweakerVanillaIngredientSerializer<IngredientIItemStack> {
    
    public static final IngredientIItemStackSerializer INSTANCE = new IngredientIItemStackSerializer();
    //TODO 1.20.5 I don't know if this makes sense or works
    public static final MapCodec<IngredientIItemStack> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(IItemStack.CODEC.fieldOf("base").forGetter(IngredientIItemStack::getCrTIngredient))
                    .apply(instance, IngredientIItemStack::of)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, IngredientIItemStack> STREAM_CODEC = IItemStack.STREAM_CODEC.map(IngredientIItemStack::of, IngredientIItemStack::getCrTIngredient);
    
    private IngredientIItemStackSerializer() {}
    
    @Override
    public ResourceLocation getId() {
        
        return IItemStack.INGREDIENT_ID;
    }
    
    @Override
    public MapCodec<IngredientIItemStack> codec() {
        
        return CODEC;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, IngredientIItemStack> streamCodec() {
        
        return STREAM_CODEC;
    }
    
}
