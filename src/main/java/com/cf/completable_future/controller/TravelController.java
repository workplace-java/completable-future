package com.cf.completable_future.controller;

import com.cf.completable_future.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/travel")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @GetMapping("/quote/{destination}")
    public CompletableFuture<Map<String, Object>> getFullQuote(@PathVariable String destination) {
        long start = System.currentTimeMillis();

        // trigger the processes parallelly
        var flightFuture = travelService.getFlightPrice(destination);
        var hotelFuture = travelService.getHotelPrice(destination);
        var weatherFuture = travelService.getWeather(destination).exceptionally(ex -> "Weather unavailable");

        // combile the results
        return flightFuture.thenCombine(hotelFuture, Double::sum)
            .thenCompose(totalUsd -> travelService.covertToAud(totalUsd))
            .thenCombine(weatherFuture, (totalAud, weather) -> Map.of(
                "destination", destination,
                "totalPriceAUD", totalAud,
                "weather", weather,
                "executionTime", (System.currentTimeMillis() - start) + "ms",
                "thread", Thread.currentThread().toString()
            ));

    }
}
