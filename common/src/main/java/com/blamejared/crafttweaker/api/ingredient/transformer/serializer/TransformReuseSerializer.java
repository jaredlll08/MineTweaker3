package com.blamejared.crafttweaker.api.ingredient.transformer.serializer;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.ingredient.transformer.IIngredientTransformerSerializer;
import com.blamejared.crafttweaker.api.ingredient.transformer.type.TransformReuse;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public class TransformReuseSerializer implements IIngredientTransformerSerializer<TransformReuse> {
    
    public static final TransformReuseSerializer INSTANCE = new TransformReuseSerializer();
    public static final MapCodec<TransformReuse> CODEC = MapCodec.unit(TransformReuse.INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, TransformReuse> STREAM_CODEC = StreamCodec.unit(TransformReuse.INSTANCE);
    
    private TransformReuseSerializer() {}
    
    
    @Override
    public MapCodec<TransformReuse> codec() {
        
        return CODEC;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, TransformReuse> streamCodec() {
        
        return STREAM_CODEC;
    }
    
    @Override
    public ResourceLocation getType() {
        
        return CraftTweakerConstants.rl("transform_reuse");
    }
    
}