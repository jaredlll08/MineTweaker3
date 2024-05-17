package com.blamejared.crafttweaker.mixin.common.transform.server;

import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.impl.loot.modifier.LootModifierManagerReloadListener;
import com.blamejared.crafttweaker.impl.script.ScriptReloadListenerAdapter;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.commands.Commands;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.flag.FeatureFlagSet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(ReloadableServerResources.class)
public abstract class MixinReloadableServerResources {
    
    @ModifyReturnValue(method = "listeners", at = @At(value = "RETURN"))
    private List<PreparableReloadListener> crafttweaker$attachListener(List<PreparableReloadListener> original) {
        //TODO 1.20.5 make sure this works
        List<PreparableReloadListener> listeners = new ArrayList<>(original);
        listeners.add(new LootModifierManagerReloadListener());
        listeners.add(new ScriptReloadListenerAdapter(GenericUtil.uncheck(this)));
        // Lets keep it unmodifiable
        return Collections.unmodifiableList(listeners);
    }
    
}
