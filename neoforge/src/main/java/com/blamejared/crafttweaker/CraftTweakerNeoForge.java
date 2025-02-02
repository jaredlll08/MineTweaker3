package com.blamejared.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.platform.NeoForgeRegistryHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(CraftTweakerConstants.MOD_ID)
public class CraftTweakerNeoForge {
    
    public CraftTweakerNeoForge(IEventBus bus) {
        
        CraftTweakerCommon.init();
        NeoForgeRegistryHelper.init(bus);
        CraftTweakerCommon.getPluginManager().loadPlugins();
        
        bus.addListener(this::setup);
        
        CraftTweakerCommon.getPluginManager().broadcastSetupEnd(); // TODO("Another place?")
    }
    
    private void setup(final FMLCommonSetupEvent event) {
        
        CraftTweakerCommon.loadInitScripts();
    }
    
}
