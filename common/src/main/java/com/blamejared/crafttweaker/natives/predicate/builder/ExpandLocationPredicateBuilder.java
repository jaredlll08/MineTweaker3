package com.blamejared.crafttweaker.natives.predicate.builder;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.FluidPredicate;
import net.minecraft.advancements.critereon.LightPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/predicate/builder/LocationPredicateBuilder")
@NativeTypeRegistration(value = LocationPredicate.Builder.class, zenCodeName = "crafttweaker.api.predicate.builder.LocationPredicateBuilder")
public final class ExpandLocationPredicateBuilder {
    
    @ZenCodeType.Method
    public static LocationPredicate.Builder x(final LocationPredicate.Builder internal, final MinMaxBounds.Doubles x) {
        
        return internal.setX(x);
    }
    
    @ZenCodeType.Method
    public static LocationPredicate.Builder y(final LocationPredicate.Builder internal, final MinMaxBounds.Doubles y) {
        
        return internal.setY(y);
    }
    
    @ZenCodeType.Method
    public static LocationPredicate.Builder z(final LocationPredicate.Builder internal, final MinMaxBounds.Doubles z) {
        
        return internal.setZ(z);
    }
    
    @ZenCodeType.Method
    public static LocationPredicate.Builder biome(final LocationPredicate.Builder internal, final ResourceLocation biome) {
        
        Holder.Reference<Biome> holder = CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess()
                .registryOrThrow(Registries.BIOME)
                .getHolderOrThrow(ResourceKey.create(Registries.BIOME, biome));
        return internal.setBiomes(HolderSet.direct(holder));
    }
    
    @ZenCodeType.Method
    public static LocationPredicate.Builder biome(final LocationPredicate.Builder internal, final String biome) {
        
        return biome(internal, ResourceLocation.parse(biome));
    }
    
    @ZenCodeType.Method
    public static LocationPredicate.Builder structure(final LocationPredicate.Builder internal, final ResourceLocation structure) {
        
        Holder.Reference<Structure> holder = CraftTweakerAPI.getAccessibleElementsProvider()
                .registryAccess()
                .registryOrThrow(Registries.STRUCTURE)
                .getHolderOrThrow(ResourceKey.create(Registries.STRUCTURE, structure));
        return internal.setStructures(HolderSet.direct(holder));
    }
    
    @ZenCodeType.Method
    public static LocationPredicate.Builder structure(final LocationPredicate.Builder internal, final String structure) {
        
        return structure(internal, ResourceLocation.parse(structure));
    }
    
    @ZenCodeType.Method
    public static LocationPredicate.Builder dimension(final LocationPredicate.Builder internal, final ResourceLocation dimension) {
        
        return internal.setDimension(ResourceKey.create(Registries.DIMENSION, dimension));
    }
    
    @ZenCodeType.Method
    public static LocationPredicate.Builder dimension(final LocationPredicate.Builder internal, final String dimension) {
        
        return dimension(internal, ResourceLocation.parse(dimension));
    }
    
    @ZenCodeType.Method
    public static LocationPredicate.Builder light(final LocationPredicate.Builder internal, final LightPredicate.Builder predicate) {
        
        return internal.setLight(predicate);
    }
    
    @ZenCodeType.Method
    public static LocationPredicate.Builder block(final LocationPredicate.Builder internal, final BlockPredicate.Builder predicate) {
        
        return internal.setBlock(predicate);
    }
    
    
    @ZenCodeType.Method
    public static LocationPredicate.Builder fluid(final LocationPredicate.Builder internal, final FluidPredicate.Builder predicate) {
        
        return internal.setFluid(predicate);
    }
    
    @ZenCodeType.Method
    public static LocationPredicate.Builder smokey(final LocationPredicate.Builder internal, @ZenCodeType.OptionalBoolean(true) final Boolean smokey) {
        
        return internal.setSmokey(smokey);
    }
    
    @ZenCodeType.Method
    public static LocationPredicate build(final LocationPredicate.Builder internal) {
        
        return internal.build();
    }
    
}
