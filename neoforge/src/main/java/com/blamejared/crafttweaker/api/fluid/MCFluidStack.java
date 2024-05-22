package com.blamejared.crafttweaker.api.fluid;

import com.blamejared.crafttweaker.api.data.IData;
import com.blamejared.crafttweaker.api.data.IntData;
import com.blamejared.crafttweaker.api.data.op.IDataOps;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public class MCFluidStack implements IFluidStack {
    
    private final FluidStack stack;
    
    public MCFluidStack(FluidStack fluidStack) {
        
        this.stack = fluidStack;
    }
    
    @Override
    public boolean isEmpty() {
        
        return getInternal().isEmpty();
    }
    
    @Override
    public long getAmount() {
        
        return getInternal().getAmount();
    }
    
    @Override
    public IFluidStack setAmount(int amount) {
        
        final FluidStack copy = getInternal().copy();
        copy.setAmount(amount);
        return IFluidStack.of(copy);
    }
    
    @Override
    public boolean isImmutable() {
        
        return true;
    }
    
    @Override
    public Fluid getFluid() {
        
        return getInternal().getFluid();
    }
    
    @Override
    public FluidStack getInternal() {
        
        return stack;
    }
    
    @Override
    public FluidStack getImmutableInternal() {
        
        return stack;
    }
    
    @Override
    public IData asIData() {
        
        IData data = IFluidStack.super.asIData();
        data.put("amount", new IntData(Math.toIntExact(this.getAmount())));
        return data;
    }
    
    @Override
    public Codec<IFluidStack> codec() {
        
        return FluidStack.CODEC.xmap(IFluidStack::of, IFluidStack::getInternal);
    }
    
    @Override
    public PatchedDataComponentMap getComponents() {
        
        return this.getInternal().getComponents();
    }
    
    @Override
    public <T> IFluidStack with(DataComponentType<T> type, @ZenCodeType.Nullable T value) {
        
        final FluidStack copy = getInternal().copy();
        copy.set(type, value);
        return IFluidStack.of(copy);
    }
    
    @Override
    public IFluidStack withJsonComponent(DataComponentType type, @ZenCodeType.Nullable IData value) {
        
        final FluidStack copy = getInternal().copy();
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
        
        final FluidStack copy = getInternal().copy();
        DataResult<Pair<DataComponentPatch, IData>> decoded = DataComponentPatch.CODEC.decode(IDataOps.INSTANCE, value);
        Pair<DataComponentPatch, IData> pair = decoded.getOrThrow();
        copy.applyComponents(pair.getFirst());
        return IFluidStack.of(copy);
    }
    
    @Override
    public <T> IFluidStack remove(DataComponentType<T> type) {
        
        final FluidStack copy = getInternal().copy();
        copy.remove(type);
        return IFluidStack.of(copy);
    }
    
    @Override
    public <T, U> IFluidStack update(DataComponentType<T> type, T defaultValue, U data, BiFunction<T, U, T> operator) {
        
        final FluidStack copy = getInternal().copy();
        copy.update(type, defaultValue, data, operator);
        return IFluidStack.of(copy);
    }
    
    @Override
    public <T> IFluidStack update(DataComponentType<T> type, T defaultValue, UnaryOperator<T> operator) {
        
        final FluidStack copy = getInternal().copy();
        copy.update(type, defaultValue, operator);
        return IFluidStack.of(copy);
    }
    
    @Override
    public IFluidStack applyComponents(DataComponentMap map) {
        
        final FluidStack copy = getInternal().copy();
        copy.applyComponents(map);
        return IFluidStack.of(copy);
    }
    
    @Override
    public IFluidStack applyComponents(DataComponentPatch patch) {
        
        final FluidStack copy = getInternal().copy();
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
        
        final FluidStack thatStack = ((MCFluidStack) o).getInternal();
        final FluidStack thisStack = getInternal();
        return FluidStack.matches(thisStack, thatStack);
    }
    
    @Override
    public int hashCode() {
        
        return getInternal().getAmount() * 31 + FluidStack.hashFluidAndComponents(this.getInternal());
    }
    
}
