package com.blamejared.crafttweaker.natives.recipe;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import org.openzen.zencode.java.ZenCodeType;

/**
 * Holds {@link Recipe} recipe and a {@link ResourceLocation} id.
 */
@ZenRegister
@Document("vanilla/api/recipe/RecipeHolder")
@NativeTypeRegistration(value = RecipeHolder.class, zenCodeName = "crafttweaker.api.recipe.RecipeHolder")
public class ExpandRecipeHolder {
    
    /**
     * Gets the id of this holder
     *
     * @return The id of this holder
     */
    @ZenCodeType.Caster(implicit = true)
    @ZenCodeType.Getter("id")
    public static ResourceLocation id(RecipeHolder<Recipe<RecipeInput>> internal) {
        
        return internal.id();
    }
    
    /**
     * Gets the recipe held by this holder.
     *
     * @return The recipe held by this holder.
     */
    @ZenCodeType.Caster(implicit = true)
    @ZenCodeType.Getter("value")
    public static Recipe<RecipeInput> value(RecipeHolder<Recipe<RecipeInput>> internal) {
        
        return internal.value();
    }
    
}
