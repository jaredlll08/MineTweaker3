package com.blamejared.crafttweaker.natives.util;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import com.mojang.authlib.GameProfile;
import org.openzen.zencode.java.ZenCodeType;

import java.util.UUID;

@ZenRegister
@Document("vanilla/api/util/GameProfile")
@NativeTypeRegistration(value = GameProfile.class, zenCodeName = "crafttweaker.api.util.GameProfile")
public class ExpandGameProfile {
    
    @ZenCodeType.Getter("id")
    public static UUID getId(GameProfile internal) {
        
        return internal.getId();
    }
    
    @ZenCodeType.Getter("name")
    public static String getName(GameProfile internal) {
        
        return internal.getName();
    }
    
}
