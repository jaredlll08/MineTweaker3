package com.blamejared.crafttweaker.mixin.common.transform.loot;

import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.impl.loot.ILootTableIdHolder;
import com.blamejared.crafttweaker.natives.loot.table.ExpandLootTable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(LootTable.class)
public abstract class MixinLootTable {
    
    @Inject(method = "getRandomItemsRaw(Lnet/minecraft/world/level/storage/loot/LootContext;Ljava/util/function/Consumer;)V", at = @At("HEAD"))
    private void crafttweaker$getRandomItemsRaw$populateContextWithTableId(final LootContext context, final Consumer<ItemStack> $$1, final CallbackInfo ci) {
        
        GenericUtil.<ILootTableIdHolder.Mutable> uncheck(context)
                .crafttweaker$tableId(ExpandLootTable.getId(GenericUtil.uncheck(this)));
    }
    
}
