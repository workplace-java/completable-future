package com.cf.completable_future.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class TravelService {

    @Autowired
    private Executor travelExecutor;

    // get flight price
    public CompletableFuture<Double> getFlightPrice(String dest) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(800);
            return 500.00;
        }, travelExecutor);
    }

    // get hotel price
    public CompletableFuture<Double> getHotelPrice(String dest) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return 100.00;
        }, travelExecutor);
    }

    // convert usd to aud
    public CompletableFuture<Double> covertToAud(double usd) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(300);
            return usd * 1.5;
        }, travelExecutor);
    }

    // get weather
    public CompletableFuture<String> getWeather(String dest) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(200);
            if (dest.equalsIgnoreCase("error")) throw new RuntimeException("Weather API down");
            return "Sunny 25°C";
        });
    }


    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
           // do nothing here
        }
    }
}
