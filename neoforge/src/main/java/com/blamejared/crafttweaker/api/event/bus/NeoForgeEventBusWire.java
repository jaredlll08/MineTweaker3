package com.blamejared.crafttweaker.api.event.bus;

import com.blamejared.crafttweaker.api.event.Phase;
import com.blamejared.crafttweaker.api.util.GenericUtil;
import com.google.common.base.Suppliers;
import com.google.common.reflect.TypeToken;
import net.minecraft.Util;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.commons.lang3.NotImplementedException;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Wires the given {@link IEventBus} onto the default NeoForge's {@link net.neoforged.bus.api.IEventBus}.
 *
 * <p>The default event bus is {@link NeoForge#EVENT_BUS}.</p>
 *
 * <p>By default, all even {@link Phase}s are automatically wired according to the various {@link EventPriority} values
 * provided by NeoForge.</p>
 *
 * @since 15.0.0
 */
public final class NeoForgeEventBusWire implements IEventBusWire {
    
    @FunctionalInterface
    private interface ListenerRegistrationHandler<E extends Event> {
        
        void register(final EventPriority priority, final boolean receiveCanceled, final Consumer<E> handler);
        
    }
    
    private static final Supplier<IEventBusWire> INSTANCE = Suppliers.memoize(NeoForgeEventBusWire::new);
    private static final Map<Phase, EventPriority> PRIORITIES = Util.make(new EnumMap<>(Phase.class), it -> {
        it.put(Phase.EARLIEST, EventPriority.HIGHEST);
        it.put(Phase.NORMAL, EventPriority.NORMAL);
        it.put(Phase.LATEST, EventPriority.LOWEST);
    });
    
    private NeoForgeEventBusWire() {}
    
    /**
     * Obtains an instance of this class.
     *
     * @return An instance of this class.
     *
     * @since 15.0.0
     */
    public static IEventBusWire of() {
        
        return INSTANCE.get();
    }
    
    @Override
    public <T> void registerBusForDispatch(final TypeToken<T> eventType, final IEventBus<T> bus) {
        
        if(!eventType.isSubtypeOf(Event.class)) {
            throw new IllegalArgumentException("Unable to wire EventBus to NeoForge bus for type " + eventType);
        }
        
        this.registerBus(GenericUtil.uncheck(eventType), GenericUtil.uncheck(bus));
    }
    
    private <T extends Event> void registerBus(final TypeToken<T> eventType, final IEventBus<T> bus) {
        
        final ListenerRegistrationHandler<T> registrationHandler = this.discoverRegistrationHandler(eventType);
        PRIORITIES.forEach((phase, priority) -> registrationHandler.register(priority, true, e -> bus.post(phase, e)));
    }
    
    private <T extends Event> ListenerRegistrationHandler<T> discoverRegistrationHandler(final TypeToken<T> token) {
        
        final net.neoforged.bus.api.IEventBus bus = this.discoverBus(token);
        final Class<T> rawClass = GenericUtil.uncheck(token.getRawType());
        return (priority, receiveCanceled, handler) -> bus.addListener(priority, receiveCanceled, rawClass, handler);
        
    }
    
    private <T extends Event> net.neoforged.bus.api.IEventBus discoverBus(final TypeToken<T> token) {
        
        if(token.isSubtypeOf(IModBusEvent.class)) {
            // TODO("")
            throw new NotImplementedException("TODO");
        }
        
        return NeoForge.EVENT_BUS;
    }
    
}
