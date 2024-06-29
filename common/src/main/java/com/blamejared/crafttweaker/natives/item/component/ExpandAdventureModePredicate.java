package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.AdventureModePredicate;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;
import java.util.function.Consumer;

@ZenRegister
@Document("vanilla/api/item/component/AdventureModePredicate")
@NativeTypeRegistration(value = AdventureModePredicate.class, zenCodeName = "crafttweaker.api.item.component.AdventureModePredicate")
public class ExpandAdventureModePredicate {
    
    @ZenCodeType.StaticExpansionMethod
    public static AdventureModePredicate of(List<BlockPredicate> predicates, boolean showInTooltip) {
        return new AdventureModePredicate(predicates, showInTooltip);
    }
    
    @ZenCodeType.Method
    public static AdventureModePredicate withTooltip(AdventureModePredicate internal, boolean value) {
        return internal.withTooltip(value);
    }
    
    @ZenCodeType.Method
    public static void addToTooltip(AdventureModePredicate internal, Consumer<Component> consumer) {
        internal.addToTooltip(consumer);
    }
}
