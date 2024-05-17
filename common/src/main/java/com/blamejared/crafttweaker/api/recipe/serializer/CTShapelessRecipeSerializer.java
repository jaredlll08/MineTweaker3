package com.blamejared.crafttweaker.api.recipe.serializer;

import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.type.CTShapelessRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.Arrays;


public class CTShapelessRecipeSerializer implements RecipeSerializer<CTShapelessRecipe> {
    
    public static final CTShapelessRecipeSerializer INSTANCE = new CTShapelessRecipeSerializer();
    public static final MapCodec<CTShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    IItemStack.CODEC.fieldOf("output").forGetter(CTShapelessRecipe::getCtOutput),
                    IIngredient.CODEC.listOf()
                            .fieldOf("ingredients")
                            .xmap(iIngredients -> iIngredients.toArray(IIngredient[]::new), Arrays::asList)
                            .forGetter(CTShapelessRecipe::getCtIngredients)
            ).apply(instance, CTShapelessRecipe::new)
    );
    
    public static final StreamCodec<RegistryFriendlyByteBuf, CTShapelessRecipe> STREAM_CODEC = StreamCodec.composite(
            IItemStack.STREAM_CODEC,
            CTShapelessRecipe::getCtOutput,
            IIngredient.STREAM_CODEC.apply(ByteBufCodecs.list()),
            CTShapelessRecipe::getFlatCtIngredients,
            (iItemStack, iIngredients) -> new CTShapelessRecipe(iItemStack, iIngredients.toArray(IIngredient[]::new))
    );
    
    private CTShapelessRecipeSerializer() {}
    
    @Override
    public MapCodec<CTShapelessRecipe> codec() {
        
        return CODEC;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, CTShapelessRecipe> streamCodec() {
        
        return STREAM_CODEC;
    }
    
}