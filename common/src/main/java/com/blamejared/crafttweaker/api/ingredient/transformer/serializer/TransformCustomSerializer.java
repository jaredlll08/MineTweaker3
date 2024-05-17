package com.blamejared.crafttweaker.api.ingredient.transformer.serializer;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.ingredient.transformer.IIngredientTransformerSerializer;
import com.blamejared.crafttweaker.api.ingredient.transformer.type.TransformCustom;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public class TransformCustomSerializer implements IIngredientTransformerSerializer<TransformCustom> {
    
    public static final TransformCustomSerializer INSTANCE = new TransformCustomSerializer();
    public static final MapCodec<TransformCustom> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.fieldOf("uid").forGetter(TransformCustom::getUid)
    ).apply(instance, TransformCustom::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, TransformCustom> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, TransformCustom::getUid, TransformCustom::new);
    
    private TransformCustomSerializer() {}
    
    @Override
    public MapCodec<TransformCustom> codec() {
        
        return CODEC;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, TransformCustom> streamCodec() {
        
        return STREAM_CODEC;
    }
    
    @Override
    public ResourceLocation getType() {
        
        return CraftTweakerConstants.rl("transform_custom");
    }
    
}