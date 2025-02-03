package com.blamejared.crafttweaker.impl.datamap;

import net.neoforged.neoforge.registries.datamaps.DataMapFile;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

import java.util.List;
import java.util.Map;

public interface LoadResultGetter {
    
    Map<DataMapType<?, ?>, List<DataMapFile<?, ?>>> results();
    
}
