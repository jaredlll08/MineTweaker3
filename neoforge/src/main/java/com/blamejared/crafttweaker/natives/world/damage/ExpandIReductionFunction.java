package com.blamejared.crafttweaker.natives.world.damage;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.common.damagesource.IReductionFunction;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.BiFunction;

@SuppressWarnings("UnstableApiUsage")
@ZenRegister
@Document("neoforge/api/world/damage/IReductionFunction")
@NativeTypeRegistration(value = IReductionFunction.class, zenCodeName = "crafttweaker.neoforge.api.world.damage.IReductionFunction")
public class ExpandIReductionFunction {
    
    @ZenCodeType.StaticExpansionMethod
    public static IReductionFunction of(BiFunction<DamageContainer, Float, Float> func) {
        
        return func::apply;
    }
    
    @ZenCodeType.Method
    public static float modify(IReductionFunction internal, DamageContainer container, float reductionIn) {
        
        return internal.modify(container, reductionIn);
    }
    
}
