package com.blamejared.crafttweaker.api.ingredient;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.item.ActionModifyAttribute;
import com.blamejared.crafttweaker.api.action.item.ActionSetBurnTime;
import com.blamejared.crafttweaker.api.action.item.tooltip.ActionAddShiftedTooltip;
import com.blamejared.crafttweaker.api.action.item.tooltip.ActionAddTooltip;
import com.blamejared.crafttweaker.api.action.item.tooltip.ActionClearTooltip;
import com.blamejared.crafttweaker.api.action.item.tooltip.ActionModifyShiftedTooltip;
import com.blamejared.crafttweaker.api.action.item.tooltip.ActionModifyTooltip;
import com.blamejared.crafttweaker.api.action.item.tooltip.ActionRemoveRegexTooltip;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import com.blamejared.crafttweaker.api.data.IData;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.data.op.IDataOps;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientCondition;
import com.blamejared.crafttweaker.api.ingredient.condition.IngredientConditions;
import com.blamejared.crafttweaker.api.ingredient.condition.type.ConditionAnyDamage;
import com.blamejared.crafttweaker.api.ingredient.condition.type.ConditionCustom;
import com.blamejared.crafttweaker.api.ingredient.condition.type.ConditionDamaged;
import com.blamejared.crafttweaker.api.ingredient.condition.type.ConditionDamagedAtLeast;
import com.blamejared.crafttweaker.api.ingredient.condition.type.ConditionDamagedAtMost;
import com.blamejared.crafttweaker.api.ingredient.transformer.IIngredientTransformer;
import com.blamejared.crafttweaker.api.ingredient.transformer.IngredientTransformers;
import com.blamejared.crafttweaker.api.ingredient.transformer.type.TransformCustom;
import com.blamejared.crafttweaker.api.ingredient.transformer.type.TransformDamage;
import com.blamejared.crafttweaker.api.ingredient.transformer.type.TransformReplace;
import com.blamejared.crafttweaker.api.ingredient.transformer.type.TransformReuse;
import com.blamejared.crafttweaker.api.ingredient.type.IIngredientList;
import com.blamejared.crafttweaker.api.ingredient.type.IngredientWithAmount;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.item.tooltip.ITooltipFunction;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.crafting.Ingredient;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;


/**
 * The CraftTweaker Ingredient class which is used to power our recipes and ItemStack matching.
 *
 * @docParam this <tag:items:minecraft:wool>
 */
@ZenRegister
@ZenCodeType.Name("crafttweaker.api.ingredient.IIngredient")
@Document("vanilla/api/ingredient/IIngredient")
public interface IIngredient extends CommandStringDisplayable {
    
    ResourceLocation INGREDIENT_ID = CraftTweakerConstants.rl("iingredient");
    Codec<IIngredient> CODEC = Ingredient.CODEC.xmap(IIngredient::fromIngredient, IIngredient::asVanillaIngredient);
    StreamCodec<RegistryFriendlyByteBuf, IIngredient> STREAM_CODEC = Ingredient.CONTENTS_STREAM_CODEC.map(IIngredient::fromIngredient, IIngredient::asVanillaIngredient);
    
    /**
     * Does the given stack match the ingredient?
     *
     * @param stack The stack to check
     *
     * @docParam stack <item:minecraft:iron_ingot>
     */
    @ZenCodeType.Method
    boolean matches(IItemStack stack);
    
    /**
     * Checks if this ingredient is empty.
     *
     * @return true if empty, false otherwise
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("empty")
    default boolean isEmpty() {
        
        return asVanillaIngredient().isEmpty();
    }
    
    /**
     * Does the ingredient contain the given ingredient?
     *
     * @param ingredient The ingredient to check
     *
     * @docParam ingredient (<item:minecraft:iron_ingot> | <item:minecraft:gold_ingot>)
     */
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.CONTAINS)
    default boolean contains(IIngredient ingredient) {
        
        return Arrays.stream(ingredient.getItems()).allMatch(this::matches);
    }
    
    /**
     * Create a Vanilla ingredient matching this one.
     */
    @ZenCodeType.Method
    @ZenCodeType.Caster(implicit = true)
    Ingredient asVanillaIngredient();
    
    /**
     * When this ingredient stack is crafted, what will remain in the grid?
     * Does not check if the stack matches though!
     * Used e.g. in Crafting Table recipes.
     *
     * @param stack The stack to provide for this ingredient.
     *
     * @docParam stack <item:minecraft:iron_ingot>
     */
    @ZenCodeType.Method
    default IItemStack getRemainingItem(IItemStack stack) {
        
        if(!transformers().isEmpty()) {
            return transformers().apply(stack);
        }
        return Services.PLATFORM.getRemainingItem(stack.getInternal());
    }
    
    /**
     * Returns the BEP to get this stack
     */
    @ZenCodeType.Getter("commandString")
    String getCommandString();
    
    @ZenCodeType.Getter("items")
    IItemStack[] getItems();
    
    /**
     * Sets the burn time of this ingredient, for use in the furnace and other machines
     *
     * @param time the new burn time
     *
     * @docParam time 500
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("burnTime")
    default void setBurnTime(int time) {
        
        CraftTweakerAPI.apply(new ActionSetBurnTime(this, time));
    }
    
    @ZenCodeType.Method
    default void clearTooltip(@ZenCodeType.OptionalBoolean() boolean leaveName) {
        
        CraftTweakerAPI.apply(new ActionClearTooltip(this, leaveName));
    }
    
    @ZenCodeType.Method
    default void addTooltip(Component content) {
        
        CraftTweakerAPI.apply(new ActionAddTooltip(this, content));
    }
    
    @ZenCodeType.Method
    default void addShiftTooltip(Component content, @ZenCodeType.Optional Component showMessage) {
        
        CraftTweakerAPI.apply(new ActionAddShiftedTooltip(this, content, showMessage));
    }
    
    @ZenCodeType.Method
    default void modifyTooltip(ITooltipFunction function) {
        
        CraftTweakerAPI.apply(new ActionModifyTooltip(this, function));
    }
    
    @ZenCodeType.Method
    default void modifyShiftTooltip(ITooltipFunction shiftedFunction, @ZenCodeType.Optional ITooltipFunction unshiftedFunction) {
        
        CraftTweakerAPI.apply(new ActionModifyShiftedTooltip(this, shiftedFunction, unshiftedFunction));
    }
    
    @ZenCodeType.Method
    default void removeTooltip(String regex) {
        
        CraftTweakerAPI.apply(new ActionRemoveRegexTooltip(this, Pattern.compile(regex)));
    }
    
    /**
     * Adds an AttributeModifier to this IIngredient.
     *
     * Attributes added with this method appear on all ItemStacks that match this IIngredient,
     * regardless of how or when the ItemStack was made, if you want to have the attribute on a
     * single specific ItemStack (such as a specific Diamond Sword made in a recipe), then you should use
     * IItemStack#withAttributeModifier
     *
     * @param attribute The Attribute of the modifier.
     * @param name      The name of the modifier.
     * @param value     The value of the modifier.
     * @param operation The operation of the modifier.
     * @param slotTypes What slots the modifier is valid for.
     *
     * @docParam attribute <attribute:minecraft:generic.attack_damage>
     * @docParam name "Extra Power"
     * @docParam value 10
     * @docParam operation AttributeOperation.ADDITION
     * @docParam slotTypes [<constant:minecraft:equipmentslot:chest>]
     */
    @ZenCodeType.Method
    default void addGlobalAttributeModifier(Attribute attribute, String name, double value, AttributeModifier.Operation operation, EquipmentSlot[] slotTypes) {
        
        AttributeModifier modifier = new AttributeModifier(name, value, operation);
        addModifier(attribute, slotTypes, modifier);
    }
    
    /**
     * Adds an AttributeModifier to this IIngredient using a specific UUID.
     *
     * The UUID can be used to override an existing attribute on an ItemStack with this new modifier.
     * You can use `/ct hand attributes` to get the UUID of the attributes on an ItemStack.
     *
     * Attributes added with this method appear on all ItemStacks that match this IIngredient,
     * regardless of how or when the ItemStack was made, if you want to have the attribute on a
     * single specific ItemStack (such as a specific Diamond Sword made in a recipe), then you should use
     * IItemStack#withAttributeModifier
     *
     * @param uuid      The unique identifier of the modifier to replace.
     * @param attribute The Attribute of the modifier.
     * @param name      The name of the modifier.
     * @param value     The value of the modifier.
     * @param operation The operation of the modifier.
     * @param slotTypes What slots the modifier is valid for.
     *
     * @docParam attribute <attribute:minecraft:generic.attack_damage>
     * @docParam uuid "8c1b5535-9f79-448b-87ae-52d81480aaa3"
     * @docParam name "Extra Power"
     * @docParam value 10
     * @docParam operation AttributeOperation.ADDITION
     * @docParam slotTypes [<constant:minecraft:equipmentslot:chest>]
     */
    @ZenCodeType.Method
    default void addGlobalAttributeModifier(Attribute attribute, String uuid, String name, double value, AttributeModifier.Operation operation, EquipmentSlot[] slotTypes) {
        
        addGlobalAttributeModifier(attribute, UUID.fromString(uuid), name, value, operation, slotTypes);
    }
    
    /**
     * Adds an AttributeModifier to this IIngredient using a specific UUID.
     *
     * The UUID can be used to override an existing attribute on an ItemStack with this new modifier.
     * You can use `/ct hand attributes` to get the UUID of the attributes on an ItemStack.
     *
     * Attributes added with this method appear on all ItemStacks that match this IIngredient,
     * regardless of how or when the ItemStack was made, if you want to have the attribute on a
     * single specific ItemStack (such as a specific Diamond Sword made in a recipe), then you should use
     * IItemStack#withAttributeModifier
     *
     * @param uuid      The unique identifier of the modifier to replace.
     * @param attribute The Attribute of the modifier.
     * @param name      The name of the modifier.
     * @param value     The value of the modifier.
     * @param operation The operation of the modifier.
     * @param slotTypes What slots the modifier is valid for.
     *
     * @docParam attribute <attribute:minecraft:generic.attack_damage>
     * @docParam uuid IItemStack.BASE_ATTACK_DAMAGE_UUID
     * @docParam name "Extra Power"
     * @docParam value 10
     * @docParam operation AttributeOperation.ADDITION
     * @docParam slotTypes [<constant:minecraft:equipmentslot:chest>]
     */
    @ZenCodeType.Method
    default void addGlobalAttributeModifier(Attribute attribute, UUID uuid, String name, double value, AttributeModifier.Operation operation, EquipmentSlot[] slotTypes) {
        
        AttributeModifier modifier = new AttributeModifier(uuid, name, value, operation);
        addModifier(attribute, slotTypes, modifier);
    }
    
    private void addModifier(Attribute attribute, EquipmentSlot[] slotTypes, AttributeModifier modifier) {
        
        final Set<EquipmentSlot> validSlots = new HashSet<>(Arrays.asList(slotTypes));
        CraftTweakerAPI.apply(new ActionModifyAttribute(this, event -> {
            if(validSlots.contains(event.getSlotType())) {
                if(event.getModifiers().containsEntry(attribute, modifier)) {
                    event.removeModifier(attribute, modifier);
                }
                event.addModifier(attribute, modifier);
            }
        }));
    }
    
    /**
     * Removes all AttributeModifiers that use the given Attribute from this IIngredient.
     *
     * Attributes removed with this method are removed from ItemStacks that match this IIngredient,
     * regardless of how or when the ItemStack was made, if you want to remove the attribute on a
     * single specific ItemStack (such as a specific Diamond Sword made in a recipe), then you should use
     * IItemStack#withoutAttribute.
     *
     * This method can only remove default Attributes from an ItemStack, it is still possible that
     * an ItemStack can override it.
     *
     * @param attribute The attribute to remove.
     * @param slotTypes The slot types to remove it from.
     *
     * @docParam attribute <attribute:minecraft:generic.attack_damage>
     * @docParam slotTypes [<constant:minecraft:equipmentslot:chest>]
     */
    @ZenCodeType.Method
    default void removeGlobalAttribute(Attribute attribute, EquipmentSlot[] slotTypes) {
        
        final Set<EquipmentSlot> validSlots = new HashSet<>(Arrays.asList(slotTypes));
        
        CraftTweakerAPI.apply(new ActionModifyAttribute(this, event -> {
            if(validSlots.contains(event.getSlotType())) {
                event.removeAttribute(attribute);
            }
        }));
    }
    
    /**
     * Removes all AttributeModifiers who's ID is the same as the given uuid from this IIngredient.
     *
     * @param uuid      The unique id of the AttributeModifier to remove.
     * @param slotTypes The slot types to remove it from.
     *
     * @docParam uuid "8c1b5535-9f79-448b-87ae-52d81480aaa3"
     * @docParam slotTypes [<constant:minecraft:equipmentslot:chest>]
     */
    @ZenCodeType.Method
    default void removeGlobalAttributeModifier(String uuid, EquipmentSlot[] slotTypes) {
        
        removeGlobalAttributeModifier(UUID.fromString(uuid), slotTypes);
    }
    
    /**
     * Removes all AttributeModifiers who's ID is the same as the given uuid from this IIngredient.
     *
     * @param uuid      The unique id of the AttributeModifier to remove.
     * @param slotTypes The slot types to remove it from.
     *
     * @docParam uuid IItemStack.BASE_ATTACK_DAMAGE_UUID
     * @docParam slotTypes [<constant:minecraft:equipmentslot:chest>]
     */
    @ZenCodeType.Method
    default void removeGlobalAttributeModifier(UUID uuid, EquipmentSlot[] slotTypes) {
        
        final Set<EquipmentSlot> validSlots = new HashSet<>(Arrays.asList(slotTypes));
        CraftTweakerAPI.apply(new ActionModifyAttribute(this, event -> {
            if(validSlots.contains(event.getSlotType())) {
                event.getModifiers()
                        .entries()
                        .stream()
                        .filter(entry -> entry.getValue().id().equals(uuid))
                        .forEach(entry -> event.removeModifier(entry.getKey(), entry.getValue()));
            }
        }));
    }
    
    static IIngredient fromIngredient(Ingredient ingredient) {
        
        return IngredientConverter.fromIngredient(ingredient);
    }
    
    @ZenCodeType.Caster(implicit = true)
    default MapData asMapData() {
        
        final IData data = this.asIData();
        return data instanceof MapData ? ((MapData) data) : new MapData();
    }
    
    @ZenCodeType.Caster(implicit = true)
    default IData asIData() {
        
        return Ingredient.CODEC_NONEMPTY.encodeStart(IDataOps.INSTANCE, this.asVanillaIngredient())
                .getOrThrow(IllegalArgumentException::new);
    }
    
    @ZenCodeType.Operator(ZenCodeType.OperatorType.OR)
    default IIngredientList or(IIngredient other) {
        
        return new IIngredientList(new IIngredient[] {this, other});
    }
    
    /**
     * Use this in contexts where machines accept more than one item to state that fact.
     */
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.MUL)
    default IIngredientWithAmount mul(int amount) {
        
        return new IngredientWithAmount(this, amount);
    }
    
    /**
     * Used implicitly when a machine can accept more than one item but you only provide one.
     */
    @ZenCodeType.Method
    @ZenCodeType.Caster(implicit = true)
    default IIngredientWithAmount asIIngredientWithAmount() {
        
        if(this instanceof IIngredientWithAmount) {
            return (IIngredientWithAmount) this;
        }
        return mul(1);
    }
    
    
    // <editor-fold desc="Transformers">
    IngredientTransformers transformers();
    
    @ZenCodeType.Method
    default IIngredient transformReplace(IItemStack replaceWith) {
        
        return transform(new TransformReplace(replaceWith));
    }
    
    @ZenCodeType.Method
    default IIngredient transformDamage(@ZenCodeType.OptionalInt(1) int amount) {
        
        return transform(new TransformDamage(amount));
    }
    
    @ZenCodeType.Method
    default IIngredient transformCustom(String uid, @ZenCodeType.Optional Function<IItemStack, IItemStack> function) {
        
        return transform(new TransformCustom(uid, function));
    }
    
    @ZenCodeType.Method
    default IIngredient reuse() {
        
        return transform(TransformReuse.INSTANCE);
    }
    
    default IIngredient transform(IIngredientTransformer transformer) {
        
        this.transformers().add(transformer);
        return this;
    }
    
    // </editor-fold>
    
    // <editor-fold desc="conditions">
    IngredientConditions conditions();
    
    @ZenCodeType.Method
    default IIngredient onlyDamaged() {
        
        return condition(ConditionDamaged.INSTANCE);
    }
    
    @ZenCodeType.Method
    default IIngredient onlyDamagedAtLeast(int minDamage) {
        
        return condition(new ConditionDamagedAtLeast(minDamage));
    }
    
    @ZenCodeType.Method
    default IIngredient onlyDamagedAtMost(int maxDamage) {
        
        return condition(new ConditionDamagedAtMost(maxDamage));
    }
    
    @ZenCodeType.Method
    default IIngredient anyDamage() {
        
        return condition(ConditionAnyDamage.INSTANCE);
    }
    
    @ZenCodeType.Method
    default IIngredient onlyIf(String uid, @ZenCodeType.Optional Predicate<IItemStack> function) {
        
        return condition(new ConditionCustom(uid, function));
    }
    
    /**
     * Use this if you already have the condition from another ingredient
     */
    @ZenCodeType.Method
    default IIngredient condition(IIngredientCondition condition) {
        
        this.conditions().add(condition);
        return this;
    }
    // </editor-fold>
    
}
