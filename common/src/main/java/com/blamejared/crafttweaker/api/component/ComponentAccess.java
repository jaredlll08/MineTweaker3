package com.blamejared.crafttweaker.api.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.google.common.collect.Lists;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
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

@ZenRegister
@ZenCodeType.Name("crafttweaker.api.component.ComponentAccess")
@Document("vanilla/api/component/ComponentAccess")
public interface ComponentAccess<T extends ComponentAccess<T>> {
    
    // <editor-fold desc="CUSTOM_DATA">
    @ZenCodeType.Getter("hasCustomData")
    default boolean hasCustomData() {
        
        return _has(DataComponents.CUSTOM_DATA);
    }
    
    @ZenCodeType.Getter("customData")
    default CustomData getCustomData() {
        
        return _get(DataComponents.CUSTOM_DATA);
    }
    
    @ZenCodeType.Method
    default T withCustomData(MapData customData) {
        
        return withCustomData(CustomData.of(customData.getInternal()));
    }
    
    @ZenCodeType.Method
    default T withCustomData(CustomData customData) {
        
        return _with(DataComponents.CUSTOM_DATA, customData);
    }
    
    @ZenCodeType.Method
    default T withoutCustomData() {
        
        return _without(DataComponents.CUSTOM_DATA);
    }
    
    //</editor-fold>
    // <editor-fold desc="MAX_STACK_SIZE">
    
    @ZenCodeType.Getter("hasMaxStackSize")
    default boolean hasMaxStackSize() {
        
        return _has(DataComponents.MAX_STACK_SIZE);
    }
    
    @ZenCodeType.Getter("maxStackSize")
    default int getMaxStackSize() {
        
        return _get(DataComponents.MAX_STACK_SIZE);
    }
    
    @ZenCodeType.Method
    default T withMaxStackSize(int maxStackSize) {
        
        return _with(DataComponents.MAX_STACK_SIZE, maxStackSize);
    }
    
    @ZenCodeType.Method
    default T withoutMaxStackSize() {
        
        return _without(DataComponents.MAX_STACK_SIZE);
    }
    
    //</editor-fold>
    // <editor-fold desc="MAX_DAMAGE">
    
    @ZenCodeType.Getter("hasMaxDamage")
    default boolean hasMaxDamage() {
        
        return _has(DataComponents.MAX_DAMAGE);
    }
    
    @ZenCodeType.Getter("maxDamage")
    default int getMaxDamage() {
        
        return _get(DataComponents.MAX_DAMAGE);
    }
    
    @ZenCodeType.Method
    default T withMaxDamage(int maxDamage) {
        
        return _with(DataComponents.MAX_DAMAGE, maxDamage);
    }
    
    @ZenCodeType.Method
    default T withoutMaxDamage() {
        
        return _without(DataComponents.MAX_DAMAGE);
    }
    
    //</editor-fold>
    // <editor-fold desc="DAMAGE">
    
    @ZenCodeType.Getter("hasDamage")
    default boolean hasDamage() {
        
        return _has(DataComponents.DAMAGE);
    }
    
    @ZenCodeType.Getter("damage")
    default int getDamage() {
        
        return _get(DataComponents.DAMAGE);
    }
    
    @ZenCodeType.Method
    default T withDamage(int damage) {
        
        return _with(DataComponents.DAMAGE, damage);
    }
    
    @ZenCodeType.Method
    default T withoutDamage() {
        
        return _without(DataComponents.DAMAGE);
    }
    
    //</editor-fold>
    // <editor-fold desc="UNBREAKABLE">
    
    @ZenCodeType.Getter("hasUnbreakable")
    default boolean hasUnbreakable() {
        
        return _has(DataComponents.UNBREAKABLE);
    }
    
    @ZenCodeType.Getter("unbreakable")
    default Unbreakable getUnbreakable() {
        
        return _get(DataComponents.UNBREAKABLE);
    }
    
    @ZenCodeType.Method
    default T withUnbreakable(@ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return withUnbreakable(new Unbreakable(showInTooltip));
    }
    
    @ZenCodeType.Method
    default T withUnbreakable(Unbreakable unbreakable) {
        
        return _with(DataComponents.UNBREAKABLE, unbreakable);
    }
    
    @ZenCodeType.Method
    default T withoutUnbreakable() {
        
        return _without(DataComponents.UNBREAKABLE);
    }
    
    //</editor-fold>
    // <editor-fold desc="CUSTOM_NAME">
    
    @ZenCodeType.Getter("hasCustomName")
    default boolean hasCustomName() {
        
        return _has(DataComponents.CUSTOM_NAME);
    }
    
    @ZenCodeType.Getter("customName")
    default Component getCustomName() {
        
        return _get(DataComponents.CUSTOM_NAME);
    }
    
    @ZenCodeType.Method
    default T withCustomName(Component name) {
        
        return _with(DataComponents.CUSTOM_NAME, name);
    }
    
    @ZenCodeType.Method
    default T withoutCustomName() {
        
        return _without(DataComponents.CUSTOM_NAME);
    }
    
    //</editor-fold>
    // <editor-fold desc="ITEM_NAME">
    
    @ZenCodeType.Getter("hasItemName")
    default boolean hasItemName() {
        
        return _has(DataComponents.ITEM_NAME);
    }
    
    @ZenCodeType.Getter("itemName")
    default Component getItemName() {
        
        return _get(DataComponents.ITEM_NAME);
    }
    
    @ZenCodeType.Method
    default T withItemName(Component name) {
        
        return _with(DataComponents.ITEM_NAME, name);
    }
    
    @ZenCodeType.Method
    default T withoutItemName() {
        
        return _without(DataComponents.ITEM_NAME);
    }
    
    //</editor-fold>
    // <editor-fold desc="LORE">
    
    @ZenCodeType.Getter("hasLore")
    default boolean hasLore() {
        
        return _has(DataComponents.LORE);
    }
    
    @ZenCodeType.Getter("lore")
    default ItemLore getLore() {
        
        return _get(DataComponents.LORE);
    }
    
    @ZenCodeType.Method
    default T withLore(List<Component> components) {
        
        return withLore(new ItemLore(components));
    }
    
    @ZenCodeType.Method
    default T withLore(ItemLore lore) {
        
        return _with(DataComponents.LORE, lore);
    }
    
    @ZenCodeType.Method
    default T withoutLore() {
        
        return _without(DataComponents.LORE);
    }
    
    //</editor-fold>
    // <editor-fold desc="RARITY">
    
    @ZenCodeType.Getter("hasRarity")
    default boolean hasRarity() {
        
        return _has(DataComponents.RARITY);
    }
    
    @ZenCodeType.Getter("rarity")
    default Rarity getRarity() {
        
        return _get(DataComponents.RARITY);
    }
    
    @ZenCodeType.Method
    default T withRarity(Rarity rarity) {
        
        return _with(DataComponents.RARITY, rarity);
    }
    
    @ZenCodeType.Method
    default T withoutRarity() {
        
        return _without(DataComponents.RARITY);
    }
    
    //</editor-fold>
    // <editor-fold desc="ENCHANTMENTS">
    
    @ZenCodeType.Getter("hasEnchantments")
    default boolean hasEnchantments() {
        
        return _has(DataComponents.ENCHANTMENTS);
    }
    
    @ZenCodeType.Getter("enchantments")
    default ItemEnchantments getEnchantments() {
        
        return _get(DataComponents.ENCHANTMENTS);
    }
    
    @ZenCodeType.Method
    default T withEnchantments(ItemEnchantments enchantments) {
        
        return _with(DataComponents.ENCHANTMENTS, enchantments);
    }
    
    @ZenCodeType.Method
    default T withEnchantment(Enchantment enchantment, @ZenCodeType.OptionalInt(1) int level) {
        
        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(hasEnchantments() ? getEnchantments() : ItemEnchantments.EMPTY);
        mutable.set(enchantment, level);
        return withEnchantments(mutable.toImmutable());
    }
    
    @ZenCodeType.Method
    default T withoutEnchantment(Enchantment enchantment) {
        
        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(hasEnchantments() ? getEnchantments() : ItemEnchantments.EMPTY);
        mutable.removeIf(enchantmentHolder -> enchantmentHolder.value() == enchantment);
        return withEnchantments(mutable.toImmutable());
    }
    
    
    @ZenCodeType.Method
    default T withoutEnchantments() {
        
        return _without(DataComponents.ENCHANTMENTS);
    }
    
    //</editor-fold>
    // <editor-fold desc="CAN_PLACE_ON">
    @ZenCodeType.Getter("hasCanPlaceOn")
    default boolean hasCanPlaceOn() {
        
        return _has(DataComponents.CAN_PLACE_ON);
    }
    
    @ZenCodeType.Getter("canPlaceOn")
    default AdventureModePredicate getCanPlaceOn() {
        
        return _get(DataComponents.CAN_PLACE_ON);
    }
    
    @ZenCodeType.Method
    default T withCanPlaceOn(List<BlockPredicate> predicates, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return withCanPlaceOn(new AdventureModePredicate(predicates, showInTooltip));
    }
    
    @ZenCodeType.Method
    default T withCanPlaceOn(AdventureModePredicate predicate) {
        
        return _with(DataComponents.CAN_PLACE_ON, predicate);
    }
    
    @ZenCodeType.Method
    default T withoutCanPlaceOn() {
        
        return _without(DataComponents.CAN_PLACE_ON);
    }
    
    //</editor-fold>
    // <editor-fold desc="CAN_BREAK">
    
    @ZenCodeType.Getter("hasCanBreak")
    default boolean hasCanBreak() {
        
        return _has(DataComponents.CAN_BREAK);
    }
    
    @ZenCodeType.Getter("canBreak")
    default AdventureModePredicate getCanBreak() {
        
        return _get(DataComponents.CAN_BREAK);
    }
    
    @ZenCodeType.Method
    default T withCanBreak(List<BlockPredicate> predicates, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return withCanBreak(new AdventureModePredicate(predicates, showInTooltip));
    }
    
    @ZenCodeType.Method
    default T withCanBreak(AdventureModePredicate predicate) {
        
        return _with(DataComponents.CAN_BREAK, predicate);
    }
    
    @ZenCodeType.Method
    default T withoutCanBreak() {
        
        return _without(DataComponents.CAN_BREAK);
    }
    
    //</editor-fold>
    // <editor-fold desc="ATTRIBUTE_MODIFIERS">
    @ZenCodeType.Getter("hasAttributeModifiers")
    default boolean hasAttributeModifiers() {
        
        return _has(DataComponents.ATTRIBUTE_MODIFIERS);
    }
    
    @ZenCodeType.Getter("attributeModifiers")
    default ItemAttributeModifiers getAttributeModifiers() {
        
        return _get(DataComponents.ATTRIBUTE_MODIFIERS);
    }
    
    @ZenCodeType.Method
    default T withAttributeModifiers(ItemAttributeModifiers modifiers) {
        
        return _with(DataComponents.ATTRIBUTE_MODIFIERS, modifiers);
    }
    
    @ZenCodeType.Method
    default T withAttributeModifiers(List<ItemAttributeModifiers.Entry> modifiers, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return _with(DataComponents.ATTRIBUTE_MODIFIERS, new ItemAttributeModifiers(modifiers, showInTooltip));
    }
    
    @ZenCodeType.Method
    default T withoutAttributeModifiers() {
        
        return _without(DataComponents.ATTRIBUTE_MODIFIERS);
    }
    
    //</editor-fold>
    // <editor-fold desc="CUSTOM_MODEL_DATA">
    
    @ZenCodeType.Getter("hasCustomModelData")
    default boolean hasCustomModelData() {
        
        return _has(DataComponents.CUSTOM_MODEL_DATA);
    }
    
    @ZenCodeType.Getter("customModelData")
    default CustomModelData getCustomModelData() {
        
        return _get(DataComponents.CUSTOM_MODEL_DATA);
    }
    
    @ZenCodeType.Method
    default T withCustomModelData(int value) {
        
        return withCustomModelData(new CustomModelData(value));
    }
    
    @ZenCodeType.Method
    default T withCustomModelData(CustomModelData data) {
        
        return _with(DataComponents.CUSTOM_MODEL_DATA, data);
    }
    
    @ZenCodeType.Method
    default T withoutCustomModelData() {
        
        return _without(DataComponents.CUSTOM_MODEL_DATA);
    }
    
    //</editor-fold>
    // <editor-fold desc="HIDE_ADDITIONAL_TOOLTIP">
    
    @ZenCodeType.Getter("hideAdditionalTooltip")
    default boolean hideAdditionalTooltip() {
        
        return _has(DataComponents.HIDE_ADDITIONAL_TOOLTIP);
    }
    
    @ZenCodeType.Method
    default T withHideAdditionalTooltip() {
        
        return _with(DataComponents.HIDE_ADDITIONAL_TOOLTIP, Unit.INSTANCE);
    }
    
    @ZenCodeType.Method
    default T withoutHideAdditionalTooltip() {
        
        return _without(DataComponents.HIDE_ADDITIONAL_TOOLTIP);
    }
    
    //</editor-fold>
    // <editor-fold desc="HIDE_TOOLTIP">
    @ZenCodeType.Getter("hideTooltip")
    default boolean hideTooltip() {
        
        return _has(DataComponents.HIDE_TOOLTIP);
    }
    
    @ZenCodeType.Method
    default T withHideTooltip() {
        
        return _with(DataComponents.HIDE_TOOLTIP, Unit.INSTANCE);
    }
    
    @ZenCodeType.Method
    default T withoutHideTooltip() {
        
        return _without(DataComponents.HIDE_TOOLTIP);
    }
    
    //</editor-fold>
    // <editor-fold desc="REPAIR_COST">
    
    @ZenCodeType.Getter("hasRepairCost")
    default boolean hasRepairCost() {
        
        return _has(DataComponents.REPAIR_COST);
    }
    
    @ZenCodeType.Getter("repairCost")
    default int repairCost() {
        
        return _get(DataComponents.REPAIR_COST);
    }
    
    @ZenCodeType.Method
    default T withRepairCost(int cost) {
        
        return _with(DataComponents.REPAIR_COST, cost);
    }
    
    @ZenCodeType.Method
    default T withoutRepairCost() {
        
        return _without(DataComponents.REPAIR_COST);
    }
    
    //</editor-fold>
    // <editor-fold desc="CREATIVE_SLOT_LOCK">
    
    @ZenCodeType.Getter("creativeSlotLock")
    default boolean creativeSlotLock() {
        
        return _has(DataComponents.CREATIVE_SLOT_LOCK);
    }
    
    @ZenCodeType.Method
    default T withCreativeSlotLock() {
        
        return _with(DataComponents.CREATIVE_SLOT_LOCK, Unit.INSTANCE);
    }
    
    @ZenCodeType.Method
    default T withoutCreativeSlotLock() {
        
        return _without(DataComponents.CREATIVE_SLOT_LOCK);
    }
    
    //</editor-fold>
    // <editor-fold desc="ENCHANTMENT_GLINT_OVERRIDE">
    
    @ZenCodeType.Getter("hasEnchantmentGlintOverride")
    default boolean hasEnchantmentGlintOverride() {
        
        return _has(DataComponents.ENCHANTMENT_GLINT_OVERRIDE);
    }
    
    @ZenCodeType.Getter("enchantmentGlintOverride")
    default boolean getEnchantmentGlintOverride() {
        
        return _get(DataComponents.ENCHANTMENT_GLINT_OVERRIDE);
    }
    
    @ZenCodeType.Method
    default T withEnchantmentGlintOverride(boolean value) {
        
        return _with(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, value);
    }
    
    @ZenCodeType.Method
    default T withoutEnchantmentGlintOverride() {
        
        return _without(DataComponents.ENCHANTMENT_GLINT_OVERRIDE);
    }
    
    //</editor-fold>
    // <editor-fold desc="INTANGIBLE_PROJECTILE">
    
    @ZenCodeType.Getter("isIntangibleProjectile")
    default boolean isInangibleProjectile() {
        
        return _has(DataComponents.INTANGIBLE_PROJECTILE);
    }
    
    @ZenCodeType.Method
    default T withIntangibleProjectile() {
        
        return _with(DataComponents.INTANGIBLE_PROJECTILE, Unit.INSTANCE);
    }
    
    @ZenCodeType.Method
    default T withoutIntangibleProjectile() {
        
        return _without(DataComponents.INTANGIBLE_PROJECTILE);
    }
    
    //</editor-fold>
    // <editor-fold desc="FOOD">
    
    @ZenCodeType.Getter("hasFood")
    default boolean hasFood() {
        
        return _has(DataComponents.FOOD);
    }
    
    @ZenCodeType.Getter("food")
    default FoodProperties getFood() {
        
        return _get(DataComponents.FOOD);
    }
    
    @ZenCodeType.Method
    default T withFood(FoodProperties food) {
        
        return _with(DataComponents.FOOD, food);
    }
    
    @ZenCodeType.Method
    default T withoutFood() {
        
        return _without(DataComponents.FOOD);
    }
    
    //</editor-fold>
    // <editor-fold desc="FIRE_RESISTANT">
    @ZenCodeType.Getter("isFireResistant")
    default boolean isFireResistant() {
        
        return _has(DataComponents.FIRE_RESISTANT);
    }
    
    @ZenCodeType.Method
    default T withFireResistant() {
        
        return _with(DataComponents.FIRE_RESISTANT, Unit.INSTANCE);
    }
    
    @ZenCodeType.Method
    default T withoutFireResistant() {
        
        return _without(DataComponents.FIRE_RESISTANT);
    }
    
    //</editor-fold>
    // <editor-fold desc="TOOL">
    @ZenCodeType.Getter("hasTool")
    default boolean hasTool() {
        
        return _has(DataComponents.TOOL);
    }
    
    @ZenCodeType.Getter("tool")
    default Tool getTool() {
        
        return _get(DataComponents.TOOL);
    }
    
    @ZenCodeType.Method
    default T withTool(List<Tool.Rule> rules, float defaultMiningSpeed, int damagePerBlock) {
        
        return withTool(new Tool(rules, defaultMiningSpeed, damagePerBlock));
    }
    
    @ZenCodeType.Method
    default T withTool(Tool tool) {
        
        return _with(DataComponents.TOOL, tool);
    }
    
    @ZenCodeType.Method
    default T withoutTool() {
        
        return _without(DataComponents.TOOL);
    }
    
    //</editor-fold>
    // <editor-fold desc="STORED_ENCHANTMENTS">
    @ZenCodeType.Getter("hasStoredEnchantments")
    default boolean hasStoredEnchantments() {
        
        return _has(DataComponents.STORED_ENCHANTMENTS);
    }
    
    @ZenCodeType.Getter("storedEnchantments")
    default ItemEnchantments getStoredEnchantments() {
        
        return _get(DataComponents.STORED_ENCHANTMENTS);
    }
    
    @ZenCodeType.Method
    default T withStoredEnchantments(ItemEnchantments enchantments) {
        
        return _with(DataComponents.STORED_ENCHANTMENTS, enchantments);
    }
    
    @ZenCodeType.Method
    default T withoutStoredEnchantments() {
        
        return _without(DataComponents.STORED_ENCHANTMENTS);
    }
    
    //</editor-fold>
    // <editor-fold desc="DYED_COLOR">
    @ZenCodeType.Getter("hasDyedColor")
    default boolean hasDyedColor() {
        
        return _has(DataComponents.DYED_COLOR);
    }
    
    @ZenCodeType.Getter("dyedColor")
    default DyedItemColor getDyedColor() {
        
        return _get(DataComponents.DYED_COLOR);
    }
    
    @ZenCodeType.Method
    default T withDyedColor(int rgb, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return withDyedColor(new DyedItemColor(rgb, showInTooltip));
    }
    
    @ZenCodeType.Method
    default T withDyedColor(DyedItemColor color) {
        
        return _with(DataComponents.DYED_COLOR, color);
    }
    
    @ZenCodeType.Method
    default T withoutDyedColor() {
        
        return _without(DataComponents.DYED_COLOR);
    }
    
    //</editor-fold>
    // <editor-fold desc="MAP_COLOR">
    @ZenCodeType.Getter("hasMapColor")
    default boolean hasMapColor() {
        
        return _has(DataComponents.MAP_COLOR);
    }
    
    @ZenCodeType.Getter("mapColor")
    default MapItemColor getMapColor() {
        
        return _get(DataComponents.MAP_COLOR);
    }
    
    @ZenCodeType.Method
    default T withMapColor(int rgb) {
        
        return withMapColor(new MapItemColor(rgb));
    }
    
    @ZenCodeType.Method
    default T withMapColor(MapItemColor color) {
        
        return _with(DataComponents.MAP_COLOR, color);
    }
    
    @ZenCodeType.Method
    default T withoutMapColor() {
        
        return _without(DataComponents.MAP_COLOR);
    }
    
    //</editor-fold>
    // <editor-fold desc="MAP_ID">
    
    @ZenCodeType.Getter("hasMapId")
    default boolean hasMapId() {
        
        return _has(DataComponents.MAP_ID);
    }
    
    @ZenCodeType.Getter("mapId")
    default MapId getMapId() {
        
        return _get(DataComponents.MAP_ID);
    }
    
    @ZenCodeType.Method
    default T withMapId(int id) {
        
        return withMapId(new MapId(id));
    }
    
    @ZenCodeType.Method
    default T withMapId(MapId mapId) {
        
        return _with(DataComponents.MAP_ID, mapId);
    }
    
    @ZenCodeType.Method
    default T withoutMapId() {
        
        return _without(DataComponents.MAP_ID);
    }
    
    //</editor-fold>
    // <editor-fold desc="MAP_DECORATIONS">
    
    @ZenCodeType.Getter("hasMapDecorations")
    default boolean hasMapDecorations() {
        
        return _has(DataComponents.MAP_DECORATIONS);
    }
    
    @ZenCodeType.Getter("mapDecorations")
    default MapDecorations getMapDecorations() {
        
        return _get(DataComponents.MAP_DECORATIONS);
    }
    
    @ZenCodeType.Method
    default T withMapDecorations(Map<String, MapDecorations.Entry> decorations) {
        
        return withMapDecorations(new MapDecorations(decorations));
    }
    
    @ZenCodeType.Method
    default T withMapDecorations(MapDecorations decorations) {
        
        return _with(DataComponents.MAP_DECORATIONS, decorations);
    }
    
    @ZenCodeType.Method
    default T withoutMapDecorations() {
        
        return _without(DataComponents.MAP_DECORATIONS);
    }
    
    //</editor-fold>
    // <editor-fold desc="MAP_POST_PROCESSING">
    
    @ZenCodeType.Getter("hasMapPostProcessing")
    default boolean hasMapPostProcessing() {
        
        return _has(DataComponents.MAP_POST_PROCESSING);
    }
    
    @ZenCodeType.Getter("mapPostProcessing")
    default MapPostProcessing getMapPostProcessing() {
        
        return _get(DataComponents.MAP_POST_PROCESSING);
    }
    
    @ZenCodeType.Method
    default T withMapPostProcessing(MapPostProcessing value) {
        
        return _with(DataComponents.MAP_POST_PROCESSING, value);
    }
    
    @ZenCodeType.Method
    default T withoutMapPostProcessing() {
        
        return _without(DataComponents.MAP_POST_PROCESSING);
    }
    
    //</editor-fold>
    // <editor-fold desc="CHARGED_PROJECTILES">
    @ZenCodeType.Getter("hasChargedProjectiles")
    default boolean hasChargedProjectiles() {
        
        return _has(DataComponents.CHARGED_PROJECTILES);
    }
    
    @ZenCodeType.Getter("chargedProjectiles")
    default ChargedProjectiles getChargedProjectiles() {
        
        return _get(DataComponents.CHARGED_PROJECTILES);
    }
    
    @ZenCodeType.Method
    default T withChargedProjectiles(IItemStack item) {
        
        return withChargedProjectiles(ChargedProjectiles.of(item.getInternal()));
    }
    
    @ZenCodeType.Method
    default T withChargedProjectiles(List<IItemStack> items) {
        
        return withChargedProjectiles(ChargedProjectiles.of(Lists.transform(items, IItemStack::getInternal)));
    }
    
    @ZenCodeType.Method
    default T withChargedProjectiles(ChargedProjectiles chargedProjectiles) {
        
        return _with(DataComponents.CHARGED_PROJECTILES, chargedProjectiles);
    }
    
    @ZenCodeType.Method
    default T withoutChargedProjectiles() {
        
        return _without(DataComponents.CHARGED_PROJECTILES);
    }
    
    //</editor-fold>
    // <editor-fold desc="BUNDLE_CONTENTS">
    @ZenCodeType.Getter("hasBundleContents")
    default boolean hasBundleContents() {
        
        return _has(DataComponents.BUNDLE_CONTENTS);
    }
    
    @ZenCodeType.Getter("bundleContents")
    default BundleContents getBundleContents() {
        
        return _get(DataComponents.BUNDLE_CONTENTS);
    }
    
    @ZenCodeType.Method
    default T withBundleContents(List<IItemStack> contents) {
        
        return _with(DataComponents.BUNDLE_CONTENTS, new BundleContents(Lists.transform(contents, IItemStack::getInternal)));
    }
    
    @ZenCodeType.Method
    default T withBundleContents(BundleContents contents) {
        
        return _with(DataComponents.BUNDLE_CONTENTS, contents);
    }
    
    @ZenCodeType.Method
    default T withoutBundleContents() {
        
        return _without(DataComponents.BUNDLE_CONTENTS);
    }
    
    //</editor-fold>
    // <editor-fold desc="POTION_CONTENTS">
    
    @ZenCodeType.Getter("hasPotionContents")
    default boolean hasPotionContents() {
        
        return _has(DataComponents.POTION_CONTENTS);
    }
    
    @ZenCodeType.Getter("potionContents")
    default PotionContents getPotionContents() {
        
        return _get(DataComponents.POTION_CONTENTS);
    }
    
    @ZenCodeType.Method
    default T withPotionContents(Potion potion) {
        
        return withPotionContents(new PotionContents(BuiltInRegistries.POTION.wrapAsHolder(potion)));
    }
    
    @ZenCodeType.Method
    default T withPotionContents(Potion potion, List<MobEffectInstance> customEffects) {
        
        return withPotionContents(new PotionContents(Optional.of(BuiltInRegistries.POTION.wrapAsHolder(potion)), Optional.empty(), customEffects));
    }
    
    @ZenCodeType.Method
    default T withPotionContents(Potion potion, int customColor, List<MobEffectInstance> customEffects) {
        
        return withPotionContents(new PotionContents(Optional.of(BuiltInRegistries.POTION.wrapAsHolder(potion)), Optional.of(customColor), customEffects));
    }
    
    @ZenCodeType.Method
    default T withPotionContents(PotionContents contents) {
        
        return _with(DataComponents.POTION_CONTENTS, contents);
    }
    
    @ZenCodeType.Method
    default T withoutPotionContents() {
        
        return _without(DataComponents.POTION_CONTENTS);
    }
    
    //</editor-fold>
    // <editor-fold desc="SUSPICIOUS_STEW_EFFECTS">
    
    @ZenCodeType.Getter("hasSuspiciousStewEffects")
    default boolean hasSuspiciousStewEffects() {
        
        return _has(DataComponents.SUSPICIOUS_STEW_EFFECTS);
    }
    
    @ZenCodeType.Getter("suspiciousStewEffects")
    default SuspiciousStewEffects getSuspiciousStewEffects() {
        
        return _get(DataComponents.SUSPICIOUS_STEW_EFFECTS);
    }
    
    @ZenCodeType.Method
    default T withSuspiciousStewEffects(List<SuspiciousStewEffects.Entry> effects) {
        
        return withSuspiciousStewEffects(new SuspiciousStewEffects(effects));
    }
    
    @ZenCodeType.Method
    default T withSuspiciousStewEffects(SuspiciousStewEffects suspiciousStewEffects) {
        
        return _with(DataComponents.SUSPICIOUS_STEW_EFFECTS, suspiciousStewEffects);
    }
    
    @ZenCodeType.Method
    default T withoutSuspiciousStewEffects() {
        
        return _without(DataComponents.SUSPICIOUS_STEW_EFFECTS);
    }
    
    //</editor-fold>
    // <editor-fold desc="WRITABLE_BOOK_CONTENT">
    @ZenCodeType.Getter("hasWritableBookContent")
    default boolean hasWritableBookContent() {
        
        return _has(DataComponents.WRITABLE_BOOK_CONTENT);
    }
    
    @ZenCodeType.Getter("writableBookContent")
    default WritableBookContent getWritableBookContent() {
        
        return _get(DataComponents.WRITABLE_BOOK_CONTENT);
    }
    
    @ZenCodeType.Method
    default T withWritableBookContent(List<Filterable<String>> pages) {
        
        return withWritableBookContent(new WritableBookContent(pages));
    }
    
    @ZenCodeType.Method
    default T withWritableBookContent(WritableBookContent content) {
        
        return _with(DataComponents.WRITABLE_BOOK_CONTENT, content);
    }
    
    @ZenCodeType.Method
    default T withoutWritableBookContent() {
        
        return _without(DataComponents.WRITABLE_BOOK_CONTENT);
    }
    
    //</editor-fold>
    // <editor-fold desc="WRITTEN_BOOK_CONTENT">
    @ZenCodeType.Getter("hasWrittenBookContent")
    default boolean hasWrittenBookContent() {
        
        return _has(DataComponents.WRITABLE_BOOK_CONTENT);
    }
    
    @ZenCodeType.Getter("writtenBookContent")
    default WrittenBookContent getWrittenBookContent() {
        
        return _get(DataComponents.WRITTEN_BOOK_CONTENT);
    }
    
    @ZenCodeType.Method
    default T withWrittenBookContent(WrittenBookContent content) {
        
        return _with(DataComponents.WRITTEN_BOOK_CONTENT, content);
    }
    
    @ZenCodeType.Method
    default T withoutWrittenBookContent() {
        
        return _without(DataComponents.WRITTEN_BOOK_CONTENT);
    }
    
    //</editor-fold>
    // <editor-fold desc="TRIM">
    @ZenCodeType.Getter("hasTrim")
    default boolean hasTrim() {
        
        return _has(DataComponents.TRIM);
    }
    
    @ZenCodeType.Getter("trim")
    default ArmorTrim getTrim() {
        
        return _get(DataComponents.TRIM);
    }
    
    @ZenCodeType.Method
    default T withTrim(ArmorTrim trim) {
        
        return _with(DataComponents.TRIM, trim);
    }
    
    @ZenCodeType.Method
    default T withoutTrim() {
        
        return _without(DataComponents.TRIM);
    }
    
    //</editor-fold>
    // <editor-fold desc="DEBUG_STICK_STATE">
    
    @ZenCodeType.Getter("hasDebugStickState")
    default boolean hasDebugStickState() {
        
        return _has(DataComponents.DEBUG_STICK_STATE);
    }
    
    @ZenCodeType.Getter("debugStickState")
    default DebugStickState getDebugStickState() {
        
        return _get(DataComponents.DEBUG_STICK_STATE);
    }
    
    @ZenCodeType.Method
    default T withDebugStickState(DebugStickState state) {
        
        return _with(DataComponents.DEBUG_STICK_STATE, state);
    }
    
    @ZenCodeType.Method
    default T withoutDebugStickState() {
        
        return _without(DataComponents.DEBUG_STICK_STATE);
    }
    
    //</editor-fold>
    // <editor-fold desc="ENTITY_DATA">
    @ZenCodeType.Getter("hasEntityData")
    default boolean hasEntityData() {
        
        return _has(DataComponents.ENTITY_DATA);
    }
    
    @ZenCodeType.Getter("entityData")
    default CustomData getEntityData() {
        
        return _get(DataComponents.ENTITY_DATA);
    }
    
    @ZenCodeType.Method
    default T withEntityData(MapData data) {
        
        return withEntityData(CustomData.of(data.getInternal()));
    }
    
    @ZenCodeType.Method
    default T withEntityData(CustomData data) {
        
        return _with(DataComponents.ENTITY_DATA, data);
    }
    
    @ZenCodeType.Method
    default T withoutEntityDate() {
        
        return _without(DataComponents.ENTITY_DATA);
    }
    
    //</editor-fold>
    // <editor-fold desc="BUCKET_ENTITY_DATA">
    
    @ZenCodeType.Getter("hasBucketEntityData")
    default boolean hasBucketEntityData() {
        
        return _has(DataComponents.BUCKET_ENTITY_DATA);
    }
    
    @ZenCodeType.Getter("bucketEntityData")
    default CustomData getBucketEntityData() {
        
        return _get(DataComponents.BUCKET_ENTITY_DATA);
    }
    
    @ZenCodeType.Method
    default T withBucketEntityData(MapData data) {
        
        return withBucketEntityData(CustomData.of(data.getInternal()));
    }
    
    @ZenCodeType.Method
    default T withBucketEntityData(CustomData data) {
        
        return _with(DataComponents.BUCKET_ENTITY_DATA, data);
    }
    
    @ZenCodeType.Method
    default T withoutBucketEntityData() {
        
        return _without(DataComponents.BUCKET_ENTITY_DATA);
    }
    
    //</editor-fold>
    // <editor-fold desc="BLOCK_ENTITY_DATA">
    
    @ZenCodeType.Getter("hasBlockEntityData")
    default boolean hasBlockEntityData() {
        
        return _has(DataComponents.BLOCK_ENTITY_DATA);
    }
    
    @ZenCodeType.Getter("blockEntityData")
    default CustomData getBlockEntityData() {
        
        return _get(DataComponents.BLOCK_ENTITY_DATA);
    }
    
    @ZenCodeType.Method
    default T withBlockEntityData(MapData data) {
        
        return withBlockEntityData(CustomData.of(data.getInternal()));
    }
    
    @ZenCodeType.Method
    default T withBlockEntityData(CustomData data) {
        
        return _with(DataComponents.BLOCK_ENTITY_DATA, data);
    }
    
    @ZenCodeType.Method
    default T withoutBlockEntityData() {
        
        return _without(DataComponents.BLOCK_ENTITY_DATA);
    }
    
    //</editor-fold>
    // <editor-fold desc="INSTRUMENT">
    
    @ZenCodeType.Getter("hasInstrument")
    default boolean hasInstrument() {
        
        return _has(DataComponents.INSTRUMENT);
    }
    
    @ZenCodeType.Getter("instrument")
    default Instrument getInstrument() {
        
        return _get(DataComponents.INSTRUMENT).value();
    }
    
    @ZenCodeType.Method
    default T withInstrument(Instrument instrument) {
        
        return _with(DataComponents.INSTRUMENT, BuiltInRegistries.INSTRUMENT.wrapAsHolder(instrument));
    }
    
    @ZenCodeType.Method
    default T withoutInstrument() {
        
        return _without(DataComponents.INSTRUMENT);
    }
    
    //</editor-fold>
    // <editor-fold desc="OMINOUS_BOTTLE_AMPLIFIER">
    @ZenCodeType.Getter("hasOminousBottleAmplifier")
    default boolean hasOminousBottleAmplifier() {
        
        return _has(DataComponents.OMINOUS_BOTTLE_AMPLIFIER);
    }
    
    @ZenCodeType.Getter("ominousBottleAmplifier")
    default int getOminousBottleAmplifier() {
        
        return _get(DataComponents.OMINOUS_BOTTLE_AMPLIFIER);
    }
    
    @ZenCodeType.Method
    default T withOminousBottleAmplifier(int amplifier) {
        
        return _with(DataComponents.OMINOUS_BOTTLE_AMPLIFIER, amplifier);
    }
    
    @ZenCodeType.Method
    default T withoutOminousBottleAmplifier() {
        
        return _without(DataComponents.OMINOUS_BOTTLE_AMPLIFIER);
    }
    
    //</editor-fold>
    // <editor-fold desc="RECIPES">
    @ZenCodeType.Getter("hasRecipes")
    default boolean hasRecipes() {
        
        return _has(DataComponents.RECIPES);
    }
    
    @ZenCodeType.Getter("recipes")
    default List<ResourceLocation> getRecipes() {
        
        return _get(DataComponents.RECIPES);
    }
    
    @ZenCodeType.Method
    default T withRecipes(List<ResourceLocation> recipes) {
        
        return _with(DataComponents.RECIPES, recipes);
    }
    
    @ZenCodeType.Method
    default T withoutRecipes() {
        
        return _without(DataComponents.RECIPES);
    }
    
    //</editor-fold>
    // <editor-fold desc="LODESTONE_TRACKER">
    @ZenCodeType.Getter("hasLodestoneTracker")
    default boolean hasLodestoneTracker() {
        
        return _has(DataComponents.LODESTONE_TRACKER);
    }
    
    @ZenCodeType.Getter("lodestoneTracker")
    default LodestoneTracker getLodestoneTracker() {
        
        return _get(DataComponents.LODESTONE_TRACKER);
    }
    
    @ZenCodeType.Method
    default T withLodestoneTracker(LodestoneTracker tracker) {
        
        return _with(DataComponents.LODESTONE_TRACKER, tracker);
    }
    
    @ZenCodeType.Method
    default T withoutLodestoneTracker() {
        
        return _without(DataComponents.LODESTONE_TRACKER);
    }
    
    //</editor-fold>
    // <editor-fold desc="FIREWORK_EXPLOSION">
    
    @ZenCodeType.Getter("hasFireworkExplosion")
    default boolean hasFireworkExplosion() {
        
        return _has(DataComponents.FIREWORK_EXPLOSION);
    }
    
    @ZenCodeType.Getter("fireworkExplosion")
    default FireworkExplosion getFireworkExplosion() {
        
        return _get(DataComponents.FIREWORK_EXPLOSION);
    }
    
    @ZenCodeType.Method
    default T withFireworkExplosion(FireworkExplosion explosion) {
        
        return _with(DataComponents.FIREWORK_EXPLOSION, explosion);
    }
    
    @ZenCodeType.Method
    default T withoutFireworkExplosion() {
        
        return _without(DataComponents.FIREWORK_EXPLOSION);
    }
    
    //</editor-fold>
    // <editor-fold desc="FIREWORKS">
    @ZenCodeType.Getter("hasFireworks")
    default boolean hasFireworks() {
        
        return _has(DataComponents.FIREWORKS);
    }
    
    @ZenCodeType.Getter("fireworks")
    default Fireworks getFireworks() {
        
        return _get(DataComponents.FIREWORKS);
    }
    
    @ZenCodeType.Method
    default T withFireworks(int flightDuration, List<FireworkExplosion> explosions) {
        
        return withFireworks(new Fireworks(flightDuration, explosions));
    }
    
    @ZenCodeType.Method
    default T withFireworks(Fireworks fireworks) {
        
        return _with(DataComponents.FIREWORKS, fireworks);
    }
    
    @ZenCodeType.Method
    default T withoutFireworks() {
        
        return _without(DataComponents.FIREWORKS);
    }
    
    //</editor-fold>
    // <editor-fold desc="PROFILE">
    
    @ZenCodeType.Getter("hasProfile")
    default boolean hasProfile() {
        
        return _has(DataComponents.PROFILE);
    }
    
    @ZenCodeType.Getter("profile")
    default ResolvableProfile getProfile() {
        
        return _get(DataComponents.PROFILE);
    }
    
    @ZenCodeType.Method
    default T withProfile(ResolvableProfile profile) {
        
        return _with(DataComponents.PROFILE, profile);
    }
    
    @ZenCodeType.Method
    default T withoutProfile() {
        
        return _without(DataComponents.PROFILE);
    }
    
    //</editor-fold>
    // <editor-fold desc="NOTE_BLOCK_SOUND">
    
    @ZenCodeType.Getter("hasNoteBlockSound")
    default boolean hasNoteBlockSound() {
        
        return _has(DataComponents.NOTE_BLOCK_SOUND);
    }
    
    @ZenCodeType.Getter("noteBlockSound")
    default ResourceLocation getNoteBlockSound() {
        
        return _get(DataComponents.NOTE_BLOCK_SOUND);
    }
    
    @ZenCodeType.Method
    default T withNoteBlockSound(ResourceLocation sound) {
        
        return _with(DataComponents.NOTE_BLOCK_SOUND, sound);
    }
    
    @ZenCodeType.Method
    default T withoutNoteBlockSound() {
        
        return _without(DataComponents.NOTE_BLOCK_SOUND);
    }
    
    //</editor-fold>
    // <editor-fold desc="BANNER_PATTERNS">
    
    @ZenCodeType.Getter("hasBannerPatterns")
    default boolean hasBannerPatterns() {
        
        return _has(DataComponents.BANNER_PATTERNS);
    }
    
    @ZenCodeType.Getter("bannerPatterns")
    default BannerPatternLayers getBannerPatterns() {
        
        return _get(DataComponents.BANNER_PATTERNS);
    }
    
    @ZenCodeType.Method
    default T withBannerPatterns(List<BannerPatternLayers.Layer> layers) {
        
        return withBannerPatterns(new BannerPatternLayers(layers));
    }
    
    @ZenCodeType.Method
    default T withBannerPatterns(BannerPatternLayers layers) {
        
        return _with(DataComponents.BANNER_PATTERNS, layers);
    }
    
    @ZenCodeType.Method
    default T withoutBannerPatterns() {
        
        return _without(DataComponents.BANNER_PATTERNS);
    }
    
    //</editor-fold>
    // <editor-fold desc="BASE_COLOR">
    
    @ZenCodeType.Getter("hasBaseColor")
    default boolean hasBaseColor() {
        
        return _has(DataComponents.BASE_COLOR);
    }
    
    @ZenCodeType.Getter("baseColor")
    default DyeColor getBaseColor() {
        
        return _get(DataComponents.BASE_COLOR);
    }
    
    @ZenCodeType.Method
    default T withBaseColor(DyeColor color) {
        
        return _with(DataComponents.BASE_COLOR, color);
    }
    
    @ZenCodeType.Method
    default T withoutBaseColor() {
        
        return _without(DataComponents.BASE_COLOR);
    }
    
    //</editor-fold>
    // <editor-fold desc="POT_DECORATIONS">
    
    @ZenCodeType.Getter("hasPotDecorations")
    default boolean hasPotDecorations() {
        
        return _has(DataComponents.POT_DECORATIONS);
    }
    
    @ZenCodeType.Getter("potDecorations")
    default PotDecorations getPotDecorations() {
        
        return _get(DataComponents.POT_DECORATIONS);
    }
    
    @ZenCodeType.Method
    default T withPotDecorations(Item back, Item left, Item right, Item front) {
        
        return withPotDecorations(new PotDecorations(back, left, right, front));
    }
    
    @ZenCodeType.Method
    default T withPotDecorations(PotDecorations decorations) {
        
        return _with(DataComponents.POT_DECORATIONS, decorations);
    }
    
    @ZenCodeType.Method
    default T withoutPotDecorations() {
        
        return _without(DataComponents.POT_DECORATIONS);
    }
    
    //</editor-fold>
    // <editor-fold desc="CONTAINER">
    
    @ZenCodeType.Getter("hasContainer")
    default boolean hasContainer() {
        
        return _has(DataComponents.CONTAINER);
    }
    
    @ZenCodeType.Getter("container")
    default ItemContainerContents getContainer() {
        
        return _get(DataComponents.CONTAINER);
    }
    
    @ZenCodeType.Method
    default T withContainer(List<IItemStack> contents) {
        
        return withContainer(ItemContainerContents.fromItems(Lists.transform(contents, IItemStack::getInternal)));
    }
    
    @ZenCodeType.Method
    default T withContainer(ItemContainerContents contents) {
        
        return _with(DataComponents.CONTAINER, contents);
    }
    
    @ZenCodeType.Method
    default T withoutContainer() {
        
        return _without(DataComponents.CONTAINER);
    }
    
    //</editor-fold>
    // <editor-fold desc="BLOCK_STATE">
    
    @ZenCodeType.Getter("hasBlockState")
    default boolean hasBlockState() {
        
        return _has(DataComponents.BLOCK_STATE);
    }
    
    @ZenCodeType.Getter("blockState")
    default BlockItemStateProperties getBlockState() {
        
        return _get(DataComponents.BLOCK_STATE);
    }
    
    @ZenCodeType.Method
    default T withBlockState(BlockItemStateProperties properties) {
        
        return _with(DataComponents.BLOCK_STATE, properties);
    }
    
    @ZenCodeType.Method
    default T withoutBlockState() {
        
        return _without(DataComponents.BLOCK_STATE);
    }
    
    //</editor-fold>
    // <editor-fold desc="BEES">
    @ZenCodeType.Getter("hasBees")
    default boolean hasBees() {
        
        return _has(DataComponents.BEES);
    }
    
    @ZenCodeType.Getter("bees")
    default List<BeehiveBlockEntity.Occupant> getBees() {
        
        return _get(DataComponents.BEES);
    }
    
    @ZenCodeType.Method
    default T withBees(List<BeehiveBlockEntity.Occupant> occupants) {
        
        return _with(DataComponents.BEES, occupants);
    }
    
    @ZenCodeType.Method
    default T withoutBees() {
        
        return _without(DataComponents.BEES);
    }
    
    //</editor-fold>
    // <editor-fold desc="LOCK">
    @ZenCodeType.Getter("hasLock")
    default boolean hasLock() {
        
        return _has(DataComponents.LOCK);
    }
    
    @ZenCodeType.Getter("lockComponent")
    default LockCode getLock() {
        
        return _get(DataComponents.LOCK);
    }
    
    @ZenCodeType.Method
    default T withLock(String code) {
        
        return withLock(new LockCode(code));
    }
    
    @ZenCodeType.Method
    default T withLock(LockCode lock) {
        
        return _with(DataComponents.LOCK, lock);
    }
    
    @ZenCodeType.Method
    default T withoutLock() {
        
        return _without(DataComponents.LOCK);
    }
    
    //</editor-fold>
    // <editor-fold desc="CONTAINER_LOOT">
    
    @ZenCodeType.Getter("hasContainerLoot")
    default boolean hasContainerLoot() {
        
        return _has(DataComponents.CONTAINER_LOOT);
    }
    
    @ZenCodeType.Getter("containerLoot")
    default SeededContainerLoot getContainerLoot() {
        
        return _get(DataComponents.CONTAINER_LOOT);
    }
    
    @ZenCodeType.Method
    default T withContainerLoot(ResourceKey<LootTable> lootTable, long seed) {
        
        return withContainerLoot(new SeededContainerLoot(lootTable, seed));
    }
    
    @ZenCodeType.Method
    default T withContainerLoot(SeededContainerLoot loot) {
        
        return _with(DataComponents.CONTAINER_LOOT, loot);
    }
    
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
