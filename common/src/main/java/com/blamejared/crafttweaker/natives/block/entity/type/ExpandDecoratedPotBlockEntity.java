package com.blamejared.crafttweaker.natives.block.entity.type;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.PotDecorations;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/block/entity/type/DecoratedPotBlockEntity")
@NativeTypeRegistration(value = DecoratedPotBlockEntity.class, zenCodeName = "crafttweaker.api.block.entity.type.DecoratedPotBlockEntity")
public class ExpandDecoratedPotBlockEntity {
    
    /**
     * Creates a new decorated pot item with a copy of the pots decorations. Additional data like the pots contained item or loot table are not copied.
     *
     * @return A new decorated pot item with a copy of the pots decorations.
     */
    @ZenCodeType.Method
    public static ItemStack getPotAsItem(DecoratedPotBlockEntity internal) {
        
        return internal.getPotAsItem();
    }
    
    /**
     * Gets access to the decorations that make up the faces of the pot.
     *
     * @return The decorations used by the faces of the pot.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("decorations")
    public static PotDecorations getDecorations(DecoratedPotBlockEntity internal) {
        
        return internal.getDecorations();
    }
    
}