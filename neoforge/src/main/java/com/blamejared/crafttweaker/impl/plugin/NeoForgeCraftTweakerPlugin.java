package com.blamejared.crafttweaker.impl.plugin;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.plugin.CraftTweakerPlugin;
import com.blamejared.crafttweaker.api.plugin.ICraftTweakerPlugin;
import com.blamejared.crafttweaker.api.plugin.IVillagerTradeRegistrationHandler;
import com.blamejared.crafttweaker.api.villager.CTTradeObject;
import com.blamejared.crafttweaker.mixin.common.access.villager.AccessBasicTrade;
import net.neoforged.neoforge.common.BasicItemListing;

@CraftTweakerPlugin(CraftTweakerConstants.MOD_ID + ":neoforge")
@SuppressWarnings("unused") // Autowired
public class NeoForgeCraftTweakerPlugin implements ICraftTweakerPlugin {
    
    @Override
    public void registerVillagerTradeConverters(IVillagerTradeRegistrationHandler handler) {
        
        ICraftTweakerPlugin.super.registerVillagerTradeConverters(handler);
        handler.registerTradeConverter(BasicItemListing.class, iTrade -> new CTTradeObject(
                IItemStack.ofMutable(((AccessBasicTrade) iTrade).crafttweaker$getPrice()),
                IItemStack.ofMutable(((AccessBasicTrade) iTrade).crafttweaker$getPrice2()),
                IItemStack.ofMutable(((AccessBasicTrade) iTrade).crafttweaker$getForSale())));
    }
    
}
