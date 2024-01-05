package com.sachin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RateLimiter {

    public static final long RATE_LIMIT_PER_MINUTE = 10;

    public static final long RATE_LIMIT_PERIOD_MS = 60 * 1000;

    Queue<Long> queue = new LinkedList<>();

    public Map<Integer, Queue<Long>> counterPerCustomer = new HashMap<>();

    private static void cleanupQueue(Queue<Long> queue) {
        if(queue != null && !queue.isEmpty()) {
            long expiryTime = System.currentTimeMillis() - RATE_LIMIT_PERIOD_MS;

            Long element = null;
            do {
                if(element != null) {
                    queue.remove();
                }
                element = queue.peek();
            } while(element != null && element < expiryTime);
        }
    }

    public boolean rateLimit(int customerId) {        
        long requestTime = System.currentTimeMillis();
        Queue<Long> customerRequestQueue = counterPerCustomer.get(customerId);
        cleanupQueue(customerRequestQueue);
        if(customerRequestQueue == null) {
            customerRequestQueue = new LinkedList<>();
            counterPerCustomer.put(customerId, customerRequestQueue);
        }
        if(customerRequestQueue.size() < RATE_LIMIT_PER_MINUTE) {
            customerRequestQueue.add(requestTime);
            return true;
        }
        return false;
    }
    


    public void cleanup() {
        counterPerCustomer.clear();
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter();

        //PER CUSTOMER
        int customerId = 1;

        //Test all number of requests getting added 
        for(long index = 0; index < RATE_LIMIT_PER_MINUTE; index++) {
            boolean value = rateLimiter.rateLimit(customerId);
            if(!value)
            System.err.println("Failed");
        }
        rateLimiter.cleanup();



        //Test additional requests are getting rejected
        long count = RATE_LIMIT_PER_MINUTE + 1;
        boolean response = true;
        for(long index = 0; index < count; index++) {
            boolean value = rateLimiter.rateLimit(customerId);
            response = response && value;
        }
        if(response) {
            System.err.println("Failed");
        }
         rateLimiter.cleanup();

        // //Test additional requests are getting rejected
        // for(long index = 0; index < RATE_LIMIT_PER_MINUTE; index++) {
        //     boolean value = rateLimiter.rateLimit(customerId);
        //     if(!value)
        //     System.err.println("Failed");
        // }
        //  rateLimiter.cleanup();

    }
}
