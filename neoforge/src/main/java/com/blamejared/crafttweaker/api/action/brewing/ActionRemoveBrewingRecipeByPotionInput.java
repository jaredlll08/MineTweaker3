//package com.blamejared.crafttweaker.api.action.brewing;
//
//import com.blamejared.crafttweaker.natives.item.alchemy.ExpandPotion;
//import net.minecraft.core.Holder;
//import net.minecraft.world.item.alchemy.Potion;
//import net.minecraft.world.item.alchemy.PotionBrewing;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.neoforged.neoforge.common.brewing.IBrewingRecipe;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ActionRemoveBrewingRecipeByPotionInput extends ActionBrewingBase {
//
//    private final List<PotionBrewing.Mix<Potion>> removed = new ArrayList<>();
//    private final Potion input;
//
//    public ActionRemoveBrewingRecipeByPotionInput(List<IBrewingRecipe> recipes, Potion input) {
//
//        super(recipes);
//        this.input = input;
//    }
//
//    @Override
//    public void apply() {
//
//        //TODO 1.20.5 also from and to return holders now
////        Iterator<PotionBrewing.Mix<Potion>> iterator = AccessPotionBrewing.crafttweaker$getPOTION_MIXES().iterator();
////        //TODO 1.20.5 this says it is unreachable?
////        while(iterator.hasNext()) {
////            PotionBrewing.Mix<Potion> mix = iterator.next();
////
////            Holder<Potion> potionInput = mix.from();
////            if(potionInput == null) {
////                throw new RuntimeException("Error getting potion from mix: " + mix + "! Please make an issue on the issue tracker!");
////            }
////            if(potionInput == input) {
////                removed.add(mix);
////                iterator.remove();
////            }
////        }
//    }
//
//    @Override
//    public void undo() {
//
//        for(PotionBrewing.Mix<Potion> potion : removed) {
//            Ingredient itemReagent = potion.ingredient();
//            Holder<Potion> potionOutput = potion.to();
//            //TODO 1.20.5
////            AccessPotionBrewing.crafttweaker$callAddMix(potion.from(), itemReagent.getItems()[0].getItem(), potionOutput);
//        }
//    }
//
//    @Override
//    public String describe() {
//
//        return "Removing Brewing recipes that have an input of: " + ExpandPotion.getCommandString(input);
//    }
//
//    @Override
//    public String describeUndo() {
//
//        return "Undoing removal of Brewing recipes that have an input of: " + ExpandPotion.getCommandString(input);
//    }
//
//}
