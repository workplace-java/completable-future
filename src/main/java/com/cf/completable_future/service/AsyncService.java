package com.cf.completable_future.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class AsyncService {

    @Autowired
    private Executor taskExecutor;

    public CompletableFuture<String> performTask(String taskName, int sleepMs) {
        return CompletableFuture.supplyAsync(() -> {
           try {
               Thread.sleep(sleepMs);
               return String.format("%s completed by %s", taskName, Thread.currentThread());
           } catch (InterruptedException ex) {
               throw new IllegalStateException(ex);
           }
        }, taskExecutor);
    }
}
