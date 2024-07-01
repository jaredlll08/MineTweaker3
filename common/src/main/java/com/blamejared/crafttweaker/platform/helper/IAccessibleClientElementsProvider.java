package com.blamejared.crafttweaker.platform.helper;

import com.blamejared.crafttweaker.api.registry.TagAddingRegistryLookup;
import net.minecraft.core.RegistryAccess;

import java.util.function.Consumer;

// TODO("Better package")
public interface IAccessibleClientElementsProvider {
    
    RegistryAccess registryAccess();
    
    void registryAccess(final RegistryAccess registryAccess);
    
    void runWithRegistryAccess(final Consumer<RegistryAccess> consumer);
    
    boolean hasRegistryAccess();
    
    TagAddingRegistryLookup tagAddingRegistryLookup();
    
}
