package com.blamejared.crafttweaker.impl.script;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.LinkedList;

public class ScriptSerializer implements RecipeSerializer<ScriptRecipe> {
    
    public static final ScriptSerializer INSTANCE = new ScriptSerializer();
    public static final MapCodec<ScriptRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.fieldOf("fileName").forGetter(ScriptRecipe::getFileName),
            Codec.STRING.fieldOf("content").forGetter(ScriptRecipe::getContent)
    ).apply(instance, ScriptRecipe::new));
    
    public static final StreamCodec<RegistryFriendlyByteBuf, ScriptRecipe> STREAM_CODEC = StreamCodec.of(ScriptSerializer::toNetwork, ScriptSerializer::fromNetwork);
    
    @Override
    public MapCodec<ScriptRecipe> codec() {
        
        return CODEC;
    }
    
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, ScriptRecipe> streamCodec() {
        
        return STREAM_CODEC;
    }
    
    private static ScriptRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
        
        String fileName = buffer.readUtf();
        int parts = buffer.readVarInt();
        StringBuilder script = new StringBuilder();
        while(parts-- > 0) {
            script.append(buffer.readUtf());
        }
        return new ScriptRecipe(fileName, script.toString());
    }
    
    private static void toNetwork(RegistryFriendlyByteBuf buffer, ScriptRecipe recipe) {
        
        String contents = recipe.getContent();
        LinkedList<String> split = Lists.newLinkedList(Splitter.fixedLength(Short.MAX_VALUE / Byte.SIZE)
                .split(contents));
        buffer.writeUtf(recipe.getFileName());
        buffer.writeVarInt(split.size());
        split.forEach(buffer::writeUtf);
    }
    
}
