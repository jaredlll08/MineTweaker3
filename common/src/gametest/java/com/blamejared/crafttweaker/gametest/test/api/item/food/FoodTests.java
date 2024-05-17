package com.blamejared.crafttweaker.gametest.test.api.item.food;

import com.blamejared.crafttweaker.gametest.CraftTweakerGameTest;
import com.blamejared.crafttweaker.gametest.framework.ScriptBuilder;
import com.blamejared.crafttweaker.gametest.framework.annotation.CraftTweakerGameTestHolder;
import com.blamejared.crafttweaker.gametest.framework.annotation.TestModifier;
import com.blamejared.crafttweaker.gametest.logging.appender.GameTestLoggerAppender;
import com.blamejared.crafttweaker.impl.script.scriptrun.GameTestScriptRunner;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.component.DataComponents;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@CraftTweakerGameTestHolder
public class FoodTests implements CraftTweakerGameTest {
    
    @GameTest(template = "crafttweaker:empty")
    @TestModifier(implicitSuccession = true)
    public void testThatFoodValuesAreCorrect(GameTestHelper helper, ScriptBuilder builder) {
        
        builder.file("item/food/create_new_food.zs");
        
        GameTestLoggerAppender.QueryableLog log = GameTestScriptRunner.runScripts(helper, builder);
        log.assertNoErrors();
        log.assertNoWarnings();
        
        FoodProperties food = Items.DIAMOND.getDefaultInstance().get(DataComponents.FOOD);
        assertThat(food, is(notNullValue()));
        
        assertThat(food.nutrition(), is(1));
        assertThat(food.saturation(), is(2.0F));
        assertThat(food.canAlwaysEat(), is(true));
        assertThat(food.eatSeconds(), is(0.8f));
        assertThat(food.effects().size(), is(1));
        FoodProperties.PossibleEffect effectPair = food.effects().get(0);
        assertThat(effectPair.effect().getEffect(), is(MobEffects.DIG_SPEED));
        assertThat(effectPair.effect().getDuration(), is(100));
        assertThat(effectPair.effect().getAmplifier(), is(2));
        assertThat(effectPair.probability(), is(1.0F));
    }
    
    @GameTest(template = "crafttweaker:empty")
    @TestModifier(implicitSuccession = true)
    public void testQueryCurrentFood(GameTestHelper helper, ScriptBuilder builder) {
        
        builder.file("item/food/query_current_food.zs");
        
        GameTestLoggerAppender.QueryableLog log = GameTestScriptRunner.runScripts(helper, builder);
        log.assertNoErrors();
        log.assertNoWarnings();
        log.assertOutput(0, "4");
        log.assertOutput(1, "0.3");
        log.assertOutput(2, "false");
        log.assertOutput(3, "false");
        log.assertOutput(4, "false");
    }
    
    @GameTest(template = "crafttweaker:empty")
    @TestModifier(implicitSuccession = true)
    public void removeCurrentFood(GameTestHelper helper, ScriptBuilder builder) {
        
        builder.file("item/food/remove_current_food.zs");
        
        GameTestLoggerAppender.QueryableLog log = GameTestScriptRunner.runScripts(helper, builder);
        log.assertNoErrors();
        log.assertNoWarnings();
        
        FoodProperties food = Items.GOLDEN_APPLE.getDefaultInstance().get(DataComponents.FOOD);
        assertThat(food, is(nullValue()));
    }
    
    @GameTest(template = "crafttweaker:empty")
    @TestModifier(implicitSuccession = true)
    public void modifyCurrentFood(GameTestHelper helper, ScriptBuilder builder) {
        
        builder.file("item/food/modify_current_food.zs");
        
        GameTestLoggerAppender.QueryableLog log = GameTestScriptRunner.runScripts(helper, builder);
        log.assertNoErrors();
        log.assertNoWarnings();
        
        FoodProperties food = Items.BREAD.getDefaultInstance().get(DataComponents.FOOD);
        assertThat(food, is(notNullValue()));
        assertThat(food.eatSeconds(), is(0.8));
    }
    
}
