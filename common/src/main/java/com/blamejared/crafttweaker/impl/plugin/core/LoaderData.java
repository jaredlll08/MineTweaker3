package com.blamejared.crafttweaker.impl.plugin.core;

import com.blamejared.crafttweaker.api.zencode.IScriptLoader;
import com.google.common.collect.Collections2;

import java.util.Collection;

record LoaderData(String name, Collection<IScriptLoader> inheritedLoaders) implements IScriptLoader {
    
    @Override
    public String toString() {
        
        return "%s <- %s".formatted(
                this.name(),
                Collections2.transform(this.allInheritedLoaders(), IScriptLoader::name)
        );
    }
    
}
