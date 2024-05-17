package com.blamejared.crafttweaker.api.ingredient.transformer.serializer;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.ingredient.transformer.IIngredientTransformerSerializer;
import com.blamejared.crafttweaker.api.ingredient.transformer.type.TransformDamage;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public class TransformDamageSerializer implements IIngredientTransformerSerializer<TransformDamage> {
    
    public static final TransformDamageSerializer INSTANCE = new TransformDamageSerializer();
    public static final MapCodec<TransformDamage> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("amount").forGetter(TransformDamage::amount)
    ).apply(instance, TransformDamage::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, TransformDamage> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.VAR_INT, TransformDamage::amount, TransformDamage::new);
    
    private TransformDamageSerializer() {}
    
    @Override
    public MapCodec<TransformDamage> codec() {
        
        return CODEC;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, TransformDamage> streamCodec() {
        
        return STREAM_CODEC;
    }
    
    @Override
    public ResourceLocation getType() {
        
        return CraftTweakerConstants.rl("transform_damage");
    }
    
}