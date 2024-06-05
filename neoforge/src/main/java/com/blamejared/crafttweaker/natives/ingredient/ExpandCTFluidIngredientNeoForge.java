package com.blamejared.crafttweaker.natives.ingredient;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.CTFluidIngredient;
import com.google.common.collect.Lists;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.CompoundFluidIngredient;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@ZenCodeType.Expansion("crafttweaker.api.fluid.FluidIngredient")
public class ExpandCTFluidIngredientNeoForge {
    
    @ZenCodeType.Caster(implicit = true)
    public static FluidIngredient asFluidIngredient(CTFluidIngredient internal) {
        
        return internal.mapTo(iFluidStack -> FluidIngredient.of(iFluidStack.<FluidStack> getInternal()), (fluidTagKey, integer) -> FluidIngredient.tag(fluidTagKey), CompoundFluidIngredient::of);
    }
    
    @ZenCodeType.Caster(implicit = true)
    public static SizedFluidIngredient asSizedFluidIngredient(CTFluidIngredient internal) {
        
        return internal.mapTo(iFluidStack -> SizedFluidIngredient.of(iFluidStack.getInternal()), SizedFluidIngredient::of, stream -> {
            List<SizedFluidIngredient> ingredients = stream.toList();
            FluidIngredient fluidIngredient = CompoundFluidIngredient.of(Lists.transform(ingredients, SizedFluidIngredient::ingredient));
            return new SizedFluidIngredient(fluidIngredient, ingredients.getFirst().amount());
        });
    }
    
}
