package com.blamejared.crafttweaker.api.action.brewing;

import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;

import java.util.ArrayList;
import java.util.List;

public class ActionRemoveBrewingRecipeByReagent extends ActionBrewingBase {
    
    private final IItemStack reagent;
    private final List<PotionBrewing.Mix<Potion>> removed = new ArrayList<>();
    private final List<IBrewingRecipe> removedRecipes = new ArrayList<>();
    
    public ActionRemoveBrewingRecipeByReagent(List<IBrewingRecipe> recipes, IItemStack reagent) {
        
        super(recipes);
        this.reagent = reagent;
        
    }
    
    @Override
    public void apply() {
        
        //TODO 1.20.5
//        Iterator<PotionBrewing.Mix<Potion>> vanillaIterator = AccessPotionBrewing.crafttweaker$getPOTION_MIXES()
//                .iterator();
//        while(vanillaIterator.hasNext()) {
//            PotionBrewing.Mix<Potion> mix = vanillaIterator.next();
//
//            Ingredient reagentInput = mix.ingredient;
//            if(reagentInput == null) {
//                throw new RuntimeException("Error getting potion from mix: " + mix + "! Please make an issue on the issue tracker!");
//            }
//            if(reagentInput.test(reagent.getInternal())) {
//                removed.add(mix);
//                vanillaIterator.remove();
//            }
//        }
//
//        Iterator<IBrewingRecipe> registryIterator = recipes.iterator();
//        while(registryIterator.hasNext()) {
//            IBrewingRecipe next = registryIterator.next();
//            if(next.isIngredient(reagent.getInternal())) {
//                removedRecipes.add(next);
//                registryIterator.remove();
//            }
//        }
    }
    
    @Override
    public void undo() {
        
        //TODO 1.20.5
//        for(PotionBrewing.Mix<Potion> potion : removed) {
//            Potion potionInput = potion.from;
//            Ingredient itemReagent = potion.ingredient;
//            Potion potionOutput = potion.to;
//            AccessPotionBrewing.crafttweaker$callAddMix(potionInput, itemReagent.getItems()[0].getItem(), potionOutput);
//        }
//        removedRecipes.forEach(BrewingRecipeRegistry::addRecipe);
    }
    
    @Override
    public String describe() {
        
        return "Removing Brewing recipes that have a reagent of: " + reagent;
    }
    
    @Override
    public String describeUndo() {
        
        return "Undoing removal of Brewing recipes that have a reagent of: " + reagent;
    }
    
}
