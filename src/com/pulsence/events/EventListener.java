package com.pulsence.events;

/**
 * 
 * @author Tim Eck II
 *
 * @param <E> The event parameter type
 */
public interface EventListener<E> {
    public void processEvent(E params);
}
