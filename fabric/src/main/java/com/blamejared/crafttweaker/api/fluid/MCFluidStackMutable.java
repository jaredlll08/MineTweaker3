package com.blamejared.crafttweaker.api.fluid;

import com.blamejared.crafttweaker.api.data.IData;
import com.blamejared.crafttweaker.impl.fluid.SimpleFluidStack;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.material.Fluid;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.Consumer;

public class MCFluidStackMutable implements IFluidStack {
    
    private final SimpleFluidStack stack;
    
    public MCFluidStackMutable(SimpleFluidStack stack) {
        
        this.stack = stack;
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
        
        getInternal().amount(amount);
        return this;
    }
    
    @Override
    public boolean isImmutable() {
        
        return false;
    }
    
    @Override
    public Fluid getFluid() {
        
        return getInternal().fluid();
    }
    
    @Override
    public IFluidStack withTag(IData tag) {
        
        //TODO 1.20.5
        //        if(tag != null) {
        //            MapData map = new MapData(tag.asMap());
        //            getInternal().components(map.getInternal());
        //        } else {
        //            getInternal().components(null);
        //        }
        //
        return this;
    }
    
    @Override
    public Codec<IFluidStack> codec() {
        
        return SimpleFluidStack.CODEC.xmap(IFluidStack::ofMutable, IFluidStack::getInternal);
    }
    
    @Override
    public PatchedDataComponentMap getComponents() {
        
        return getInternal().getComponents();
    }
    
    @Override
    public <T> IFluidStack with(DataComponentType<T> type, @ZenCodeType.Nullable T value) {
        
        getInternal().set(type, value);
        return this;
    }
    
    @Override
    public <T> IFluidStack remove(DataComponentType<T> type) {
        
        getInternal().remove(type);
        return this;
    }
    
    @Override
    public CompoundTag getInternalTag() {
        
        //TODO 1.20.5
        //        return getInternal().components();
        return null;
    }
    
    @Override
    public IFluidStack modifyThis(Consumer<IFluidStack> modifier) {
        
        modifier.accept(this);
        return this;
    }
    
    @Override
    public SimpleFluidStack getInternal() {
        
        return stack;
    }
    
    @Override
    public SimpleFluidStack getImmutableInternal() {
        
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
    //        final SimpleFluidStack thatStack = ((MCFluidStackMutable) o).getInternal();
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
    //
    //    @Override
    //    public int hashCode() {
    //
    //        return Objects.hash(getInternal().amount(), getInternal().fluid(), getInternal().components());
    //    }
    
}
