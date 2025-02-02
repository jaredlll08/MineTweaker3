package com.blamejared.crafttweaker.api.recipe.type;

import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.logging.CommonLoggers;
import com.blamejared.crafttweaker.api.recipe.fun.RecipeFunction1D;
import com.blamejared.crafttweaker.api.recipe.serializer.CTShapelessRecipeSerializer;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;

public class CTShapelessRecipe extends ShapelessRecipe {
    
    private final IIngredient[] ctIngredients;
    private final IItemStack output;
    @Nullable
    private final RecipeFunction1D function;
    
    public CTShapelessRecipe(IItemStack output, IIngredient[] ingredients) {
        
        this(CraftingBookCategory.MISC, output, ingredients, null);
    }
    
    public CTShapelessRecipe(IItemStack output, IIngredient[] ingredients, @Nullable RecipeFunction1D function) {
        
        this(CraftingBookCategory.MISC, output, ingredients, function);
    }
    
    public CTShapelessRecipe(CraftingBookCategory category, IItemStack output, IIngredient[] ingredients, @Nullable RecipeFunction1D function) {
        
        super("", category, output.getInternal(), NonNullList.create());
        
        this.output = output;
        this.function = function;
        
        boolean containsNull = false;
        for(IIngredient ingredient : ingredients) {
            if(ingredient == null || ingredient.asVanillaIngredient().isEmpty()) {
                containsNull = true;
                break;
            }
        }
        if(containsNull) {
            ingredients = Arrays.stream(ingredients)
                    .filter(Objects::nonNull)
                    .filter(iIngredient -> !iIngredient.asVanillaIngredient().isEmpty())
                    .toArray(IIngredient[]::new);
        }
        this.ctIngredients = ingredients;
        this.getIngredients().addAll(Arrays.stream(this.ctIngredients).map(IIngredient::asVanillaIngredient).toList());
    }
    
    public static boolean checkEmptyIngredient(ResourceLocation name, IIngredient[] ingredients) {
        
        for(IIngredient ingredient : ingredients) {
            if(ingredient == null || ingredient.asVanillaIngredient().isEmpty()) {
                CommonLoggers.api()
                        .warn("Shapeless recipe with ID '{}' contains null or empty ingredients, entries will be removed!", name);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean matches(CraftingInput inv, Level worldIn) {
        //Don't do anything here, just make sure all slots have been visited
        final boolean[] visited = forAllUniqueMatches(inv, (ingredientIndex, matchingSlot, stack) -> {});
        
        int visitedCount = 0;
        for(int slot = 0; slot < visited.length; slot++) {
            if(visited[slot]) {
                visitedCount++;
            } else if(!inv.getItem(slot).isEmpty()) {
                return false;
            }
        }
        return visitedCount == this.ctIngredients.length;
    }
    
    
    @Override
    public ItemStack assemble(CraftingInput inv, HolderLookup.Provider lookup) {
        
        if(this.function == null) {
            return this.output.getInternal().copy();
        }
        
        final IItemStack[] stacks = new IItemStack[this.ctIngredients.length];
        
        forAllUniqueMatches(inv, (ingredientIndex, matchingSlot, stack) -> stacks[ingredientIndex] = stack.withAmount(1));
        
        return this.function.process(this.output, stacks).getImmutableInternal();
    }
    
    
    @Nullable
    public RecipeFunction1D getFunction() {
        
        return function;
    }
    
    @Override
    public ItemStack getResultItem(HolderLookup.Provider lookup) {
        
        return output.getInternal().copy();
    }
    
    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput inv) {
        
        final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.size(), ItemStack.EMPTY);
        forAllUniqueMatches(inv, (ingredientIndex, matchingSlot, stack) -> remainingItems.set(matchingSlot, this.ctIngredients[ingredientIndex]
                .getRemainingItem(stack)
                .getInternal()));
        return remainingItems;
    }
    
    /**
     * Helper method to avoid redundant code.
     * performs the Action for all matched items.
     *
     * Returns a bool array which slots have been visited, false implies the slot was not used.
     * If there are less 'true' slots than there are ingredients, then the recipe doesn't match.
     * It can be possible for a slot to not be visited but still contain items.
     * Both cases need to be checked in the matches function!
     */
    private boolean[] forAllUniqueMatches(CraftingInput inv, ForAllUniqueAction action) {
        
        final boolean[] visited = new boolean[inv.size()];
        
        outer:
        for(int ingredientIndex = 0; ingredientIndex < this.ctIngredients.length; ingredientIndex++) {
            IIngredient ingredient = this.ctIngredients[ingredientIndex];
            for(int i = 0; i < inv.size(); i++) {
                if(visited[i]) {
                    continue;
                }
                
                final ItemStack stackInSlot = inv.getItem(i);
                if(stackInSlot.isEmpty()) {
                    continue;
                }
                
                final IItemStack stack = IItemStack.of(stackInSlot);
                if(ingredient.matches(stack)) {
                    visited[i] = true;
                    action.accept(ingredientIndex, i, stack);
                    continue outer;
                }
            }
        }
        return visited;
    }
    
    @Override
    public NonNullList<Ingredient> getIngredients() {
        
        NonNullList<Ingredient> ingredients = NonNullList.create();
        for(IIngredient ingredient : this.ctIngredients) {
            ingredients.add(ingredient.asVanillaIngredient());
        }
        return ingredients;
    }
    
    public NonNullList<IIngredient> getFlatCtIngredients() {
        NonNullList<IIngredient> ingredients = NonNullList.create();
        ingredients.addAll(Arrays.asList(this.ctIngredients));
        return ingredients;
    }
    
    @Override
    public RecipeSerializer<CTShapelessRecipe> getSerializer() {
        
        return CTShapelessRecipeSerializer.INSTANCE;
    }
    
    public IIngredient[] getCtIngredients() {
        
        return this.ctIngredients;
    }
    
    public IItemStack getCtOutput() {
        
        return this.output;
    }
    
    @FunctionalInterface
    private interface ForAllUniqueAction {
        
        void accept(int ingredientIndex, int matchingSlot, IItemStack stack);
        
    }
    
    @Override
    public boolean equals(Object o) {
        
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        CTShapelessRecipe that = (CTShapelessRecipe) o;
        return Arrays.equals(ctIngredients, that.ctIngredients) && Objects.equals(output, that.output) && Objects.equals(function, that.function);
    }
    
    @Override
    public int hashCode() {
        
        int result = Objects.hash(output, function);
        result = 31 * result + Arrays.hashCode(ctIngredients);
        return result;
    }
    
}
