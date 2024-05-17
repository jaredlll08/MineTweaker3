package com.blamejared.crafttweaker.api.fluid;

import com.blamejared.crafttweaker.api.data.IData;
import com.blamejared.crafttweaker.api.data.IntData;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.Consumer;

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
    public IFluidStack withTag(@ZenCodeType.Nullable IData tag) {
        
        final FluidStack copy = getInternal().copy();
        //TODO 1.20.5 rename method?
        //        if (tag != null) {
        //            MapData map = new MapData(tag.asMap());
        //            copy.setTag(map.getInternal());
        //        } else {
        //            copy.setTag(null);
        //        }
        
        return IFluidStack.of(copy);
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
    public <T> IFluidStack remove(DataComponentType<T> type) {
        
        final FluidStack copy = getInternal().copy();
        copy.remove(type);
        return IFluidStack.of(copy);
    }
    
    @Override
    public IFluidStack modifyThis(Consumer<IFluidStack> modifier) {
        
        IFluidStack newStack = copy();
        modifier.accept(newStack);
        return newStack;
    }
    
    @Override
    public CompoundTag getInternalTag() {
        
        return null;
        // TODO 1.20.5
        //        return getInternal().getTag();
    }
    
    //TODO 1.20.5
    //    @Override
    //    @ZenCodeType.Operator(ZenCodeType.OperatorType.EQUALS)
    //    public boolean equals(Object o) {
    //
    //        if (this == o) {
    //            return true;
    //        }
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //
    //        final FluidStack thatStack = ((MCFluidStack) o).getInternal();
    //        final FluidStack thisStack = getInternal();
    //
    //        if (thisStack.isEmpty()) {
    //            return thatStack.isEmpty();
    //        }
    //
    //        if (thisStack.getAmount() != thatStack.getAmount()) {
    //            return false;
    //        }
    //
    //        if (!Objects.equals(thisStack.getFluid(), thatStack.getFluid())) {
    //            return false;
    //        }
    //
    //        return Objects.equals(thisStack.getTag(), thatStack.getTag());
    //    }
    
    //TODO 1.20.5
    //    @Override
    //    public int hashCode() {
    //
    //        return Objects.hash(getInternal().getAmount(), getInternal().getFluid(), getInternal().getTag());
    //    }
    
}
