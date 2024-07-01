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
    
    /**
     * The map id to assign to the new object.
     * @param value A map id. These are assigned by vanilla on the server side.
     * @return A new MapId instance.
     */
    @ZenCodeType.StaticExpansionMethod
    public static MapId of(int value) {
        return new MapId(value);
    }
    
    @ZenCodeType.Getter("id")
    public static int getId(MapId internal) {
        return internal.id();
    }
}
