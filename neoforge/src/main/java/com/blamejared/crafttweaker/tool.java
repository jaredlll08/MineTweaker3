package com.blamejared.crafttweaker;

import com.blamejared.crafttweaker.api.bracket.BracketHandlers;
import com.blamejared.crafttweaker.api.zencode.CraftTweakerGlobals;
import com.blamejared.crafttweaker.natives.component.ExpandDataComponentHolder;
import com.blamejared.crafttweaker.natives.item.component.ExpandItemLore;
import com.blamejared.crafttweaker.natives.text.ExpandMessage;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.ItemLore;

import java.util.Iterator;

public class tool {
    public static void run() {
        DataComponentType component = BracketHandlers.getComponentType("minecraft:tool");
        ItemLore tool = ExpandDataComponentHolder.getComponent(BracketHandlers.getItem("minecraft:diamond_pickaxe").getInternal(), ItemLore.class, component);
        Iterator var10000 = ExpandItemLore.lines((ItemLore)tool).iterator();

        while(var10000.hasNext()) {
            Component line = (Component)var10000.next();
            CraftTweakerGlobals.println(ExpandMessage.getString(line));
        }

    }
}