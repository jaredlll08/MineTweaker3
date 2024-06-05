package com.blamejared.crafttweaker.natives.ingredient;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.CTFluidIngredient;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.tag.type.KnownTag;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;

@ZenRegister
@Document("neoforge/api/ingredient/FluidIngredient")
@NativeTypeRegistration(value = FluidIngredient.class, zenCodeName = "crafttweaker.neoforge.api.item.FluidIngredient")
public class ExpandFluidIngredient {
    
    @ZenCodeType.StaticExpansionMethod
    public static FluidIngredient of() {
        
        return FluidIngredient.of();
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static FluidIngredient of(IFluidStack... fluids) {
        
        return FluidIngredient.of(Arrays.stream(fluids)
                .map(IFluidStack::<FluidStack>getInternal)
                .toArray(FluidStack[]::new));
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static FluidIngredient of(Fluid... fluids) {
        
        return FluidIngredient.of(fluids);
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static FluidIngredient single(IFluidStack stack) {
        
        return FluidIngredient.single(stack.<FluidStack> getInternal());
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static FluidIngredient single(Fluid fluid) {
        
        return FluidIngredient.single(fluid);
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static FluidIngredient tag(KnownTag<Fluid> tag) {
        
        return FluidIngredient.tag(tag.getTagKey());
    }
    
    @ZenCodeType.Getter("hasNoFluids")
    public static boolean hasNoFluids(FluidIngredient internal) {
        
        return internal.hasNoFluids();
    }
    
    @ZenCodeType.Getter("empty")
    public static boolean isEmpty(FluidIngredient internal) {
        
        return internal.isEmpty();
    }
    
    @ZenCodeType.Getter("simple")
    public static boolean isSimple(FluidIngredient internal) {
        
        return internal.isSimple();
    }
    
    @ZenCodeType.Method
    public static boolean test(FluidIngredient internal, FluidStack fluidStack) {
        
        return internal.test(fluidStack);
    }
    
    @ZenCodeType.Getter("stacks")
    public static FluidStack[] getStacks(FluidIngredient internal) {
        
        return internal.getStacks();
    }
    
    @ZenCodeType.Caster(implicit = true)
    public static CTFluidIngredient asCTFluidIngredient(FluidIngredient internal) {
        
        if(internal.hasNoFluids()) {
            return CTFluidIngredient.EMPTY.get();
        }
        
        return Arrays.stream(internal.getStacks())
                .map(fluidStack -> IFluidStack.of(fluidStack).asFluidIngredient())
                .reduce(CTFluidIngredient::asCompound).orElseGet(CTFluidIngredient.EMPTY);
    }
    
}
