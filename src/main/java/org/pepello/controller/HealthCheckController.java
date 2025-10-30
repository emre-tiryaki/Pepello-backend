package org.pepello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    sadece api'ın çalışıp çalışmadığına bakmak için.
 */
@RestController
public class HealthCheckController {
    @GetMapping("/health-check")
    public boolean healthCheck() {
        return true;
    }
}
