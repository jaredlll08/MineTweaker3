package com.blamejared.crafttweaker.api.command.argument;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.bracket.BracketHandlers;
import com.blamejared.crafttweaker.api.data.IData;
import com.blamejared.crafttweaker.api.data.MapData;
import com.blamejared.crafttweaker.api.data.converter.StringConverter;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.google.common.collect.Lists;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zenscript.lexer.ParseException;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IItemStackArgument implements ArgumentType<IItemStack> {
    
    public static final ResourceLocation ID = CraftTweakerConstants.rl("item");
    private static final Collection<String> EXAMPLES = Lists.newArrayList("<item:minecraft:apple>", "<item:minecraft:diamond_pickaxe>.withJsonComponents({\"minecraft:max_stack_size\": 5, \"!minecraft:tool\": {}})");
    private static final DynamicCommandExceptionType MALFORMED_DATA = new DynamicCommandExceptionType(o -> new LiteralMessage(((ParseException) o).message));
    private static final DynamicCommandExceptionType UNKNOWN_ITEM = new DynamicCommandExceptionType(o -> new LiteralMessage("Unknown item: " + o));
    private static final SimpleCommandExceptionType INVALID_STRING = new SimpleCommandExceptionType(new LiteralMessage("invalid string"));
    private static final Pattern ITEM_PATTERN = Pattern.compile("^<item:(\\w+:\\w+)>(\\.withJsonComponents\\((\\{.*})\\))?(\\s*\\*\s*(\\d+))?");
    
    @Override
    public IItemStack parse(StringReader reader) throws CommandSyntaxException {
        
        Matcher matcher = ITEM_PATTERN.matcher(reader.getRemaining());
        if(!matcher.find()) {
            throw INVALID_STRING.createWithContext(reader);
        }
        String itemLocation = matcher.group(1);
        try {
            IItemStack stack = getItem(itemLocation, matcher.group(3), matcher.group(5));
            reader.setCursor(reader.getCursor() + matcher.group(0).length());
            return stack;
        } catch(ParseException e) {
            reader.setCursor(reader.getCursor() + itemLocation.length() + "<item:>.withJsonComponents(".length() + e.position.getFromLineOffset());
            throw MALFORMED_DATA.createWithContext(reader, e);
        } catch(IllegalArgumentException e) {
            reader.setCursor(reader.getCursor() + matcher.group(0).length());
            throw UNKNOWN_ITEM.createWithContext(reader, itemLocation);
        }
    }
    
    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        
        return SharedSuggestionProvider.suggest(BuiltInRegistries.ITEM.keySet()
                .stream()
                .map(it -> String.format("<item:%s>", it)), builder);
    }
    
    @Override
    public Collection<String> getExamples() {
        
        return EXAMPLES;
    }
    
    private static IItemStack getItem(String location, String components, String amount) throws ParseException, IllegalArgumentException {
        
        IItemStack stack = BracketHandlers.getItem(location).asMutable();
        if(components != null) {
            IData data = StringConverter.convert(components);
            if(data instanceof MapData map) {
                stack.withJsonComponents(map);
            } else {
                throw new IllegalArgumentException("Given components: '%s' was not of type MapData!".formatted(components));
            }
            
        }
        if(amount != null) {
            try {
                int stackAmount = Integer.parseInt(amount);
                stack.withAmount(stackAmount);
            } catch(NumberFormatException e) {
                throw new IllegalArgumentException("Given amount: '%s' was not a valid integer!".formatted(components));
            }
        }
        return stack;
    }
    
    public static IItemStackArgument get() {
        
        return new IItemStackArgument();
    }
    
}
