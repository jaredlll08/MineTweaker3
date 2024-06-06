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
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;

@ZenRegister
@Document("neoforge/api/ingredient/SizedFluidIngredient")
@NativeTypeRegistration(value = SizedFluidIngredient.class, zenCodeName = "crafttweaker.neoforge.api.item.SizedFluidIngredient")
public class ExpandSizedFluidIngredient {
    
    @ZenCodeType.Getter("ingredient")
    public static FluidIngredient ingredient(SizedFluidIngredient internal) {
        
        return internal.ingredient();
    }
    
    @ZenCodeType.Getter("amount")
    public static int amount(SizedFluidIngredient internal) {
        
        return internal.amount();
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static SizedFluidIngredient of(Fluid fluid, int amount) {
        
        return SizedFluidIngredient.of(fluid, amount);
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static SizedFluidIngredient of(FluidStack stack) {
        
        return SizedFluidIngredient.of(stack);
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static SizedFluidIngredient of(KnownTag<Fluid> tag, int amount) {
        
        return SizedFluidIngredient.of(tag.getTagKey(), amount);
    }
    
    @ZenCodeType.Getter("fluids")
    public static IFluidStack[] getFluids(SizedFluidIngredient internal) {
        
        return Arrays.stream(internal.getFluids()).map(IFluidStack::of).toArray(IFluidStack[]::new);
    }
    
    @ZenCodeType.Method
    public static boolean test(SizedFluidIngredient internal, IFluidStack stack) {
        
        return internal.test(stack.getInternal());
    }
    
    @ZenCodeType.Caster(implicit = true)
    public static CTFluidIngredient asCTFluidIngredient(SizedFluidIngredient internal) {
        
        return ExpandFluidIngredient.asCTFluidIngredient(internal.ingredient(), internal.amount());
    }
    
}
