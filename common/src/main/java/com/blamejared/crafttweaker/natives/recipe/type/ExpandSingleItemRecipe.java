package com.blamejared.crafttweaker.natives.recipe.type;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.crafting.SingleItemRecipe;

@ZenRegister
@Document("vanilla/api/recipe/type/SingleItemRecipe")
@NativeTypeRegistration(value = SingleItemRecipe.class, zenCodeName = "crafttweaker.api.recipe.type.SingleItemRecipe")
public class ExpandSingleItemRecipe {

}
