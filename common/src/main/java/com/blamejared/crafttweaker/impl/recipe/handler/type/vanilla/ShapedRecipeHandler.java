package com.blamejared.crafttweaker.impl.recipe.handler.type.vanilla;

import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.component.BuiltinRecipeComponents;
import com.blamejared.crafttweaker.api.recipe.component.IDecomposedRecipe;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.handler.helper.CraftingTableRecipeConflictChecker;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.util.ItemStackUtil;
import com.blamejared.crafttweaker.api.util.RecipeUtil;
import com.blamejared.crafttweaker.api.util.StringUtil;
import com.blamejared.crafttweaker.platform.Services;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.ShapedRecipe;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@IRecipeHandler.For(ShapedRecipe.class)
public final class ShapedRecipeHandler implements IRecipeHandler<ShapedRecipe> {
    
    @Override
    public String dumpToCommandString(final IRecipeManager<? super ShapedRecipe> manager, final RegistryAccess registryAccess, final RecipeHolder<ShapedRecipe> holder) {
        
        ShapedRecipe recipe = holder.value();
        final NonNullList<Ingredient> ingredients = recipe.getIngredients();
        return String.format(
                "craftingTable.addShaped(%s, %s, %s);",
                StringUtil.quoteAndEscape(holder.id()),
                ItemStackUtil.getCommandString(recipe.getResultItem(registryAccess)),
                IntStream.range(0, recipe.getHeight())
                        .mapToObj(y -> IntStream.range(0, recipe.getWidth())
                                .mapToObj(x -> ingredients.get(y * recipe.getWidth() + x))
                                .map(IIngredient::fromIngredient)
                                .map(IIngredient::getCommandString)
                                .collect(Collectors.joining(", ", "[", "]")))
                        .collect(Collectors.joining(", ", "[", "]"))
        );
    }
    
    @Override
    public <U extends Recipe<?>> boolean doesConflict(final IRecipeManager<? super ShapedRecipe> manager, final ShapedRecipe firstRecipe, final U secondRecipe) {
        
        return CraftingTableRecipeConflictChecker.checkConflicts(manager, firstRecipe, secondRecipe);
    }
    
    @Override
    public Optional<IDecomposedRecipe> decompose(final IRecipeManager<? super ShapedRecipe> manager, final RegistryAccess registryAccess, final ShapedRecipe recipe) {
        
        final List<IIngredient> ingredients = recipe.getIngredients().stream()
                .map(IIngredient::fromIngredient)
                .toList();
        final IDecomposedRecipe decomposedRecipe = IDecomposedRecipe.builder()
                .with(BuiltinRecipeComponents.Metadata.GROUP, recipe.getGroup())
                .with(BuiltinRecipeComponents.Metadata.CRAFTING_BOOK_CATEGORY, recipe.category())
                .with(BuiltinRecipeComponents.Metadata.SHAPE_SIZE_2D, Pair.of(recipe.getWidth(), recipe.getHeight()))
                .with(BuiltinRecipeComponents.Input.INGREDIENTS, ingredients)
                .with(BuiltinRecipeComponents.Output.ITEMS, IItemStack.of(recipe.getResultItem(registryAccess)))
                .build();
        return Optional.of(decomposedRecipe);
    }
    
    @Override
    public Optional<ShapedRecipe> recompose(final IRecipeManager<? super ShapedRecipe> manager, final RegistryAccess registryAccess, final IDecomposedRecipe recipe) {
        
        final String group = recipe.getOrThrowSingle(BuiltinRecipeComponents.Metadata.GROUP);
        final CraftingBookCategory category = recipe.getOrThrowSingle(BuiltinRecipeComponents.Metadata.CRAFTING_BOOK_CATEGORY);
        final Pair<Integer, Integer> size = recipe.getOrThrowSingle(BuiltinRecipeComponents.Metadata.SHAPE_SIZE_2D);
        final List<IIngredient> ingredients = recipe.getOrThrow(BuiltinRecipeComponents.Input.INGREDIENTS);
        final IItemStack output = recipe.getOrThrowSingle(BuiltinRecipeComponents.Output.ITEMS);
        
        final int width = size.getFirst();
        final int height = size.getSecond();
        
        if(width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Invalid shape size: bounds must be positive but got " + size);
        }
        if(width * height != ingredients.size()) {
            throw new IllegalArgumentException("Invalid shape size: incompatible with ingredients, got " + size + " with " + ingredients.size());
        }
        if(output.isEmpty()) {
            throw new IllegalArgumentException("Invalid output: empty item");
        }
        
        final NonNullList<Ingredient> recipeIngredients = ingredients.stream()
                .map(IIngredient::asVanillaIngredient)
                .collect(NonNullList::create, NonNullList::add, NonNullList::addAll);
        return Optional.of(new ShapedRecipe(group, category, RecipeUtil.createPattern(recipeIngredients, width, height), output.getInternal()));
    }
    
}
