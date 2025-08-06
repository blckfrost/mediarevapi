package com.frost.mediarevapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {
    @GetMapping("/api/health")
    public Map health() {
        return Map.of(
                "status", true,
                "message", "API  is up and running..."
        );
    }
}
