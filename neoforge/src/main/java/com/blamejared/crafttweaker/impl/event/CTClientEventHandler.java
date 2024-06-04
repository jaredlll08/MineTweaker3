package com.blamejared.crafttweaker.impl.event;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.entity.NameTagResult;
import com.blamejared.crafttweaker.api.logging.CommonLoggers;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.impl.script.RecipeManagerScriptLoader;
import com.blamejared.crafttweaker.natives.entity.ExpandEntityType;
import com.blamejared.crafttweaker.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RecipesUpdatedEvent;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.function.Predicate;

@EventBusSubscriber(value = Dist.CLIENT, modid = CraftTweakerConstants.MOD_ID)
public class CTClientEventHandler {
    
    @SubscribeEvent
    public static void onRecipesUpdated(RecipesUpdatedEvent event) {
        
        RecipeManagerScriptLoader.updateState(RecipeManagerScriptLoader.UpdatedState.RECIPES, event::getRecipeManager);
    }
    
    @SubscribeEvent
    public static void handleTooltips(ItemTooltipEvent e) {
        
        Services.CLIENT.applyTooltips(e.getItemStack(), e.getContext(), e.getFlags(), e.getToolTip());
    }
    
    @SubscribeEvent
    public static void nameTag(RenderNameTagEvent e) {
        
        Entity entity = e.getEntity();
        TriState canRender = e.canRender();
        Component content = e.getContent();
        Component originalContent = e.getOriginalContent();
        for(Predicate<Entity> predicate : Services.CLIENT.NAMETAGS.keySet()) {
            if(predicate.test(entity)) {
                try {
                    NameTagResult nameTagResult = new NameTagResult(canRender.isTrue(), content, originalContent);
                    Services.CLIENT.NAMETAGS.get(predicate).apply(entity, nameTagResult);
                    e.setCanRender(nameTagResult.getResult() == null ? TriState.DEFAULT : nameTagResult.getResult() ? TriState.TRUE : TriState.FALSE);
                    e.setContent(nameTagResult.getContent());
                } catch(final Exception exception) {
                    CommonLoggers.api().error(
                            "Unable to run one of the name tag functions for {} due to an error (for experts, refer to {})",
                            ExpandEntityType.getCommandString(GenericUtil.uncheck(entity.getType())),
                            Services.CLIENT.NAMETAGS.get(predicate).getClass().getName()
                            , exception
                    );
                }
            }
        }
    }
    
}
