package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.LockCode;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/component/LockCode")
@NativeTypeRegistration(value = LockCode.class, zenCodeName = "crafttweaker.api.item.component.LockCode")
public class ExpandLockCode {
    
    /**
     * The item hover name that will allow unlocking this code.
     *
     * @param name The hover name to compare against.
     * @return A new LockCode instance.
     */
    @ZenCodeType.StaticExpansionMethod
    public static LockCode of(String name) {
        return new LockCode(name);
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static LockCode of(IItemStack item) {
        return new LockCode(item.getHoverName().getString());
    }
    
    @ZenCodeType.Getter("key")
    public static String getKey(LockCode internal) {
        return internal.key();
    }
}
