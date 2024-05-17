package com.blamejared.crafttweaker.api.fluid;

import com.blamejared.crafttweaker.api.data.IData;
import com.blamejared.crafttweaker.api.data.LongData;
import com.blamejared.crafttweaker.impl.fluid.SimpleFluidStack;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.material.Fluid;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.Consumer;

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
    public IFluidStack withTag(@ZenCodeType.Nullable IData tag) {
        
        final SimpleFluidStack copy = getInternal().copy();
        //TODO 1.20.5
        //        if(tag != null) {
        //            MapData map = new MapData(tag.asMap());
        //            copy.components(map.getInternal());
        //        } else {
        //            copy.components(null);
        //        }
        
        return IFluidStack.of(copy);
    }
    
    @Override
    public CompoundTag getInternalTag() {
        
        // TODO 1.20.5
        //        return getInternal().components();
        return null;
    }
    
    @Override
    public IFluidStack modifyThis(Consumer<IFluidStack> modifier) {
        
        IFluidStack newStack = copy();
        modifier.accept(newStack);
        return newStack;
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
    public <T> IFluidStack remove(DataComponentType<T> type) {
        
        final SimpleFluidStack copy = getInternal().copy();
        copy.remove(type);
        return IFluidStack.of(copy);
    }
    
    //TODO 1.20.5
    //    @Override
    //    @ZenCodeType.Operator(ZenCodeType.OperatorType.EQUALS)
    //    public boolean equals(Object o) {
    //
    //        if(this == o) {
    //            return true;
    //        }
    //        if(o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //
    //        final SimpleFluidStack thatStack = ((MCFluidStack) o).getInternal();
    //        final SimpleFluidStack thisStack = getInternal();
    //
    //        if(thisStack.isEmpty()) {
    //            return thatStack.isEmpty();
    //        }
    //
    //        if(thisStack.amount() != thatStack.amount()) {
    //            return false;
    //        }
    //
    //        if(!Objects.equals(thisStack.fluid(), thatStack.fluid())) {
    //            return false;
    //        }
    //
    //        return Objects.equals(thisStack.components(), thatStack.components());
    //    }
    
    //TODO 1.20.5
    //    @Override
    //    public int hashCode() {
    //
    //        return Objects.hash(getInternal().amount(), getInternal().fluid(), getInternal().components());
    //    }
    
}
