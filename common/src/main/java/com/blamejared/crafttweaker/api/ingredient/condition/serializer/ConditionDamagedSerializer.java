package com.blamejared.crafttweaker.api.ingredient.condition.serializer;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientConditionSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.type.ConditionDamaged;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public class ConditionDamagedSerializer implements IIngredientConditionSerializer<ConditionDamaged> {
    
    public static final ConditionDamagedSerializer INSTANCE = new ConditionDamagedSerializer();
    
    public static final MapCodec<ConditionDamaged> CODEC = MapCodec.unit(ConditionDamaged.INSTANCE);
    
    public static final StreamCodec<RegistryFriendlyByteBuf, ConditionDamaged> STREAM_CODEC = StreamCodec.unit(ConditionDamaged.INSTANCE);
    
    private ConditionDamagedSerializer() {}
    
    @Override
    public MapCodec<ConditionDamaged> codec() {
        
        return CODEC;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ConditionDamaged> streamCodec() {
        
        return STREAM_CODEC;
    }
    
    @Override
    public ResourceLocation getType() {
        
        return CraftTweakerConstants.rl("only_damaged");
    }
    
}