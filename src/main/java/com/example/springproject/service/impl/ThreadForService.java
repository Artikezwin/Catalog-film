package com.example.springproject.service.impl;

public class ThreadForService extends Thread {

    CounterServiceImpl counterService;

    public ThreadForService(CounterServiceImpl counterService) {
        this.counterService = counterService;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            counterService.increment();
        }
    }
}
