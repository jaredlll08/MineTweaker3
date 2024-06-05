package com.blamejared.crafttweaker.natives.ingredient;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.ingredient.IIngredientWithAmount;
import com.blamejared.crafttweaker.api.ingredient.type.IngredientWithAmount;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.tag.type.KnownTag;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.crafting.SizedIngredient;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;

@ZenRegister
@Document("neoforge/api/ingredient/SizedIngredient")
@NativeTypeRegistration(value = SizedIngredient.class, zenCodeName = "crafttweaker.neoforge.api.item.SizedIngredient")
public class ExpandSizedIngredient {
    
    @ZenCodeType.StaticExpansionMethod
    public static SizedIngredient of(KnownTag<Item> tag, int count) {
        
        return SizedIngredient.of(tag.<TagKey<Item>> getTagKey(), count);
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static SizedIngredient of(ItemLike item, int count) {
        
        return SizedIngredient.of(item, count);
    }
    
    @ZenCodeType.StaticExpansionMethod
    public static SizedIngredient of(IIngredient ingredient, int count) {
        
        return new SizedIngredient(ingredient.asVanillaIngredient(), count);
    }
    
    @ZenCodeType.Getter("count")
    public static int count(SizedIngredient internal) {
        
        return internal.count();
    }
    
    @ZenCodeType.Method
    public static boolean test(SizedIngredient internal, IItemStack stack) {
        
        return internal.test(stack.getInternal());
    }
    
    @ZenCodeType.Getter("ingredient")
    public static IIngredient ingredient(SizedIngredient internal) {
        
        return IIngredient.fromIngredient(internal.ingredient());
    }
    
    @ZenCodeType.Getter("items")
    public static IItemStack[] getItems(SizedIngredient internal) {
        
        return Arrays.stream(internal.getItems()).map(IItemStack::of).toArray(IItemStack[]::new);
    }
    
    @ZenCodeType.Caster(implicit = true)
    public static IIngredientWithAmount asIIngredientWithAmount(SizedIngredient internal) {
        
        return new IngredientWithAmount(IIngredient.fromIngredient(internal.ingredient()), internal.count());
    }
    
    
}
