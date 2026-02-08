package com.cf.completable_future.controller;

import com.cf.completable_future.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/run-parallel")
    public Map<String, Object> runTasks() {
        long start = System.currentTimeMillis();

        var future1 = asyncService.performTask("Task-A", 1000);
        var future2 = asyncService.performTask("Task-B", 1000);
        var future3 = asyncService.performTask("Task-C", 1000);

        CompletableFuture.allOf(future1, future2, future3).join();

        return Map.of(
            "results : ", List.of(future1.join(), future2.join(), future3.join()),
            "totalTime", (System.currentTimeMillis() - start) + "ms"
        );
    }
}
