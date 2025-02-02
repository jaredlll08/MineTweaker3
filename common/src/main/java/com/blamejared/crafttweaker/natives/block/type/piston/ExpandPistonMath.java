package com.blamejared.crafttweaker.natives.block.type.piston;


import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.piston.PistonMath;
import net.minecraft.world.phys.AABB;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/block/type/piston/PistonMath")
@NativeTypeRegistration(value = PistonMath.class, zenCodeName = "crafttweaker.api.block.type.piston.PistonMath")
public class ExpandPistonMath {
    
    /**
     * Gets the movement area of a piston.
     *
     * @param area      The area of the piston.
     * @param direction The direction of the piston.
     * @param progress  The progress of the piston.
     *
     * @return The movement area of the piston.
     */
    @ZenCodeType.StaticExpansionMethod
    public static AABB getMovementArea(AABB area, Direction direction, double progress) {
        
        return PistonMath.getMovementArea(area, direction, progress);
    }
    
}
