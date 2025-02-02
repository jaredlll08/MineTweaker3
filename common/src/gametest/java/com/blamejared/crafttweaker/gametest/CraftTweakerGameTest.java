package com.blamejared.crafttweaker.gametest;

import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.gametest.framework.DelegatingGameTestAssertException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import io.netty.buffer.Unpooled;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public interface CraftTweakerGameTest {
    
    Gson GSON = new GsonBuilder().create();
    
    
    default String scriptPath() {
        
        throw new UnsupportedOperationException("GameTest class does not override 'scriptPath'!");
    }
    
    default String named(String name) {
        
        return "%s/%s.zs".formatted(scriptPath(), name);
    }
    
    default void fail() {
        
        fail("Assertion failed!");
    }
    
    default void fail(String message) {
        
        throw new GameTestAssertException(message);
    }
    
    default void fail(Exception e) {
        
        fail(e.getMessage(), e);
    }
    
    default void fail(String message, Exception e) {
        
        throw new DelegatingGameTestAssertException(message, e);
    }
    
    default ItemStack stack(ItemLike item) {
        
        return new ItemStack(item);
    }
    
    default ItemStack stack(ItemLike item, Consumer<CompoundTag> tag) {
        
        ItemStack itemStack = new ItemStack(item);
        itemStack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, customData -> customData.update(tag));
        return itemStack;
    }
    
    default IItemStack immutableStack(ItemStack stack) {
        
        return IItemStack.of(stack);
    }
    
    default IItemStack immutableStack(ItemLike item) {
        
        return IItemStack.of(new ItemStack(item));
    }
    
    default IItemStack mutableStack(ItemStack stack) {
        
        return IItemStack.of(stack);
    }
    
    default IItemStack mutableStack(ItemLike item) {
        
        return IItemStack.of(new ItemStack(item));
    }
    
    
    default RegistryFriendlyByteBuf createBuffer(RegistryAccess registryAccess) {
        
        return new RegistryFriendlyByteBuf(Unpooled.buffer(), registryAccess);
    }
    
    default <T> DataResult<JsonElement> encode(Codec<T> codec, T t) {
        
        return codec.encodeStart(JsonOps.INSTANCE, t);
    }
    
    default <T> DataResult<JsonElement> encode(MapCodec<T> codec, T t) {
        
        return codec.codec().encodeStart(JsonOps.INSTANCE, t);
    }
    
    default <T> DataResult<Pair<T, JsonElement>> decode(Codec<T> codec, JsonElement element) {
        
        return codec.decode(JsonOps.INSTANCE, element);
    }
    
    default <T> DataResult<Pair<T, JsonElement>> decode(MapCodec<T> codec, JsonElement element) {
        
        return codec.codec().decode(JsonOps.INSTANCE, element);
    }
    
    default JsonElement parseJson(String json) {
        
        return GSON.fromJson(json, JsonElement.class);
    }
    
}
