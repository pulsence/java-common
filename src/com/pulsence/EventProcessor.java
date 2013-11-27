package com.pulsence;

import java.util.HashMap;

/**
 * Simple lightweight event processor class. This only handles the event
 * listeners for this exact event.
 * @author Tim Eck II
 *
 * @param <K> This is the data type for the event listener keys
 * @param <E> This is the data type for the arguments that are passed to the
 * event
 */
public class EventProcessor<K, E>{
    private HashMap<K, EventListener<E>> eventListeners;
    
    public EventProcessor(){
        eventListeners = new HashMap<K, EventListener<E>>();
    }

    /**
     * Registers an event listener with this event
     * @param listenerKey The key for this listener
     * @param listener The actual listener
     */
    public void registerEventListener(K listenerKey, EventListener<E> listener) {
        eventListeners.put(listenerKey, listener);
    }
    
    /**
     * Removes a listener from this event
     * @param listenerKey The key of the listener to remove
     */
    public void unregisterEventListener(K listenerKey){
        eventListeners.remove(listenerKey);
    }
    
    public void unregisterAllEventListeners(){
        eventListeners.clear();
    }

    /**
     * Calls {@link EventProcessor#triggerEvent(Object)} with null passed to
     * params.
     */
    public void triggerEvent() {
        triggerEvent(null);
    }
    
    /**
     * Triggers this event. This means that all currently registered listeners
     * are processed in a consistent, undefined pattern.
     * @param params The parameters to be passed to each of the event listeners.
     */
    public void triggerEvent(E params){
        for(EventListener<E> listener : eventListeners.values()){
            listener.processEvent(params);
        }
    }
}
