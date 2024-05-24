package com.blamejared.crafttweaker.platform.helper;

import com.blamejared.crafttweaker.mixin.common.access.server.AccessReloadableServerResources;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.world.item.alchemy.PotionBrewing;

import java.util.function.Consumer;

// TODO("Better package")
public interface IAccessibleServerElementsProvider {
    
    ReloadableServerResources resources();
    
    AccessReloadableServerResources accessibleResources();
    
    boolean hasResources();
    
    void resources(final ReloadableServerResources resources);
    
    RegistryAccess registryAccess();
    
    void registryAccess(final RegistryAccess registryAccess);
    
    void runWithRegistryAccess(final Consumer<RegistryAccess> consumer);
    
    boolean hasRegistryAccess();
    
}
