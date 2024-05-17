package com.blamejared.crafttweaker.platform.helper;

import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.alchemy.PotionBrewing;

// TODO("Better package")
public interface IAccessibleClientElementsProvider {
    
    RegistryAccess registryAccess();
    
    void registryAccess(final RegistryAccess registryAccess);
    
    boolean hasRegistryAccess();
    
}
