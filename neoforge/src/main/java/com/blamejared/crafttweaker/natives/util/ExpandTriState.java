package com.blamejared.crafttweaker.natives.util;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.neoforged.neoforge.common.util.TriState;
import org.openzen.zencode.java.ZenCodeType;


@ZenRegister
@Document("neoforge/api/util/TriState")
@NativeTypeRegistration(value = TriState.class, zenCodeName = "crafttweaker.neoforge.api.tool.TriState")
@BracketEnum("neoforge:tristate")
public class ExpandTriState {
    
    @ZenCodeType.Getter("isFalse")
    public static boolean isFalse(TriState internal) {
        
        return internal.isFalse();
    }
    
    @ZenCodeType.Getter("isDefault")
    public static boolean isDefault(TriState internal) {
        
        return internal.isDefault();
    }
    
    @ZenCodeType.Getter("isTrue")
    public static boolean isTrue(TriState internal) {
        
        return internal.isTrue();
    }
    
}
