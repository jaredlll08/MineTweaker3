package com.blamejared.crafttweaker.natives.item.type.projectileweapon;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/item/type/projectileweapon/CrossbowItem")
@NativeTypeRegistration(value = CrossbowItem.class, zenCodeName = "crafttweaker.api.item.type.projectileweapon.CrossbowItem")
public class ExpandCrossBowItem {
    
    @ZenCodeType.Method
    public static void performShooting(CrossbowItem internal, Level level, LivingEntity shooter, InteractionHand hand, ItemStack stack, float power, float accuracy, @ZenCodeType.Nullable LivingEntity target) {
        
        internal.performShooting(level, shooter, hand, stack, power, accuracy, target);
    }
    
    /**
     * Checks if the stack is charged
     *
     * @param crossbowStack the stack to check
     *
     * @return true if charged, false otherwise.
     *
     * @docParam crossbowStack <item:minecraft:crossbow>
     */
    @ZenCodeType.StaticExpansionMethod
    public static boolean isCharged(ItemStack crossbowStack) {
        
        return CrossbowItem.isCharged(crossbowStack);
    }
    
    /**
     * Gets the charged duration of the given stack.
     *
     * @param crossbowStack The stack to check
     * @param entity The entity to get the charge duration for
     *
     * @return the charged duration of the given stack.
     *
     * @docParam crossbowStack <item:minecraft:crossbow>
     * @docParam entity entity
     */
    @ZenCodeType.StaticExpansionMethod
    public static int getChargeDuration(ItemStack crossbowStack, LivingEntity entity) {
        
        return CrossbowItem.getChargeDuration(crossbowStack, entity);
    }
    
}
