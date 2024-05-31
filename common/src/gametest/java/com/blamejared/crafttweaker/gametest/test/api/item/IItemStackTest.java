package com.blamejared.crafttweaker.gametest.test.api.item;

import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.serializer.CTShapelessRecipeSerializer;
import com.blamejared.crafttweaker.api.recipe.type.CTShapelessRecipe;
import com.blamejared.crafttweaker.gametest.CraftTweakerGameTest;
import com.blamejared.crafttweaker.gametest.framework.annotation.CraftTweakerGameTestHolder;
import com.blamejared.crafttweaker.gametest.framework.annotation.TestModifier;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.item.Items;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
@CraftTweakerGameTestHolder
public class IItemStackTest  implements CraftTweakerGameTest {
    
    @GameTest(template = "crafttweaker:empty")
    @TestModifier(implicitSuccession = true)
    public void testCodecEncode(GameTestHelper helper) {
        
        DataResult<JsonElement> encodeResult = encode(IItemStack.CODEC, IItemStack.of(Items.APPLE));
        JsonElement jsonResult = encodeResult.getOrThrow(GameTestAssertException::new);
        assertThat(jsonResult.isJsonObject(), is(true));
        assertThat(jsonResult.getAsJsonObject(), is(parseJson("""
  {
    "item": {
      "id": "minecraft:apple",
      "Count": 1
    },
    "mutable": false,
    "conditions": {
      "conditions": []
    },
    "transformers": {
      "transformers": []
    }
  }
  """)));
    }
    
    @GameTest(template = "crafttweaker:empty")
    @TestModifier(implicitSuccession = true)
    public void testCodecDecode(GameTestHelper helper) {
        
        DataResult<Pair<IItemStack, JsonElement>> decode = decode(IItemStack.CODEC, parseJson("""
                {
                      "item": {
                        "id": "minecraft:apple",
                        "Count": 1
                      },
                      "mutable": false,
                      "conditions": {
                        "conditions": []
                      },
                      "transformers": {
                        "transformers": []
                      }
                    }
"""));
        Pair<IItemStack, JsonElement> decodeResult = decode.getOrThrow(GameTestAssertException::new);
        assertThat(decodeResult.getFirst(), is(IItemStack.of(Items.APPLE)));
    }
    
}
