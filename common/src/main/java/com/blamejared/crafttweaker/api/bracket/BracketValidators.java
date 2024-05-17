package com.blamejared.crafttweaker.api.bracket;

import com.blamejared.crafttweaker.api.annotation.BracketValidator;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.logging.CommonLoggers;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Locale;
import java.util.function.Function;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.BracketValidators")
@Document("vanilla/api/BracketValidators")
public class BracketValidators {
    
    private BracketValidators() {
        
    }
    
    @ZenCodeType.Method
    @BracketValidator("block")
    public static boolean validateBlockBracket(String tokens) {
        
        return validateBracket("block", tokens, BracketHandlers::getBlock);
    }
    
    @ZenCodeType.StaticExpansionMethod
    @BracketValidator("fluid")
    public static boolean validateFluidStack(String tokens) {
        
        return validateBracket("fluid", tokens, BracketHandlers::getFluidStack);
    }
    
    @ZenCodeType.Method
    @BracketValidator("blockstate")
    public static boolean validateBlockStateMaterialBracket(String tokens) {
        
        final String[] split = tokens.split(":");
        if(split.length > 4 || split.length < 2) {
            CommonLoggers.zenCode()
                    .error("Invalid bracket Syntax: <blockstate:{}>! Correct syntax is <blockstate:modid:block_name:properties> or <blockstate:modid:block_name>!", tokens);
            return false;
        }
        
        final String resourceLocation = split[0] + ":" + split[1];
        if(ResourceLocation.tryParse(resourceLocation) == null) {
            CommonLoggers.zenCode()
                    .error("Invalid Block name for Blockstate bracket. '{}' is not a valid resource location!", resourceLocation);
            return false;
        }
        
        final String properties = split.length == 3 ? split[2] : "";
        final BlockState blockState = BracketHandlers.getBlockState(resourceLocation, properties);
        return blockState != null;
    }
    
    
    @ZenCodeType.Method
    @BracketValidator("mobeffect")
    public static boolean validateEffectBracket(String tokens) {
        
        if(tokens.split(":").length != 2) {
            CommonLoggers.zenCode()
                    .error("Invalid Bracket Syntax: <effect:{}>! Syntax is <effect:modid:potionname>", tokens);
            return false;
        }
        
        return validateBracket("effect", tokens, BracketHandlers::getMobEffect);
    }
    
    @ZenCodeType.Method
    @BracketValidator("enchantment")
    public static boolean validateEnchantment(String tokens) {
        
        if(!tokens.toLowerCase(Locale.ENGLISH).equals(tokens)) {
            CommonLoggers.zenCode().warn("Enchantment bracket <enchantment:{}> is not lower-case!", tokens);
        }
        
        final String[] split = tokens.split(":");
        if(split.length != 2) {
            CommonLoggers.zenCode()
                    .error("Could not get enchantment '{}': not a valid bracket handler, syntax is <enchantment:modid:name>", tokens);
            return false;
        }
        
        final ResourceLocation key = new ResourceLocation(split[0], split[1]);
        if(!BuiltInRegistries.ENCHANTMENT.containsKey(key)) {
            CommonLoggers.zenCode().error("Could not get enchantment '{}': the enchantment isn't registered", tokens);
            return false;
        }
        
        return true;
    }
    
    @ZenCodeType.Method
    @BracketValidator("entitytype")
    public static boolean validateEntityType(String tokens) {
        
        if(ResourceLocation.tryParse(tokens) == null) {
            CommonLoggers.zenCode()
                    .error("Invalid Bracket Syntax: <entitytype:{}>! Syntax is <entitytype:modid:entity_type_name>", tokens);
            return false;
        }
        
        return validateBracket("entitytype", tokens, BracketHandlers::getEntityType);
    }
    
    @ZenCodeType.Method
    @BracketValidator("item")
    public static boolean validateItemBracket(String tokens) {
        
        if(!tokens.toLowerCase(Locale.ENGLISH).equals(tokens)) {
            CommonLoggers.zenCode().warn("Item bracket <item:{}> is not lower-cased!", tokens);
        }
        
        final String[] split = tokens.split(":");
        if(split.length != 2) {
            CommonLoggers.zenCode()
                    .error("Could not get item with name: <item:{}>! Syntax is <item:modid:itemname>", tokens);
            return false;
        }
        ResourceLocation key = new ResourceLocation(split[0], split[1]);
        
        if(!BuiltInRegistries.ITEM.containsKey(key)) {
            CommonLoggers.zenCode().error("Could not get item with name: <item:{}>! Item does not exist!", tokens);
            return false;
        }
        
        return true;
    }
    
    @ZenCodeType.Method
    @BracketValidator("profession")
    public static boolean validateProfessionBracket(String tokens) {
        
        if(tokens.split(":").length != 2) {
            CommonLoggers.zenCode()
                    .error("Invalid Bracket Syntax: <profession:{}>! Syntax is <profession:modid:profession_name>", tokens);
            return false;
        }
        
        return validateBracket("profession", tokens, BracketHandlers::getProfession);
    }
    
    @ZenCodeType.Method
    @BracketValidator("resource")
    public static boolean validateResourceBracket(String tokens) {
        
        return ResourceLocation.tryParse(tokens) != null;
    }
    
    public static boolean validateBracket(String bracketName, String tokens, Function<String, ?> bracketMethod, boolean logError) {
        
        try {
            return bracketMethod.apply(tokens) != null;
        } catch(Exception e) {
            if(logError) {
                CommonLoggers.zenCode().error("Error validating BEP <{}:{}>", bracketName, tokens, e);
            }
            return false;
        }
    }
    
    public static boolean validateBracket(String bracketName, String tokens, Function<String, ?> bracketMethod) {
        
        return validateBracket(bracketName, tokens, bracketMethod, true);
    }
    
    @ZenCodeType.Method
    @BracketValidator("soundevent")
    public static boolean validateSoundEvent(String tokens) {
        
        if(tokens.split(":").length != 2) {
            CommonLoggers.zenCode()
                    .error("Invalid Bracket Syntax: <soundevent:{}>! Syntax is <soundevent:modid:name>", tokens);
            return false;
        }
        
        return validateBracket("soundevent", tokens, BracketHandlers::getSoundEvent);
    }
    
    @ZenCodeType.Method
    @BracketValidator("targetingstrategy")
    public static boolean validateTargetingStrategy(final String tokens) {
        
        if(tokens.split(":").length != 2) {
            CommonLoggers.zenCode()
                    .error("Invalid Bracket Syntax <targetingstrategy:{}>! Syntax is <targetingstrategy:modid:name>", tokens);
            return false;
        }
        
        return validateBracket("targetingstrategy", tokens, BracketHandlers::getTargetingStrategy);
    }
    
    @ZenCodeType.Method
    @BracketValidator("componenttype")
    public static boolean validateComponentType(String tokens) {
        
        if(tokens.split(":").length != 2) {
            CommonLoggers.zenCode()
                    .error("Invalid Bracket Syntax: <componenttype:{}>! Syntax is <componenttype:modid:component_type_name>", tokens);
            return false;
        }
        
        return validateBracket("componenttype", tokens, BracketHandlers::getComponentType);
    }
    
}
