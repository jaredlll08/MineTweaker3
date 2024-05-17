package com.blamejared.crafttweaker.api.ingredient.transformer;

import com.blamejared.crafttweaker.api.item.IItemStack;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class IngredientTransformers implements UnaryOperator<IItemStack> {
    
    public static final Codec<IngredientTransformers> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            IIngredientTransformer.CODEC.listOf()
                    .fieldOf("transformers")
                    .forGetter(IngredientTransformers::transformers)
    ).apply(instance, IngredientTransformers::new));
    
    public static final StreamCodec<RegistryFriendlyByteBuf, IngredientTransformers> STREAM_CODEC = StreamCodec.composite(
            IIngredientTransformer.STREAM_CODEC.apply(ByteBufCodecs.list()),
            IngredientTransformers::transformers,
            IngredientTransformers::new
    );
    public static final IngredientTransformers EMPTY = new IngredientTransformers();
    
    private final List<IIngredientTransformer> transformers;
    private final List<IIngredientTransformer> transformersView;
    
    public IngredientTransformers() {
        
        this(new ArrayList<>());
    }
    
    public IngredientTransformers(List<IIngredientTransformer> transformers) {
        
        this.transformers = transformers;
        this.transformersView = Collections.unmodifiableList(this.transformers);
    }
    
    public IngredientTransformers copy() {
        
        return new IngredientTransformers(new ArrayList<>(this.transformersView));
    }
    
    public boolean isEmpty() {
        
        return this == EMPTY || this.transformers().isEmpty();
    }
    
    public void add(IIngredientTransformer transformer) {
        
        if(this == EMPTY) {
            throw new UnsupportedOperationException("Unable to add '" + transformer + "' to an empty transformer!");
        }
        this.transformers.add(transformer);
    }
    
    public List<IIngredientTransformer> transformers() {
        
        return transformersView;
    }
    
    @Override
    public IItemStack apply(IItemStack in) {
        
        return transformers().stream()
                .reduce(in.asImmutable(), (stack, transformer) -> transformer.transform(stack), (a, b) -> b);
    }
    
    public String getCommandString(String base) {
        
        return transformers().stream()
                .map(iIngredientTransformer -> iIngredientTransformer.getCommandString(base))
                .collect(Collectors.joining());
    }
    
    
    @Override
    public boolean equals(Object o) {
        
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        IngredientTransformers that = (IngredientTransformers) o;
        return Objects.equals(transformers, that.transformers) && Objects.equals(transformersView, that.transformersView);
    }
    
    @Override
    public int hashCode() {
        
        return Objects.hash(transformers, transformersView);
    }
    
}
