package com.pulsence;

import java.util.HashMap;

/**
 * A class that stores multiple {@link EventProcessor} objects in a hash map,
 * allowing you to combine multiple events into a single easy to handle object.
 * @author Tim Eck II
 *
 * @param <K> The data type that will be used for the keys of the events.
 */
public class EventCollection<K> {
    private HashMap<K, EventProcessor<?, ?>> events;
    
    public EventCollection(){
        events = new HashMap<K, EventProcessor<?, ?>>();
    }
    
    /**
     * Registers an event, which in turn creates a new event processor.
     * @param eventKey The key that this event processor will correspond too.
     * @param <H> The key data type for the event processor
     * @param <E> The key data type for the event processors parameters
     */
    public <H, E> void registerEvent(K eventKey){
        events.put(eventKey, new EventProcessor<H, E>());
    }
    
    /**
     * Adds an existing event processor under a given key to the collection 
     * @param eventKey The key that this event processor will correspond too.
     * @param processor The pre-created processor to add
     * @param <H> The key data type for the event processor
     * @param <E> The key data type for the event processors parameters
     */
    public <H, E> void registerEvent(K eventKey, EventProcessor<H, E> processor){
        events.put(eventKey, processor);
    }
    
    /**
     * Unregisters a event from the collection.
     * @param eventKey The key of the event to remove.
     */
    public void unregisterEvent(K eventKey){
        events.remove(eventKey);
    }
    
    /**
     * Triggers the event at the given key. Calls {@link EventProcessor#triggerEvent()}.
     * @param eventKey The key for the event to trigger
     */
    public void triggerEvent(K eventKey){
        events.get(eventKey).triggerEvent();
    }
    
    /**
     * Triggers the event at the given key and passes the parameters provided.
     * Calls {@link EventProcessor#triggerEvent(Object)}.
     * @param eventKey The key for the event to trigger
     * @param params The parameters to pass to the event
     * @param <H> The key data type for the event processor
     * @param <E> The key data type for the event processors parameters
     */
    @SuppressWarnings("unchecked")
    public <H, E> void triggerEvent(K eventKey, E params){
        ((EventProcessor<H, E>)events.get(eventKey)).triggerEvent(params);
    }

    /**
     * Gets a specific event processor. This is useful for registering event
     * listeners to the event after it has been added into this collection.
     * @param eventKey The event to retrieve.
     * @param <H> The key data type for the event processor
     * @param <E> The key data type for the event processors parameters
     * @return The event corresponding to the eventKey, null if there is no
     * event for that key.
     */
    @SuppressWarnings("unchecked")
    public <H, E> EventProcessor<H, E> getEventProcessor(K eventKey){
        return (EventProcessor<H, E>) events.get(eventKey);
    }
}
