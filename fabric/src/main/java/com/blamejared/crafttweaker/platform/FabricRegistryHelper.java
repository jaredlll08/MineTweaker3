package com.blamejared.crafttweaker.platform;

import com.blamejared.crafttweaker.CraftTweakerRegistries;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.command.argument.IItemStackArgument;
import com.blamejared.crafttweaker.api.command.argument.RecipeTypeArgument;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionAnyDamagedSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionCustomSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionDamagedAtLeastSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionDamagedAtMostSerializer;
import com.blamejared.crafttweaker.api.ingredient.condition.serializer.ConditionDamagedSerializer;
import com.blamejared.crafttweaker.api.ingredient.transformer.serializer.TransformCustomSerializer;
import com.blamejared.crafttweaker.api.ingredient.transformer.serializer.TransformDamageSerializer;
import com.blamejared.crafttweaker.api.ingredient.transformer.serializer.TransformReplaceSerializer;
import com.blamejared.crafttweaker.api.ingredient.transformer.serializer.TransformReuseSerializer;
import com.blamejared.crafttweaker.api.ingredient.vanilla.CraftTweakerIngredients;
import com.blamejared.crafttweaker.api.recipe.serializer.CTShapedRecipeSerializer;
import com.blamejared.crafttweaker.api.recipe.serializer.CTShapelessRecipeSerializer;
import com.blamejared.crafttweaker.impl.loot.condition.LootTableIdCondition;
import com.blamejared.crafttweaker.impl.loot.condition.LootTableIdRegexCondition;
import com.blamejared.crafttweaker.impl.script.ScriptRecipeType;
import com.blamejared.crafttweaker.impl.script.ScriptSerializer;
import com.blamejared.crafttweaker.platform.services.IRegistryHelper;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredientSerializer;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class FabricRegistryHelper implements IRegistryHelper {
    
    public static void init() {
        
        Registry.register(BuiltInRegistries.RECIPE_TYPE, ScriptRecipeType.INSTANCE.id(), ScriptRecipeType.INSTANCE);
        
        CraftTweakerRegistries.init();
        
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, CraftTweakerConstants.rl("shapeless"), CTShapelessRecipeSerializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, CraftTweakerConstants.rl("shaped"), CTShapedRecipeSerializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, CraftTweakerConstants.rl("script"), ScriptSerializer.INSTANCE);
        
        Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, CraftTweakerConstants.rl("table_id"), LootTableIdCondition.LOOT_TABLE_ID);
        Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, CraftTweakerConstants.rl("regex_table_id"), LootTableIdRegexCondition.LOOT_TABLE_ID_REGEX);
        
        CustomIngredientSerializer.register(CraftTweakerIngredients.Serializers.ANY);
        CustomIngredientSerializer.register(CraftTweakerIngredients.Serializers.LIST);
        CustomIngredientSerializer.register(CraftTweakerIngredients.Serializers.CRAFTTWEAKER);
        CustomIngredientSerializer.register(CraftTweakerIngredients.Serializers.IITEMSTACK);
        
        
        ArgumentTypeRegistry.registerArgumentType(RecipeTypeArgument.ID, RecipeTypeArgument.class, SingletonArgumentInfo.contextFree(RecipeTypeArgument::get));
        ArgumentTypeRegistry.registerArgumentType(IItemStackArgument.ID, IItemStackArgument.class, SingletonArgumentInfo.contextFree(IItemStackArgument::get));
        
        Registry.register(CraftTweakerRegistries.TRANSFORMER_SERIALIZER, TransformReplaceSerializer.INSTANCE.getType(), TransformReplaceSerializer.INSTANCE);
        Registry.register(CraftTweakerRegistries.TRANSFORMER_SERIALIZER, TransformDamageSerializer.INSTANCE.getType(), TransformDamageSerializer.INSTANCE);
        Registry.register(CraftTweakerRegistries.TRANSFORMER_SERIALIZER, TransformCustomSerializer.INSTANCE.getType(), TransformCustomSerializer.INSTANCE);
        Registry.register(CraftTweakerRegistries.TRANSFORMER_SERIALIZER, TransformReuseSerializer.INSTANCE.getType(), TransformReuseSerializer.INSTANCE);
        
        Registry.register(CraftTweakerRegistries.CONDITIONER_SERIALIZER, ConditionDamagedSerializer.INSTANCE.getType(), ConditionDamagedSerializer.INSTANCE);
        Registry.register(CraftTweakerRegistries.CONDITIONER_SERIALIZER, ConditionAnyDamagedSerializer.INSTANCE.getType(), ConditionAnyDamagedSerializer.INSTANCE);
        Registry.register(CraftTweakerRegistries.CONDITIONER_SERIALIZER, ConditionCustomSerializer.INSTANCE.getType(), ConditionCustomSerializer.INSTANCE);
        Registry.register(CraftTweakerRegistries.CONDITIONER_SERIALIZER, ConditionDamagedAtMostSerializer.INSTANCE.getType(), ConditionDamagedAtMostSerializer.INSTANCE);
        Registry.register(CraftTweakerRegistries.CONDITIONER_SERIALIZER, ConditionDamagedAtLeastSerializer.INSTANCE.getType(), ConditionDamagedAtLeastSerializer.INSTANCE);
    }
    
}
