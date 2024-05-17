package com.blamejared.crafttweaker.natives.world;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.BracketEnum;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("vanilla/api/world/ItemInteractionResult")
@NativeTypeRegistration(value = ItemInteractionResult.class, zenCodeName = "crafttweaker.api.world.ItemInteractionResult")
@BracketEnum("minecraft:world/iteminteractionresult")
public class ExpandItemInteractionResult {
    
    @ZenCodeType.Getter("result")
    public static InteractionResult result(ItemInteractionResult internal) {
        
        return internal.result();
    }
    
    @ZenCodeType.Getter("consumesAction")
    public static boolean consumesAction(ItemInteractionResult internal) {
        
        return internal.consumesAction();
    }
    
    /**
     * Returns true if {@code successSide} is true, this can be used to return {@code SUCCESS} on the client, but {@code CONSUME} on the server.
     *
     * @param successSide Is the current side the success side.
     *
     * @return <constant:minecraft:world/iteminteractionresult:success> if {@code successSide} is true, otherwise <constant:minecraft:world/iteminteractionresult:consume>.
     *
     * @docParam successSide true
     */
    @ZenCodeType.StaticExpansionMethod
    public static ItemInteractionResult sidedSuccess(boolean successSide) {
        
        return ItemInteractionResult.sidedSuccess(successSide);
    }
    
}
