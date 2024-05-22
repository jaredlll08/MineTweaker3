package com.blamejared.crafttweaker.api.fluid;

import com.blamejared.crafttweaker.api.data.IData;
import com.blamejared.crafttweaker.api.data.LongData;
import com.blamejared.crafttweaker.api.data.op.IDataOps;
import com.blamejared.crafttweaker.impl.fluid.SimpleFluidStack;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.world.level.material.Fluid;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public class MCFluidStack implements IFluidStack {
    
    private final SimpleFluidStack stack;
    
    public MCFluidStack(SimpleFluidStack fluidStack) {
        
        this.stack = fluidStack;
    }
    
    @Override
    public boolean isEmpty() {
        
        return getInternal().isEmpty();
    }
    
    @Override
    public long getAmount() {
        
        return getInternal().amount();
    }
    
    @Override
    public IFluidStack setAmount(int amount) {
        
        final SimpleFluidStack copy = getInternal().copy();
        copy.amount(amount);
        return IFluidStack.of(copy);
    }
    
    @Override
    public boolean isImmutable() {
        
        return true;
    }
    
    @Override
    public Fluid getFluid() {
        
        return getInternal().fluid();
    }
    
    @Override
    public SimpleFluidStack getInternal() {
        
        return stack;
    }
    
    @Override
    public SimpleFluidStack getImmutableInternal() {
        
        return stack.copy();
    }
    
    @Override
    public IData asIData() {
        
        IData data = IFluidStack.super.asIData();
        data.put("amount", new LongData(this.getAmount()));
        return data;
    }
    
    @Override
    public Codec<IFluidStack> codec() {
        
        return SimpleFluidStack.CODEC.xmap(IFluidStack::of, IFluidStack::getInternal);
    }
    
    @Override
    public PatchedDataComponentMap getComponents() {
        
        return this.getInternal().getComponents();
    }
    
    @Override
    public <T> IFluidStack with(DataComponentType<T> type, @ZenCodeType.Nullable T value) {
        
        final SimpleFluidStack copy = getInternal().copy();
        copy.set(type, value);
        return IFluidStack.of(copy);
    }
    
    @Override
    public IFluidStack withJsonComponent(DataComponentType type, @ZenCodeType.Nullable IData value) {
        
        final SimpleFluidStack copy = getInternal().copy();
        if(value == null) {
            copy.remove(type);
        } else {
            Codec<?> codec = type.codecOrThrow();
            DataResult<? extends Pair<?, IData>> decode = codec.decode(IDataOps.INSTANCE, value);
            copy.set(type, decode.getOrThrow().getFirst());
        }
        return IFluidStack.of(copy);
    }
    
    @Override
    public IFluidStack withJsonComponents(IData value) {
        
        final SimpleFluidStack copy = getInternal().copy();
        DataResult<Pair<DataComponentPatch, IData>> decoded = DataComponentPatch.CODEC.decode(IDataOps.INSTANCE, value);
        Pair<DataComponentPatch, IData> pair = decoded.getOrThrow();
        copy.applyComponents(pair.getFirst());
        return IFluidStack.of(copy);
    }
    
    @Override
    public <T> IFluidStack remove(DataComponentType<T> type) {
        
        final SimpleFluidStack copy = getInternal().copy();
        copy.remove(type);
        return IFluidStack.of(copy);
    }
    
    @Override
    public <T, U> IFluidStack update(DataComponentType<T> type, T defaultValue, U data, BiFunction<T, U, T> operator) {
        
        final SimpleFluidStack copy = getInternal().copy();
        copy.update(type, defaultValue, data, operator);
        return IFluidStack.of(copy);
    }
    
    @Override
    public <T> IFluidStack update(DataComponentType<T> type, T defaultValue, UnaryOperator<T> operator) {
        
        final SimpleFluidStack copy = getInternal().copy();
        copy.update(type, defaultValue, operator);
        return IFluidStack.of(copy);
    }
    
    @Override
    public IFluidStack applyComponents(DataComponentMap map) {
        
        final SimpleFluidStack copy = getInternal().copy();
        copy.applyComponents(map);
        return IFluidStack.of(copy);
    }
    
    @Override
    public IFluidStack applyComponents(DataComponentPatch patch) {
        
        final SimpleFluidStack copy = getInternal().copy();
        copy.applyComponents(patch);
        return IFluidStack.of(copy);
    }
    
    @Override
    @ZenCodeType.Operator(ZenCodeType.OperatorType.EQUALS)
    public boolean equals(Object o) {
        
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        
        final SimpleFluidStack thatStack = ((MCFluidStack) o).getInternal();
        final SimpleFluidStack thisStack = getInternal();
        return SimpleFluidStack.matches(thisStack, thatStack);
    }
    
    @Override
    public int hashCode() {
        
        return Long.hashCode(getInternal().amount()) + SimpleFluidStack.hashFluidAndComponents(this.getInternal());
    }
    
}
