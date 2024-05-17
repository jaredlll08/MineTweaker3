package com.blamejared.crafttweaker.api.fluid;

import com.blamejared.crafttweaker.api.data.IData;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.Consumer;

public class MCFluidStackMutable implements IFluidStack {
    
    private final FluidStack stack;
    
    public MCFluidStackMutable(FluidStack stack) {
        
        this.stack = stack;
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
        
        getInternal().setAmount(amount);
        return this;
    }
    
    @Override
    public boolean isImmutable() {
        
        return false;
    }
    
    @Override
    public Fluid getFluid() {
        
        return getInternal().getFluid();
    }
    
    @Override
    public IFluidStack withTag(IData tag) {
        
        //TODO 1.20.5
        //        if (tag != null) {
        //            MapData map = new MapData(tag.asMap());
        //            getInternal().setTag(map.getInternal());
        //        } else {
        //            getInternal().setTag(null);
        //        }
        
        return this;
    }
    
    @Override
    public Codec<IFluidStack> codec() {
        
        return FluidStack.CODEC.xmap(IFluidStack::ofMutable, IFluidStack::getInternal);
    }
    
    @Override
    public PatchedDataComponentMap getComponents() {
        
        return getInternal().getComponents();
    }
    
    @Override
    public <T> IFluidStack with(DataComponentType<T> type, @ZenCodeType.Nullable T value) {
        
        this.getInternal().set(type, value);
        return this;
    }
    
    @Override
    public <T> IFluidStack remove(DataComponentType<T> type) {
        
        this.getInternal().remove(type);
        return this;
    }
    
    @Override
    public CompoundTag getInternalTag() {
        
        //TODO 1.20.5
        //        return getInternal().getTag();
        return null;
    }
    
    @Override
    public IFluidStack modifyThis(Consumer<IFluidStack> modifier) {
        
        modifier.accept(this);
        return this;
    }
    
    @Override
    public FluidStack getInternal() {
        
        return stack;
    }
    
    @Override
    public FluidStack getImmutableInternal() {
        
        return stack.copy();
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
    //        final FluidStack thatStack = ((MCFluidStackMutable) o).getInternal();
    //        final FluidStack thisStack = getInternal();
    //
    //        if(thisStack.isEmpty()) {
    //            return thatStack.isEmpty();
    //        }
    //
    //        if(thisStack.getAmount() != thatStack.getAmount()) {
    //            return false;
    //        }
    //
    //        if(!Objects.equals(thisStack.getFluid(), thatStack.getFluid())) {
    //            return false;
    //        }
    //
    //        return Objects.equals(thisStack.getTag(), thatStack.getTag());
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //
    //        return Objects.hash(getInternal().getAmount(), getInternal().getFluid(), getInternal().getTag());
    //    }
    //
}
