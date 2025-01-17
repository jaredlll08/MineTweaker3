package com.blamejared.crafttweaker.natives.advancement;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.advancements.AdvancementRequirements;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@ZenRegister
@Document("vanilla/api/advancement/AdvancementRequirements")
@NativeTypeRegistration(value = AdvancementRequirements.class, zenCodeName = "crafttweaker.api.advancement.AdvancementRequirements")
public class ExpandAdvancementRequirements {
    
    /**
     * Gets the size of the advancement requirements
     *
     * @return The size of the advancement requirements
     */
    @ZenCodeType.Getter("size")
    public static int size(AdvancementRequirements internal) {
        
        return internal.size();
    }
    
    /**
     * Tests the advancement requirements
     *
     * @param test The predicate to test the advancement requirements with
     *
     * @return true if the advancement requirements test passes, false otherwise
     */
    @ZenCodeType.Method
    public static boolean test(AdvancementRequirements internal, Predicate<String> test) {
        
        return internal.test(test);
    }
    
    /**
     * Counts the advancement requirements
     *
     * @param test The predicate to count the advancement requirements with
     *
     * @return The count of the advancement requirements
     */
    @ZenCodeType.Method
    public static int count(AdvancementRequirements internal, Predicate<String> test) {
        
        return internal.count(test);
    }
    
    /**
     * Checks if the advancement requirements is empty
     *
     * @return true if the advancement requirements is empty, false otherwise
     */
    @ZenCodeType.Getter("isEmpty")
    public static boolean isEmpty(AdvancementRequirements internal) {
        
        return internal.isEmpty();
    }
    
    /**
     * Gets the names of the advancement requirements
     *
     * @return The names of the advancement requirements
     */
    @ZenCodeType.Getter("names")
    public static Set<String> names(AdvancementRequirements internal) {
        
        return internal.names();
    }
    
    /**
     * Gets the requirements of the advancement
     *
     * @return The requirements of the advancement
     */
    @ZenCodeType.Getter("requirements")
    public static List<List<String>> requirements(AdvancementRequirements internal) {
        
        return internal.requirements();
    }
    
}
