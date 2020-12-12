package com.blamejared.crafttweaker.api;

import com.blamejared.crafttweaker.api.annotations.Preprocessor;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.api.zencode.IPreprocessor;
import com.blamejared.crafttweaker.api.zencode.brackets.ValidatedEscapableBracketParser;
import com.blamejared.crafttweaker.api.zencode.impl.registry.BracketResolverRegistry;
import com.blamejared.crafttweaker.api.zencode.impl.registry.PreprocessorRegistry;
import com.blamejared.crafttweaker.api.zencode.impl.registry.ZenClassRegistry;
import com.blamejared.crafttweaker.api.zencode.impl.registry.wrapper.WrapperRegistry;
import com.blamejared.crafttweaker.impl.commands.BracketDumperInfo;
import com.blamejared.crafttweaker.impl.native_types.NativeTypeRegistry;
import com.blamejared.crafttweaker.impl.tag.manager.TagManager;
import com.blamejared.crafttweaker.impl.tag.registry.CrTTagRegistryData;
import com.blamejared.crafttweaker_annotations.annotations.ZenWrapper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.ModFileScanData;
import org.objectweb.asm.Type;
import org.openzen.zencode.java.ScriptingEngine;
import org.openzen.zencode.java.module.JavaNativeModule;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class CraftTweakerRegistry {
    
    private static final BracketResolverRegistry BRACKET_RESOLVER_REGISTRY = new BracketResolverRegistry();
    private static final PreprocessorRegistry PREPROCESSOR_REGISTRY = new PreprocessorRegistry();
    private static final ZenClassRegistry ZEN_CLASS_REGISTRY = new ZenClassRegistry();
    private static final WrapperRegistry WRAPPER_REGISTRY = new WrapperRegistry();
    
    /**
     * Find all classes that have a {@link ZenRegister} annotation and registers them to the class list for loading.
     */
    public static void findClasses() {
        ZEN_CLASS_REGISTRY.initNativeTypes();
        
        getAllTypesWith(ZenRegister.class).forEach(ZEN_CLASS_REGISTRY::addType);
        
        BRACKET_RESOLVER_REGISTRY.addClasses(ZEN_CLASS_REGISTRY.getAllRegisteredClasses());
        BRACKET_RESOLVER_REGISTRY.validateBrackets();
        
        getAllTypesWith(Preprocessor.class).forEach(PREPROCESSOR_REGISTRY::addType);
        getAllTypesWith(ZenWrapper.class).forEach(WRAPPER_REGISTRY::addType);
        
        ZEN_CLASS_REGISTRY.getImplementationsOf(TagManager.class)
                .forEach(CrTTagRegistryData.INSTANCE::addTagImplementationClass);
    }
    
    private static Stream<Type> getAllTypesWith(Class<? extends Annotation> annotationCls) {
        final Type annotationType = Type.getType(annotationCls);
        return ModList.get()
                .getAllScanData()
                .stream()
                .map(ModFileScanData::getAnnotations)
                .flatMap(Collection::stream)
                .filter(a -> annotationType.equals(a.getAnnotationType()))
                .map(ModFileScanData.AnnotationData::getClassType);
    }
    
    
    //<editor-fold desc="ZenClassRegistry Delegates">
    // ##################################
    // ### ZenClassRegistry Delegates ###
    // ##################################
    
    /**
     * Gets an ImmutableMap of the classMap.
     * <p>
     * Classes should not be added manually to this map. See {@link ZenRegister}
     *
     * @return Map of String -> Class for ZenName -> Java class
     */
    public static Map<String, Class<?>> getZenClassMap() {
        return ZEN_CLASS_REGISTRY.getZenClasses();
    }
    
    /**
     * Gets all classes with a given package root.
     * <p>
     * Providing "crafttweaker" as the name can return:
     * <p>
     * {@code "crafttweaker.sub.package.Class}
     * and
     * {@code "crafttweaker.sub.other.package.Class}
     *
     * @param name Name of the Zen Package.
     * @return list of classes in the Zen Package.
     */
    public static List<Class<?>> getClassesInPackage(String name) {
        return ZEN_CLASS_REGISTRY.getClassesInPackage(name);
    }
    
    /**
     * Gets all the top level Zen Packages.
     *
     * @return Set of top level packages
     */
    public static Set<String> getRootPackages() {
        return ZEN_CLASS_REGISTRY.getRootPackages();
    }
    
    /**
     * Gets the ZenGlobals list.
     *
     * @return ImmutableList of the ZenGlobals
     */
    public static List<Class<?>> getZenGlobals() {
        return ZEN_CLASS_REGISTRY.getZenGlobals();
    }
    
    /**
     * Gets the registered Expansions by expanded name
     *
     * @return The Map
     */
    public static Map<String, List<Class<?>>> getExpansions() {
        return ZEN_CLASS_REGISTRY.getExpansionsByExpandedName();
    }
    
    /**
     * Gets the found recipe managers
     *
     * @return ImmutableList of the recipe managers.
     */
    public static List<Class<? extends IRecipeManager>> getRecipeManagers() {
        return ZEN_CLASS_REGISTRY.getRecipeManagers();
    }
    //</editor-fold>
    
    
    //<editor-fold desc="BracketResolverRegistry Delegates">
    // #########################################
    // ### BracketResolverRegistry Delegates ###
    // #########################################
    
    /**
     * Gets the Bracket dumper Map
     *
     * @return ImmutableMap of the Bracket dumpers
     */
    public static Map<String, BracketDumperInfo> getBracketDumpers() {
        return BRACKET_RESOLVER_REGISTRY.getBracketDumpers();
    }
    
    /**
     * Adds a name as advanced BEP name.
     * This means that a Bracket Validator may exist that has no matching BEP method in here.
     *
     * @param name The name to add
     */
    public static void addAdvancedBEPName(String name) {
        BRACKET_RESOLVER_REGISTRY.addAdvancedBEPName(name);
    }
    
    public static List<ValidatedEscapableBracketParser> getBracketResolvers(String name, ScriptingEngine scriptingEngine, JavaNativeModule crafttweakerModule) {
        return BRACKET_RESOLVER_REGISTRY.getBracketResolvers(name, scriptingEngine, crafttweakerModule);
    }
    //</editor-fold>
    
    
    //<editor-fold desc="BracketResolverRegistry Delegates">
    // #########################################
    // ### BracketResolverRegistry Delegates ###
    // #########################################
    public static List<IPreprocessor> getPreprocessors() {
        return PREPROCESSOR_REGISTRY.getPreprocessors();
    }
    //</editor-fold>
    
    //</editor-fold>
    
    
    //<editor-fold desc="WrapperRegistry">
    // #########################################
    // ### WrapperRegistry ###
    // #########################################
    public static WrapperRegistry getWrapperRegistry() {
        return WRAPPER_REGISTRY;
    }
    //</editor-fold>
    
    public static NativeTypeRegistry getNativeTypeRegistry() {
        return ZEN_CLASS_REGISTRY.getNativeTypeRegistry();
    }
}
