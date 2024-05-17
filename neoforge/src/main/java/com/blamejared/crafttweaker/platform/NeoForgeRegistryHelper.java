package com.blamejared.crafttweaker.platform;

import com.blamejared.crafttweaker.CraftTweakerRegistries;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.command.argument.IItemStackArgument;
import com.blamejared.crafttweaker.api.command.argument.RecipeTypeArgument;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionAnyDamagedSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionCustomSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionDamagedAtMostSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionDamagedSerializer;
import com.blamejared.crafttweaker.api.ingredient.transformer.serializer.TransformCustomSerializer;
import com.blamejared.crafttweaker.api.ingredient.transformer.serializer.TransformDamageSerializer;
import com.blamejared.crafttweaker.api.ingredient.transformer.serializer.TransformReplaceSerializer;
import com.blamejared.crafttweaker.api.ingredient.transformer.serializer.TransformReuseSerializer;
import com.blamejared.crafttweaker.api.ingredient.type.IIngredientAny;
import com.blamejared.crafttweaker.api.ingredient.type.IIngredientList;
import com.blamejared.crafttweaker.api.ingredient.vanilla.CraftTweakerIngredients;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.serializer.CTShapedRecipeSerializer;
import com.blamejared.crafttweaker.api.recipe.serializer.CTShapelessRecipeSerializer;
import com.blamejared.crafttweaker.impl.loot.condition.LootTableIdRegexCondition;
import com.blamejared.crafttweaker.impl.script.ScriptRecipeType;
import com.blamejared.crafttweaker.impl.script.ScriptSerializer;
import com.blamejared.crafttweaker.platform.services.IRegistryHelper;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.function.Consumer;

public class NeoForgeRegistryHelper implements IRegistryHelper {
    
    public static void init(IEventBus modBus) {
        
        modBus
                .addListener((Consumer<NewRegistryEvent>) newRegistry -> CraftTweakerRegistries.init());
        
        // Thank you (neo)forge, very cool.
        modBus.addListener((Consumer<RegisterEvent>) event -> {
            if(Registries.BLOCK.equals(event.getRegistryKey())) {
                Registry.register(
                        BuiltInRegistries.LOOT_CONDITION_TYPE,
                        CraftTweakerConstants.rl("loot_table_id_regex"),
                        LootTableIdRegexCondition.LOOT_TABLE_ID_REGEX
                );
            } else if(Registries.RECIPE_SERIALIZER.equals(event.getRegistryKey())) {
                event.register(Registries.RECIPE_SERIALIZER, helper -> {
                    helper.register(CraftTweakerConstants.rl("shapeless"), CTShapelessRecipeSerializer.INSTANCE);
                    helper.register(CraftTweakerConstants.rl("shaped"), CTShapedRecipeSerializer.INSTANCE);
                    helper.register(CraftTweakerConstants.rl("script"), ScriptSerializer.INSTANCE);
                });
            } else if(Registries.RECIPE_TYPE.equals(event.getRegistryKey())) {
                event.register(Registries.RECIPE_TYPE, helper -> {
                    helper.register(ScriptRecipeType.INSTANCE.id(), ScriptRecipeType.INSTANCE);
                });
            } else if(Registries.COMMAND_ARGUMENT_TYPE.equals(event.getRegistryKey())) {
                event.register(Registries.COMMAND_ARGUMENT_TYPE, helper -> {
                    helper.register(RecipeTypeArgument.ID, ArgumentTypeInfos.registerByClass(RecipeTypeArgument.class, SingletonArgumentInfo.contextFree(RecipeTypeArgument::get)));
                    helper.register(IItemStackArgument.ID, ArgumentTypeInfos.registerByClass(IItemStackArgument.class, SingletonArgumentInfo.contextFree(IItemStackArgument::get)));
                });
            } else if(NeoForgeRegistries.Keys.INGREDIENT_TYPES.equals(event.getRegistryKey())) {
                event.register(NeoForgeRegistries.Keys.INGREDIENT_TYPES, helper -> {
                    helper.register(IIngredientAny.ID, CraftTweakerIngredients.Types.ANY);
                    helper.register(IIngredientList.ID, CraftTweakerIngredients.Types.LIST);
                    helper.register(IIngredient.INGREDIENT_ID, CraftTweakerIngredients.Types.CRAFTTWEAKER);
                    helper.register(IItemStack.INGREDIENT_ID, CraftTweakerIngredients.Types.IITEMSTACK);
                });
            } else if(CraftTweakerRegistries.Keys.TRANSFORMER_SERIALIZER.equals(event.getRegistryKey())) {
                event.register(CraftTweakerRegistries.Keys.TRANSFORMER_SERIALIZER, helper -> {
                    helper.register(TransformReplaceSerializer.INSTANCE.getType(), TransformReplaceSerializer.INSTANCE);
                    helper.register(TransformDamageSerializer.INSTANCE.getType(), TransformDamageSerializer.INSTANCE);
                    helper.register(TransformCustomSerializer.INSTANCE.getType(), TransformCustomSerializer.INSTANCE);
                    helper.register(TransformReuseSerializer.INSTANCE.getType(), TransformReuseSerializer.INSTANCE);
                });
            } else if(CraftTweakerRegistries.Keys.CONDITIONER_SERIALIZER.equals(event.getRegistryKey())) {
                event.register(CraftTweakerRegistries.Keys.CONDITIONER_SERIALIZER, helper -> {
                    helper.register(ConditionDamagedSerializer.INSTANCE.getType(), ConditionDamagedSerializer.INSTANCE);
                    helper.register(ConditionAnyDamagedSerializer.INSTANCE.getType(), ConditionAnyDamagedSerializer.INSTANCE);
                    helper.register(ConditionCustomSerializer.INSTANCE.getType(), ConditionCustomSerializer.INSTANCE);
                    helper.register(ConditionDamagedAtMostSerializer.INSTANCE.getType(), ConditionDamagedAtMostSerializer.INSTANCE);
                });
            }
        });
        
    }
    
    @Override
    public <T> Registry<T> makeRegistry(ResourceKey<Registry<T>> resourceKey) {
        
        return new RegistryBuilder<>(resourceKey).sync(true).create();
    }
    
}
