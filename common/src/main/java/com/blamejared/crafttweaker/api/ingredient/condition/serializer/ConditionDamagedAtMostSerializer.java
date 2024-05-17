package com.blamejared.crafttweaker.api.ingredient.condition.serializer;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientConditionSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.type.ConditionDamagedAtMost;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public class ConditionDamagedAtMostSerializer implements IIngredientConditionSerializer<ConditionDamagedAtMost> {
    
    public static final ConditionDamagedAtMostSerializer INSTANCE = new ConditionDamagedAtMostSerializer();
    public static final MapCodec<ConditionDamagedAtMost> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.INT.fieldOf("damage").forGetter(ConditionDamagedAtMost::maxDamage)).
            apply(instance, ConditionDamagedAtMost::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, ConditionDamagedAtMost> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.VAR_INT, ConditionDamagedAtMost::maxDamage, ConditionDamagedAtMost::new);
    
    private ConditionDamagedAtMostSerializer() {}
    
    
    @Override
    public MapCodec<ConditionDamagedAtMost> codec() {
        
        return CODEC;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ConditionDamagedAtMost> streamCodec() {
        
        return STREAM_CODEC;
    }
    
    @Override
    public ResourceLocation getType() {
        
        return CraftTweakerConstants.rl("only_damaged_at_most");
    }
    
}