package com.blamejared.crafttweaker.mixin.common.transform.server;

import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.impl.loot.modifier.LootModifierManagerReloadListener;
import com.blamejared.crafttweaker.impl.script.ScriptReloadListenerAdapter;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mixin(ReloadableServerResources.class)
public abstract class MixinReloadableServerResources {
    
    @ModifyReturnValue(method = "listeners", at = @At(value = "RETURN"))
    private List<PreparableReloadListener> crafttweaker$attachListener(List<PreparableReloadListener> original) {
        
        List<PreparableReloadListener> listeners = new ArrayList<>(original);
        listeners.add(new LootModifierManagerReloadListener());
        listeners.add(new ScriptReloadListenerAdapter(GenericUtil.uncheck(this)));
        // Lets keep it unmodifiable
        return Collections.unmodifiableList(listeners);
    }
    
}
