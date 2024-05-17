package com.blamejared.crafttweaker.api.ingredient.condition.serializer;


import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientConditionSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.type.ConditionDamagedAtLeast;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public class ConditionDamagedAtLeastSerializer implements IIngredientConditionSerializer<ConditionDamagedAtLeast> {
    
    public static final ConditionDamagedAtLeastSerializer INSTANCE = new ConditionDamagedAtLeastSerializer();
    public static final MapCodec<ConditionDamagedAtLeast> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.INT.fieldOf("damage").forGetter(ConditionDamagedAtLeast::minDamage)).
            apply(instance, ConditionDamagedAtLeast::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, ConditionDamagedAtLeast> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, ConditionDamagedAtLeast::minDamage, ConditionDamagedAtLeast::new
    );
    
    private ConditionDamagedAtLeastSerializer() {}
    
    @Override
    public MapCodec<ConditionDamagedAtLeast> codec() {
        
        return CODEC;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ConditionDamagedAtLeast> streamCodec() {
        
        return STREAM_CODEC;
    }
    
    @Override
    public ResourceLocation getType() {
        
        return CraftTweakerConstants.rl("only_damaged_at_least");
    }
    
}