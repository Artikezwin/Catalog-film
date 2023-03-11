package com.example.springproject.controller;

import com.example.springproject.service.impl.CounterServiceImpl;
import com.example.springproject.service.impl.ThreadForService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/counter")
public class CounterController {
    @Autowired
    private CounterServiceImpl counterService;
    private static final int amountThreads = 3;

    @GetMapping("/multithreaded")
    public String counter() {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < amountThreads; i++) {
            threads.add(new ThreadForService(counterService));
            threads.get(i).start();
        }

        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return Integer.toString(counterService.getValue());
    }
}
