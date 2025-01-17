package com.blamejared.crafttweaker.natives.advancement;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/advancement/DisplayInfo")
@NativeTypeRegistration(value = DisplayInfo.class, zenCodeName = "crafttweaker.api.advancement.DisplayInfo")
public class ExpandDisplayInfo {
    
    /**
     * Sets the location of the display info.
     *
     * @param x The x coordinate of the display info.
     * @param y The y coordinate of the display info.
     */
    @ZenCodeType.Method
    public static void setLocation(DisplayInfo internal, float x, float y) {
        
        internal.setLocation(x, y);
    }
    
    /**
     * Gets the title of the display info.
     *
     * @return The title of the display info.
     */
    @ZenCodeType.Getter("title")
    public static Component getTitle(DisplayInfo internal) {
        
        return internal.getTitle();
    }
    
    /**
     * Gets the description of the display info.
     *
     * @return The description of the display info.
     */
    @ZenCodeType.Getter("description")
    public static Component getDescription(DisplayInfo internal) {
        
        return internal.getDescription();
    }
    
    /**
     * Gets the icon of the display info.
     *
     * @return The icon of the display info.
     */
    @ZenCodeType.Getter("icon")
    public static ItemStack getIcon(DisplayInfo internal) {
        
        return internal.getIcon();
    }
    
    /**
     * Gets the background of the display info, or null if it is not set.
     *
     * @return The background of the display info, or null if it is not set.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("background")
    public static ResourceLocation getBackground(DisplayInfo internal) {
        
        return internal.getBackground().orElse(null);
    }
    
    /**
     * Gets the type of the display info.
     *
     * @return The type of the display info.
     */
    @ZenCodeType.Getter("type")
    public static AdvancementType getFrame(DisplayInfo internal) {
        
        return internal.getType();
    }
    
    /**
     * Gets the x coordinate of the display info.
     *
     * @return The x coordinate of the display info.
     */
    @ZenCodeType.Getter("x")
    public static float getX(DisplayInfo internal) {
        
        return internal.getX();
    }
    
    /**
     * Gets the y coordinate of the display info.
     *
     * @return The y coordinate of the display info.
     */
    @ZenCodeType.Getter("y")
    public static float getY(DisplayInfo internal) {
        
        return internal.getY();
    }
    
    /**
     * Gets whether the toast should be shown for the display info.
     *
     * @return Whether the toast should be shown for the display info.
     */
    @ZenCodeType.Getter("shouldShowToast")
    public static boolean shouldShowToast(DisplayInfo internal) {
        
        return internal.shouldShowToast();
    }
    
    /**
     * Gets whether the advancement should be displayed in chat when earned.
     *
     * @return Whether the advancement should be displayed in chat when earned.
     */
    @ZenCodeType.Getter("shouldAnnounceChat")
    public static boolean shouldAnnounceChat(DisplayInfo internal) {
        
        return internal.shouldAnnounceChat();
    }
    
    /**
     * Gets whether the advancement should be hidden.
     *
     * @return Whether the advancement should be hidden.
     */
    @ZenCodeType.Getter("isHidden")
    public static boolean isHidden(DisplayInfo internal) {
        
        return internal.isHidden();
    }
    
}
