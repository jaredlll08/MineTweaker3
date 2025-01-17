package com.blamejared.crafttweaker.natives.advancement;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerAdvancementManager;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Collection;

@ZenRegister
@Document("vanilla/api/advancement/ServerAdvancementManager")
@NativeTypeRegistration(value = ServerAdvancementManager.class, zenCodeName = "crafttweaker.api.advancement.ServerAdvancementManager")
public class ExpandServerAdvancementManager {
    
    /**
     * Gets an advancement by its id.
     *
     * @param id The id of the advancement to get.
     *
     * @return The advancement with the given id, or null if it does not exist.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.INDEXGET)
    public static AdvancementHolder getAdvancement(ServerAdvancementManager internal, ResourceLocation id) {
        
        return internal.get(id);
    }
    
    /**
     * Gets all advancements.
     *
     * @return All advancements.
     */
    @ZenCodeType.Getter("allAdvancements")
    public static Collection<AdvancementHolder> getAllAdvancements(ServerAdvancementManager internal) {
        
        return internal.getAllAdvancements();
    }
    
}
