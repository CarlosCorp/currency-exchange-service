package com.carloscorp.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@Slf4j
@RestController
public class SampleApiController {

    private final RestClient restClient;

    public SampleApiController() {
        this.restClient = RestClient.builder().build();
    }

    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "fallbackErrorMethod")
    public ResponseEntity<String> getSampleApi(){
        log.info("Calling the dummy api");

        //esta llamada fallara es solo para probar el retry
        String body = restClient.get()
                .uri("http://localhost:8080/non-existent-api")
                .retrieve()
                .body(String.class);

        return ResponseEntity.ok("Sample Api");
    }

    @GetMapping("/circuit-sample-api")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackErrorMethodCircuit")
    public ResponseEntity<String> getCircuitSampleApi(){
        log.info("Calling the dummy api");

        //esta llamada fallara es solo para probar el retry
        String body = restClient.get()
                .uri("http://localhost:8080/non-existent-api")
                .retrieve()
                .body(String.class);

        return ResponseEntity.ok("Circuit Sample Api");
    }

    @GetMapping("/rate-sample-api")
    @RateLimiter(name = "rate-sample-api", fallbackMethod = "fallbackErrorMethodRate")
    public ResponseEntity<String> getRateSampleApi(){
        log.info("Calling the dummy api");

        return ResponseEntity.ok("Rate Sample Api");
    }

    @GetMapping("/bulk-sample-api")
    @Bulkhead(name = "bulk-sample-api", fallbackMethod = "fallbackErrorMethodBulk")
    public ResponseEntity<String> getBulkSampleApi(){
        log.info("Calling the dummy api");

        return ResponseEntity.ok("Bulk Sample Api");
    }

    public ResponseEntity<String> fallbackErrorMethod(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
    }

    public ResponseEntity<String> fallbackErrorMethodCircuit(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
    }

    public ResponseEntity<String> fallbackErrorMethodRate(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<String> fallbackErrorMethodBulk(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }
}
