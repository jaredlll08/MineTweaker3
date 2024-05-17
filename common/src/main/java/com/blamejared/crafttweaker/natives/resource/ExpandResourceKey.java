package com.blamejared.crafttweaker.natives.resource;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/resource/ResourceKey")
@NativeTypeRegistration(value = ResourceKey.class, zenCodeName = "crafttweaker.api.resource.ResourceKey")
public class ExpandResourceKey {
    
    @ZenCodeType.StaticExpansionMethod
    public static ResourceKey create(ResourceKey base, ResourceLocation location) {
        
        return ResourceKey.create(base, location);
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static ResourceKey createRegistryKey(ResourceLocation location) {
        
        return ResourceKey.createRegistryKey(location);
    }
    
    @ZenCodeType.Method
    public static ResourceLocation location(ResourceKey internal) {
        
        return internal.location();
    }
    
    @ZenCodeType.Method
    public static ResourceLocation registry(ResourceKey internal) {
        
        return internal.registry();
    }
    
}
