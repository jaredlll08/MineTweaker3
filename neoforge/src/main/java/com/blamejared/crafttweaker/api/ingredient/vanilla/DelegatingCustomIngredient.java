package com.blamejared.crafttweaker.api.ingredient.vanilla;

import com.blamejared.crafttweaker.api.ingredient.vanilla.serializer.CraftTweakerVanillaIngredientSerializer;
import com.blamejared.crafttweaker.api.ingredient.vanilla.type.CraftTweakerVanillaIngredient;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.Util;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.crafting.ICustomIngredient;
import net.neoforged.neoforge.common.crafting.IngredientType;

import java.util.function.Function;
import java.util.stream.Stream;

@MethodsReturnNonnullByDefault
record DelegatingCustomIngredient<T extends CraftTweakerVanillaIngredient>(T internal) implements ICustomIngredient {
    
    public static final Function<CraftTweakerVanillaIngredientSerializer<CraftTweakerVanillaIngredient>, IngredientType<DelegatingCustomIngredient<CraftTweakerVanillaIngredient>>> CODEC_CACHE = Util.memoize((internal) -> new IngredientType<>(internal.codec()
            .xmap(CraftTweakerIngredients.Ingredients::of, DelegatingCustomIngredient::internal), internal.streamCodec()
            .map(CraftTweakerIngredients.Ingredients::of, DelegatingCustomIngredient::internal)));
    
    @Override
    public boolean test(ItemStack stack) {
        
        return internal.test(stack);
    }
    
    @Override
    public Stream<ItemStack> getItems() {
        
        return internal.getMatchingStacks().stream();
    }
    
    @Override
    public boolean isSimple() {
        
        return !internal.requiresTesting();
    }
    
    @Override
    public IngredientType<?> getType() {
        
        return CraftTweakerIngredients.Types.of(internal.serializer());
    }
    
    public static <T extends CraftTweakerVanillaIngredient> IngredientType<DelegatingCustomIngredient<T>> ingredientType(CraftTweakerVanillaIngredientSerializer<T> serializer) {
        
        return GenericUtil.uncheck(CODEC_CACHE.apply(GenericUtil.uncheck(serializer)));
    }
    
}
