package com.blamejared.crafttweaker.api.ingredient.vanilla.serializer;

import com.blamejared.crafttweaker.api.ingredient.type.IIngredientList;
import com.blamejared.crafttweaker.api.ingredient.vanilla.type.IngredientList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class IngredientListSerializer implements CraftTweakerVanillaIngredientSerializer<IngredientList> {
    
    public static final IngredientListSerializer INSTANCE = new IngredientListSerializer();
    public static final MapCodec<IngredientList> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(IngredientList::getChildren)
    ).apply(instance, IngredientList::of));
    public static final StreamCodec<RegistryFriendlyByteBuf, IngredientList> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
            IngredientList::getChildren,
            IngredientList::of
    );
    
    private IngredientListSerializer() {}
    
    @Override
    public ResourceLocation getId() {
        
        return IIngredientList.ID;
    }
    
    @Override
    public MapCodec<IngredientList> codec() {
        
        return CODEC;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, IngredientList> streamCodec() {
        
        return STREAM_CODEC;
    }
    
}
