package com.blamejared.crafttweaker.api.recipe.serializer;

import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.fun.RecipeFunction1D;
import com.blamejared.crafttweaker.api.recipe.type.CTShapelessRecipe;
import com.blamejared.crafttweaker.impl.helper.AccessibleElementsProvider;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;


public class CTShapelessRecipeSerializer implements RecipeSerializer<CTShapelessRecipe> {
    
    public static final CTShapelessRecipeSerializer INSTANCE = new CTShapelessRecipeSerializer();
    
    private CTShapelessRecipeSerializer() {}
    
    private static NonNullList<Ingredient> readIngredients(JsonArray p_199568_0_) {
        
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        
        for(int i = 0; i < p_199568_0_.size(); ++i) {
            Ingredient ingredient = Ingredient.fromJson(p_199568_0_.get(i));
            if(!ingredient.isEmpty()) {
                nonnulllist.add(ingredient);
            }
        }
        
        return nonnulllist;
    }
    
    @Override
    public CTShapelessRecipe fromJson(ResourceLocation recipeId, JsonObject jsonObject) {
        
        NonNullList<Ingredient> nonnulllist = readIngredients(GsonHelper.getAsJsonArray(jsonObject, "ingredients"));
        IIngredient[] ingredients = new IIngredient[nonnulllist.size()];
        for(int i = 0; i < nonnulllist.size(); i++) {
            ingredients[i] = IIngredient.fromIngredient(nonnulllist.get(i));
        }
        if(nonnulllist.isEmpty()) {
            throw new JsonParseException("No ingredients for shapeless recipe");
        } else if(nonnulllist.size() > 3 * 3) {
            throw new JsonParseException("Too many ingredients for shapeless recipe the max is " + (3 * 3));
        } else {
            ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
            return makeRecipe(recipeId, IItemStack.of(itemstack), ingredients, null);
        }
    }
    
    @Override
    public CTShapelessRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        
        int i = buffer.readVarInt();
        IIngredient[] ingredients = new IIngredient[i];
        
        for(int j = 0; j < ingredients.length; ++j) {
            ingredients[j] = IIngredient.fromIngredient(Ingredient.fromNetwork(buffer));
        }
        
        ItemStack output = buffer.readItem();
        return makeRecipe(recipeId, IItemStack.of(output), ingredients, null);
    }
    
    @Override
    public void toNetwork(FriendlyByteBuf buffer, CTShapelessRecipe recipe) {
        
        
        buffer.writeVarInt(recipe.getIngredients().size());
        for(Ingredient ingredient : recipe.getIngredients()) {
            ingredient.toNetwork(buffer);
        }
        buffer.writeItem(AccessibleElementsProvider.get().registryAccess(recipe::getResultItem));
    }
    
    public CTShapelessRecipe makeRecipe(ResourceLocation recipeId, IItemStack output, IIngredient[] ingredients, @javax.annotation.Nullable RecipeFunction1D function) {
        
        return new CTShapelessRecipe(recipeId.getPath(), output, ingredients, function);
    }
    
}