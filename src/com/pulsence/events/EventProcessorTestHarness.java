package com.pulsence.events;

import java.util.Random;

/**
 * Simple test harness to do performance testing. The code here is meant to testing
 * performance tweaks and to make sure that I do no decrees perfromance accidentally.
 * 
 * @author Tim Eck II
 *
 */
public class EventProcessorTestHarness {
    public static void main(String[] args){
        System.out.println("Beginning Tests...");
        EventCollection<String> events = new EventCollection<String>();
        String eventName = "my-event";
        events.<Integer, Integer>registerEvent(eventName);
        testEventProcessor(events, eventName);
    }
    
    private static void testEventProcessor(EventCollection<String> events, String eventName){
        EventProcessor<Integer, Integer> processor = events.<Integer, Integer>getEventProcessor(eventName);
        long timeDelta = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            processor.registerEventListener(i, new EventListener<Integer>(){
                @Override
                public void processEvent(Integer args) {
                    Random r = new Random();
                    int x = 10;
                    x += r.nextInt();
                    x /= 10;
                    x = x + x;
                }
            });
        }
        timeDelta = System.currentTimeMillis() - timeDelta;
        System.out.println("Time to register 1000 event listeners: " + timeDelta);
        
        System.out.println("Time to processor event 1 times: " +  
                                            sampleEvent(events, eventName, 1));
        System.out.println("Time to processor event 100 times: " +  
                                            sampleEvent(events, eventName, 100));
        System.out.println("Time to processor event 500 times: " + 
                                            sampleEvent(events, eventName, 500));
    }
    
    /**
     * Determines the average amount of time to fire an even n number of times
     * @param processor
     * @param eventName  The event to fire
     * @param count The number of times to fire the event
     * @return
     */
    private static float sampleEvent(EventCollection<String> events, String eventName, int count){
        float sum = 0;
        int n = 100;
        for(int i = 0; i < n; i++){
            long start = System.currentTimeMillis();
            for(int j = 0; j < count; j++){
                events.triggerEvent(eventName);
            }
            sum += System.currentTimeMillis() - start;
        }
        return sum / n;
    }
}
