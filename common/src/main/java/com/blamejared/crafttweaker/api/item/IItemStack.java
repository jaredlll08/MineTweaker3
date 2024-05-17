package com.blamejared.crafttweaker.api.item;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.IData;
import com.blamejared.crafttweaker.api.data.IntData;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.data.converter.tag.TagToDataConverter;
import com.blamejared.crafttweaker.api.data.op.IDataOps;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.ingredient.IIngredientWithAmount;
import com.blamejared.crafttweaker.api.ingredient.condition.IIngredientCondition;
import com.blamejared.crafttweaker.api.ingredient.condition.IngredientConditions;
import com.blamejared.crafttweaker.api.ingredient.transformer.IIngredientTransformer;
import com.blamejared.crafttweaker.api.ingredient.transformer.IngredientTransformers;
import com.blamejared.crafttweaker.api.ingredient.vanilla.type.IngredientIItemStack;
import com.blamejared.crafttweaker.api.util.EnchantmentUtil;
import com.blamejared.crafttweaker.api.util.ItemStackUtil;
import com.blamejared.crafttweaker.api.util.random.Percentaged;
import com.blamejared.crafttweaker.mixin.common.access.item.AccessItem;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.item.IItemStack")
@Document("vanilla/api/item/IItemStack")
public interface IItemStack extends IIngredient, IIngredientWithAmount, DataComponentHolder {
    //TODO 1.20.5 redo all of this and the comments!!!
    //TODO 1.20.5 with<Type>(<component:minecraft:food>, new Food()));
    
    ResourceLocation INGREDIENT_ID = CraftTweakerConstants.rl("iitemstack");
    
    Codec<IItemStack> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.CODEC.fieldOf("item").forGetter(IItemStack::getInternal),
            Codec.BOOL.fieldOf("mutable").forGetter(IItemStack::isMutable),
            IngredientTransformers.CODEC.fieldOf("transformers").forGetter(IItemStack::transformers)
    ).apply(instance, IItemStack::of));
    
    StreamCodec<RegistryFriendlyByteBuf, IItemStack> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC,
            IItemStack::getInternal,
            ByteBufCodecs.BOOL,
            IItemStack::isMutable,
            IngredientTransformers.STREAM_CODEC,
            IItemStack::transformers,
            IItemStack::of
    );
    
    @ZenCodeType.Field
    String CRAFTTWEAKER_DATA_KEY = "CraftTweakerData";
    
    @ZenCodeType.Field
    UUID BASE_ATTACK_DAMAGE_UUID = AccessItem.crafttweaker$getBASE_ATTACK_DAMAGE_UUID();
    
    @ZenCodeType.Field
    UUID BASE_ATTACK_SPEED_UUID = AccessItem.crafttweaker$getBASE_ATTACK_SPEED_UUID();
    
    static IItemStack empty() {
        
        return IItemStackConstants.EMPTY_STACK.get();
    }
    
    //TODO 1.20.5 clean these up and fix
    static IItemStack of(final ItemLike item) {
        
        return of(new ItemStack(item));
    }
    
    static IItemStack of(final ItemStack stack) {
        
        return Services.PLATFORM.createItemStack(stack, IngredientConditions.EMPTY, IngredientTransformers.EMPTY);
    }
    
    static IItemStack of(final ItemStack stack, final IngredientConditions conditions, final IngredientTransformers transformers) {
        
        return Services.PLATFORM.createItemStack(stack, conditions, transformers);
    }
    
    static IItemStack of(final ItemStack stack, final boolean mutable) {
        
        return mutable ? ofMutable(stack) : of(stack);
    }
    
    static IItemStack ofMutable(final ItemStack stack) {
        
        return Services.PLATFORM.createItemStackMutable(stack, IngredientConditions.EMPTY, IngredientTransformers.EMPTY);
    }
    
    static IItemStack ofMutable(final ItemStack stack, final IngredientConditions conditions, final IngredientTransformers transformers) {
        
        return Services.PLATFORM.createItemStackMutable(stack, conditions, transformers);
    }
    
    static IItemStack of(final ItemStack stack, final boolean mutable, final IngredientTransformers transformers) {
        
        return mutable ? ofMutable(stack) : of(stack);
    }
    
    /**
     * Creates a copy
     */
    @ZenCodeType.Method
    IItemStack copy();
    
    /**
     * Gets the registry name for the Item in this IItemStack
     *
     * @return registry name of the Item this IItemStack represents
     */
    @ZenCodeType.Getter("registryName")
    default ResourceLocation getRegistryName() {
        
        return BuiltInRegistries.ITEM.getKey(getInternal().getItem());
    }
    
    /**
     * Gets owning mod for the Item in this IItemStack
     *
     * @return owning mod of the Item this IItemStack represents
     */
    @ZenCodeType.Getter("owner")
    default String getOwner() {
        
        return getRegistryName().getNamespace();
    }
    
    /**
     * Returns if the ItemStack is empty
     *
     * @return true if empty, false if not
     */
    @Override
    default boolean isEmpty() {
        
        return getInternal().isEmpty();
    }
    
    @ZenCodeType.Getter("prototype")
    default DataComponentMap getPrototype() {
        
        return getInternal().getPrototype();
    }
    
    @ZenCodeType.Getter("componentsPatch")
    default DataComponentPatch getComponentsPatch() {
        
        return getInternal().getComponentsPatch();
    }
    
    @ZenCodeType.Method
    default <T> IItemStack with(DataComponentType<T> type, @ZenCodeType.Nullable T value) {
        
        return modify(itemStack -> {
            itemStack.set(type, value);
        });
    }
    
    //TODO 1.20.5 do we want to change this name?
    @ZenCodeType.Method
    default IItemStack withJsonComponent(DataComponentType type, @ZenCodeType.Nullable IData value) {
        
        return modify(itemStack -> {
            if(value == null) {
                itemStack.remove(type);
            } else {
                Codec<?> codec = type.codecOrThrow();
                DataResult<? extends Pair<?, IData>> decode = codec.decode(IDataOps.INSTANCE, value);
                itemStack.set(type, decode.getOrThrow().getFirst());
            }
        });
    }
    
    @ZenCodeType.Method
    default <T, U> IItemStack update(DataComponentType<T> type, T defaultValue, U data, BiFunction<T, U, T> operator) {
        
        return modify(itemStack -> {
            itemStack.update(type, defaultValue, data, operator);
        });
    }
    
    @ZenCodeType.Method
    default <T> IItemStack update(DataComponentType<T> type, T defaultValue, UnaryOperator<T> operator) {
        
        return modify(itemStack -> {
            itemStack.update(type, defaultValue, operator);
        });
    }
    
    @ZenCodeType.Method
    default <T> IItemStack remove(DataComponentType<T> type) {
        
        return modify(itemStack -> {
            itemStack.remove(type);
        });
    }
    
    @ZenCodeType.Method
    default IItemStack applyComponents(DataComponentMap map) {
        
        return modify(itemStack -> {
            itemStack.applyComponents(map);
        });
    }
    
    @ZenCodeType.Method
    default IItemStack applyComponents(DataComponentPatch patch) {
        
        return modify(itemStack -> {
            itemStack.applyComponents(patch);
        });
    }
    
    @ZenCodeType.Method
    default IItemStack applyComponentsAndValidate(DataComponentPatch patch) {
        
        return modify(itemStack -> {
            itemStack.applyComponents(patch);
        });
    }
    
    /**
     * Returns the max stack size of the Item in the ItemStack
     *
     * @return Max stack size of the Item.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("maxStackSize")
    default int getMaxStackSize() {
        
        return getInternal().getMaxStackSize();
    }
    //TODO 1.20.5 make setters for these type of things?
    
    /**
     * Sets the max stacksize of the ItemStack
     *
     * @param newMaxStackSize The new max stack size of the Item.
     *
     * @docParam newMaxStackSize 16
     */
    @ZenCodeType.Method
    default IItemStack withMaxStackSize(int newMaxStackSize) {
        
        return modify(itemStack -> {
            itemStack.set(DataComponents.MAX_STACK_SIZE, newMaxStackSize);
        });
    }
    
    /**
     * Returns the rarity of the Item in the ItemStack
     *
     * @return Rarity of the Item.
     */
    @ZenCodeType.Method
    @ZenCodeType.Getter("rarity")
    default Rarity getRarity() {
        
        return getInternal().getRarity();
    }
    
    /**
     * Sets the rarity of the Item.
     *
     * @param newRarity The new rarity of the Item.
     *
     * @docParam newRarity Rarity.UNCOMMON
     */
    @ZenCodeType.Method
    default void setRarity(Rarity newRarity) {
        
        getInternal().set(DataComponents.RARITY, newRarity);
    }
    
    
    /**
     * Sets the lore of the ItemStack
     *
     * @param lore the new Lore of the ItemStack.
     *
     * @docParam lore [crafttweaker.api.text.Component.literal("I am the lore I speak for the trees")];
     */
    @ZenCodeType.Method
    default IItemStack withLore(@ZenCodeType.Nullable Component[] lore) {
        
        return modify(itemStack -> {
            if(lore == null) {
                itemStack.remove(DataComponents.LORE);
            } else {
                itemStack.set(DataComponents.LORE, new ItemLore(Arrays.asList(lore)));
            }
        });
    }
    
    /**
     * Gets the display name of the ItemStack
     *
     * @return formatted display name of the ItemStack.
     */
    @ZenCodeType.Getter("displayName")
    default Component getDisplayName() {
        
        return getInternal().getDisplayName();
    }
    
    /**
     * Sets the display name of the ItemStack
     *
     * @param name New name of the stack.
     *
     * @docParam name "totally not dirt"
     */
    @ZenCodeType.Method
    default IItemStack withDisplayName(Component name) {
        
        return modify(itemStack -> itemStack.set(DataComponents.ITEM_NAME, name));
    }
    
    /**
     * Gets the hover name of the ItemStack.
     *
     * <p>This will give the raw name without the formatting that 'displayName' applies. </p>
     *
     * @return The hover name of the ItemStack.
     */
    @ZenCodeType.Getter("hoverName")
    default Component getHoverName() {
        
        return getInternal().getHoverName();
    }
    
    /**
     * Returns true if this ItemStack has a foil effect.
     *
     * Foil is the glint / effect that is added to enchanted ItemStacks (and other items).
     *
     * @return true if this ItemStack has a foil effect.
     */
    @ZenCodeType.Getter("hasFoil")
    default boolean hasFoil() {
        
        return getInternal().hasFoil();
    }
    
    /**
     * Can this ItemStack be enchanted?
     *
     * @return true if this ItemStack can be enchanted.
     */
    @ZenCodeType.Getter("isEnchantable")
    default boolean isEnchantable() {
        
        return getInternal().isEnchantable();
    }
    
    /**
     * Is this ItemStack enchanted?
     *
     * @return true if this ItemStack is enchanted.
     */
    @ZenCodeType.Getter("isEnchanted")
    default boolean isEnchanted() {
        
        return getInternal().isEnchanted();
    }
    
    /**
     * Gets the amount of Items in the ItemStack
     *
     * @return ItemStack's amount
     */
    @ZenCodeType.Getter("amount")
    default int amount() {
        
        return getInternal().getCount();
    }
    
    /**
     * Sets the amount of the ItemStack
     *
     * @param amount new amount
     *
     * @docParam amount 3
     */
    @ZenCodeType.Operator(ZenCodeType.OperatorType.MUL)
    default IItemStack setAmount(int amount) {
        
        return modify(itemStack -> itemStack.setCount(amount));
    }
    
    /**
     * Grows this IItemStack's stack size by the given amount, or 1 if no amount is given.
     *
     * @param amount The amount to grow by.
     *
     * @return This IItemStack if mutable, a new one with the new amount otherwise.
     *
     * @docParam amount 2
     */
    @ZenCodeType.Method
    default IItemStack grow(@ZenCodeType.OptionalInt(1) int amount) {
        
        return setAmount(amount() + amount);
    }
    
    /**
     * Shrinks this IItemStack's stack size by the given amount, or 1 if no amount is given.
     *
     * @param amount The amount to shrink by.
     *
     * @return This IItemStack if mutable, a new one with the new amount otherwise.
     *
     * @docParam amount 2
     */
    @ZenCodeType.Method
    default IItemStack shrink(@ZenCodeType.OptionalInt(1) int amount) {
        
        return setAmount(amount() - amount);
    }
    
    /**
     * Returns if the ItemStack can have an amount greater than 1
     * I.E Swords and tools are not stackable, sticks are.
     *
     * @return true if this ItemStack can contain more than one item.
     */
    @ZenCodeType.Getter("stackable")
    default boolean isStackable() {
        
        return getInternal().isStackable();
    }
    
    /**
     * Sets the damage of the ItemStack
     *
     * @param damage the new damage value
     *
     * @docParam damage 10
     */
    @ZenCodeType.Method
    default IItemStack withDamage(int damage) {
        
        return modify(itemStack -> itemStack.setDamageValue(damage));
    }
    
    /**
     * Adds an AttributeModifier to this IItemStack using a specific UUID.
     *
     * The UUID can be used to override an existing attribute on an ItemStack with this new modifier.
     * You can use `/ct hand attributes` to get the UUID of the attributes on an ItemStack.
     *
     * Attributes added with this method will only appear on this specific IItemStack.
     *
     * By defaults, adding a modifier will remove the default Attribute Modifiers on the Item, like the Diamond Chestplate's Armor and Toughness values.
     * When `preserveDefaults` is set to true (by default it is false.), the default Attribute Modifiers will be preserved when adding this modifier.
     * This means that if you were adding the `forge:nametag_distance` attribute to an Item, it would keep its default attributes (like Armor and Toughness values).
     *
     * @param uuid             The unique identifier of the modifier to replace.
     * @param attribute        The Attribute of the modifier.
     * @param name             The name of the modifier.
     * @param value            The value of the modifier.
     * @param operation        The operation of the modifier.
     * @param slotTypes        What slots the modifier is valid for.
     * @param preserveDefaults Should the default Item Attribute Modifiers be preserved when adding this modifier.
     *
     * @docParam attribute <attribute:minecraft:generic.attack_damage>
     * @docParam uuid "8c1b5535-9f79-448b-87ae-52d81480aaa3"
     * @docParam name "Extra Power"
     * @docParam value 10
     * @docParam operation AttributeOperation.ADDITION
     * @docParam slotTypes [<constant:minecraft:equipmentslot:chest>]
     * @docParam preserveDefaults true
     */
    @ZenCodeType.Method
    default IItemStack withAttributeModifier(Attribute attribute, String uuid, String name, double value, AttributeModifier.Operation operation, EquipmentSlot[] slotTypes, @ZenCodeType.OptionalBoolean boolean preserveDefaults) {
        
        return withAttributeModifier(attribute, UUID.fromString(uuid), name, value, operation, slotTypes, preserveDefaults);
    }
    
    /**
     * Adds an AttributeModifier to this IItemStack using a specific UUID.
     *
     * The UUID can be used to override an existing attribute on an ItemStack with this new modifier.
     * You can use `/ct hand attributes` to get the UUID of the attributes on an ItemStack.
     *
     * Attributes added with this method will only appear on this specific IItemStack.
     *
     * By defaults, adding a modifier will remove the default Attribute Modifiers on the Item, like the Diamond Chestplate's Armor and Toughness values.
     * When `preserveDefaults` is set to true (by default it is false.), the default Attribute Modifiers will be preserved when adding this modifier.
     * This means that if you were adding the `forge:nametag_distance` attribute to an Item, it would keep its default attributes (like Armor and Toughness values).
     *
     * @param uuid             The unique identifier of the modifier to replace.
     * @param attribute        The Attribute of the modifier.
     * @param name             The name of the modifier.
     * @param value            The value of the modifier.
     * @param operation        The operation of the modifier.
     * @param slotTypes        What slots the modifier is valid for.
     * @param preserveDefaults Should the default Item Attribute Modifiers be preserved when adding this modifier.
     *
     * @docParam attribute <attribute:minecraft:generic.attack_damage>
     * @docParam uuid "8c1b5535-9f79-448b-87ae-52d81480aaa3"
     * @docParam name "Extra Power"
     * @docParam value 10
     * @docParam operation AttributeOperation.ADDITION
     * @docParam slotTypes [<constant:minecraft:equipmentslot:chest>]
     * @docParam preserveDefaults true
     */
    @ZenCodeType.Method
    default IItemStack withAttributeModifier(Attribute attribute, UUID uuid, String name, double value, AttributeModifier.Operation operation, EquipmentSlot[] slotTypes, @ZenCodeType.OptionalBoolean boolean preserveDefaults) {
        
        return modify(itemStack -> {
            //TODO 1.20.5 fix
            //            for(EquipmentSlot slotType : slotTypes) {
            //                if(preserveDefaults) {
            //                    AttributeUtil.addAttributeModifier(itemStack, attribute, new AttributeModifier(uuid, name, value, operation), slotType);
            //                } else {
            //                    itemStack.addAttributeModifier(attribute, new AttributeModifier(uuid, name, value, operation), slotType);
            //                }
            //            }
        });
    }
    
    /**
     * Adds an AttributeModifier to this IItemStack.
     *
     * The UUID can be used to override an existing attribute on an ItemStack with this new modifier.
     * You can use `/ct hand attributes` to get the UUID of the attributes on an ItemStack.
     *
     * Attributes added with this method will only appear on this specific IItemStack.
     *
     * By defaults, adding a modifier will remove the default Attribute Modifiers on the Item, like the Diamond Chestplate's Armor and Toughness values.
     * When `preserveDefaults` is set to true (by default it is false.), the default Attribute Modifiers will be preserved when adding this modifier.
     * This means that if you were adding the `forge:nametag_distance` attribute to an Item, it would keep its default attributes (like Armor and Toughness values).
     *
     * @param attribute        The Attribute of the modifier.
     * @param name             The name of the modifier.
     * @param value            The value of the modifier.
     * @param operation        The operation of the modifier.
     * @param slotTypes        What slots the modifier is valid for.
     * @param preserveDefaults Should the default Item Attribute Modifiers be preserved when adding this modifier.
     *
     * @docParam attribute <attribute:minecraft:generic.attack_damage>
     * @docParam name "Extra Power"
     * @docParam value 10
     * @docParam operation AttributeOperation.ADDITION
     * @docParam slotTypes [<constant:minecraft:equipmentslot:chest>]
     * @docParam preserveDefaults true
     */
    @ZenCodeType.Method
    default IItemStack withAttributeModifier(Attribute attribute, String name, double value, AttributeModifier.Operation operation, EquipmentSlot[] slotTypes, @ZenCodeType.OptionalBoolean boolean preserveDefaults) {
        
        return modify(itemStack -> {
            //TODO 1.20.5
            //            for(EquipmentSlot slotType : slotTypes) {
            //                if(preserveDefaults) {
            //                    AttributeUtil.addAttributeModifier(itemStack, attribute, new AttributeModifier(name, value, operation), slotType);
            //                } else {
            //                    itemStack.addAttributeModifier(attribute, new AttributeModifier(name, value, operation), slotType);
            //                }
            //            }
        });
    }
    
    /**
     * Gets the Attributes and the AttributeModifiers on this IItemStack for the given EquipmentSlot
     *
     * @param slotType The slot to get the Attributes for.
     *
     * @return A Map of Attribute to a List of AttributeModifier for the given EquipmentSlot.
     *
     * @docParam slotType <constant:minecraft:equipmentslot:chest>
     */
    @ZenCodeType.Method
    default Map<Attribute, List<AttributeModifier>> getAttributes(EquipmentSlot slotType) {
        
        // I don't think we expose Collection, so just convert it to a list.
        Map<Attribute, List<AttributeModifier>> ret = new HashMap<>();
        getInternal().forEachModifier(slotType, (attributeHolder, attributeModifier) -> ret.computeIfAbsent(attributeHolder.value(), attribute -> new ArrayList<>())
                .add(attributeModifier));
        return ret;
    }
    
    /**
     * Returns if the ItemStack is damageable
     * I.E Swords and tools are damageable, sticks are not.
     *
     * @return true if this ItemStack can take damage.
     */
    @ZenCodeType.Getter("damageableItem")
    default boolean isDamageableItem() {
        
        return getInternal().isDamageableItem();
    }
    
    /**
     * Returns if the ItemStack is damaged
     * I.E a Swords that is no at full durability is damaged.
     *
     * @return true if this ItemStack is damaged.
     */
    @ZenCodeType.Getter("damaged")
    default boolean isDamaged() {
        
        return getInternal().isDamaged();
    }
    
    /**
     * Returns the max damage of the ItemStack
     * This is the max durability of the ItemStack.
     *
     * @return The ItemStack's max durability.
     */
    @ZenCodeType.Getter("maxDamage")
    default int getMaxDamage() {
        
        return getInternal().getMaxDamage();
    }
    
    /**
     * Sets the max damage of the ItemStack.
     *
     * Setting the damage to `0` will make the item unbreakable.
     *
     * @param newMaxDamage The new max damage of the ItemStack
     *
     * @docParam maxDamage 5
     */
    @ZenCodeType.Method
    default IItemStack setMaxDamage(int newMaxDamage) {
        
        return modify(itemStack -> itemStack.set(DataComponents.MAX_DAMAGE, newMaxDamage));
    }
    
    /**
     * Returns the unlocalized Name of the Item in the ItemStack
     *
     * @return the unlocalized name.
     */
    @ZenCodeType.Getter("descriptionId")
    default String getDescriptionId() {
        
        return getInternal().getDescriptionId();
    }
    
    /**
     * Sets the tag for the ItemStack.
     *
     * @param tag The tag to set.
     *
     * @return This itemStack if it is mutable, a new one with the changed property otherwise
     *
     * @docParam tag {Display: {lore: ["Hello"]}}
     */
    @ZenCodeType.Method
    default IItemStack withTag(MapData tag) {
        
        return modify(itemStack -> itemStack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag.getInternal())));
    }
    
    /**
     * Removes the tag from this ItemStack.
     *
     * @return This itemStack if it is mutable, a new one with the changed property otherwise
     */
    @ZenCodeType.Method
    default IItemStack withoutTag() {
        
        return modify(itemStack -> itemStack.remove(DataComponents.CUSTOM_DATA));
    }
    
    //TODO 1.20.5 do we want this?
    
    /**
     * Returns true if this ItemStack has a Tag
     *
     * @return true if tag is present.
     */
    @ZenCodeType.Getter("hasTag")
    default boolean hasTag() {
        
        return getInternal().has(DataComponents.CUSTOM_DATA);
    }
    
    /**
     * Returns the NBT tag attached to this ItemStack.
     *
     * @return MapData of the ItemStack NBT Tag, null if it doesn't exist.
     */
    @ZenCodeType.Nullable
    @ZenCodeType.Getter("tag")
    default IData getTag() {
        
        CustomData customData = getInternal().get(DataComponents.CUSTOM_DATA);
        if(customData == null) {
            return null;
        }
        //noinspection deprecation
        return TagToDataConverter.convert(customData.getUnsafe());
    }
    
    //TODO 1.20.5, have this just return an empty CustomData, not set it?
    //    /**
    //     * Returns the NBT tag attached to this ItemStack or makes a new tag.
    //     *
    //     * @return MapData of the ItemStack NBT Tag, empty tag if it doesn't exist.
    //     */
    //    @ZenCodeType.Method
    //    default IData getOrCreateTag() {
    //        if(getInternal().getTag() == null) {
    //            getInternal().setTag(new CompoundTag());
    //        }
    //        return getTag();
    //    }
    
    @Override
    default boolean matches(IItemStack stack) {
        
        return ItemStackUtil.areStacksTheSame(this.getInternal(), stack.getInternal(), true) && this.conditions()
                .test(stack);
    }
    
    @Override
    default String getCommandString() {
        
        return ItemStackUtil.getCommandString(this.getInternal(), this.isMutable());
    }
    
    /**
     * Gets the use duration of the ItemStack
     *
     * @return use duration
     */
    @ZenCodeType.Getter("useDuration")
    default int getUseDuration() {
        
        return getInternal().getUseDuration();
    }
    
    /**
     * Returns true if this stack is considered a crossbow item
     *
     * @return true if stack is a crossbow
     */
    @ZenCodeType.Getter("useOnRelease")
    default boolean useOnRelease() {
        
        return getInternal().useOnRelease();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("food")
    @ZenCodeType.Nullable
    default FoodProperties getFood() {
        
        return getInternal().get(DataComponents.FOOD);
    }
    
    @ZenCodeType.Method
    default IItemStack withFood(@ZenCodeType.Nullable FoodProperties food) {
        
        return modify(itemStack -> {
            if(food == null) {
                getInternal().remove(DataComponents.FOOD);
            } else {
                getInternal().set(DataComponents.FOOD, food);
            }
        });
    }
    
    @ZenCodeType.Getter("burnTime")
    default int getBurnTime() {
        
        return Services.EVENT.getBurnTime(this);
    }
    
    /**
     * Checks if this IItemStack burns when thrown into fire / lava or damaged by fire.
     *
     * @return True if this IItemStack is immune to fire. False otherwise.
     */
    @ZenCodeType.Getter("fireResistant")
    default boolean isFireResistant() {
        
        return getInternal().has(DataComponents.FIRE_RESISTANT);
    }
    
    /**
     * Sets if this IItemStack is immune to fire / lava.
     *
     * If true, the item will not burn when thrown into fire or lava.
     *
     * @param fireResistant Should the item be immune to fire.
     *
     * @docParam immuneToFire true
     */
    @ZenCodeType.Method
    default IItemStack setFireResistant(boolean fireResistant) {
        
        return modify(itemStack -> itemStack.set(DataComponents.FIRE_RESISTANT, Unit.INSTANCE));
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Operator(ZenCodeType.OperatorType.MOD)
    default Percentaged<IItemStack> percent(double percentage) {
        
        return new Percentaged<>(this, percentage / 100.0D, iItemStack -> iItemStack.getCommandString() + " % " + percentage);
    }
    
    //    @ZenCodeType.Method
    //    default WeightedEntry.Wrapper<IItemStack> weight(double weight) {
    //
    //        return new WeightedEntry.Wrapper<>(this, Weight.of(weight));
    //    }
    
    @ZenCodeType.Caster(implicit = true)
    default Percentaged<IItemStack> asWeightedItemStack() {
        
        return percent(100.0D);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("definition")
    @ZenCodeType.Caster(implicit = true)
    default Item getDefinition() {
        
        return getInternal().getItem();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Caster(implicit = true)
    default ItemLike asItemLike() {
        
        return getInternal().getItem();
    }
    
    @ZenCodeType.Method
    IItemStack asMutable();
    
    @ZenCodeType.Method
    IItemStack asImmutable();
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("isImmutable")
    boolean isImmutable();
    
    @ZenCodeType.Method
    @ZenCodeType.Getter("isMutable")
    default boolean isMutable() {
        
        return !isImmutable();
    }
    
    
    @ZenCodeType.Getter("damage")
    default int getDamage() {
        
        return getInternal().getDamageValue();
    }
    
    //TODO 1.20.5 make this return ItemEnchantments? I think it solves a lot of our issues
    //    @ZenCodeType.Method
    //    @ZenCodeType.Getter("enchantments")
    //    default Map<Enchantment, Integer> getEnchantments() {
    //
    //        return EnchantmentHelper.getEnchantments(getInternal());
    //    }
    
    /**
     * Sets the enchantments on this IItemStack.
     *
     * @param enchantments The new enchantments
     *
     * @return This itemStack if it is mutable, a new one with the enchantments otherwise
     */
    @ZenCodeType.Method
    @ZenCodeType.Setter("enchantments")
    default IItemStack setEnchantments(Map<Enchantment, Integer> enchantments) {
        
        return modify(newStack -> EnchantmentUtil.setEnchantments(enchantments, newStack));
    }
    
    //TODO 1.20.5 remove?
    //    /**
    //     * Gets the level of the given enchantment on the item. Returns 0 if the item doesn't have the given enchantment.
    //     */
    //    @ZenCodeType.Method
    //    default int getEnchantmentLevel(Enchantment enchantment) {
    //
    //        return getEnchantments().getOrDefault(enchantment, 0);
    //    }
    
    /**
     * Enchants this IItemStack with the given Enchantment.
     *
     * @param enchantment The enchantment to add.
     * @param level       The level of the enchantment
     *
     * @return This itemStack if it is mutable, a new one with the enchantment added otherwise
     *
     * @docParam enchantment <enchantment:minecraft:riptide>
     * @docParam level 2
     */
    @ZenCodeType.Method
    default IItemStack withEnchantment(Enchantment enchantment, @ZenCodeType.OptionalInt(1) int level) {
        
        return modify(itemStack -> {
            ItemEnchantments.Mutable enchantments = new ItemEnchantments.Mutable(itemStack.getEnchantments());
            enchantments.set(enchantment, level);
            itemStack.set(DataComponents.ENCHANTMENTS, enchantments.toImmutable());
        });
    }
    
    /**
     * Removes the given enchantment from this IItemStack.
     *
     * @param enchantment The enchantment to remove.
     *
     * @return This itemStack if it is mutable, a new one with the enchantment removed otherwise
     *
     * @docParam enchantment <enchantment:minecraft:riptide>
     */
    @ZenCodeType.Method
    default IItemStack removeEnchantment(Enchantment enchantment) {
        
        return modify(itemStack -> {
            ItemEnchantments.Mutable enchantments = new ItemEnchantments.Mutable(itemStack.getEnchantments());
            enchantments.removeIf(enchantmentHolder -> enchantmentHolder.value() == enchantment);
            itemStack.set(DataComponents.ENCHANTMENTS, enchantments.toImmutable());
        });
    }
    
    /**
     * Gets the internal {@link ItemStack} for this IItemStack.
     *
     * @return internal ItemStack
     */
    @ZenCodeType.Method
    @ZenCodeType.Caster(implicit = true)
    ItemStack getInternal();
    
    @Override
    default Ingredient asVanillaIngredient() {
        
        if(getInternal().isEmpty()) {
            return Ingredient.EMPTY;
        }
        
        return IngredientIItemStack.ingredient(this);
    }
    
    @ZenCodeType.Method
    default ItemStack getImmutableInternal() {
        
        return getInternal().copy();
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Caster(implicit = true)
    default IIngredientWithAmount asIIngredientWithAmount() {
        
        return this;
    }
    
    @Override
    default IItemStack ingredient() {
        
        return this;
    }
    
    @Override
    default IData asIData() {
        
        final IData data = IIngredient.super.asIData();
        assert data instanceof MapData;
        data.put("count", new IntData(this.amount()));
        return data;
    }
    
    @Override
    default IIngredient transform(IIngredientTransformer transformer) {
        
        return modifyThis(iItemStack -> iItemStack.transformers().add(transformer));
    }
    
    @Override
    default IIngredient condition(IIngredientCondition condition) {
        
        return modifyThis(iItemStack -> iItemStack.conditions().add(condition));
    }
    
    IItemStack modify(Consumer<ItemStack> stackModifier);
    
    IItemStack modifyThis(Consumer<IItemStack> modifier);
    
    @Override
    default DataComponentMap getComponents() {
        
        return getInternal().getComponents();
    }
    
    @Nullable
    @Override
    default <T> T get(DataComponentType<? extends T> type) {
        
        return getInternal().get(type);
    }
    
    @Override
    default <T> T getOrDefault(DataComponentType<? extends T> type, T value) {
        
        return getInternal().getOrDefault(type, value);
    }
    
    @Override
    default boolean has(DataComponentType<?> type) {
        
        return getInternal().has(type);
    }
    
}
