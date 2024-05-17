package com.blamejared.crafttweaker.api.ingredient.condition.serializer;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientConditionSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.type.ConditionCustom;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public class ConditionCustomSerializer implements IIngredientConditionSerializer<ConditionCustom> {
    
    public static final ConditionCustomSerializer INSTANCE = new ConditionCustomSerializer();
    public static final MapCodec<ConditionCustom> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.fieldOf("uid").forGetter(ConditionCustom::getUid)
    ).apply(instance, ConditionCustom::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, ConditionCustom> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, ConditionCustom::getUid, ConditionCustom::new);
    
    private ConditionCustomSerializer() {}
    
    @Override
    public MapCodec<ConditionCustom> codec() {
        
        return CODEC;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ConditionCustom> streamCodec() {
        
        return STREAM_CODEC;
    }
    
    @Override
    public ResourceLocation getType() {
        
        return CraftTweakerConstants.rl("condition_custom");
    }
    
}