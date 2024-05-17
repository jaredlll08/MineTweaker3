package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.server.network.Filterable;
import net.minecraft.world.item.component.WrittenBookContent;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/item/component/WrittenBookContent")
@NativeTypeRegistration(value = WrittenBookContent.class, zenCodeName = "crafttweaker.api.item.component.WrittenBookContent")
public class ExpandWrittenBookContent {
    
    
    @ZenCodeType.Getter("generation")
    public static int generation(WrittenBookContent internal) {
        
        return internal.generation();
    }
    
    @ZenCodeType.Method
    public static WrittenBookContent markResolved(WrittenBookContent internal) {
        
        return internal.markResolved();
    }
    
    @ZenCodeType.Method
    public static WrittenBookContent withReplacedPages(WrittenBookContent internal, List<Filterable<Component>> pages) {
        
        return internal.withReplacedPages(pages);
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static WrittenBookContent tryCraftCopy(WrittenBookContent internal) {
        
        return internal.tryCraftCopy();
    }
    
    @ZenCodeType.Getter("author")
    public static String author(WrittenBookContent internal) {
        
        return internal.author();
    }
    
    @ZenCodeType.Getter("resolved")
    public static boolean resolved(WrittenBookContent internal) {
        
        return internal.resolved();
    }
    
    @ZenCodeType.Method
    public static List<Component> getPages(WrittenBookContent internal, boolean filtered) {
        
        return internal.getPages(filtered);
    }
    
    @ZenCodeType.Getter("title")
    public static Filterable<String> title(WrittenBookContent internal) {
        
        return internal.title();
    }
    
    @ZenCodeType.Getter("pages")
    public static List<Filterable<Component>> pages(WrittenBookContent internal) {
        
        return internal.pages();
    }
    
}
