package com.blamejared.crafttweaker.natives.item.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.server.network.Filterable;
import net.minecraft.world.item.component.BookContent;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@Document("vanilla/api/item/component/BookContent")
@NativeTypeRegistration(value = BookContent.class, zenCodeName = "crafttweaker.api.item.component.BookContent")
public class ExpandBookContent {
    
    @ZenCodeType.Method
    public static <T, C> List<Filterable<T>> pages(BookContent<T, C> internal) {
        
        return internal.pages();
    }
    
    @ZenCodeType.Method
    public static <T, C> C withReplacedPages(BookContent<T, C> internal, List<Filterable<T>> list) {
        
        return internal.withReplacedPages(list);
    }
    
}
