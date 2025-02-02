package com.blamejared.crafttweaker.api.fluid;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import com.blamejared.crafttweaker.api.component.ComponentAccess;
import com.blamejared.crafttweaker.api.data.IData;
import com.blamejared.crafttweaker.api.data.op.IDataOps;
import com.blamejared.crafttweaker.natives.component.ExpandDataComponentType;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.Nullable;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.fluid.IFluidStack")
@Document("vanilla/api/fluid/IFluidStack")
public interface IFluidStack extends CommandStringDisplayable, DataComponentHolder, ComponentAccess<IFluidStack> {
    
    /**
     * Gets the empty IFluidStack
     *
     * @return The empty IFluidStack
     */
    @ZenCodeType.Method
    static IFluidStack empty() {
        
        return FluidStackConstants.EMPTY.get();
    }
    
    /**
     * Gets an IFluidStack from the given T type.
     * On Forge, the only supported T type is {@code net.minecraftforge.fluids.FluidStack}.
     * On Fabric, the only supported T type is {@code com.blamejared.crafttweaker.impl.fluid.SimpleFluidStack}
     *
     * @param convertable An instance of T to convert.
     *
     * @return An IFluidStack created from the given T.
     *
     * @throws IllegalArgumentException if convertable is not a known type that can be converted
     */
    static <T> IFluidStack of(final T convertable) {
        
        return Services.PLATFORM.createFluidStack(convertable);
    }
    
    static IFluidStack of(final Fluid fluid, final long amount) {
        
        return Services.PLATFORM.createFluidStack(fluid, amount, DataComponentPatch.EMPTY);
    }
    
    static IFluidStack of(final Fluid fluid, final long amount, final DataComponentPatch components) {
        
        return Services.PLATFORM.createFluidStack(fluid, amount, components);
    }
    
    /**
     * Gets an IFluidStack from the given T type.
     * On Forge, the only supported T type is {@code net.minecraftforge.fluids.FluidStack}.
     * On Fabric, the only supported T type is {@code com.blamejared.crafttweaker.impl.fluid.SimpleFluidStack}
     *
     * @param convertable An instance of T to convert.
     * @param mutable     If the returned IFluidStack should be mutable or not
     *
     * @return A IFluidStack created from the given T.
     *
     * @throws IllegalArgumentException if convertable is not a known type that can be converted
     */
    static <T> IFluidStack of(final T convertable, final boolean mutable) {
        
        return mutable ? ofMutable(convertable) : of(convertable);
    }
    
    static IFluidStack of(final Fluid fluid, final long amount, final boolean mutable) {
        
        return mutable ? ofMutable(fluid, amount, DataComponentPatch.EMPTY) : of(fluid, amount, DataComponentPatch.EMPTY);
    }
    
    static IFluidStack of(final Fluid fluid, final long amount, final DataComponentPatch components, final boolean mutable) {
        
        return mutable ? ofMutable(fluid, amount, components) : of(fluid, amount, components);
    }
    
    /**
     * Gets a mutable IFluidStack from the given T type.
     * On Forge, the only supported T type is {@code net.minecraftforge.fluids.FluidStack}.
     * On Fabric, the only supported T type is {@code com.blamejared.crafttweaker.impl.fluid.SimpleFluidStack}
     *
     * @param convertable An instance of T to convert.
     *
     * @return A mutable IFluidStack created from the given T.
     *
     * @throws IllegalArgumentException if convertable is not a known type that can be converted
     */
    static <T> IFluidStack ofMutable(final T convertable) {
        
        return Services.PLATFORM.createFluidStackMutable(convertable);
    }
    
    static IFluidStack ofMutable(final Fluid fluid, final long amount) {
        
        return Services.PLATFORM.createFluidStackMutable(fluid, amount, DataComponentPatch.EMPTY);
    }
    
    static IFluidStack ofMutable(final Fluid fluid, final long amount, final DataComponentPatch components) {
        
        return Services.PLATFORM.createFluidStackMutable(fluid, amount, components);
    }
    
    /**
     * Gets the registry name for the fluid this stack is representing.
     *
     * @return A MCResourceLocation representing the registry name.
     */
    @ZenCodeType.Getter("registryName")
    default ResourceLocation getRegistryName() {
        
        return BuiltInRegistries.FLUID.getKey(getFluid());
    }
    
    /**
     * Checks if this IFluidStack, matches the given IFluidStack by checking if the fluids are the same, and if this fluid's amount is bigger than the given fluid's amount
     *
     * @param other other IFluidStack to compare against
     *
     * @return true if this fluid contains the other fluid
     */
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.CONTAINS)
    default boolean matches(IFluidStack other) {
        
        return getAmount() >= other.getAmount() && isFluidEqual(other);
    }
    
    default boolean isFluidEqual(IFluidStack other) {
        
        return this.getFluid() == other.getFluid() && FluidStackConstants.TAG_EQUALS.test(this, other);
    }
    
    /**
     * Gets whether this fluid stack is empty.
     *
     * @return {@code true} if this stack is empty, {@code false} otherwise.
     */
    @ZenCodeType.Getter("empty")
    boolean isEmpty();
    
    /**
     * Gets the fluid amount in MilliBuckets (mB).
     *
     * @return The amount of this fluid
     */
    @ZenCodeType.Getter("amount")
    long getAmount();
    
    /**
     * Sets the fluid amount in MilliBuckets (mB)
     *
     * @param amount The amount to multiply this stack
     *
     * @return A new stack, or this stack, depending on if this stack is mutable
     *
     * @docParam amount 1000
     */
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.MUL)
    IFluidStack setAmount(int amount);
    
    /**
     * Makes this stack mutable
     *
     * @return A new Stack, that is mutable.
     */
    @ZenCodeType.Method
    default IFluidStack asMutable() {
        
        if(!this.isImmutable()) {
            return this;
        }
        return IFluidStack.of(getInternal());
    }
    
    @ZenCodeType.Method
    default IFluidStack asImmutable() {
        
        if(this.isImmutable()) {
            return this;
        }
        return IFluidStack.of(getImmutableInternal());
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("isImmutable")
    boolean isImmutable();
    
    /**
     * Copies the stack. Only needed when mutable stacks are involved.
     *
     * @return A new stack, that contains the same info as this one
     */
    @ZenCodeType.Method
    default IFluidStack copy() {
        
        if(isImmutable()) {
            return IFluidStack.of(getImmutableInternal());
        }
        return IFluidStack.ofMutable(getImmutableInternal());
    }
    
    /**
     * Retrieves this fluid stack's fluid.
     *
     * @return The fluid.
     */
    @ZenCodeType.Getter("fluid")
    @ZenCodeType.Caster(implicit = true)
    Fluid getFluid();
    
    @ZenCodeType.Caster(implicit = true)
    default CTFluidIngredient asFluidIngredient() {
        
        return new CTFluidIngredient.FluidStackIngredient(this);
    }
    
    @ZenCodeType.Operator(ZenCodeType.OperatorType.OR)
    default CTFluidIngredient asList(CTFluidIngredient other) {
        
        List<CTFluidIngredient> elements = new ArrayList<>();
        elements.add(asFluidIngredient());
        elements.add(other);
        return new CTFluidIngredient.CompoundFluidIngredient(elements);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Caster(implicit = true)
    default IData asIData() {
        
        return codec().encodeStart(IDataOps.INSTANCE.withRegistryAccess(), this)
                .getOrThrow(s -> new IllegalStateException("Error while encoding IFluidStack as IData: " + s));
    }
    
    Codec<IFluidStack> codec();
    
    @Override
    default String getCommandString() {
        
        final Fluid fluid = getFluid();
        final StringBuilder builder = new StringBuilder().append("<fluid:")
                .append(BuiltInRegistries.FLUID.getKey(fluid))
                .append(">");
        
        DataComponentPatch.SplitResult split = getComponents().asPatch().split();
        split.added().filter(Predicate.not(DataComponentType::isTransient)).forEach(typedDataComponent -> {
            builder.append(".withJsonComponent(")
                    .append(ExpandDataComponentType.getCommandString(typedDataComponent.type()))
                    .append(", ")
                    .append(typedDataComponent.encodeValue(IDataOps.INSTANCE.withRegistryAccess()).getOrThrow())
                    .append(")");
        });
        split.removed()
                .forEach(dataComponentType -> builder.append(".without(")
                        .append(ExpandDataComponentType.getCommandString(dataComponentType))
                        .append(")"));
        
        if(!isEmpty()) {
            if(getAmount() != 1) {
                builder.append(" * ").append(getAmount());
            }
        }
        return builder.toString();
    }
    
    PatchedDataComponentMap getComponents();
    
    @ZenCodeType.Method
    <T> IFluidStack with(DataComponentType<T> type, @ZenCodeType.Nullable T value);
    
    @ZenCodeType.Method
    default <T> IFluidStack without(DataComponentType<T> type) {
        
        return remove(type);
    }
    
    @ZenCodeType.Method
    IFluidStack withJsonComponent(DataComponentType type, @ZenCodeType.Nullable IData value);
    
    @ZenCodeType.Method
    IFluidStack withJsonComponents(IData value);
    
    @ZenCodeType.Method
    <T> IFluidStack remove(DataComponentType<T> type);
    
    @ZenCodeType.Method
    <T, U> IFluidStack update(DataComponentType<T> type, T defaultValue, U data, BiFunction<T, U, T> operator);
    
    @ZenCodeType.Method
    <T> IFluidStack update(DataComponentType<T> type, T defaultValue, UnaryOperator<T> operator);
    
    @ZenCodeType.Method
    IFluidStack applyComponents(DataComponentMap map);
    
    @ZenCodeType.Method
    IFluidStack applyComponents(DataComponentPatch patch);
    
    /**
     * Gets the internal FluidStack that this IFluidStack is based on.
     * On Forge, this will be a {@code net.minecraftforge.fluids.FluidStack}.
     * On Fabric, this will be a {@code com.blamejared.crafttweaker.impl.fluid.SimpleFluidStack}
     *
     * You may need to specify the type in the call itself at times, like so {@code iFluidStack.<FluidStack>getInternal().getFluid()} or {@code iFluidStack.<SimpleFluidStack>getInternal().fluid()}
     *
     * @return The internal FluidStack that this IFluidStack is based on.
     */
    <T> T getInternal();
    
    /**
     * Gets a copy of the internal FluidStack that this IFluidStack is based on.
     * On Forge, this will be a {@code net.minecraftforge.fluids.FluidStack}.
     * On Fabric, this will be a {@code com.blamejared.crafttweaker.impl.fluid.SimpleFluidStack}
     *
     * You may need to specify the type in the call itself at times, like so {@code iFluidStack.<FluidStack>getInternal().getFluid()} or {@code iFluidStack.<SimpleFluidStack>getInternal().fluid()}
     *
     * @return A copy of the FluidStack that this IFluidStack is based on.
     */
    <T> T getImmutableInternal();
    
    @Override
    default <U> IFluidStack _without(DataComponentType<U> componentType) {
        
        return without(componentType);
    }
    
    @Override
    default <U> IFluidStack _with(DataComponentType<U> componentType, @Nullable U value) {
        
        return with(componentType, value);
    }
    
    @Override
    default <U> U _get(DataComponentType<? extends U> componentType) {
        
        return get(componentType);
    }
    
    @Override
    default <U> boolean _has(DataComponentType<U> componentType) {
        
        return has(componentType);
    }
    
}
