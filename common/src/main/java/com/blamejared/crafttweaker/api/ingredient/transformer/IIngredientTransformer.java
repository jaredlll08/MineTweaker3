package com.blamejared.crafttweaker.api.ingredient.transformer;


import com.blamejared.crafttweaker.CraftTweakerRegistries;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.ingredient.transformer.IIngredientTransformer")
@Document("vanilla/api/ingredient/transformer/IIngredientTransformer")
public interface IIngredientTransformer {
    
    Codec<IIngredientTransformer> CODEC = IIngredientTransformerSerializer.CODEC.dispatch(IIngredientTransformer::getSerializer, IIngredientTransformerSerializer::codec);
    StreamCodec<RegistryFriendlyByteBuf, IIngredientTransformer> STREAM_CODEC = ByteBufCodecs.registry(CraftTweakerRegistries.Keys.TRANSFORMER_SERIALIZER)
            .dispatch(IIngredientTransformer::getSerializer, IIngredientTransformerSerializer::streamCodec);
    
    @ZenCodeType.Method
    IItemStack transform(IItemStack stack);
    
    @ZenCodeType.Method
    String getCommandString(String base);
    
    IIngredientTransformerSerializer<?> getSerializer();
    
}
