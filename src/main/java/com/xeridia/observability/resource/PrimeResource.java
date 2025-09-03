package com.xeridia.observability.resource;

import com.xeridia.observability.service.PrimeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/primes")
@RequestScoped
public class PrimeResource {

    private PrimeService primeService;

    public PrimeResource(PrimeService primeService) {
        this.primeService = primeService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{max}")
    public List<Long> getPrimes(@PathParam("max") long max) {
        return primeService.getPrimes(max);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/isPrime/{number}")
    public boolean isPrime(@PathParam("number") long number) {
        return primeService.isPrime(number);
    }

}
