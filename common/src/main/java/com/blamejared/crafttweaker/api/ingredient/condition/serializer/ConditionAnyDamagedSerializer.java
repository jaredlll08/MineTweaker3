package com.blamejared.crafttweaker.api.ingredient.condition.serializer;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientConditionSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.type.ConditionAnyDamage;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public class ConditionAnyDamagedSerializer implements IIngredientConditionSerializer<ConditionAnyDamage> {
    
    public static final ConditionAnyDamagedSerializer INSTANCE = new ConditionAnyDamagedSerializer();
    
    public static final MapCodec<ConditionAnyDamage> CODEC = MapCodec.unit(ConditionAnyDamage.INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, ConditionAnyDamage> STREAM_CODEC = StreamCodec.unit(ConditionAnyDamage.INSTANCE);
    
    private ConditionAnyDamagedSerializer() {}
    
    @Override
    public MapCodec<ConditionAnyDamage> codec() {
        
        return CODEC;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ConditionAnyDamage> streamCodec() {
        
        return STREAM_CODEC;
    }
    
    @Override
    public ResourceLocation getType() {
        
        return CraftTweakerConstants.rl("any_damage");
    }
    
}