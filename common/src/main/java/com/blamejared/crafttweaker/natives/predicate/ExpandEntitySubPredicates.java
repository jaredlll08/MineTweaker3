package com.blamejared.crafttweaker.natives.predicate;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.advancements.critereon.EntitySubPredicates;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/predicate/EntitySubPredicates")
@NativeTypeRegistration(value = EntitySubPredicates.class, zenCodeName = "crafttweaker.api.predicate.EntitySubPredicates")
public final class ExpandEntitySubPredicates {
    
    @ZenCodeType.StaticExpansionMethod
    public static EntitySubPredicate paintingVariant(final ResourceLocation... variants) {
        
        return EntitySubPredicates.PAINTING.createPredicate(HolderSet.direct(variant -> BuiltInRegistries.PAINTING_VARIANT.getHolderOrThrow(ResourceKey.create(Registries.PAINTING_VARIANT, variant)), variants));
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static EntitySubPredicate catVariant(final ResourceLocation... variants) {
        
        return EntitySubPredicates.CAT.createPredicate(HolderSet.direct(variant -> BuiltInRegistries.CAT_VARIANT.getHolderOrThrow(ResourceKey.create(Registries.CAT_VARIANT, variant)), variants));
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static EntitySubPredicate frogVariant(final ResourceLocation... variants) {
        
        return EntitySubPredicates.FROG.createPredicate(HolderSet.direct(variant -> BuiltInRegistries.FROG_VARIANT.getHolderOrThrow(ResourceKey.create(Registries.FROG_VARIANT, variant)), variants));
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static EntitySubPredicate wolfVariant(final ResourceLocation... variants) {
        
        return EntitySubPredicates.WOLF.createPredicate(CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess(registryAccess -> HolderSet.direct(variant -> registryAccess.lookupOrThrow(Registries.WOLF_VARIANT)
                        .getOrThrow(ResourceKey.create(Registries.WOLF_VARIANT, variant)), variants)));
    }
    
}
