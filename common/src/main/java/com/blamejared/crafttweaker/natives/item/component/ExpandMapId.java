package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.level.saveddata.maps.MapId;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/component/MapId")
@NativeTypeRegistration(value = MapId.class, zenCodeName = "crafttweaker.api.item.component.MapId")
public class ExpandMapId {
    
    @ZenCodeType.Getter("id")
    public static int getId(MapId internal) {
        return internal.id();
    }
}
