package com.blamejared.crafttweaker.api.util;

import com.blamejared.crafttweaker.natives.resource.ExpandResourceLocation;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.shared.CodePosition;
import org.openzen.zencode.shared.LiteralSourceFile;
import org.openzen.zenscript.lexer.ParseException;
import org.openzen.zenscript.lexer.ZSTokenParser;
import org.openzen.zenscript.lexer.ZSTokenType;
import org.openzen.zenscript.parser.expression.ParsedCallArguments;
import org.openzen.zenscript.parser.expression.ParsedExpression;
import org.openzen.zenscript.parser.expression.ParsedExpressionCall;
import org.openzen.zenscript.parser.expression.ParsedExpressionMember;
import org.openzen.zenscript.parser.expression.ParsedExpressionString;
import org.openzen.zenscript.parser.expression.ParsedExpressionVariable;
import org.openzen.zenscript.parser.type.IParsedType;
import org.openzen.zenscript.parser.type.ParsedTypeBasic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ParseUtil {
    
    private ParseUtil() {
    
    }
    
    public static ParsedExpression staticMemberExpression(CodePosition position, String name) {
        
        String[] strExpressions = name.split("\\.");
        ParsedExpression expression = new ParsedExpressionVariable(position, strExpressions[0], null);
        for(int i = 1; i < strExpressions.length; i++) {
            expression = new ParsedExpressionMember(position, expression, strExpressions[i], List.of());
        }
        
        return expression;
    }
    
    public static IParsedType readParsedType(String name, CodePosition position) throws ParseException {
        
        try {
            return IParsedType.parse(ZSTokenParser.create(new LiteralSourceFile("type reading: " + name, name), null));
        } catch(IOException ignored) {
        }
        return ParsedTypeBasic.UNDETERMINED;
    }
    
    public static String readBracketContent(CodePosition position, ZSTokenParser tokens) throws ParseException {
        
        return readContent(position, tokens, ZSTokenType.T_GREATER);
    }
    
    public static String readContent(CodePosition position, ZSTokenParser tokens, ZSTokenType endType) throws ParseException {
        
        StringBuilder builder = new StringBuilder();
        
        while(tokens.optional(endType) == null) {
            ZSTokenType peekType = tokens.peek().getType();
            if(peekType == ZSTokenType.EOF) {
                throw new ParseException(position, "Reached EOF, BEP is missing a closing " + endType.flyweight.content);
            }
            if(tokens.getLastWhitespace().contains("\n")) {
                throw new ParseException(position, "BEPs cannot contain new lines!");
            }
            builder.append(tokens.next().content);
            builder.append(tokens.getLastWhitespace());
        }
        return builder.toString();
    }
    
    public static ParsedExpression createResourceLocationArgument(CodePosition position, ResourceLocation location) throws ParseException {
        
        final List<ParsedExpression> arguments = new ArrayList<>(2);
        arguments.add(new ParsedExpressionString(position, location.getNamespace(), false));
        arguments.add(new ParsedExpressionString(position, location.getPath(), false));
        final ParsedCallArguments callArguments = new ParsedCallArguments(null, arguments);
        
        final ParsedExpression classCall = ParseUtil.staticMemberExpression(position, ExpandResourceLocation.ZC_CLASS_NAME);
        final ParsedExpression parseCall = new ParsedExpressionMember(position, classCall, "fromNamespaceAndPath", null);
        return new ParsedExpressionCall(position, parseCall, callArguments);
    }
    
}
