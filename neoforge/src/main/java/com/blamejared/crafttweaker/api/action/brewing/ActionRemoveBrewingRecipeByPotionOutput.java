package com.blamejared.crafttweaker.api.action.brewing;

import com.blamejared.crafttweaker.natives.item.alchemy.ExpandPotion;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;

import java.util.ArrayList;
import java.util.List;

public class ActionRemoveBrewingRecipeByPotionOutput extends ActionBrewingBase {
    
    private final List<PotionBrewing.Mix<Potion>> removed = new ArrayList<>();
    private final Potion output;
    
    
    public ActionRemoveBrewingRecipeByPotionOutput(List<IBrewingRecipe> recipes, Potion output) {
        
        super(recipes);
        this.output = output;
        
    }
    
    @Override
    public void apply() {
        
        //TODO 1.20.5 also from and to return holders now
//        Iterator<PotionBrewing.Mix<Potion>> iterator = AccessPotionBrewing.crafttweaker$getPOTION_MIXES().iterator();
//        while(iterator.hasNext()) {
//            PotionBrewing.Mix<Potion> mix = iterator.next();
//
//            if(mix.to() == null) {
//                throw new RuntimeException("Error getting potion from mix: " + mix + "! Please make an issue on the issue tracker!");
//            }
//            if(mix.to().equals(output)) {
//                removed.add(mix);
//                iterator.remove();
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
//
//            AccessPotionBrewing.crafttweaker$callAddMix(potionInput, itemReagent.getItems()[0].getItem(), potionOutput);
//        }
    }
    
    @Override
    public String describe() {
        
        return "Removing Brewing recipes that output: " + ExpandPotion.getCommandString(output);
    }
    
    @Override
    public String describeUndo() {
        
        return "Undoing removal of Brewing recipes that output: " + ExpandPotion.getCommandString(output);
    }
    
}
