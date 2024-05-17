package com.blamejared.crafttweaker.natives.server;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraft.server.network.Filterable;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Optional;
import java.util.function.Function;

@ZenRegister
@Document("vanilla/api/server/Filterable")
@NativeTypeRegistration(value = Filterable.class, zenCodeName = "crafttweaker.api.server.Filterable")
public class ExpandFilterable {
    
    @ZenCodeType.StaticExpansionMethod
    public static <T> Filterable<T> of(T raw, @ZenCodeType.Optional @ZenCodeType.Nullable T filtered) {
        
        return new Filterable<>(raw, Optional.ofNullable(filtered));
    }
    
    @ZenCodeType.Method
    @ZenCodeType.Nullable
    public static <T> T filtered(Filterable<T> internal) {
        
        return internal.filtered().orElse(null);
    }
    
    @ZenCodeType.Method
    public static <T> T getFiltered(Filterable<T> internal, boolean filtered) {
        
        return internal.get(filtered);
    }
    
    @ZenCodeType.Method
    public static <T, U> Optional<Filterable<U>> resolve(Filterable<T> internal, Function<T, Optional<U>> resolver) {
        
        return internal.resolve(resolver);
    }
    
    @ZenCodeType.Method
    public static <T, U> Filterable<U> map(Filterable<T> internal, Function<T, U> mapper) {
        
        return internal.map(mapper);
    }
    
    @ZenCodeType.Method
    public static <T> T raw(Filterable<T> internal) {
        
        return internal.raw();
    }
    
}
