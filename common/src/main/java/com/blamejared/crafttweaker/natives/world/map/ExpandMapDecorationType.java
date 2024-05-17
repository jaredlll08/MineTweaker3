package com.blamejared.crafttweaker.natives.world.map;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/world/map/MapDecorationType")
@NativeTypeRegistration(value = MapDecorationType.class, zenCodeName = "crafttweaker.api.world.map.MapDecorationType")
@BracketEnum("minecraft:world/map/decorationtype")
public class ExpandMapDecorationType {
    
    
    @ZenCodeType.Getter("hasMapColor")
    public static boolean hasMapColor(MapDecorationType internal) {
        
        return internal.hasMapColor();
    }
    
    @ZenCodeType.Getter("assetId")
    public static ResourceLocation assetId(MapDecorationType internal) {
        
        return internal.assetId();
    }
    
    @ZenCodeType.Getter("explorationMapElement")
    public static boolean explorationMapElement(MapDecorationType internal) {
        
        return internal.explorationMapElement();
    }
    
    @ZenCodeType.Getter("mapColor")
    public static int mapColor(MapDecorationType internal) {
        
        return internal.mapColor();
    }
    
    @ZenCodeType.Getter("showOnItemFrame")
    public static boolean showOnItemFrame(MapDecorationType internal) {
        
        return internal.showOnItemFrame();
    }
    
    @ZenCodeType.Getter("trackCount")
    public static boolean trackCount(MapDecorationType internal) {
        
        return internal.trackCount();
    }
    
}
