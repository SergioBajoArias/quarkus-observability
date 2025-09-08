package com.xeridia.observability.service;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.logging.Log;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PrimeService {


    public List<Long> getPrimes(long max) {
        Log.info("Calculating prime numbers under" + max);
        List<Long> results = new ArrayList<>();
        for(long i = 1; i <= max; i++) {
            if(isPrime(i)) {
                results.add(i);
            }
        }
        return results;
    }

    public boolean isPrime(long number) {
        Log.info("Checking whether " + number + " is prime or not");
        if (number == 1 || (number > 2 && number % 2 == 0)) {
            return false;
        } else {
            return testPrimeNumber(number);
        }
    }

    protected boolean testPrimeNumber(long number) {
        for (int i = 3; i < Math.floor(Math.sqrt(number)) + 1; i = i + 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
