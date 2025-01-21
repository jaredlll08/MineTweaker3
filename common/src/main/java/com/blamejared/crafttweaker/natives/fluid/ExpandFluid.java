package com.blamejared.crafttweaker.natives.fluid;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.tag.type.KnownTag;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.blamejared.crafttweaker_annotations.annotations.TaggableElement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/fluid/Fluid")
@NativeTypeRegistration(value = Fluid.class, zenCodeName = "crafttweaker.api.fluid.Fluid")
@TaggableElement("minecraft:fluid")
public class ExpandFluid {
    
    
    /**
     * Creates a new {@link IFluidStack} with the given amount of fluid.
     *
     * @return a new (immutable) {@link IFluidStack}
     *
     * @docParam amount 1000
     */
    @ZenCodeType.Operator(ZenCodeType.OperatorType.MUL)
    public static IFluidStack multiply(Fluid internal, int amount) {
        
        return makeStack(internal, amount);
    }
    
    
    /**
     * Creates a new {@link IFluidStack} with the given amount of fluid.
     *
     * @return a new (immutable) {@link IFluidStack}
     *
     * @docParam amount 1000
     */
    @ZenCodeType.Method
    public static IFluidStack makeStack(Fluid internal, int amount) {
        
        return IFluidStack.of(internal, amount);
    }
    
    /**
     * Gets the bucket item for this fluid.
     *
     * @return The bucket item for this fluid.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("bucket")
    public static Item getBucket(Fluid internal) {
        
        return internal.getBucket();
    }
    
    /**
     * Checks if this fluid is the same as another fluid.
     *
     * @param other The other fluid to check.
     *
     * @return True if this fluid is the same as the other fluid, false otherwise.
     */
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.EQUALS)
    public static boolean isSame(Fluid internal, Fluid other) {
        
        return internal.isSame(other);
    }
    
    /**
     * Checks if this fluid is in the given tag.
     *
     * @param tag The tag to check.
     *
     * @return True if this fluid is in the given tag, false otherwise.
     */
    @ZenCodeType.Method
    public static boolean isIn(Fluid internal, KnownTag<Fluid> tag) {
        
        return internal.is(tag.getTagKey());
    }
    
    /**
     * Gets the registry name of this fluid.
     *
     * @return The registry name of this fluid.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("registryName")
    public static ResourceLocation getRegistryName(Fluid internal) {
        
        return BuiltInRegistries.FLUID.getKey(internal);
    }
    
    /**
     * Gets the command string for this fluid.
     *
     * @return The command string for this fluid.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(Fluid internal) {
        
        return "<fluid:" + BuiltInRegistries.FLUID.getKey(internal) + ">.definition";
    }
    
}
