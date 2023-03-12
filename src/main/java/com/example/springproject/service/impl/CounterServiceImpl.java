package com.example.springproject.service.impl;

import com.example.springproject.service.CounterService;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {
    private static int counter = 0;

    @Override
    public synchronized void increment() {
        counter++;
    }

    @Override
    public int getValue() {
        return counter;
    }
}

