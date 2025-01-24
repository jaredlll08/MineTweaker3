package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.component.CustomData;
import org.openzen.zencode.java.ZenCodeType;

import java.util.function.Consumer;

@ZenRegister
@Document("vanilla/api/item/component/CustomData")
@NativeTypeRegistration(value = CustomData.class, zenCodeName = "crafttweaker.api.item.component.CustomData")
public class ExpandCustomData {
    
    /**
     * Creates a new CustomData with the given data.
     *
     * @param tag The data to create the CustomData from.
     *
     * @return The new CustomData.
     */
    @ZenCodeType.StaticExpansionMethod
    public static CustomData of(MapData tag) {
        
        return CustomData.of(tag.getInternal());
    }
    
    /**
     * Gets the copy of the tag of the CustomData.
     *
     * @param internal The CustomData.
     *
     * @return The copy of the tag of the CustomData.
     */
    //TODO 1.21.2 make this return a MapData
    @ZenCodeType.Getter("copyTag")
    public static CompoundTag copyTag(CustomData internal) {
        
        return internal.copyTag();
    }
    
    /**
     * Checks if the CustomData contains the given key.
     *
     * @param key The key to check for.
     *
     * @return Whether the CustomData contains the given key.
     */
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.CONTAINS)
    public static boolean contains(CustomData internal, String key) {
        
        return internal.contains(key);
    }
    
    /**
     * Gets the size of the CustomData.
     *
     * @return The size of the CustomData.
     */
    @ZenCodeType.Getter("size")
    public static int size(CustomData internal) {
        
        return internal.size();
    }
    
    /**
     * Checks if the CustomData matches the given tag.
     *
     * @param tag The tag to check for.
     *
     * @return Whether the CustomData matches the given tag.
     */
    @ZenCodeType.Method
    public static boolean matchedBy(CustomData internal, MapData tag) {
        
        return internal.matchedBy(tag.getInternal());
    }
    
    /**
     * Updates the CustomData with the given updater.
     *
     * @param updater The updater to update the CustomData with.
     *
     * @return The updated CustomData.
     */
    @ZenCodeType.Method
    public static CustomData update(CustomData internal, Consumer<MapData> updater) {
        
        return internal.update(compoundTag -> updater.accept(new MapData(compoundTag)));
    }
    
    /**
     * Checks if the CustomData is empty.
     *
     * @return Whether the CustomData is empty.
     */
    @ZenCodeType.Getter("isEmpty")
    public static boolean isEmpty(CustomData internal) {
        
        return internal.isEmpty();
    }
    
    
}
