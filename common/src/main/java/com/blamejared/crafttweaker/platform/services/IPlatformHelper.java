package com.blamejared.crafttweaker.platform.services;

import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.ingredient.condition.IngredientConditions;
import com.blamejared.crafttweaker.api.ingredient.transformer.IngredientTransformers;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.loot.modifier.ILootModifier;
import com.blamejared.crafttweaker.api.mod.Mod;
import com.blamejared.crafttweaker.api.mod.PlatformMod;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.blamejared.crafttweaker.api.villager.trade.type.IBasicItemListing;
import com.blamejared.crafttweaker.mixin.common.access.item.AccessIngredient;
import com.blamejared.crafttweaker.platform.helper.inventory.IInventoryWrapper;
import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Either;
import net.minecraft.Optionull;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.material.Fluid;

import java.lang.annotation.Annotation;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface IPlatformHelper {
    
    /**
     * Fabric doesn't have an MC formatting sanitizer.
     */
    default String getLogFormat() {
        
        return "[%d{HH:mm:ss.SSS}][%level][%markerSimpleName]: %msg%n%throwable";
    }
    
    String getPlatformName();
    
    boolean isModLoaded(String modId);
    
    boolean isDevelopmentEnvironment();
    
    boolean isDataGen();
    
    List<Mod> getMods();
    
    Optional<Mod> getMod(String modid);
    
    IItemStack createItemStack(ItemStack stack, IngredientConditions conditions, IngredientTransformers transformers);
    
    IItemStack createItemStackMutable(ItemStack stack, IngredientConditions conditions, IngredientTransformers transformers);
    
    IFluidStack createFluidStack(Fluid fluid, long amount, DataComponentPatch patch);
    
    IFluidStack createFluidStackMutable(Fluid fluid, long amount, DataComponentPatch patch);
    
    <T> IFluidStack createFluidStack(T stack);
    
    <T> IFluidStack createFluidStackMutable(T stack);
    
    Fluid getBucketContent(BucketItem item);
    
    Path getGameDirectory();
    
    /**
     * Finds classes with the given annotation and applies a filter.
     *
     * @param annotationClass       The annotation class to look for.
     * @param classProviderConsumer Consumer to collect the given mod that added the class if available.
     * @param annotationFilter      A filter to apply to the search.
     *
     * @return A stream of classes with the annotation
     */
    <T extends Annotation> Stream<? extends Class<?>> findClassesWithAnnotation(
            final Class<T> annotationClass,
            final Consumer<PlatformMod> classProviderConsumer,
            final Predicate<Either<T, Map<String, Object>>> annotationFilter
    );
    
    default String findMappedMethodName(final Class<?> clazz, final String methodName, final Class<?> returnType, final Class<?>... parameterTypes) {
        
        return methodName;
    }
    
    default String findMappedFieldName(final Class<?> clazz, final String fieldName, final Class<?> fieldType) {
        
        return fieldName;
    }
    
    Map<ResourceLocation, ILootModifier> getLootModifiersMap();
    
    IInventoryWrapper getPlayerInventory(Player player);
    
    boolean doCraftingTableRecipesConflict(final IRecipeManager<?> manager, final Recipe<?> first, final Recipe<?> second);
    
    Set<MutableComponent> getFluidsForDump(ItemStack stack, Player player, InteractionHand hand);
    
    CompoundTag getCustomData(Entity entity);
    
    CompoundTag getPersistentData(ServerPlayer player);
    
    boolean doesIngredientRequireTesting(Ingredient ingredient);
    
    default void invalidateIngredients(List<Ingredient> ingredients) {
        
        ingredients.forEach(ingredient -> {
            ((AccessIngredient) (Object) ingredient).crafttweaker$setItemStacks(null);
        });
        ingredients.clear();
    }
    
    default IItemStack getRemainingItem(ItemStack stack) {
        
        return Optionull.mapOrDefault(stack.getItem()
                .getCraftingRemainingItem(), IItemStack::of, IItemStack.empty());
    }
    
    Ingredient getIngredientAny();
    
    Ingredient getIngredientList(List<Ingredient> children);
    
    Ingredient getCraftTweakerIngredient(IIngredient internal);
    
    Ingredient getIItemStackIngredient(IItemStack internal);
    
    boolean isCustomIngredient(Ingredient ingredient);
    
    default Stream<ItemStack> getCustomIngredientItems(Ingredient ingredient) {
        // Default for neoforge
        return Arrays.stream(ingredient.getItems());
    }
    
    Stream<GameProfile> fakePlayers();
    
    boolean isFakePlayer(Player player);
    
    default ItemStack getBasicTradePrice(IBasicItemListing internal) {
        
        throw new UnsupportedOperationException("Unable to get price for '%s'".formatted(internal));
    }
    
    default ItemStack getBasicTradePrice2(IBasicItemListing internal) {
        
        throw new UnsupportedOperationException("Unable to get price2 for '%s'".formatted(internal));
    }
    
    default ItemStack getBasicTradeForSale(IBasicItemListing internal) {
        
        throw new UnsupportedOperationException("Unable to get forSale for '%s'".formatted(internal));
    }
    
    default int getBasicTradeMaxTrades(IBasicItemListing internal) {
        
        throw new UnsupportedOperationException("Unable to get maxTrades for '%s'".formatted(internal));
    }
    
    default int getBasicTradeXp(IBasicItemListing internal) {
        
        throw new UnsupportedOperationException("Unable to get xp for '%s'".formatted(internal));
    }
    
    default float getBasicTradePriceMult(IBasicItemListing internal) {
        
        throw new UnsupportedOperationException("Unable to get priceMult for '%s'".formatted(internal));
    }
    
    FoodProperties.PossibleEffect createPossibleEffect(MobEffectInstance effect, float probability);
    
}
