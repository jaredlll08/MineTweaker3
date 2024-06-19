package com.blamejared.crafttweaker.natives.resource;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.IData;
import com.blamejared.crafttweaker.api.data.StringData;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister(loaders = {CraftTweakerConstants.DEFAULT_LOADER_NAME, CraftTweakerConstants.TAGS_LOADER_NAME})
@Document("vanilla/api/resource/ResourceLocation")
@NativeTypeRegistration(value = ResourceLocation.class, zenCodeName = ExpandResourceLocation.ZC_CLASS_NAME)
public class ExpandResourceLocation {
    
    public static final String ZC_CLASS_NAME = "crafttweaker.api.resource.ResourceLocation";
    
    /**
     * Creates a new ResourceLocation from the given string.
     *
     * @param id The ResourceLocation to parse.
     *
     * @return A new ResourceLocation from the given id
     *
     * @docParam id "crafttweaker:some-path/to.1"
     */
    @ZenCodeType.StaticExpansionMethod
    public static ResourceLocation parse(String id) {
        
        return ResourceLocation.parse(id);
    }
    
    /**
     * Creates a new ResourceLocation from the given namespace and path
     *
     * @param namespace The namespace of the resource, usually a modid
     * @param path      The path of the resource, May only contain lower-cased alphanumeric values, as well as / and _
     *
     * @return A new ResourceLocation from the given values.
     *
     * @docParam namespace "crafttweaker"
     * @docParam path "some-path/to.1"
     */
    @ZenCodeType.StaticExpansionMethod
    public static ResourceLocation fromNamespaceAndPath(String namespace, String path) {
        
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.COMPARE)
    public static int compareTo(ResourceLocation internal, ResourceLocation other) {
        
        return internal.compareTo(other);
    }
    
    @ZenCodeType.Method
    public static int hashCode(ResourceLocation internal) {
        
        return internal.hashCode();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("path")
    public static String getPath(ResourceLocation internal) {
        
        return internal.getPath();
    }
    
    
    @ZenCodeType.Method
    @ZenCodeType.Caster(implicit = true)
    public static String toString(ResourceLocation internal) {
        
        return internal.toString();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Caster(implicit = true)
    public static IData asData(ResourceLocation internal) {
        
        return new StringData(toString(internal));
    }
    
    
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.EQUALS)
    public static boolean equals(ResourceLocation internal, Object other) {
        
        return internal.equals(other);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("namespace")
    public static String getNamespace(ResourceLocation internal) {
        
        return internal.getNamespace();
    }
    
    @ZenCodeType.Getter("commandString")
    public static String getCommandString(ResourceLocation internal) {
        
        return "<resource:" + internal + ">";
    }
    
}
