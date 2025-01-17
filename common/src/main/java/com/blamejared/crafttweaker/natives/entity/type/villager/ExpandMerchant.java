package com.blamejared.crafttweaker.natives.entity.type.villager;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/entity/type/villager/Merchant")
@NativeTypeRegistration(value = Merchant.class, zenCodeName = "crafttweaker.api.entity.type.villager.Merchant")
public class ExpandMerchant {
    
    /**
     * Sets the trading player of the merchant.
     *
     * @param player The player to set as the trading player.
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("tradingPlayer")
    public static void setTradingPlayer(Merchant internal, @ZenCodeType.Nullable Player player) {
        
        internal.setTradingPlayer(player);
    }
    
    /**
     * Gets the trading player of the merchant.
     *
     * @return The trading player of the merchant.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("tradingPlayer")
    @ZenCodeType.Nullable
    public static Player getTradingPlayer(Merchant internal) {
        
        return internal.getTradingPlayer();
    }
    
    /**
     * Gets the {@link MerchantOffers} of the merchant.
     *
     * @return The {@link MerchantOffers} of the merchant.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("offers")
    public static MerchantOffers getOffers(Merchant internal) {
        
        return internal.getOffers();
    }
    
    /**
     * Notifies the merchant that a trade has occurred.
     *
     * @param offer The {@link MerchantOffer} that occurred.
     */
    @ZenCodeType.Method
    public static void notifyTrade(Merchant internal, MerchantOffer offer) {
        
        internal.notifyTrade(offer);
    }
    
    /**
     * Notifies the merchant that the trade has been updated.
     *
     * @param stack The {@link ItemStack} that was traded.
     */
    @ZenCodeType.Method
    public static void notifyTradeUpdated(Merchant internal, ItemStack stack) {
        
        internal.notifyTradeUpdated(stack);
    }
    
    /**
     * Gets the villager XP of the merchant.
     *
     * @return The villager XP of the merchant.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("villagerXp")
    public static int getVillagerXp(Merchant internal) {
        
        return internal.getVillagerXp();
    }
    
    /**
     * Gets if the merchant should show the progress bar.
     *
     * @return If the merchant should show the progress bar.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("showProgressBar")
    public static boolean showProgressBar(Merchant internal) {
        
        return internal.showProgressBar();
    }
    
    /**
     * Gets the notify trade sound of the merchant.
     *
     * @return The notify trade sound of the merchant.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("notifyTradeSound")
    public static SoundEvent getNotifyTradeSound(Merchant internal) {
        
        return internal.getNotifyTradeSound();
    }
    
    /**
     * Checks if the merchant can restock.
     *
     * @return true if the merchant can restock.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("canRestock")
    public static boolean canRestock(Merchant internal) {
        
        return internal.canRestock();
    }
    
    /**
     * Opens the trading screen for the merchant.
     *
     * @param player      The player to open the trading screen for.
     * @param displayName The display name of the merchant.
     * @param level       The level of the merchant.
     */
    @ZenCodeType.Method
    public static void openTradingScreen(Merchant internal, Player player, Component displayName, int level) {
        
        internal.openTradingScreen(player, displayName, level);
    }
    
    /**
     * Checks if the merchant is on the client side.
     *
     * @return true if the merchant is on the client side.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("isClientSide")
    public static boolean isClientSide(Merchant internal) {
        
        return internal.isClientSide();
    }
    
}
