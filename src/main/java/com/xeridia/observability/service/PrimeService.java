package com.xeridia.observability.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PrimeService {

    public List<Long> getPrimes(long max) {
        List<Long> results = new ArrayList<>();
        for(long i = 1; i <= max; i++) {
            if(testPrimeNumber(i)) {
                results.add(i);
            }
        }
        return results;
    }

    public boolean isPrime(long number) {
        if (number == 1 || number % 2 == 0) {
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
