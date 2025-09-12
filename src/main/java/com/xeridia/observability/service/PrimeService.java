package com.xeridia.observability.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.logging.Log;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PrimeService {

    private final MeterRegistry registry;

    public PrimeService(MeterRegistry registry) {
        this.registry = registry;
    }

    @CacheResult(cacheName = "getPrimes")
    public List<Long> getPrimes(long max) {
        Log.info("Calculating prime numbers under" + max);
        List<Long> results = new ArrayList<>();
        for(long i = 1; i <= max; i++) {
            if(checkIsPrime(i)) {
                results.add(i);
            }
        }
        return results;
    }

    @CacheResult(cacheName = "isPrime")
    public boolean isPrime(long number) {
        Log.info("Checking whether " + number + " is prime or not");
        registry.counter("request.prime.number").increment();
        boolean isPrime = checkIsPrime(number);
        if(isPrime) {
            registry.counter("result.prime.number", "type", "prime").increment();
        } else {
            registry.counter("result.prime.number", "type", "not-prime").increment();
        }
        return isPrime;
    }

    private boolean checkIsPrime(long number) {
        if (number == 1 || (number > 2 && number % 2 == 0)) {
            registry.counter("result.prime.number", "type", "not-prime").increment();
            return false;
        } else {
            for (int i = 3; i < Math.floor(Math.sqrt(number)) + 1; i = i + 2) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
