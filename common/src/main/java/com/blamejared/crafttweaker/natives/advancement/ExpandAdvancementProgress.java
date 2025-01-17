package com.blamejared.crafttweaker.natives.advancement;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.CriterionProgress;
import net.minecraft.network.chat.Component;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;
import java.util.stream.StreamSupport;

@ZenRegister
@Document("vanilla/api/advancement/AdvancementProgress")
@NativeTypeRegistration(value = AdvancementProgress.class, zenCodeName = "crafttweaker.api.advancement.AdvancementProgress")
public class ExpandAdvancementProgress {
    
    /**
     * Checks if the advancement is done
     *
     * @return true if the advancement is done, false otherwise
     */
    @ZenCodeType.Getter("done")
    public static boolean isDone(AdvancementProgress internal) {
        
        return internal.isDone();
    }
    
    /**
     * Checks if the advancement has progress
     *
     * @return true if the advancement has progress, false otherwise
     */
    @ZenCodeType.Getter("hasProgress")
    public static boolean hasProgress(AdvancementProgress internal) {
        
        return internal.hasProgress();
    }
    
    /**
     * Grants progress to the advancement
     *
     * @param criterionName The name of the criterion to grant progress to
     *
     * @return true if the progress was granted, false otherwise
     */
    @ZenCodeType.Method
    public static boolean grantProgress(AdvancementProgress internal, String criterionName) {
        
        return internal.grantProgress(criterionName);
    }
    
    /**
     * Revokes progress from the advancement
     *
     * @param criterionName The name of the criterion to revoke progress from
     *
     * @return true if the progress was revoked, false otherwise
     */
    @ZenCodeType.Method
    public static boolean revokeProgress(AdvancementProgress internal, String criterionName) {
        
        return internal.revokeProgress(criterionName);
    }
    
    /**
     * Gets the criterion progress for the advancement
     *
     * @param criterionName The name of the criterion to get progress for
     *
     * @return The criterion progress, or null if it doesn't exist
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Method
    public static CriterionProgress getCriterion(AdvancementProgress internal, String criterionName) {
        
        return internal.getCriterion(criterionName);
    }
    
    /**
     * Gets the percentage of the advancement that is complete
     *
     * @return The percentage of the advancement that is complete
     */
    @ZenCodeType.Getter("percent")
    public static float getPercent(AdvancementProgress internal) {
        
        return internal.getPercent();
    }
    
    /**
     * Gets the progress text for the advancement
     *
     * @return The progress text for the advancement, or null if it doesn't exist
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("progressText")
    public static Component getProgressText(AdvancementProgress internal) {
        
        return internal.getProgressText();
    }
    
    /**
     * Gets the remaining criteria for the advancement
     *
     * @return The remaining criteria for the advancement
     */
    @ZenCodeType.Getter("remainingCriteria")
    public static List<String> getRemainingCriteria(AdvancementProgress internal) {
        
        return StreamSupport.stream(internal.getRemainingCriteria().spliterator(), false).toList();
    }
    
    /**
     * Gets the completed criteria for the advancement
     *
     * @return The completed criteria for the advancement
     */
    @ZenCodeType.Getter("completedCriteria")
    public static List<String> getCompletedCriteria(AdvancementProgress internal) {
        
        return StreamSupport.stream(internal.getCompletedCriteria().spliterator(), false).toList();
    }
    
}
