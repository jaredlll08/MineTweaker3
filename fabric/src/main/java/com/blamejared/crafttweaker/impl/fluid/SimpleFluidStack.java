package com.blamejared.crafttweaker.impl.fluid;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public class SimpleFluidStack implements DataComponentHolder {
    
    private static final Codec<Fluid> FLUID_NON_EMPTY_CODEC = BuiltInRegistries.FLUID.byNameCodec()
            .validate(fluid -> fluid == Fluids.EMPTY ? DataResult.error(() -> "Fluid must not be empty") : DataResult.success(fluid));
    
    public static final Codec<SimpleFluidStack> CODEC = Codec.lazyInitialized(
            () -> RecordCodecBuilder.create(
                    instance -> instance.group(
                                    BuiltInRegistries.FLUID.byNameCodec().fieldOf("id").forGetter(SimpleFluidStack::fluid),
                                    Codec.LONG.validate(aLong -> aLong > 0L ? DataResult.success(aLong) : DataResult.error(() -> "Value must be positive: " + aLong))
                                            .fieldOf("amount")
                                            .forGetter(SimpleFluidStack::amount),
                                    DataComponentPatch.CODEC.optionalFieldOf("components", DataComponentPatch.EMPTY)
                                            .forGetter(stack -> stack.components.asPatch()))
                            .apply(instance, SimpleFluidStack::new)));
    
    private final Fluid fluid;
    private long amount;
    private final PatchedDataComponentMap components;
    
    public SimpleFluidStack(Fluid fluid, long amount) {
        
        this(fluid, amount, new PatchedDataComponentMap(DataComponentMap.EMPTY));
    }
    
    public SimpleFluidStack(Fluid fluid, long amount, DataComponentPatch components) {
        
        this(fluid, amount, PatchedDataComponentMap.fromPatch(DataComponentMap.EMPTY, components));
    }
    
    public SimpleFluidStack(Fluid fluid, long amount, PatchedDataComponentMap components) {
        
        this.fluid = fluid;
        this.amount = amount;
        this.components = components;
    }
    
    public SimpleFluidStack copy() {
        
        return new SimpleFluidStack(fluid(), amount(), this.getComponents().copy());
    }
    
    public boolean isEmpty() {
        
        return fluid == Fluids.EMPTY || amount() <= 0;
    }
    
    public Fluid fluid() {
        
        return fluid;
    }
    
    public long amount() {
        
        return amount;
    }
    
    public void amount(long amount) {
        
        this.amount = amount;
    }
    
    @Override
    public PatchedDataComponentMap getComponents() {
        
        return components;
    }
    
    @Nullable
    public <T> T set(DataComponentType<? super T> componentType, @Nullable T value) {
        
        return this.components.set(componentType, value);
    }
    
    @Nullable
    public <T, U> T update(DataComponentType<T> componentType, T value, U updateContext, BiFunction<T, U, T> updater) {
        
        return set(componentType, updater.apply(getOrDefault(componentType, value), updateContext));
    }
    
    @Nullable
    public <T> T update(DataComponentType<T> componentType, T value, UnaryOperator<T> updater) {
        
        return set(componentType, updater.apply(getOrDefault(componentType, value)));
    }
    
    public <T> T remove(DataComponentType<? extends T> componentType) {
        
        return this.components.remove(componentType);
    }
    
    public void applyComponents(DataComponentPatch patch) {
        
        this.components.applyPatch(patch);
    }
    
    public void applyComponents(DataComponentMap components) {
        
        this.components.setAll(components);
    }
    
    public static boolean matches(SimpleFluidStack first, SimpleFluidStack second) {
        
        if(first == second) {
            return true;
        } else {
            return first.amount() == second.amount() && isSameFluidSameComponents(first, second);
        }
    }
    
    public static boolean isSameFluidSameComponents(SimpleFluidStack first, SimpleFluidStack second) {
        
        if(first.fluid() != second.fluid()) {
            return false;
        } else {
            return first.isEmpty() && second.isEmpty() || Objects.equals(first.components, second.components);
        }
    }
    
    public static int hashFluidAndComponents(@Nullable SimpleFluidStack stack) {
        
        if(stack != null) {
            int i = 31 + stack.fluid().hashCode();
            return 31 * i + stack.getComponents().hashCode();
        } else {
            return 0;
        }
    }
    
}
