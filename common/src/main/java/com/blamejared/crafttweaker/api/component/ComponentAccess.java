package com.blamejared.crafttweaker.api.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.platform.Services;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.google.common.collect.Lists;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.network.Filterable;
import net.minecraft.util.Unit;
import net.minecraft.world.LockCode;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.AdventureModePredicate;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.DebugStickState;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.item.component.MapDecorations;
import net.minecraft.world.item.component.MapItemColor;
import net.minecraft.world.item.component.MapPostProcessing;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.item.component.SeededContainerLoot;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.item.component.WritableBookContent;
import net.minecraft.world.item.component.WrittenBookContent;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.PotDecorations;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.Nullable;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * An interface exposing methods to handle setting and removing DataComponents.
 *
 * DataComponents are the new way of storing data in memory, instead of writing it to NBT data and to disk every time, which has a performance cost.
 * DataComponents now occupy the space that was previously occupied or known as a NBT tag.
 *
 * DataComponents are immutable. Operations that set or remove a component return a new instance of the object implementing {@link ComponentAccess}
 *
 * DataComponents may optionally be persistent. They also may optionally be syncronised.
 *
 * In most cases, ComponentAccesses are going to be ItemStacks, although in NeoForge FluidStacks are too.
 */
@ZenRegister
@ZenCodeType.Name("crafttweaker.api.component.ComponentAccess")
@Document("vanilla/api/component/ComponentAccess")
public interface ComponentAccess<T extends ComponentAccess<T>> {
    
    /**
     * Checks whether the ComponentAccess has the &lt;componenttype:minecraft:custom_data&gt;
     *
     * @return Whether the componenttype is stored.
     */
    // <editor-fold desc="CUSTOM_DATA">
    @ZenCodeType.Getter("hasCustomData")
    default boolean hasCustomData() {
        
        return _has(DataComponents.CUSTOM_DATA);
    }
    
    /**
     * Gets the data in the &lt;componenttype:minecraft:custom_data&gt;
     *
     * @return A {@link CustomData} that wraps around a {@link MapData} object containing the data.
     */
    @ZenCodeType.Getter("customData")
    default CustomData getCustomData() {
        
        return _get(DataComponents.CUSTOM_DATA);
    }
    
    /**
     * Sets the <componenttype:minecraft:custom_data> of the ComponentAccess to have the given {@link MapData}
     *
     * @param customData The MapData to set the component to.
     *
     * @return The new instance with the modified data.
     *
     * @docParam customData {custom_ammo_thing: 1, owner: "Benji"}
     */
    @ZenCodeType.Method
    default T withCustomData(MapData customData) {
        
        return withCustomData(CustomData.of(customData.getInternal()));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:custom_data&gt; of the ComponentAccess to have the given {@link CustomData}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withCustomData(CustomData customData) {
        
        return _with(DataComponents.CUSTOM_DATA, customData);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:custom_data&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutCustomData() {
        
        return _without(DataComponents.CUSTOM_DATA);
    }
    
    /**
     * Checks whether the ComponentAccess has the &lt;componenttype:minecraft:max_stack_size&gt;
     *
     * @return Whether the componenttype is stored.
     */
    //</editor-fold>
    // <editor-fold desc="MAX_STACK_SIZE">
    @ZenCodeType.Getter("hasMaxStackSize")
    default boolean hasMaxStackSize() {
        
        return _has(DataComponents.MAX_STACK_SIZE);
    }
    
    /**
     * Gets the data in the &lt;componenttype:minecraft:max_stack_size&gt;
     *
     * @return An int with the max stack size of the ComponentAccess.
     */
    @ZenCodeType.Getter("maxStackSize")
    default int getMaxStackSize() {
        
        return _get(DataComponents.MAX_STACK_SIZE);
    }
    
    
    /**
     * Sets the &lt;componenttype:minecraft:stack_size&gt; of the ComponentAccess to have the given value.
     * Non standard stack sizes may render differently and may not be handled correctly by all mods. Test your changes!
     *
     * @param maxStackSize The new maxStackSize of the ComponentAccess.
     *
     * @return The new instance with the modified data.
     *
     * @docParam maxStackSize 16
     */
    @ZenCodeType.Method
    default T withMaxStackSize(int maxStackSize) {
        
        return _with(DataComponents.MAX_STACK_SIZE, maxStackSize);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:stack_size&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutMaxStackSize() {
        
        return _without(DataComponents.MAX_STACK_SIZE);
    }
    
    /**
     * Checks whether the ComponentAccess has the &lt;componenttype:minecraft:max_damage&gt;
     *
     * @return Whether the componenttype is stored.
     */
    //</editor-fold>
    // <editor-fold desc="MAX_DAMAGE">
    @ZenCodeType.Getter("hasMaxDamage")
    default boolean hasMaxDamage() {
        
        return _has(DataComponents.MAX_DAMAGE);
    }
    
    /**
     * Gets the data in the &lt;componenttype:minecraft:max_damage&gt;
     *
     * @return An int returning the maximum durability, damage or uses.
     */
    @ZenCodeType.Getter("maxDamage")
    default int getMaxDamage() {
        
        return _get(DataComponents.MAX_DAMAGE);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:max_damage&gt; of the ComponentAccess to have the given value.
     *
     * It is likely that you need to call withDamage(0) in order for some things to function properly.
     *
     * @docParam maxDamage 1024
     * @return The new instance with the modified data.
     *
     * @docParam maxDamage 1024
     */
    @ZenCodeType.Method
    default T withMaxDamage(int maxDamage) {
        
        return _with(DataComponents.MAX_DAMAGE, maxDamage);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:max_damage&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutMaxDamage() {
        
        return _without(DataComponents.MAX_DAMAGE);
    }
    
    /**
     * Checks whether the ComponentAccess has the &lt;componenttype:minecraft:damage&gt;
     *
     * @return Whether the componenttype is stored.
     */
    //</editor-fold>
    // <editor-fold desc="DAMAGE">
    @ZenCodeType.Getter("hasDamage")
    default boolean hasDamage() {
        
        return _has(DataComponents.DAMAGE);
    }
    
    /**
     * Gets the data in the &lt;componenttype:minecraft:damage&gt;
     *
     * @return An int returning the current durability, damage or uses.
     */
    @ZenCodeType.Getter("damage")
    default int getDamage() {
        
        return _get(DataComponents.DAMAGE);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:damage&gt; of the ComponentAccess to have the given value.
     * Damage is related to durability. Using this on an item that has no maxDamage may have unforeseen issues.
     *
     * @param damage The new damage of the ComponentAccess.
     *
     * @return The new instance with the modified data.
     *
     * @docParam damage 16
     */
    @ZenCodeType.Method
    default T withDamage(int damage) {
        
        return _with(DataComponents.DAMAGE, damage);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:damage&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutDamage() {
        
        return _without(DataComponents.DAMAGE);
    }
    
    /**
     * Checks whether the ComponentAccess has the &lt;componenttype:minecraft:unbreakable&gt;
     *
     * @return Whether the componenttype is stored.
     */
    //</editor-fold>
    // <editor-fold desc="UNBREAKABLE">
    @ZenCodeType.Getter("hasUnbreakable")
    default boolean hasUnbreakable() {
        
        return _has(DataComponents.UNBREAKABLE);
    }
    
    /**
     * Gets the data in the &lt;componenttype:minecraft:unbreakable&gt;
     *
     * @return An {@link Unbreakable} containing the configuration of the Component.
     */
    @ZenCodeType.Getter("unbreakable")
    default Unbreakable getUnbreakable() {
        
        return _get(DataComponents.UNBREAKABLE);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:unbreakable&gt; of the ComponentAccess.
     * The existance of the component makes the ComponentAccess unbreakable.
     *
     * @param showInTooltip Whether to show that the ComponentAccess is unbreakable in a tooltip.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withUnbreakable(@ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return withUnbreakable(new Unbreakable(showInTooltip));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:unbreakable&gt; of the ComponentAccess.
     * The existance of the component makes the ComponentAccess unbreakable.
     *
     * @param unbreakable The {@link Unbreakable} instance with the configured values.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withUnbreakable(Unbreakable unbreakable) {
        
        return _with(DataComponents.UNBREAKABLE, unbreakable);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:unbreakable&gt; from the ComponentAccess
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutUnbreakable() {
        
        return _without(DataComponents.UNBREAKABLE);
    }
    
    /**
     * Checks whether the ComponentAccess has the &lt;componenttype:minecraft:custom_name&gt;
     *
     * @return Whether the componenttype is stored.
     */
    //</editor-fold>
    // <editor-fold desc="CUSTOM_NAME">
    @ZenCodeType.Getter("hasCustomName")
    default boolean hasCustomName() {
        
        return _has(DataComponents.CUSTOM_NAME);
    }
    
    /**
     * Gets the data in the &lt;componenttype:minecraft:custom_name&gt;
     *
     * A custom name is generally displayed in italics and controlled by the user.
     *
     * @return A {@link Component} representing the ComponentAccess's custom name.
     */
    @ZenCodeType.Getter("customName")
    default Component getCustomName() {
        
        return _get(DataComponents.CUSTOM_NAME);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:custom_name&gt; of the ComponentAccess.
     *
     * @param name The component to use.
     *
     * @return The new instance with the modified data.
     *
     * @docParam name Component.literal("Fancy Water")
     * @docParam name Component.translatable("mypack.lore.fancy_water")
     */
    @ZenCodeType.Method
    default T withCustomName(Component name) {
        
        return _with(DataComponents.CUSTOM_NAME, name);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:custom_name&gt; from the ComponentAccess
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutCustomName() {
        
        return _without(DataComponents.CUSTOM_NAME);
    }
    
    /**
     * Checks whether the ComponentAccess has the &lt;componenttype:minecraft:item_namegt;
     *
     * @return Whether the componenttype is stored.
     */
    //</editor-fold>
    // <editor-fold desc="ITEM_NAME">
    @ZenCodeType.Getter("hasItemName")
    default boolean hasItemName() {
        
        return _has(DataComponents.ITEM_NAME);
    }
    
    /**
     * Gets the data in the &lt;componenttype:minecraft:custom_name&gt;
     *
     * In the case of items, an item name is set by the make to make a special instance of an item, such as with
     * ominous banners.
     *
     * @return A {@link Component} representing the ComponentAccess's custom name.
     */
    @ZenCodeType.Getter("itemName")
    default Component getItemName() {
        
        return _get(DataComponents.ITEM_NAME);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:item_name&gt; of the ComponentAccess to have the given {@link Component}
     *
     * @return The new instance with the modified data.
     *
     * @return The new instance with the modified data.
     *
     * @docParam name Component.literal("Fancy Water")
     * @docParam name Component.translatable("mypack.lore.fancy_water")
     */
    @ZenCodeType.Method
    default T withItemName(Component name) {
        
        return _with(DataComponents.ITEM_NAME, name);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:item_name&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutItemName() {
        
        return _without(DataComponents.ITEM_NAME);
    }
    
    /**
     * Checks whether the ComponentAccess has the &lt;componenttype:minecraft:lore&gt;
     *
     * @return Whether the componenttype is stored.
     */
    //</editor-fold>
    // <editor-fold desc="LORE">
    @ZenCodeType.Getter("hasLore")
    default boolean hasLore() {
        
        return _has(DataComponents.LORE);
    }
    
    /**
     * Gets the data in the &lt;componenttype:minecraft:lore&gt;
     *
     * @return A {@link ItemLore} instance, which effectively behaves as a stdlib.List&lt;{@link Component}&gt;.
     */
    @ZenCodeType.Getter("lore")
    default ItemLore getLore() {
        
        return _get(DataComponents.LORE);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:lore&gt; of the ComponentAccess to have the given stdlib.List&lt;{@link Component}&gt;
     *
     * @param components The list of Components to add as lore.
     *
     * @return The new instance with the modified data.
     *
     * @docParam components [Component.literal("Found in a dark cave"), Component.translatable("Belonged to a Dragon")]
     */
    @ZenCodeType.Method
    default T withLore(List<Component> components) {
        
        return withLore(new ItemLore(components));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:lore&gt; of the ComponentAccess to have the given {@link ItemLore}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withLore(ItemLore lore) {
        
        return _with(DataComponents.LORE, lore);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:lore&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutLore() {
        
        return _without(DataComponents.LORE);
    }
    
    /**
     * Checks whether the ComponentAccess has the &lt;componenttype:minecraft:rarity&gt;
     *
     * @return Whether the componenttype is stored.
     */
    //</editor-fold>
    // <editor-fold desc="RARITY">
    @ZenCodeType.Getter("hasRarity")
    default boolean hasRarity() {
        
        return _has(DataComponents.RARITY);
    }
    
    /**
     * Gets the data in the &lt;componenttype:minecraft:rarity&gt;
     *
     * @return A {@link Rarity}
     */
    @ZenCodeType.Getter("rarity")
    default Rarity getRarity() {
        
        return _get(DataComponents.RARITY);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:rarity&gt; of the ComponentAccess to have the given {@link Rarity}
     *
     * @param rarity The rarity to set
     *
     * @return The new instance with the modified data.
     *
     * @docParam rarity <constant:minecraft:item/rarity:epic>
     */
    @ZenCodeType.Method
    default T withRarity(Rarity rarity) {
        
        return _with(DataComponents.RARITY, rarity);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:rarity&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutRarity() {
        
        return _without(DataComponents.RARITY);
    }
    
    /**
     * Checks whether the ComponentAccess has the &lt;componenttype:minecraft:enchantments&gt;
     *
     * @return Whether the componenttype is stored.
     */
    //</editor-fold>
    // <editor-fold desc="ENCHANTMENTS">
    @ZenCodeType.Getter("hasEnchantments")
    default boolean hasEnchantments() {
        
        return _has(DataComponents.ENCHANTMENTS);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:enchantments&gt;
     *
     * @return A {@link ItemEnchantments} instance
     */
    @ZenCodeType.Getter("enchantments")
    default ItemEnchantments getEnchantments() {
        
        return _get(DataComponents.ENCHANTMENTS);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:enchantments&gt; of the ComponentAccess to have the given {@link ItemEnchantments}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withEnchantments(ItemEnchantments enchantments) {
        
        return _with(DataComponents.ENCHANTMENTS, enchantments);
    }
    
    
    /**
     * Sets the &lt;componenttype:minecraft:enchantments&gt; of the ComponentAccess to have the given {@link Enchantment} and level.
     *
     * @param enchantment The enchantment to add
     * @param level       The level of the enchantment to set
     *
     * @return The new instance with the modified data.
     *
     * @docParam enchantment <enchantment:minecraft:efficiency>
     * @docParam level 4
     */
    @ZenCodeType.Method
    default T withEnchantment(Enchantment enchantment, @ZenCodeType.OptionalInt(1) int level) {
        
        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(hasEnchantments() ? getEnchantments() : ItemEnchantments.EMPTY);
        mutable.set(Services.REGISTRY.holderOrThrow(Registries.ENCHANTMENT, enchantment), level);
        return withEnchantments(mutable.toImmutable());
    }
    
    /**
     * Removes the {@link Enchantment} from the ComponentAccess.
     *
     * @param enchantment The {@link Enchantment} to remove
     *
     * @return The new instance with the modified data.
     *
     * @docParam enchantment <enchantment:minecraft:mending>
     */
    @ZenCodeType.Method
    default T withoutEnchantment(Enchantment enchantment) {
        
        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(hasEnchantments() ? getEnchantments() : ItemEnchantments.EMPTY);
        mutable.removeIf(enchantmentHolder -> enchantmentHolder.value() == enchantment);
        return withEnchantments(mutable.toImmutable());
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:enchantments&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutEnchantments() {
        
        return _without(DataComponents.ENCHANTMENTS);
    }
    
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:can_place_on&gt;
     *
     * @return Whether the componenttype is stored.
     */
    //</editor-fold>
    // <editor-fold desc="CAN_PLACE_ON">
    @ZenCodeType.Getter("hasCanPlaceOn")
    default boolean hasCanPlaceOn() {
        
        return _has(DataComponents.CAN_PLACE_ON);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:can_place_on&gt;
     *
     * @return An {@link AdventureModePredicate} instance that holds data on whether a Block is placeable in adventure mode.
     */
    @ZenCodeType.Getter("canPlaceOn")
    default AdventureModePredicate getCanPlaceOn() {
        
        return _get(DataComponents.CAN_PLACE_ON);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:can_place_on&gt; with the given BlockPredicates.
     *
     * Any predicate that matches will allow the BlockItem within the ItemStack to be placed.
     *
     * @param predicates    The collection of predicates to test for
     * @param showInTooltip Whether to show the restriction in the tooltip or not.
     *
     * @return The new instance with the modified data.
     *
     * @docParam predicates BlockPredicate.create().of(<block:minecraft:dirt>).build()
     */
    @ZenCodeType.Method
    default T withCanPlaceOn(List<BlockPredicate> predicates, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return withCanPlaceOn(new AdventureModePredicate(predicates, showInTooltip));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:can_place_on&gt; of the ComponentAccess to have the given {@link AdventureModePredicate}
     *
     * @param predicate The adventure mode predicate to use to determine whether the current block can be placed on another block.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withCanPlaceOn(AdventureModePredicate predicate) {
        
        return _with(DataComponents.CAN_PLACE_ON, predicate);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:can_place_on&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    
    @ZenCodeType.Method
    default T withoutCanPlaceOn() {
        
        return _without(DataComponents.CAN_PLACE_ON);
    }
    
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:can_break&gt;
     *
     * @return Whether the componenttype is stored.
     */
    //</editor-fold>
    // <editor-fold desc="CAN_BREAK">
    @ZenCodeType.Getter("hasCanBreak")
    default boolean hasCanBreak() {
        
        return _has(DataComponents.CAN_BREAK);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:can_break&gt;
     *
     * @return An {@link AdventureModePredicate} instance that holds data on whether a Block is breakable using this Item.
     */
    @ZenCodeType.Getter("canBreak")
    default AdventureModePredicate getCanBreak() {
        
        return _get(DataComponents.CAN_BREAK);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:can_break&gt; with the given BlockPredicates.
     *
     * Any predicate that matches will allow the Block to be broken by this ItemStack.
     *
     * @param predicates    The collection of predicates to test for
     * @param showInTooltip Whether to show the ability in the tooltip or not.
     *
     * @return The new instance with the modified data.
     *
     * @docParam predicates BlockPredicate.create().of(<block:minecraft:diamond_ore>).build()
     * @docParam showInTooltip true
     */
    @ZenCodeType.Method
    default T withCanBreak(List<BlockPredicate> predicates, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return withCanBreak(new AdventureModePredicate(predicates, showInTooltip));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:can_break&gt; of the ComponentAccess to have the given {@link AdventureModePredicate}
     *
     * @param predicate The adventure mode predicate to use to determine whether the item can be used to break a block.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withCanBreak(AdventureModePredicate predicate) {
        
        return _with(DataComponents.CAN_BREAK, predicate);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:can_break&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutCanBreak() {
        
        return _without(DataComponents.CAN_BREAK);
    }
    
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:attribute_modifiers&gt;
     *
     * @return Whether the componenttype is stored.
     */
    //</editor-fold>
    // <editor-fold desc="ATTRIBUTE_MODIFIERS">
    @ZenCodeType.Getter("hasAttributeModifiers")
    default boolean hasAttributeModifiers() {
        
        return _has(DataComponents.ATTRIBUTE_MODIFIERS);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:attribute_modifiers&gt;
     *
     * @return An {@link ItemAttributeModifiers}.
     */
    @ZenCodeType.Getter("attributeModifiers")
    default ItemAttributeModifiers getAttributeModifiers() {
        
        return _get(DataComponents.ATTRIBUTE_MODIFIERS);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:attribute_modifiers&gt; of the ComponentAccess to have the given {@link ItemAttributeModifiers}
     *
     * @param modifiers The attributes to give to the item.
     *
     * @return The new instance with the modified data.
     *
     * @docParam modifiers ItemAttributeModifiers.builder().add(<attribute:minecraft:player.block_break_speed>,
     * AttributeModifier.create("test", 2.0, <constant:minecraft:attribute/operation:add_value>, "596e0826-7c66-42c6-b3da-45a6d667ccf7"),
     * <constant:minecraft:equipmentslot/group:mainhand>).build();
     */
    @ZenCodeType.Method
    default T withAttributeModifiers(ItemAttributeModifiers modifiers) {
        
        return _with(DataComponents.ATTRIBUTE_MODIFIERS, modifiers);
    }
    
    /**
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withAttributeModifiers(ItemAttributeModifiers.Entry modifier, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return _with(DataComponents.ATTRIBUTE_MODIFIERS, new ItemAttributeModifiers(List.of(modifier), showInTooltip));
    }
    
    @ZenCodeType.Method
    default T withAttributeModifiers(List<ItemAttributeModifiers.Entry> modifiers, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return _with(DataComponents.ATTRIBUTE_MODIFIERS, new ItemAttributeModifiers(modifiers, showInTooltip));
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:attribute_modifiers&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutAttributeModifiers() {
        
        return _without(DataComponents.ATTRIBUTE_MODIFIERS);
    }
    
    //</editor-fold>
    // <editor-fold desc="CUSTOM_MODEL_DATA">
    
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:custom_model_data&gt;
     *
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasCustomModelData")
    default boolean hasCustomModelData() {
        
        return _has(DataComponents.CUSTOM_MODEL_DATA);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:custom_model_data&gt;
     *
     * @return A {@link CustomModelData}
     */
    @ZenCodeType.Getter("customModelData")
    default CustomModelData getCustomModelData() {
        
        return _get(DataComponents.CUSTOM_MODEL_DATA);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:custom_data&gt; of the ComponentAccess to have the given {@link CustomData}
     *
     * @param value The value to set the CustomModelData to
     *
     * @return The new instance with the modified data.
     *
     * @docParam value 2
     */
    @ZenCodeType.Method
    default T withCustomModelData(int value) {
        
        return withCustomModelData(new CustomModelData(value));
    }
    
    /**
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withCustomModelData(CustomModelData data) {
        
        return _with(DataComponents.CUSTOM_MODEL_DATA, data);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:custom_model_data&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutCustomModelData() {
        
        return _without(DataComponents.CUSTOM_MODEL_DATA);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:hide_additional_tooltip&gt;
     *
     * @return A boolean representing whether to hide the additional tooltip.
     */
    //</editor-fold>
    // <editor-fold desc="HIDE_ADDITIONAL_TOOLTIP">
    @ZenCodeType.Getter("hideAdditionalTooltip")
    default boolean hideAdditionalTooltip() {
        
        return _has(DataComponents.HIDE_ADDITIONAL_TOOLTIP);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:hide_additional_tooltip&gt; of the ComponentAccess to exist.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withHideAdditionalTooltip() {
        
        return _with(DataComponents.HIDE_ADDITIONAL_TOOLTIP, Unit.INSTANCE);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:hide_additional_tooltip&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutHideAdditionalTooltip() {
        
        return _without(DataComponents.HIDE_ADDITIONAL_TOOLTIP);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:hide_tooltip&gt;
     *
     * @return A boolean representing whether to hide the tooltip.
     */
    //</editor-fold>
    // <editor-fold desc="HIDE_TOOLTIP">
    @ZenCodeType.Getter("hideTooltip")
    default boolean hideTooltip() {
        
        return _has(DataComponents.HIDE_TOOLTIP);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:hide_tooltip&gt; of the ComponentAccess to exist.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withHideTooltip() {
        
        return _with(DataComponents.HIDE_TOOLTIP, Unit.INSTANCE);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:hide_tooltip&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutHideTooltip() {
        
        return _without(DataComponents.HIDE_TOOLTIP);
    }
    
    //</editor-fold>
    // <editor-fold desc="REPAIR_COST">
    
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:repair_cost&gt;
     *
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasRepairCost")
    default boolean hasRepairCost() {
        
        return _has(DataComponents.REPAIR_COST);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:repair_cost&gt;
     *
     * @return An int representing the repair cost of the item. Used for anvil and grindstone xp.
     */
    @ZenCodeType.Getter("repairCost")
    default int repairCost() {
        
        return _get(DataComponents.REPAIR_COST);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:repair_cost&gt; of the ComponentAccess to have the given value.
     *
     * @param cost The repair cost
     *
     * @return The new instance with the modified data.
     *
     * @docParam cost 20
     */
    @ZenCodeType.Method
    default T withRepairCost(int cost) {
        
        return _with(DataComponents.REPAIR_COST, cost);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:repair_cost&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutRepairCost() {
        
        return _without(DataComponents.REPAIR_COST);
    }
    
    //</editor-fold>
    // <editor-fold desc="CREATIVE_SLOT_LOCK">
    
    /**
     * Gets the data in &lt;componenttype:minecraft:creative_slot_lock&gt;
     *
     * @return A boolean representing whether to disallow picking up the item from the creative menu.
     */
    @ZenCodeType.Getter("creativeSlotLock")
    default boolean creativeSlotLock() {
        
        return _has(DataComponents.CREATIVE_SLOT_LOCK);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:creative_slot_lock&gt; of the ComponentAccess to exist.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withCreativeSlotLock() {
        
        return _with(DataComponents.CREATIVE_SLOT_LOCK, Unit.INSTANCE);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:creative_slot_lock&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutCreativeSlotLock() {
        
        return _without(DataComponents.CREATIVE_SLOT_LOCK);
    }
    
    //</editor-fold>
    // <editor-fold desc="ENCHANTMENT_GLINT_OVERRIDE">
    
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:enchantment_glint_override&gt;
     *
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasEnchantmentGlintOverride")
    default boolean hasEnchantmentGlintOverride() {
        
        return _has(DataComponents.ENCHANTMENT_GLINT_OVERRIDE);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:enchantment_glint_override&gt;
     *
     * If it is present, it is first checked to determine whether there is special behaviour, otherwise
     * the code checks for enchantments.
     *
     * @return A boolean representing whether to override enchantment glint rendering.
     */
    @ZenCodeType.Getter("enchantmentGlintOverride")
    default boolean getEnchantmentGlintOverride() {
        
        return _get(DataComponents.ENCHANTMENT_GLINT_OVERRIDE);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:enchantment_glint_override&gt; of the ComponentAccess to have the given value.
     *
     * If it is false, the item will never render the enchantment glint
     * If it is true, the item will render the enchantment glint always, indenpendently of whether it is enchanted or not.
     *
     * @param value The value of the override, as described above
     *
     * @return The new instance with the modified data.
     *
     * @docParam value true
     */
    @ZenCodeType.Method
    default T withEnchantmentGlintOverride(boolean value) {
        
        return _with(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, value);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:enchantment_glint_override&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutEnchantmentGlintOverride() {
        
        return _without(DataComponents.ENCHANTMENT_GLINT_OVERRIDE);
    }
    
    //</editor-fold>
    // <editor-fold desc="INTANGIBLE_PROJECTILE">
    
    /**
     * Gets the data in &lt;componenttype:minecraft:intangible_projectile&gt;
     *
     * @return A boolean representing whether the projectile is intangible.
     */
    @ZenCodeType.Getter("isIntangibleProjectile")
    default boolean isIntangibleProjectile() {
        
        return _has(DataComponents.INTANGIBLE_PROJECTILE);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:intangible_projectile&gt; of the ComponentAccess to exist.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withIntangibleProjectile() {
        
        return _with(DataComponents.INTANGIBLE_PROJECTILE, Unit.INSTANCE);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:intangible_projectile&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutIntangibleProjectile() {
        
        return _without(DataComponents.INTANGIBLE_PROJECTILE);
    }
    
    //</editor-fold>
    // <editor-fold desc="FOOD">
    
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:food&gt;
     *
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasFood")
    default boolean hasFood() {
        
        return _has(DataComponents.FOOD);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:food&gt;
     *
     * @return A {@link FoodProperties} instance.
     */
    @ZenCodeType.Getter("food")
    default FoodProperties getFood() {
        
        return _get(DataComponents.FOOD);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:food&gt; of the ComponentAccess to have the given {@link FoodProperties}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withFood(FoodProperties food) {
        
        return _with(DataComponents.FOOD, food);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:food&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutFood() {
        
        return _without(DataComponents.FOOD);
    }
    
    //</editor-fold>
    // <editor-fold desc="FIRE_RESISTANT">
    
    /**
     * Gets the data in &lt;componenttype:minecraft:fire_resistant&gt;
     * @return A boolean representing whether to resist fire.
     */
    @ZenCodeType.Getter("isFireResistant")
    default boolean isFireResistant() {
        
        return _has(DataComponents.FIRE_RESISTANT);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:fire_resistant&gt; of the ComponentAccess to exist.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withFireResistant() {
        
        return _with(DataComponents.FIRE_RESISTANT, Unit.INSTANCE);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:fire_resistant&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutFireResistant() {
        
        return _without(DataComponents.FIRE_RESISTANT);
    }
    
    //</editor-fold>
    // <editor-fold desc="TOOL">
    
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:tool&gt;
     *
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasTool")
    default boolean hasTool() {
        
        return _has(DataComponents.TOOL);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:tool&gt;
     *
     * @return A {@link Tool} instance.
     */
    @ZenCodeType.Getter("tool")
    default Tool getTool() {
        
        return _get(DataComponents.TOOL);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:tool&gt; of the ComponentAccess to have the given data.
     * A {@link Tool} instance is constructed from the parameters.
     *
     * @param rules The list of rules the new Tool instance will have
     * @param defaultMiningSpeed The default mining speed of the new tool
     * @param damagePerBlock The damage or durability that is dealt to the Tool after use.
     *
     * @docParam rules [ToolRule.minesAndDrops(<tag:blocks:minecraft:planks>, 6.0f)]
     * @docParam defaultMiningSpeed 0.25f
     * @docParam damagePerBlock 1
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withTool(List<Tool.Rule> rules, float defaultMiningSpeed, int damagePerBlock) {
        
        return withTool(new Tool(rules, defaultMiningSpeed, damagePerBlock));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:tool&gt; of the ComponentAccess to have the given {@link Tool}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withTool(Tool tool) {
        
        return _with(DataComponents.TOOL, tool);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:tool&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutTool() {
        
        return _without(DataComponents.TOOL);
    }
    
    //</editor-fold>
    // <editor-fold desc="STORED_ENCHANTMENTS">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:stored_enchantments&gt;
     *
     * The stored enchantments component is only present in enchanted books, since it just stores the enchantments
     * under a lid for future use. The item is not enchanted.
     *
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasStoredEnchantments")
    default boolean hasStoredEnchantments() {
        
        return _has(DataComponents.STORED_ENCHANTMENTS);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:stored_enchantments&gt;
     * @return An {@link ItemEnchantments} instance.
     */
    @ZenCodeType.Getter("storedEnchantments")
    default ItemEnchantments getStoredEnchantments() {
        
        return _get(DataComponents.STORED_ENCHANTMENTS);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:stored_enchantments&gt; of the ComponentAccess to have the given {@link ItemEnchantments}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withStoredEnchantments(ItemEnchantments enchantments) {
        
        return _with(DataComponents.STORED_ENCHANTMENTS, enchantments);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:stored_enchantments&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutStoredEnchantments() {
        
        return _without(DataComponents.STORED_ENCHANTMENTS);
    }
    
    //</editor-fold>
    // <editor-fold desc="DYED_COLOR">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:dyed_color&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasDyedColor")
    default boolean hasDyedColor() {
        
        return _has(DataComponents.DYED_COLOR);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:dye_color&gt;
     * @return A {@link DyedItemColor} instance.
     */
    @ZenCodeType.Getter("dyedColor")
    default DyedItemColor getDyedColor() {
        
        return _get(DataComponents.DYED_COLOR);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:dyed_color&gt; of the ComponentAccess.
     *
     * @param rgb The colour to dye this item with
     * @param showInTooltip Whether to show this information in a tooltip
     *
     * @docParam rgb 0xFFFF00
     * @docParam showInTooltip true
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withDyedColor(int rgb, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return withDyedColor(new DyedItemColor(rgb, showInTooltip));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:dyed_color&gt; of the ComponentAccess to have the given {@link DyedItemColor}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withDyedColor(DyedItemColor color) {
        
        return _with(DataComponents.DYED_COLOR, color);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:dyed_color&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutDyedColor() {
        
        return _without(DataComponents.DYED_COLOR);
    }
    
    //</editor-fold>
    // <editor-fold desc="MAP_COLOR">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:map_color&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasMapColor")
    default boolean hasMapColor() {
        
        return _has(DataComponents.MAP_COLOR);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:map_color&gt;
     * @return A {@link MapItemColor} that stores an RGB color..
     */
    @ZenCodeType.Getter("mapColor")
    default MapItemColor getMapColor() {
        
        return _get(DataComponents.MAP_COLOR);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:map_color&gt; of the ComponentAccess.
     *
     * @param rgb The new map colour
     *
     * @docParam rgb 0xFF0000
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withMapColor(int rgb) {
        
        return withMapColor(new MapItemColor(rgb));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:map_color&gt; of the ComponentAccess to have the given {@link MapItemColor}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withMapColor(MapItemColor color) {
        
        return _with(DataComponents.MAP_COLOR, color);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:map_color&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutMapColor() {
        
        return _without(DataComponents.MAP_COLOR);
    }
    
    //</editor-fold>
    // <editor-fold desc="MAP_ID">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:map_id&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasMapId")
    default boolean hasMapId() {
        
        return _has(DataComponents.MAP_ID);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:map_id&gt;
     * @return A {@link MapId}
     */
    @ZenCodeType.Getter("mapId")
    default MapId getMapId() {
        
        return _get(DataComponents.MAP_ID);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:map_id&gt; of the ComponentAccess to have the given value.
     *
     * A map id is only useful after the game generates one to store data.
     * Therefore, you should always pass in the parameter for an id you know is available.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withMapId(int id) {
        
        return withMapId(new MapId(id));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:map_id&gt; of the ComponentAccess to have the given {@link MapId}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withMapId(MapId mapId) {
        
        return _with(DataComponents.MAP_ID, mapId);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:map_id&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutMapId() {
        
        return _without(DataComponents.MAP_ID);
    }
    
    //</editor-fold>
    // <editor-fold desc="MAP_DECORATIONS">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:map_decorations&gt;
     *
     * MapDecorations are additional elements that render on a map, such as waypoints.
     *
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasMapDecorations")
    default boolean hasMapDecorations() {
        
        return _has(DataComponents.MAP_DECORATIONS);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:custom_data&gt;
     * @return A {@link MapDecorations}.
     */
    @ZenCodeType.Getter("mapDecorations")
    default MapDecorations getMapDecorations() {
        
        return _get(DataComponents.MAP_DECORATIONS);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:map_decorations&gt; of the ComponentAccess to have the given values.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withMapDecorations(Map<String, MapDecorations.Entry> decorations) {
        
        return withMapDecorations(new MapDecorations(decorations));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:map_decorations&gt; of the ComponentAccess to have the given {@link MapDecorations}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withMapDecorations(MapDecorations decorations) {
        
        return _with(DataComponents.MAP_DECORATIONS, decorations);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:map_decorations&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutMapDecorations() {
        
        return _without(DataComponents.MAP_DECORATIONS);
    }
    
    //</editor-fold>
    // <editor-fold desc="MAP_POST_PROCESSING">
    
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:map_post_processing&gt;
     *
     * This componenet stores additional info on the map, such as the scale or whether to lock it.
     *
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasMapPostProcessing")
    default boolean hasMapPostProcessing() {
        
        return _has(DataComponents.MAP_POST_PROCESSING);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:map_post_processing&gt;
     * @return A {@link MapPostProcessing}
     */
    @ZenCodeType.Getter("mapPostProcessing")
    default MapPostProcessing getMapPostProcessing() {
        
        return _get(DataComponents.MAP_POST_PROCESSING);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:map_post_processing&gt; of the ComponentAccess to have the given {@link MapPostProcessing}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withMapPostProcessing(MapPostProcessing value) {
        
        return _with(DataComponents.MAP_POST_PROCESSING, value);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:map_post_processing&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutMapPostProcessing() {
        
        return _without(DataComponents.MAP_POST_PROCESSING);
    }
    
    //</editor-fold>
    // <editor-fold desc="CHARGED_PROJECTILES">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:charged_projectiles&gt;
     *
     * This component is used for crossbows.
     *
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasChargedProjectiles")
    default boolean hasChargedProjectiles() {
        
        return _has(DataComponents.CHARGED_PROJECTILES);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:custom_data&gt;
     * @return A {@link ChargedProjectiles}.
     */
    @ZenCodeType.Getter("chargedProjectiles")
    default ChargedProjectiles getChargedProjectiles() {
        
        return _get(DataComponents.CHARGED_PROJECTILES);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:charged_projectiles&gt; of the ComponentAccess to have the given {@link IItemStack}
     *
     * @param item The item to store within.
     *
     * @docParam item <item:minecraft:arrow>
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withChargedProjectiles(IItemStack item) {
        
        return withChargedProjectiles(ChargedProjectiles.of(item.getInternal()));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:custom_data&gt; of the ComponentAccess to have the given items.
     *
     * @param items The item to store within.
     *
     * @docParam items [<item:minecraft:arrow>]
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withChargedProjectiles(List<IItemStack> items) {
        
        return withChargedProjectiles(ChargedProjectiles.of(Lists.transform(items, IItemStack::getInternal)));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:charged_projectiles&gt; of the ComponentAccess to have the given {@link ChargedProjectiles}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withChargedProjectiles(ChargedProjectiles chargedProjectiles) {
        
        return _with(DataComponents.CHARGED_PROJECTILES, chargedProjectiles);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:charged_projectiles&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutChargedProjectiles() {
        
        return _without(DataComponents.CHARGED_PROJECTILES);
    }
    
    //</editor-fold>
    // <editor-fold desc="BUNDLE_CONTENTS">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:bundle_contents&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasBundleContents")
    default boolean hasBundleContents() {
        
        return _has(DataComponents.BUNDLE_CONTENTS);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:bundle_contents&gt;
     * @return A {@link BundleContents}.
     */
    @ZenCodeType.Getter("bundleContents")
    default BundleContents getBundleContents() {
        
        return _get(DataComponents.BUNDLE_CONTENTS);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:bundle_contents&gt; of the ComponentAccess to have the given contents
     *
     * @param contents A list of items to store within.
     *
     * @docParam contents [<item:minecraft:diamond> * 64]
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withBundleContents(List<IItemStack> contents) {
        
        return _with(DataComponents.BUNDLE_CONTENTS, new BundleContents(Lists.transform(contents, IItemStack::getInternal)));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:bundle_contents&gt; of the ComponentAccess to have the given {@link BundleContents}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withBundleContents(BundleContents contents) {
        
        return _with(DataComponents.BUNDLE_CONTENTS, contents);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:bundle_contents&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutBundleContents() {
        
        return _without(DataComponents.BUNDLE_CONTENTS);
    }
    
    //</editor-fold>
    // <editor-fold desc="POTION_CONTENTS">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:potion_contents&gt;
     *
     * Stores Potion Data on an item.
     *
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasPotionContents")
    default boolean hasPotionContents() {
        
        return _has(DataComponents.POTION_CONTENTS);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:potion_contents&gt;
     * @return A {@link PotionContents}.
     */
    @ZenCodeType.Getter("potionContents")
    default PotionContents getPotionContents() {
        
        return _get(DataComponents.POTION_CONTENTS);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:potion_contents&gt; of the ComponentAccess to have the given {@link Potion}
     * and no custom effects
     *
     * @param potion The potion to store within
     *
     * @docParam potion <potion:minecraft:swiftness>
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withPotionContents(Potion potion) {
        
        
        return withPotionContents(new PotionContents(Services.REGISTRY.holderOrThrow(Registries.POTION, potion)));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:potion_contents&gt; of the ComponentAccess to have the given {@link Potion}
     * and the given {@link MobEffectInstance} effects.
     *
     * @param potion The potion to store within.
     * @param customEffects The effects to grant upon consuming this potion.
     *
     * @docParam potion <potion:minecraft:swiftness>
     * @docParam customEffects [MobEffectInstance.of(<mobeffect:minecraft:strength>, 20 * 20, 1, false, false, true)]
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withPotionContents(Potion potion, List<MobEffectInstance> customEffects) {
        
        return withPotionContents(new PotionContents(Optional.of(Services.REGISTRY.holderOrThrow(Registries.POTION, potion)), Optional.empty(), customEffects));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:potion_contents&gt; of the ComponentAccess to have the given {@link Potion}
     * and the given {@link MobEffectInstance} effects.
     *
     * @param potion The potion to store within.
     * @param customColor The ARGB color the potion will have.
     * @param customEffects The effects to grant upon consuming this potion.
     *
     * @docParam potion <potion:minecraft:swiftness>
     * @docParam customColor 0xFFFFFF00
     * @docParam customEffects [MobEffectInstance.of(<mobeffect:minecraft:strength>, 20 * 20, 1, false, false, true)]
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withPotionContents(Potion potion, int customColor, List<MobEffectInstance> customEffects) {
        
        return withPotionContents(new PotionContents(Optional.of(Services.REGISTRY.holderOrThrow(Registries.POTION, potion)), Optional.of(customColor), customEffects));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:potion_contents&gt; of the ComponentAccess to have the given {@link PotionContents}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withPotionContents(PotionContents contents) {
        
        return _with(DataComponents.POTION_CONTENTS, contents);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:potion_contents&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutPotionContents() {
        
        return _without(DataComponents.POTION_CONTENTS);
    }
    
    //</editor-fold>
    // <editor-fold desc="SUSPICIOUS_STEW_EFFECTS">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:suspicious_stew_effects&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasSuspiciousStewEffects")
    default boolean hasSuspiciousStewEffects() {
        
        return _has(DataComponents.SUSPICIOUS_STEW_EFFECTS);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:suspicious_stew_effects&gt;
     * @return A {@link SuspiciousStewEffects}.
     */
    @ZenCodeType.Getter("suspiciousStewEffects")
    default SuspiciousStewEffects getSuspiciousStewEffects() {
        
        return _get(DataComponents.SUSPICIOUS_STEW_EFFECTS);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:suspicious_stew_effects&gt; of the ComponentAccess to have the given {@link SuspiciousStewEffects}
     * {@link SuspiciousStewEffects.Entry} is effectively a pair of Effect and duration.
     * 
     * @param effects The list of effects to grant on consumption.
     * 
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withSuspiciousStewEffects(List<SuspiciousStewEffects.Entry> effects) {
        
        return withSuspiciousStewEffects(new SuspiciousStewEffects(effects));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:suspicious_stew_effects&gt; of the ComponentAccess to have the given {@link SuspiciousStewEffects}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withSuspiciousStewEffects(SuspiciousStewEffects suspiciousStewEffects) {
        
        return _with(DataComponents.SUSPICIOUS_STEW_EFFECTS, suspiciousStewEffects);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:suspicious_stew_effects&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutSuspiciousStewEffects() {
        
        return _without(DataComponents.SUSPICIOUS_STEW_EFFECTS);
    }
    
    //</editor-fold>
    // <editor-fold desc="WRITABLE_BOOK_CONTENT">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:writable_book_content&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasWritableBookContent")
    default boolean hasWritableBookContent() {
        
        return _has(DataComponents.WRITABLE_BOOK_CONTENT);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:writable_book_content&gt;
     * @return A {@link WritableBookContent}.
     */
    @ZenCodeType.Getter("writableBookContent")
    default WritableBookContent getWritableBookContent() {
        
        return _get(DataComponents.WRITABLE_BOOK_CONTENT);
    }
    
    /**
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withWritableBookContent(List<Filterable<String>> pages) {
        
        return withWritableBookContent(new WritableBookContent(pages));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:writable_book_content&gt; of the ComponentAccess to have the given {@link WritableBookContent}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withWritableBookContent(WritableBookContent content) {
        
        return _with(DataComponents.WRITABLE_BOOK_CONTENT, content);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:writable_book_content&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutWritableBookContent() {
        
        return _without(DataComponents.WRITABLE_BOOK_CONTENT);
    }
    
    //</editor-fold>
    // <editor-fold desc="WRITTEN_BOOK_CONTENT">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:written_book_content&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasWrittenBookContent")
    default boolean hasWrittenBookContent() {
        
        return _has(DataComponents.WRITABLE_BOOK_CONTENT);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:written_book_content&gt;
     * @return A {@link WrittenBookContent}.
     */
    @ZenCodeType.Getter("writtenBookContent")
    default WrittenBookContent getWrittenBookContent() {
        
        return _get(DataComponents.WRITTEN_BOOK_CONTENT);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:written_book_content&gt; of the ComponentAccess to have the given {@link WrittenBookContent}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withWrittenBookContent(WrittenBookContent content) {
        
        return _with(DataComponents.WRITTEN_BOOK_CONTENT, content);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:written_book_content&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutWrittenBookContent() {
        
        return _without(DataComponents.WRITTEN_BOOK_CONTENT);
    }
    
    //</editor-fold>
    // <editor-fold desc="TRIM">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:trim&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasTrim")
    default boolean hasTrim() {
        
        return _has(DataComponents.TRIM);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:trim&gt;
     * @return A {@link ArmorTrim}.
     */
    @ZenCodeType.Getter("trim")
    default ArmorTrim getTrim() {
        
        return _get(DataComponents.TRIM);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:trim&gt; of the ComponentAccess to have the given {@link ArmorTrim}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withTrim(ArmorTrim trim) {
        
        return _with(DataComponents.TRIM, trim);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:trim&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutTrim() {
        
        return _without(DataComponents.TRIM);
    }
    
    //</editor-fold>
    // <editor-fold desc="DEBUG_STICK_STATE">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:debug_stick_state&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasDebugStickState")
    default boolean hasDebugStickState() {
        
        return _has(DataComponents.DEBUG_STICK_STATE);
    }
    /**
     * Gets the data in &lt;componenttype:minecraft:debug_stick_state&gt;
     * @return A {@link DebugStickState}.
     */
    @ZenCodeType.Getter("debugStickState")
    default DebugStickState getDebugStickState() {
        
        return _get(DataComponents.DEBUG_STICK_STATE);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:debug_stick_state&gt; of the ComponentAccess to have the given {@link DebugStickState}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withDebugStickState(DebugStickState state) {
        
        return _with(DataComponents.DEBUG_STICK_STATE, state);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:debug_stick_state&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutDebugStickState() {
        
        return _without(DataComponents.DEBUG_STICK_STATE);
    }
    
    //</editor-fold>
    // <editor-fold desc="ENTITY_DATA">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:entity_data&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasEntityData")
    default boolean hasEntityData() {
        
        return _has(DataComponents.ENTITY_DATA);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:entity_data&gt;
     * @return A {@link CustomData}.
     */
    @ZenCodeType.Getter("entityData")
    default CustomData getEntityData() {
        
        return _get(DataComponents.ENTITY_DATA);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:entity_data&gt; of the ComponentAccess to have the given {@link MapData}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withEntityData(MapData data) {
        
        return withEntityData(CustomData.of(data.getInternal()));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:entity_data&gt; of the ComponentAccess to have the given {@link CustomData}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withEntityData(CustomData data) {
        
        return _with(DataComponents.ENTITY_DATA, data);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:entity_data&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutEntityDate() {
        
        return _without(DataComponents.ENTITY_DATA);
    }
    
    //</editor-fold>
    // <editor-fold desc="BUCKET_ENTITY_DATA">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:bucket_entity_data&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasBucketEntityData")
    default boolean hasBucketEntityData() {
        
        return _has(DataComponents.BUCKET_ENTITY_DATA);
    }
    /**
     * Gets the data in &lt;componenttype:minecraft:bucket_entity_data&gt;
     * @return A {@link CustomData}.
     */
    @ZenCodeType.Getter("bucketEntityData")
    default CustomData getBucketEntityData() {
        
        return _get(DataComponents.BUCKET_ENTITY_DATA);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:bucket_entity_data&gt; of the ComponentAccess to have the given {@link MapData}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withBucketEntityData(MapData data) {
        
        return withBucketEntityData(CustomData.of(data.getInternal()));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:bucket_entity_data&gt; of the ComponentAccess to have the given {@link CustomData}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withBucketEntityData(CustomData data) {
        
        return _with(DataComponents.BUCKET_ENTITY_DATA, data);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:bucket_entity_data&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutBucketEntityData() {
        
        return _without(DataComponents.BUCKET_ENTITY_DATA);
    }
    
    //</editor-fold>
    // <editor-fold desc="BLOCK_ENTITY_DATA">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:block_entity_data&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasBlockEntityData")
    default boolean hasBlockEntityData() {
        
        return _has(DataComponents.BLOCK_ENTITY_DATA);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:block_entity_data&gt;
     * @return A {@link CustomData}.
     */
    @ZenCodeType.Getter("blockEntityData")
    default CustomData getBlockEntityData() {
        
        return _get(DataComponents.BLOCK_ENTITY_DATA);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:block_entity_data&gt; of the ComponentAccess to have the given {@link MapData}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withBlockEntityData(MapData data) {
        
        return withBlockEntityData(CustomData.of(data.getInternal()));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:block_entity_data&gt; of the ComponentAccess to have the given {@link CustomData}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withBlockEntityData(CustomData data) {
        
        return _with(DataComponents.BLOCK_ENTITY_DATA, data);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:block_entity_data&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutBlockEntityData() {
        
        return _without(DataComponents.BLOCK_ENTITY_DATA);
    }
    
    //</editor-fold>
    // <editor-fold desc="INSTRUMENT">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:instrument&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasInstrument")
    default boolean hasInstrument() {
        
        return _has(DataComponents.INSTRUMENT);
    }
    /**
     * Gets the data in &lt;componenttype:minecraft:instrument&gt;
     * @return A {@link Instrument}.
     */
    @ZenCodeType.Getter("instrument")
    default Instrument getInstrument() {
        
        return _get(DataComponents.INSTRUMENT).value();
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:instrument&gt; of the ComponentAccess to have the given {@link Instrument}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withInstrument(Instrument instrument) {
        
        return _with(DataComponents.INSTRUMENT, Services.REGISTRY.holderOrThrow(Registries.INSTRUMENT, instrument));
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:instrument&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutInstrument() {
        
        return _without(DataComponents.INSTRUMENT);
    }
    
    //</editor-fold>
    // <editor-fold desc="OMINOUS_BOTTLE_AMPLIFIER">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:ominous_bottle_amplifier&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasOminousBottleAmplifier")
    default boolean hasOminousBottleAmplifier() {
        
        return _has(DataComponents.OMINOUS_BOTTLE_AMPLIFIER);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:ominous_bottle_amplifier&gt;
     * @return The amplifier number.
     */
    @ZenCodeType.Getter("ominousBottleAmplifier")
    default int getOminousBottleAmplifier() {
        
        return _get(DataComponents.OMINOUS_BOTTLE_AMPLIFIER);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:ominous_bottle_amplifier&gt; of the ComponentAccess to have the given amplifier.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withOminousBottleAmplifier(int amplifier) {
        
        return _with(DataComponents.OMINOUS_BOTTLE_AMPLIFIER, amplifier);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:ominous_bottle_amplifier&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutOminousBottleAmplifier() {
        
        return _without(DataComponents.OMINOUS_BOTTLE_AMPLIFIER);
    }
    
    //</editor-fold>
    // <editor-fold desc="RECIPES">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:recipes&gt;
     *
     * Used for Knowledge Books.
     *
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasRecipes")
    default boolean hasRecipes() {
        
        return _has(DataComponents.RECIPES);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:recipes&gt;
     * @return A {@link List<ResourceLocation>}.
     */
    @ZenCodeType.Getter("recipes")
    default List<ResourceLocation> getRecipes() {
        
        return _get(DataComponents.RECIPES);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:recipes&gt; of the ComponentAccess to have the given {@link List<ResourceLocation>}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withRecipes(List<ResourceLocation> recipes) {
        
        return _with(DataComponents.RECIPES, recipes);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:recipes&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutRecipes() {
        
        return _without(DataComponents.RECIPES);
    }
    
    //</editor-fold>
    // <editor-fold desc="LODESTONE_TRACKER">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:lodestone_tracker&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasLodestoneTracker")
    default boolean hasLodestoneTracker() {
        
        return _has(DataComponents.LODESTONE_TRACKER);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:lodestone_tracker&gt;
     * @return A {@link LodestoneTracker}.
     */
    @ZenCodeType.Getter("lodestoneTracker")
    default LodestoneTracker getLodestoneTracker() {
        
        return _get(DataComponents.LODESTONE_TRACKER);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:lodestone_tracker&gt; of the ComponentAccess to have the given {@link LodestoneTracker}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withLodestoneTracker(LodestoneTracker tracker) {
        
        return _with(DataComponents.LODESTONE_TRACKER, tracker);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:lodestone_tracker&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutLodestoneTracker() {
        
        return _without(DataComponents.LODESTONE_TRACKER);
    }
    
    //</editor-fold>
    // <editor-fold desc="FIREWORK_EXPLOSION">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:firework_explosion&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasFireworkExplosion")
    default boolean hasFireworkExplosion() {
        
        return _has(DataComponents.FIREWORK_EXPLOSION);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:firework_explosion&gt;
     * @return A {@link FireworkExplosion}.
     */
    @ZenCodeType.Getter("fireworkExplosion")
    default FireworkExplosion getFireworkExplosion() {
        
        return _get(DataComponents.FIREWORK_EXPLOSION);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:firework_explosion&gt; of the ComponentAccess to have the given {@link FireworkExplosion}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withFireworkExplosion(FireworkExplosion explosion) {
        
        return _with(DataComponents.FIREWORK_EXPLOSION, explosion);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:firework_explosion&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutFireworkExplosion() {
        
        return _without(DataComponents.FIREWORK_EXPLOSION);
    }
    
    //</editor-fold>
    // <editor-fold desc="FIREWORKS">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:fireworks&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasFireworks")
    default boolean hasFireworks() {
        
        return _has(DataComponents.FIREWORKS);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:fireworks&gt;
     * @return A {@link Fireworks}.
     */
    @ZenCodeType.Getter("fireworks")
    default Fireworks getFireworks() {
        
        return _get(DataComponents.FIREWORKS);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:fireworks&gt; of the ComponentAccess to have the given duration and explosions.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withFireworks(int flightDuration, List<FireworkExplosion> explosions) {
        
        return withFireworks(new Fireworks(flightDuration, explosions));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:fireworks&gt; of the ComponentAccess to have the given {@link Fireworks}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withFireworks(Fireworks fireworks) {
        
        return _with(DataComponents.FIREWORKS, fireworks);
    }
    
    
    /**
     * Removes the &lt;componenttype:minecraft:fireworks&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutFireworks() {
        
        return _without(DataComponents.FIREWORKS);
    }
    
    //</editor-fold>
    // <editor-fold desc="PROFILE">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:profile&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasProfile")
    default boolean hasProfile() {
        
        return _has(DataComponents.PROFILE);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:profile&gt;
     * @return A {@link ResolvableProfile}.
     */
    @ZenCodeType.Getter("profile")
    default ResolvableProfile getProfile() {
        
        return _get(DataComponents.PROFILE);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:profile&gt; of the ComponentAccess to have the given {@link ResolvableProfile}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withProfile(ResolvableProfile profile) {
        
        return _with(DataComponents.PROFILE, profile);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:profile&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutProfile() {
        
        return _without(DataComponents.PROFILE);
    }
    
    //</editor-fold>
    // <editor-fold desc="NOTE_BLOCK_SOUND">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:note_block_sound&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasNoteBlockSound")
    default boolean hasNoteBlockSound() {
        
        return _has(DataComponents.NOTE_BLOCK_SOUND);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:note_block_sound&gt;
     * @return A {@link ResourceLocation}.
     */
    @ZenCodeType.Getter("noteBlockSound")
    default ResourceLocation getNoteBlockSound() {
        
        return _get(DataComponents.NOTE_BLOCK_SOUND);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:note_block_sound&gt; of the ComponentAccess to have the given {@link ResourceLocation}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withNoteBlockSound(ResourceLocation sound) {
        
        return _with(DataComponents.NOTE_BLOCK_SOUND, sound);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:note_block_sound&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutNoteBlockSound() {
        
        return _without(DataComponents.NOTE_BLOCK_SOUND);
    }
    
    //</editor-fold>
    // <editor-fold desc="BANNER_PATTERNS">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:banner_patterns&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasBannerPatterns")
    default boolean hasBannerPatterns() {
        
        return _has(DataComponents.BANNER_PATTERNS);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:banner_patterns&gt;
     * @return A {@link BannerPatternLayers}.
     */
    @ZenCodeType.Getter("bannerPatterns")
    default BannerPatternLayers getBannerPatterns() {
        
        return _get(DataComponents.BANNER_PATTERNS);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:banner_patterns&gt; of the ComponentAccess to have the given {@link List<BannerPatternLayers.Layer>}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withBannerPatterns(List<BannerPatternLayers.Layer> layers) {
        
        return withBannerPatterns(new BannerPatternLayers(layers));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:banner_patterns&gt; of the ComponentAccess to have the given {@link BannerPatternLayers}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withBannerPatterns(BannerPatternLayers layers) {
        
        return _with(DataComponents.BANNER_PATTERNS, layers);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:banner_patterns&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutBannerPatterns() {
        
        return _without(DataComponents.BANNER_PATTERNS);
    }
    
    //</editor-fold>
    // <editor-fold desc="BASE_COLOR">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:base_color&gt;
     *
     * Used for shield colouring.
     *
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasBaseColor")
    default boolean hasBaseColor() {
        
        return _has(DataComponents.BASE_COLOR);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:base_color&gt;
     * @return A {@link DyeColor}.
     */
    @ZenCodeType.Getter("baseColor")
    default DyeColor getBaseColor() {
        
        return _get(DataComponents.BASE_COLOR);
    }
    
    
    /**
     * Sets the &lt;componenttype:minecraft:base_color&gt; of the ComponentAccess to have the given {@link DyeColor}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withBaseColor(DyeColor color) {
        
        return _with(DataComponents.BASE_COLOR, color);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:base_color&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutBaseColor() {
        
        return _without(DataComponents.BASE_COLOR);
    }
    
    //</editor-fold>
    // <editor-fold desc="POT_DECORATIONS">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:pot_decorations&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasPotDecorations")
    default boolean hasPotDecorations() {
        
        return _has(DataComponents.POT_DECORATIONS);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:pot_decorations&gt;
     * @return A {@link PotDecorations}.
     */
    @ZenCodeType.Getter("potDecorations")
    default PotDecorations getPotDecorations() {
        
        return _get(DataComponents.POT_DECORATIONS);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:pot_decorations&gt; of the ComponentAccess to have the given items.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withPotDecorations(Item back, Item left, Item right, Item front) {
        
        return withPotDecorations(new PotDecorations(back, left, right, front));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:pot_decorations&gt; of the ComponentAccess to have the given {@link PotDecorations}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withPotDecorations(PotDecorations decorations) {
        
        return _with(DataComponents.POT_DECORATIONS, decorations);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:pot_decorations&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutPotDecorations() {
        
        return _without(DataComponents.POT_DECORATIONS);
    }
    
    //</editor-fold>
    // <editor-fold desc="CONTAINER">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:container&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasContainer")
    default boolean hasContainer() {
        
        return _has(DataComponents.CONTAINER);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:container&gt;
     * @return A {@link ItemContainerContents}.
     */
    @ZenCodeType.Getter("container")
    default ItemContainerContents getContainer() {
        
        return _get(DataComponents.CONTAINER);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:container&gt; of the ComponentAccess to have the given values.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withContainer(List<IItemStack> contents) {
        
        return withContainer(ItemContainerContents.fromItems(Lists.transform(contents, IItemStack::getInternal)));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:container&gt; of the ComponentAccess to have the given {@link ItemContainerContents}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withContainer(ItemContainerContents contents) {
        
        return _with(DataComponents.CONTAINER, contents);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:container&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutContainer() {
        
        return _without(DataComponents.CONTAINER);
    }
    
    //</editor-fold>
    // <editor-fold desc="BLOCK_STATE">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:block_state&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasBlockState")
    default boolean hasBlockState() {
        
        return _has(DataComponents.BLOCK_STATE);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:block_state&gt;
     * @return A {@link BlockItemStateProperties}.
     */
    @ZenCodeType.Getter("blockState")
    default BlockItemStateProperties getBlockState() {
        
        return _get(DataComponents.BLOCK_STATE);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:block_state&gt; of the ComponentAccess to have the given {@link BlockItemStateProperties}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withBlockState(BlockItemStateProperties properties) {
        
        return _with(DataComponents.BLOCK_STATE, properties);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:block_state&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutBlockState() {
        
        return _without(DataComponents.BLOCK_STATE);
    }
    
    //</editor-fold>
    // <editor-fold desc="BEES">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:bees&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasBees")
    default boolean hasBees() {
        
        return _has(DataComponents.BEES);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:bees&gt;
     * @return A {@link List<BeehiveBlockEntity.Occupant>}.
     */
    @ZenCodeType.Getter("bees")
    default List<BeehiveBlockEntity.Occupant> getBees() {
        
        return _get(DataComponents.BEES);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:bees&gt; of the ComponentAccess to have the given occupants
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withBees(List<BeehiveBlockEntity.Occupant> occupants) {
        
        return _with(DataComponents.BEES, occupants);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:bees&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutBees() {
        
        return _without(DataComponents.BEES);
    }
    
    //</editor-fold>
    // <editor-fold desc="LOCK">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:lock&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasLock")
    default boolean hasLock() {
        
        return _has(DataComponents.LOCK);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:lock&gt;
     * @return A {@link LockCode}.
     */
    @ZenCodeType.Getter("lockComponent")
    default LockCode getLock() {
        
        return _get(DataComponents.LOCK);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:lock&gt; of the ComponentAccess to have the given string.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withLock(String code) {
        
        return withLock(new LockCode(code));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:lock&gt; of the ComponentAccess to have the given {@link LockCode}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withLock(LockCode lock) {
        
        return _with(DataComponents.LOCK, lock);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:lock&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutLock() {
        
        return _without(DataComponents.LOCK);
    }
    
    //</editor-fold>
    // <editor-fold desc="CONTAINER_LOOT">
    /**
     * Checks whether the ComponentAccess has &lt;componenttype:minecraft:container_loot&gt;
     * @return Whether the componenttype is stored.
     */
    @ZenCodeType.Getter("hasContainerLoot")
    default boolean hasContainerLoot() {
        
        return _has(DataComponents.CONTAINER_LOOT);
    }
    
    /**
     * Gets the data in &lt;componenttype:minecraft:container_loot&gt;
     * @return A {@link SeededContainerLoot}.
     */
    @ZenCodeType.Getter("containerLoot")
    default SeededContainerLoot getContainerLoot() {
        
        return _get(DataComponents.CONTAINER_LOOT);
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:container_loot&gt; of the ComponentAccess to have the given loot table and seed.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withContainerLoot(ResourceKey<LootTable> lootTable, long seed) {
        
        return withContainerLoot(new SeededContainerLoot(lootTable, seed));
    }
    
    /**
     * Sets the &lt;componenttype:minecraft:container_loot&gt; of the ComponentAccess to have the given {@link SeededContainerLoot}
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withContainerLoot(SeededContainerLoot loot) {
        
        return _with(DataComponents.CONTAINER_LOOT, loot);
    }
    
    /**
     * Removes the &lt;componenttype:minecraft:container_loot&gt; from the ComponentAccess.
     *
     * @return The new instance with the modified data.
     */
    @ZenCodeType.Method
    default T withoutContainerLoot() {
        
        return _without(DataComponents.CONTAINER_LOOT);
    }
    
    //</editor-fold>
    
    <U> U _get(DataComponentType<? extends U> componentType);
    
    <U> T _with(DataComponentType<U> componentType, @Nullable U value);
    
    <U> T _without(DataComponentType<U> componentType);
    
    <U> boolean _has(DataComponentType<U> componentType);
    
}