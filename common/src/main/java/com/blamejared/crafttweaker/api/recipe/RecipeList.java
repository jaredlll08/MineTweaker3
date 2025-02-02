package com.blamejared.crafttweaker.api.recipe;

import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.logging.CommonLoggers;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.blamejared.crafttweaker.impl.helper.AccessibleElementsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * This class acts as a wrapper for the (currently) two recipe maps that vanilla stores recipes in:
 * The {@code recipes} map, which is used for actual recipe lookups.
 * The {@code byName} map, which is used when you have a recipe id, and want to get the recipe for that id, such as in the recipe book.
 *
 * @param <T> The base type of {@link Recipe} in this list.
 */
public class RecipeList<T extends Recipe<?>> {
    
    private final RecipeType<T> recipeType;
    private final Collection<RecipeHolder<T>> recipes;
    private final Map<ResourceLocation, RecipeHolder<T>> byName;
    
    private final Collection<RecipeHolder<T>> unmodifiableRecipes;
    private final Map<ResourceLocation, RecipeHolder<T>> unmodifiableByName;
    
    public RecipeList(RecipeType<T> recipeType, Collection<RecipeHolder<T>> recipes, Map<ResourceLocation, RecipeHolder<?>> byName) {
        
        this.recipeType = recipeType;
        this.recipes = recipes;
        this.byName = GenericUtil.uncheck(byName);
        this.unmodifiableRecipes = Collections.unmodifiableCollection(this.recipes);
        this.unmodifiableByName = Collections.unmodifiableMap(this.byName);
    }
    
    /**
     * Gets recipes based on the output item, checked against the given IIngredient.
     *
     * @param output The output of the recipes to get.
     *
     * @return A List of recipes who's output matches the given IIngredient.
     */
    public List<RecipeHolder<T>> getRecipesByOutput(IIngredient output) {
        
        return AccessibleElementsProvider.get().registryAccess(registryAccess -> getRecipes().stream()
                .filter(recipe -> output.matches(IItemStack.of(recipe.value().getResultItem(registryAccess))))
                .toList());
    }
    
    /**
     * Gets recipes that match the predicate.
     *
     * @param predicate A predicate of {@link RecipeHolder<T>} to test recipes against.
     *
     * @return A List of recipes that match the given predicate
     */
    public List<RecipeHolder<T>> getRecipesMatching(Predicate<RecipeHolder<T>> predicate) {
        
        return getRecipes()
                .stream()
                .filter(predicate)
                .toList();
    }
    
    /**
     * Gets a recipe based on the given id.
     *
     * @param id The resource location id of the recipe.
     *
     * @return Teh found recipe or null if not found.
     */
    public RecipeHolder<T> get(ResourceLocation id) {
        
        return getByName().get(id);
    }
    
    /**
     * Gets a recipe based on the given id.
     *
     * @param id The string id of the recipe.
     *
     * @return Teh found recipe or null if not found.
     */
    public RecipeHolder<T> get(String id) {
        
        return get(ResourceLocation.tryParse(id));
    }
    
    /**
     * Checks if this list contains a recipe with the given key.
     *
     * @param id The resource location id of the recipe.
     *
     * @return True if the list has the recipe, false otherwise.
     */
    public boolean has(ResourceLocation id) {
        
        return getByName().containsKey(id);
    }
    
    /**
     * Checks if this list contains a recipe with the given key.
     *
     * @param id The string id of the recipe.
     *
     * @return True if the list has the recipe, false otherwise.
     */
    public boolean has(String id) {
        
        return has(ResourceLocation.tryParse(id));
    }
    
    /**
     * Adds the given recipe to this list.
     *
     * @param id     The id of the recipe.
     * @param recipe The recipe to add.
     */
    public void add(ResourceLocation id, RecipeHolder<T> recipe) {
        
        if(getByName().containsKey(recipe.id())) {
            CommonLoggers.api().warn(
                    "A recipe with the name '{}' already exists and will be overwritten: this is most likely an error in your scripts",
                    recipe.id().getPath()
            );
        }
        
        recipes.add(recipe);
        byName.put(id, recipe);
    }
    
    /**
     * Remove the recipe with the given id.
     *
     * @param id The id of the recipe to remove.
     */
    public void remove(ResourceLocation id) {
        
        recipes.removeIf(tRecipeHolder -> tRecipeHolder.id().equals(id));
        byName.remove(id);
    }
    
    /**
     * Removes recipes that pass the given recipe Predicate
     *
     * @param recipePredicate The predicate to check the recipes against.
     */
    public void removeByRecipeTest(Predicate<RecipeHolder<T>> recipePredicate) {
        
        Iterator<RecipeHolder<T>> iterator = recipes.iterator();
        
        while(iterator.hasNext()) {
            RecipeHolder<T> next = iterator.next();
            if(recipePredicate.test(next)) {
                byName.remove(next.id());
                iterator.remove();
            }
        }
    }
    
    /**
     * Removes recipes that pass the given id Predicate.
     *
     * @param idPredicate The predicate to check the recipe id against.
     */
    public void removeByIdTest(Predicate<ResourceLocation> idPredicate) {
        
        removeByIdTest(idPredicate, s -> false);
    }
    
    /**
     * Removes recipes that pass the given id Predicate.
     *
     * @param idPredicate The predicate to check the recipe id against.
     * @param exclusions  A predicate to exclude certain recipes from removal.
     */
    public void removeByIdTest(Predicate<ResourceLocation> idPredicate, Predicate<String> exclusions) {
        
        Iterator<RecipeHolder<T>> iterator = recipes.iterator();
        
        while(iterator.hasNext()) {
            ResourceLocation next = iterator.next().id();
            if(idPredicate.test(next) && !exclusions.test(next.getPath())) {
                byName.remove(next);
                iterator.remove();
            }
        }
    }
    
    /**
     * Removes all recipes in this list.
     */
    public void removeAll() {
        
        byName.values().removeAll(recipes);
        recipes.clear();
    }
    
    /**
     * Gets the recipe type that this list deals with.
     *
     * @return The recipe type that this list deals with.
     */
    public RecipeType<T> getRecipeType() {
        
        return recipeType;
    }
    
    /**
     * Gets an unmodifiable view of the recipes in this list.
     *
     * @return An unmodifiable view of the recipes in this list.
     */
    public Collection<RecipeHolder<T>> getRecipes() {
        
        return unmodifiableRecipes;
    }
    
    /**
     * Gets an unmodifiable view of the byName map in this list.
     *
     * @return An unmodifiable view of the byName map in this list.
     */
    public Map<ResourceLocation, RecipeHolder<T>> getByName() {
        
        return unmodifiableByName;
    }
    
    /**
     * Gets how many recipes are in this list.
     *
     * @return How many recipes are in this list.
     */
    public int getSize() {
        
        return getRecipes().size();
    }
    
}
