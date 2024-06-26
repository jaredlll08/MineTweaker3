package com.blamejared.crafttweaker.natives.world.damage;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.damagesource.DamageSource;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.common.damagesource.IReductionFunction;
import org.openzen.zencode.java.ZenCodeType;

@SuppressWarnings("UnstableApiUsage")
@ZenRegister
@Document("neoforge/api/world/damage/DamageContainer")
@NativeTypeRegistration(value = DamageContainer.class, zenCodeName = "crafttweaker.neoforge.api.world.damage.DamageContainer")
public class ExpandDamageContainer {
    
    @ZenCodeType.StaticExpansionMethod
    public static DamageContainer of(DamageSource source, float originalDamage) {
        
        return new DamageContainer(source, originalDamage);
    }
    
    @ZenCodeType.Getter("originalDamage")
    public static float getOriginalDamage(DamageContainer internal) {
        
        return internal.getOriginalDamage();
    }
    
    @ZenCodeType.Getter("blockedDamage")
    public static float getBlockedDamage(DamageContainer internal) {
        
        return internal.getBlockedDamage();
    }
    
    @ZenCodeType.Getter("newDamage")
    public static float getNewDamage(DamageContainer internal) {
        
        return internal.getNewDamage();
    }
    
    @ZenCodeType.Getter("postAttackInvulnerabilityTicks")
    public static int getPostAttackInvulnerabilityTicks(DamageContainer internal) {
        
        return internal.getPostAttackInvulnerabilityTicks();
    }
    
    @ZenCodeType.Setter("newDamage")
    public static void setNewDamage(DamageContainer internal, float damage) {
        
        internal.setNewDamage(damage);
    }
    
    @ZenCodeType.Method
    public static void addModifier(DamageContainer internal, DamageContainer.Reduction type, IReductionFunction reductionFunction) {
        
        internal.addModifier(type, reductionFunction);
    }
    
    @ZenCodeType.Setter("postAttackInvulnerabilityTicks")
    public static void setPostAttackInvulnerabilityTicks(DamageContainer internal, int ticks) {
        
        internal.setPostAttackInvulnerabilityTicks(ticks);
    }
    
    @ZenCodeType.Method
    public static float getReduction(DamageContainer internal, DamageContainer.Reduction type) {
        
        return internal.getReduction(type);
    }
    
    @ZenCodeType.Getter("source")
    public static DamageSource getSource(DamageContainer internal) {
        
        return internal.getSource();
    }
    
    @ZenCodeType.Getter("shieldDamage")
    public static float getShieldDamage(DamageContainer internal) {
        
        return internal.getShieldDamage();
    }
    
    @SuppressWarnings("UnstableApiUsage")
    @ZenRegister
    @Document("neoforge/api/world/damage/DamageContainerReduction")
    @NativeTypeRegistration(value = DamageContainer.Reduction.class, zenCodeName = "crafttweaker.neoforge.api.world.damage.DamageContainerReduction")
    @BracketEnum("neoforge:damage/reduction")
    public static class ExpandDamageContainerReduction {
    
    }
    
}
