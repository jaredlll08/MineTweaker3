package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.mojang.authlib.GameProfile;
import net.minecraft.world.item.component.ResolvableProfile;
import org.openzen.zencode.java.ZenCodeType;

import java.util.UUID;

@ZenRegister
@Document("vanilla/api/item/component/ResolvableProfile")
@NativeTypeRegistration(value = ResolvableProfile.class, zenCodeName = "crafttweaker.api.item.component.ResolvableProfile")
public class ExpandResolvableProfile {
    
    @ZenCodeType.Getter("id")
    @ZenCodeType.Nullable
    public static UUID id(ResolvableProfile internal) {
        
        return internal.id().orElse(null);
    }
    
    @ZenCodeType.Getter("isResolved")
    public static boolean isResolved(ResolvableProfile internal) {
        
        return internal.isResolved();
    }
    
    @ZenCodeType.Getter("name")
    @ZenCodeType.Nullable
    public static String name(ResolvableProfile internal) {
        
        return internal.name().orElse(null);
    }
    
    @ZenCodeType.Getter("gameProfile")
    public static GameProfile gameProfile(ResolvableProfile internal) {
        
        return internal.gameProfile();
    }
    
}
