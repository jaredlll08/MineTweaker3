package com.blamejared.crafttweaker.natives.predicate;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.advancements.critereon.EntitySubPredicates;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/predicate/EntitySubPredicates")
@NativeTypeRegistration(value = EntitySubPredicates.class, zenCodeName = "crafttweaker.api.predicate.EntitySubPredicates")
public final class ExpandEntitySubPredicates {
    
    @ZenCodeType.StaticExpansionMethod
    public static EntitySubPredicate paintingVariant(final ResourceLocation... variants) {
        
        return EntitySubPredicates.PAINTING.createPredicate(HolderSet.direct(variant -> Services.REGISTRY.holderOrThrow(Registries.PAINTING_VARIANT, variant), variants));
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static EntitySubPredicate catVariant(final ResourceLocation... variants) {
        
        return EntitySubPredicates.CAT.createPredicate(HolderSet.direct(variant -> Services.REGISTRY.holderOrThrow(Registries.CAT_VARIANT, variant), variants));
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static EntitySubPredicate frogVariant(final ResourceLocation... variants) {
        
        return EntitySubPredicates.FROG.createPredicate(HolderSet.direct(variant -> Services.REGISTRY.holderOrThrow(Registries.FROG_VARIANT, variant), variants));
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static EntitySubPredicate wolfVariant(final ResourceLocation... variants) {
        
        return EntitySubPredicates.WOLF.createPredicate(HolderSet.direct(variant -> Services.REGISTRY.holderOrThrow(Registries.WOLF_VARIANT, variant), variants));
    }
    
}
