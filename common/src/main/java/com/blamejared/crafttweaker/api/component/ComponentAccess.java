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
import net.minecraft.world.item.component.SeededContainerLoot;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.item.component.WritableBookContent;
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
    
    @ZenCodeType.Method
    default T withCustomData(MapData customData) {
        
        return withCustomData(CustomData.of(customData.getInternal()));
    }
    
    @ZenCodeType.Method
    default T withCustomData(CustomData customData) {
        
        return with(DataComponents.CUSTOM_DATA, customData);
    }
    
    @ZenCodeType.Method
    default T withoutCustomData() {
        
        return without(DataComponents.CUSTOM_DATA);
    }
    
    @ZenCodeType.Method
    default T withMaxStackSize(int maxStackSize) {
        
        return with(DataComponents.MAX_STACK_SIZE, maxStackSize);
    }
    
    @ZenCodeType.Method
    default T withoutMaxStackSize() {
        
        return without(DataComponents.MAX_STACK_SIZE);
    }
    
    @ZenCodeType.Method
    default T withDamage(int damage) {
        
        return with(DataComponents.DAMAGE, damage);
    }
    
    @ZenCodeType.Method
    default T withoutDamage() {
        
        return without(DataComponents.DAMAGE);
    }
    
    @ZenCodeType.Method
    default T withUnbreakable(@ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return withUnbreakable(new Unbreakable(showInTooltip));
    }
    
    @ZenCodeType.Method
    default T withUnbreakable(Unbreakable unbreakable) {
        
        return with(DataComponents.UNBREAKABLE, unbreakable);
    }
    
    @ZenCodeType.Method
    default T withoutUnbreakable() {
        
        return without(DataComponents.UNBREAKABLE);
    }
    
    @ZenCodeType.Method
    default T withCustomName(Component name) {
        
        return with(DataComponents.CUSTOM_NAME, name);
    }
    
    @ZenCodeType.Method
    default T withoutCustomName() {
        
        return without(DataComponents.CUSTOM_NAME);
    }
    
    @ZenCodeType.Method
    default T withItemName(Component name) {
        
        return with(DataComponents.ITEM_NAME, name);
    }
    
    @ZenCodeType.Method
    default T withoutItemName() {
        
        return without(DataComponents.ITEM_NAME);
    }
    
    @ZenCodeType.Method
    default T withLore(List<Component> components) {
        
        return withLore(new ItemLore(components));
    }
    
    @ZenCodeType.Method
    default T withLore(ItemLore lore) {
        
        return with(DataComponents.LORE, lore);
    }
    
    @ZenCodeType.Method
    default T withoutLore() {
        
        return without(DataComponents.LORE);
    }
    
    @ZenCodeType.Method
    default T withRarity(Rarity rarity) {
        
        return with(DataComponents.RARITY, rarity);
    }
    
    @ZenCodeType.Method
    default T withoutRarity() {
        
        return without(DataComponents.RARITY);
    }
    
    @ZenCodeType.Method
    default T withEnchantments(ItemEnchantments enchantments) {
        
        return with(DataComponents.ENCHANTMENTS, enchantments);
    }
    
    @ZenCodeType.Method
    default T withoutEnchantments() {
        
        return without(DataComponents.ENCHANTMENTS);
    }
    
    @ZenCodeType.Method
    default T withCanPlaceOn(List<BlockPredicate> predicates, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return withCanPlaceOn(new AdventureModePredicate(predicates, showInTooltip));
    }
    
    @ZenCodeType.Method
    default T withCanPlaceOn(AdventureModePredicate predicate) {
        
        return with(DataComponents.CAN_PLACE_ON, predicate);
    }
    
    @ZenCodeType.Method
    default T withoutCanPlaceOn() {
        
        return without(DataComponents.CAN_PLACE_ON);
    }
    
    @ZenCodeType.Method
    default T withCanBreak(List<BlockPredicate> predicates, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return withCanBreak(new AdventureModePredicate(predicates, showInTooltip));
    }
    
    @ZenCodeType.Method
    default T withCanBreak(AdventureModePredicate predicate) {
        
        return with(DataComponents.CAN_PLACE_ON, predicate);
    }
    
    @ZenCodeType.Method
    default T withoutCanBreak() {
        
        return without(DataComponents.CAN_BREAK);
    }
    
    @ZenCodeType.Method
    default T withAttributeModifiers(ItemAttributeModifiers modifiers) {
        
        return with(DataComponents.ATTRIBUTE_MODIFIERS, modifiers);
    }
    
    @ZenCodeType.Method
    default T withAttributeModifiers(List<ItemAttributeModifiers.Entry> modifiers, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return with(DataComponents.ATTRIBUTE_MODIFIERS, new ItemAttributeModifiers(modifiers, showInTooltip));
    }
    
    @ZenCodeType.Method
    default T withoutAttributeModifiers() {
        
        return without(DataComponents.ATTRIBUTE_MODIFIERS);
    }
    
    @ZenCodeType.Method
    default T withCustomModelData(int value) {
        
        return withCustomModelData(new CustomModelData(value));
    }
    
    @ZenCodeType.Method
    default T withCustomModelData(CustomModelData data) {
        
        return with(DataComponents.CUSTOM_MODEL_DATA, data);
    }
    
    @ZenCodeType.Method
    default T withoutCustomModelData() {
        
        return without(DataComponents.CUSTOM_MODEL_DATA);
    }
    
    @ZenCodeType.Method
    default T withHideAdditionalTooltip() {
        
        return with(DataComponents.HIDE_ADDITIONAL_TOOLTIP, Unit.INSTANCE);
    }
    
    @ZenCodeType.Method
    default T withoutHideAdditionalTooltip() {
        
        return without(DataComponents.HIDE_ADDITIONAL_TOOLTIP);
    }
    
    @ZenCodeType.Method
    default T withHideTooltip() {
        
        return with(DataComponents.HIDE_TOOLTIP, Unit.INSTANCE);
    }
    
    @ZenCodeType.Method
    default T withoutHideTooltip() {
        
        return without(DataComponents.HIDE_TOOLTIP);
    }
    
    @ZenCodeType.Method
    default T withRepairCost(int cost) {
        
        return with(DataComponents.REPAIR_COST, cost);
    }
    
    @ZenCodeType.Method
    default T withoutRepairCost() {
        
        return without(DataComponents.REPAIR_COST);
    }
    
    @ZenCodeType.Method
    default T withCreativeSlotLock() {
        
        return with(DataComponents.CREATIVE_SLOT_LOCK, Unit.INSTANCE);
    }
    
    @ZenCodeType.Method
    default T withoutCreativeSlotLock() {
        
        return without(DataComponents.CREATIVE_SLOT_LOCK);
    }
    
    @ZenCodeType.Method
    default T withEnchantmentGlintOverride(boolean value) {
        
        return with(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, value);
    }
    
    @ZenCodeType.Method
    default T withoutEnchantmentGlintOverride() {
        
        return without(DataComponents.ENCHANTMENT_GLINT_OVERRIDE);
    }
    
    @ZenCodeType.Method
    default T withIntangibleProjectile() {
        
        return with(DataComponents.INTANGIBLE_PROJECTILE, Unit.INSTANCE);
    }
    
    @ZenCodeType.Method
    default T withoutIntangibleProjectile() {
        
        return without(DataComponents.INTANGIBLE_PROJECTILE);
    }
    
    @ZenCodeType.Method
    default T withFood(FoodProperties food) {
        
        return with(DataComponents.FOOD, food);
    }
    
    @ZenCodeType.Method
    default T withoutFood() {
        
        return without(DataComponents.FOOD);
    }
    
    @ZenCodeType.Method
    default T withFireResistant() {
        
        return with(DataComponents.FIRE_RESISTANT, Unit.INSTANCE);
    }
    
    @ZenCodeType.Method
    default T withoutFireResistant() {
        
        return without(DataComponents.FIRE_RESISTANT);
    }
    
    @ZenCodeType.Method
    default T withTool(List<Tool.Rule> rules, float defaultMiningSpeed, int damagePerBlock) {
        
        return withTool(new Tool(rules, defaultMiningSpeed, damagePerBlock));
    }
    
    @ZenCodeType.Method
    default T withTool(Tool tool) {
        
        return with(DataComponents.TOOL, tool);
    }
    
    @ZenCodeType.Method
    default T withoutTool() {
        
        return without(DataComponents.TOOL);
    }
    
    @ZenCodeType.Method
    default T withStoredEnchantments(ItemEnchantments enchantments) {
        
        return with(DataComponents.STORED_ENCHANTMENTS, enchantments);
    }
    
    @ZenCodeType.Method
    default T withoutStoredEnchantments() {
        
        return without(DataComponents.STORED_ENCHANTMENTS);
    }
    
    @ZenCodeType.Method
    default T withDyedColor(int rgb, @ZenCodeType.OptionalBoolean(true) boolean showInTooltip) {
        
        return withDyedColor(new DyedItemColor(rgb, showInTooltip));
    }
    
    @ZenCodeType.Method
    default T withDyedColor(DyedItemColor color) {
        
        return with(DataComponents.DYED_COLOR, color);
    }
    
    @ZenCodeType.Method
    default T withoutDyedColor() {
        
        return without(DataComponents.DYED_COLOR);
    }
    
    @ZenCodeType.Method
    default T withMapColor(int rgb) {
        
        return withMapColor(new MapItemColor(rgb));
    }
    
    @ZenCodeType.Method
    default T withMapColor(MapItemColor color) {
        
        return with(DataComponents.MAP_COLOR, color);
    }
    
    @ZenCodeType.Method
    default T withoutMapColor() {
        
        return without(DataComponents.MAP_COLOR);
    }
    
    @ZenCodeType.Method
    default T withMapId(int id) {
        
        return withMapId(new MapId(id));
    }
    
    @ZenCodeType.Method
    default T withMapId(MapId mapId) {
        
        return with(DataComponents.MAP_ID, mapId);
    }
    
    @ZenCodeType.Method
    default T withoutMapId() {
        
        return without(DataComponents.MAP_ID);
    }
    
    @ZenCodeType.Method
    default T withMapDecorations(Map<String, MapDecorations.Entry> decorations) {
        
        return withMapDecorations(new MapDecorations(decorations));
    }
    
    @ZenCodeType.Method
    default T withMapDecorations(MapDecorations decorations) {
        
        return with(DataComponents.MAP_DECORATIONS, decorations);
    }
    
    @ZenCodeType.Method
    default T withoutMapDecorations() {
        
        return without(DataComponents.MAP_DECORATIONS);
    }
    
    @ZenCodeType.Method
    default T withMapPostProcessing(MapPostProcessing value) {
        
        return with(DataComponents.MAP_POST_PROCESSING, value);
    }
    
    @ZenCodeType.Method
    default T withoutMapPostProcessing() {
        
        return without(DataComponents.MAP_POST_PROCESSING);
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
        
        return with(DataComponents.CHARGED_PROJECTILES, chargedProjectiles);
    }
    
    @ZenCodeType.Method
    default T withoutChargedProjeciles() {
        
        return without(DataComponents.CHARGED_PROJECTILES);
    }
    
    @ZenCodeType.Method
    default T withBundleContents(List<IItemStack> contents) {
        
        return with(DataComponents.BUNDLE_CONTENTS, new BundleContents(Lists.transform(contents, IItemStack::getInternal)));
    }
    
    @ZenCodeType.Method
    default T withBundleContents(BundleContents contents) {
        
        return with(DataComponents.BUNDLE_CONTENTS, contents);
    }
    
    @ZenCodeType.Method
    default T withoutBundleContents() {
        
        return without(DataComponents.BUNDLE_CONTENTS);
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
        
        return with(DataComponents.POTION_CONTENTS, contents);
    }
    
    @ZenCodeType.Method
    default T withoutPotionContents() {
        
        return without(DataComponents.POTION_CONTENTS);
    }
    
    @ZenCodeType.Method
    default T withSuspiciousStewEffects(List<SuspiciousStewEffects.Entry> effects) {
        
        return withSuspiciousStewEffects(new SuspiciousStewEffects(effects));
    }
    
    @ZenCodeType.Method
    default T withSuspiciousStewEffects(SuspiciousStewEffects suspiciousStewEffects) {
        
        return with(DataComponents.SUSPICIOUS_STEW_EFFECTS, suspiciousStewEffects);
    }
    
    @ZenCodeType.Method
    default T withoutSuspiciousStewEffects() {
        
        return without(DataComponents.SUSPICIOUS_STEW_EFFECTS);
    }
    
    @ZenCodeType.Method
    default T withWritableBookContent(List<Filterable<String>> pages) {
        
        return withWritableBookContent(new WritableBookContent(pages));
    }
    
    @ZenCodeType.Method
    default T withWritableBookContent(WritableBookContent content) {
        
        return with(DataComponents.WRITABLE_BOOK_CONTENT, content);
    }
    
    @ZenCodeType.Method
    default T withoutWritableBookContent() {
        
        return without(DataComponents.WRITABLE_BOOK_CONTENT);
    }
    
    @ZenCodeType.Method
    default T withWrittenBookContent(WritableBookContent content) {
        
        return with(DataComponents.WRITABLE_BOOK_CONTENT, content);
    }
    
    @ZenCodeType.Method
    default T withoutWrittenBookContent() {
        
        return without(DataComponents.WRITTEN_BOOK_CONTENT);
    }
    
    @ZenCodeType.Method
    default T withTrim(ArmorTrim trim) {
        
        return with(DataComponents.TRIM, trim);
    }
    
    @ZenCodeType.Method
    default T withoutTrim() {
        
        return without(DataComponents.TRIM);
    }
    
    @ZenCodeType.Method
    default T withDebugStickState(DebugStickState state) {
        
        return with(DataComponents.DEBUG_STICK_STATE, state);
    }
    
    @ZenCodeType.Method
    default T withoutDebugStickState() {
        
        return without(DataComponents.DEBUG_STICK_STATE);
    }
    
    @ZenCodeType.Method
    default T withEntityData(MapData data) {
        
        return withEntityData(CustomData.of(data.getInternal()));
    }
    
    @ZenCodeType.Method
    default T withEntityData(CustomData data) {
        
        return with(DataComponents.ENTITY_DATA, data);
    }
    
    @ZenCodeType.Method
    default T withoutEntityDate() {
        
        return without(DataComponents.ENTITY_DATA);
    }
    
    @ZenCodeType.Method
    default T withBucketEntityData(MapData data) {
        
        return withBucketEntityData(CustomData.of(data.getInternal()));
    }
    
    @ZenCodeType.Method
    default T withBucketEntityData(CustomData data) {
        
        return with(DataComponents.BUCKET_ENTITY_DATA, data);
    }
    
    @ZenCodeType.Method
    default T withoutBucketEntityData() {
        
        return without(DataComponents.BUCKET_ENTITY_DATA);
    }
    
    @ZenCodeType.Method
    default T withBlockEntityData(MapData data) {
        
        return withBlockEntityData(CustomData.of(data.getInternal()));
    }
    
    @ZenCodeType.Method
    default T withBlockEntityData(CustomData data) {
        
        return with(DataComponents.BLOCK_ENTITY_DATA, data);
    }
    
    @ZenCodeType.Method
    default T withoutBlockEntityData() {
        
        return without(DataComponents.BLOCK_ENTITY_DATA);
    }
    
    @ZenCodeType.Method
    default T withInstrument(Instrument instrument) {
        
        return with(DataComponents.INSTRUMENT, BuiltInRegistries.INSTRUMENT.wrapAsHolder(instrument));
    }
    
    @ZenCodeType.Method
    default T withoutInstrument() {
        
        return without(DataComponents.INSTRUMENT);
    }
    
    @ZenCodeType.Method
    default T withOminousBottleAmplifier(int amplifier) {
        
        return with(DataComponents.OMINOUS_BOTTLE_AMPLIFIER, amplifier);
    }
    
    @ZenCodeType.Method
    default T withoutOminousBottleAmplifier() {
        
        return without(DataComponents.OMINOUS_BOTTLE_AMPLIFIER);
    }
    
    @ZenCodeType.Method
    default T withRecipes(List<ResourceLocation> recipes) {
        
        return with(DataComponents.RECIPES, recipes);
    }
    
    @ZenCodeType.Method
    default T withoutRecipes() {
        
        return without(DataComponents.RECIPES);
    }
    
    @ZenCodeType.Method
    default T withLodestoneTracker(LodestoneTracker tracker) {
        
        return with(DataComponents.LODESTONE_TRACKER, tracker);
    }
    
    @ZenCodeType.Method
    default T withoutLodestoneTracker() {
        
        return without(DataComponents.LODESTONE_TRACKER);
    }
    
    @ZenCodeType.Method
    default T withFireworkExplosion(FireworkExplosion explosion) {
        
        return with(DataComponents.FIREWORK_EXPLOSION, explosion);
    }
    
    @ZenCodeType.Method
    default T withoutFireworkExplosion() {
        
        return without(DataComponents.FIREWORK_EXPLOSION);
    }
    
    @ZenCodeType.Method
    default T withFireworks(int flightDuration, List<FireworkExplosion> explosions) {
        
        return withFireworks(new Fireworks(flightDuration, explosions));
    }
    
    @ZenCodeType.Method
    default T withFireworks(Fireworks fireworks) {
        
        return with(DataComponents.FIREWORKS, fireworks);
    }
    
    
    @ZenCodeType.Method
    default T withoutFireworks() {
        
        return without(DataComponents.FIREWORKS);
    }
    
    
    // withProfile() but we don't have ResolvableProfile
    
    @ZenCodeType.Method
    default T withoutProfile() {
        
        return without(DataComponents.PROFILE);
    }
    
    @ZenCodeType.Method
    default T withNoteBlockSound(ResourceLocation sound) {
        
        return with(DataComponents.NOTE_BLOCK_SOUND, sound);
    }
    
    @ZenCodeType.Method
    default T withoutNoteBlockSound() {
        
        return without(DataComponents.NOTE_BLOCK_SOUND);
    }
    
    @ZenCodeType.Method
    default T withBannerPatterns(List<BannerPatternLayers.Layer> layers) {
        
        return withBannerPatterns(new BannerPatternLayers(layers));
    }
    
    @ZenCodeType.Method
    default T withBannerPatterns(BannerPatternLayers layers) {
        
        return with(DataComponents.BANNER_PATTERNS, layers);
    }
    
    @ZenCodeType.Method
    default T withoutBannerPatterns() {
        
        return without(DataComponents.BANNER_PATTERNS);
    }
    
    @ZenCodeType.Method
    default T withBaseColor(DyeColor color) {
        
        return with(DataComponents.BASE_COLOR, color);
    }
    
    @ZenCodeType.Method
    default T withoutBaseColor() {
        
        return without(DataComponents.BASE_COLOR);
    }
    
    @ZenCodeType.Method
    default T withPotDecorations(Item back, Item left, Item right, Item front) {
        
        return withPotDecorations(new PotDecorations(back, left, right, front));
    }
    
    @ZenCodeType.Method
    default T withPotDecorations(PotDecorations decorations) {
        
        return with(DataComponents.POT_DECORATIONS, decorations);
    }
    
    @ZenCodeType.Method
    default T withoutPotDecorations() {
        
        return without(DataComponents.POT_DECORATIONS);
    }
    
    @ZenCodeType.Method
    default T withContainer(List<IItemStack> contents) {
        
        return withContainer(ItemContainerContents.fromItems(Lists.transform(contents, IItemStack::getInternal)));
    }
    
    @ZenCodeType.Method
    default T withContainer(ItemContainerContents contents) {
        
        return with(DataComponents.CONTAINER, contents);
    }
    
    @ZenCodeType.Method
    default T withoutContainer() {
        
        return without(DataComponents.CONTAINER);
    }
    
    @ZenCodeType.Method
    default T withBlockState(BlockItemStateProperties properties) {
        
        return with(DataComponents.BLOCK_STATE, properties);
    }
    
    @ZenCodeType.Method
    default T withoutBlockState() {
        
        return without(DataComponents.BLOCK_STATE);
    }
    
    @ZenCodeType.Method
    default T withBees(List<BeehiveBlockEntity.Occupant> occupants) {
        
        return with(DataComponents.BEES, occupants);
    }
    
    @ZenCodeType.Method
    default T withoutBees() {
        
        return without(DataComponents.BEES);
    }
    
    @ZenCodeType.Method
    default T withLock(String code) {
        
        return withLock(new LockCode(code));
    }
    
    @ZenCodeType.Method
    default T withLock(LockCode lock) {
        
        return with(DataComponents.LOCK, lock);
    }
    
    @ZenCodeType.Method
    default T withoutLock() {
        
        return without(DataComponents.LOCK);
    }
    
    @ZenCodeType.Method
    default T withContainerLoot(ResourceKey<LootTable> lootTable, long seed) {
        
        return withContainerLoot(new SeededContainerLoot(lootTable, seed));
    }
    
    @ZenCodeType.Method
    default T withContainerLoot(SeededContainerLoot loot) {
        
        return with(DataComponents.CONTAINER_LOOT, loot);
    }
    
    @ZenCodeType.Method
    default T withoutContainerLoot() {
        
        return without(DataComponents.CONTAINER_LOOT);
    }
    
    @ZenCodeType.Method
    <U> T with(DataComponentType<U> componentType, @Nullable U value);
    
    @ZenCodeType.Method
    <U> T without(DataComponentType<U> componentType);
    
}
