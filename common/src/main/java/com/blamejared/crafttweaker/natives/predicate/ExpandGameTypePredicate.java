package com.blamejared.crafttweaker.natives.predicate;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.advancements.critereon.GameTypePredicate;
import net.minecraft.world.level.GameType;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/predicate/GameTypePredicate")
@NativeTypeRegistration(value = GameTypePredicate.class, zenCodeName = "crafttweaker.api.predicate.GameTypePredicate")
public final class ExpandGameTypePredicate {
    
    @ZenCodeType.StaticExpansionMethod
    public static GameTypePredicate create(GameType... gameTypes) {
        
        return GameTypePredicate.of(gameTypes);
    }
    
    
}
