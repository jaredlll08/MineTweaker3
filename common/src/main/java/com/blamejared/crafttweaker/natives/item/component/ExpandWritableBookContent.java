package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.server.network.Filterable;
import net.minecraft.world.item.component.WritableBookContent;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/item/component/WritableBookContent")
@NativeTypeRegistration(value = WritableBookContent.class, zenCodeName = "crafttweaker.api.item.component.WritableBookContent")
public class ExpandWritableBookContent {
    
    @ZenCodeType.StaticExpansionMethod
    public static WritableBookContent of(List<Filterable<String>> pages) {
        
        return new WritableBookContent(pages);
    }
    
    @ZenCodeType.Method
    public static List<String> getPages(WritableBookContent internal, boolean filtered) {
        
        return internal.getPages(filtered).toList();
    }
    
    @ZenCodeType.Method
    public static WritableBookContent withReplacedPages(WritableBookContent internal, List<Filterable<String>> pages) {
        
        return internal.withReplacedPages(pages);
    }
    
    @ZenCodeType.Getter("pages")
    public static List<Filterable<String>> pages(WritableBookContent internal) {
        
        return internal.pages();
    }
    
}
