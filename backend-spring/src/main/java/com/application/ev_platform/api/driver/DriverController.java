package com.application.ev_platform.api.driver;

import com.application.ev_platform.shared.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {

    @GetMapping("/api/driver/health")
    public ApiResponse<String> health() {
        return ApiResponse.ok("Driver API is running");
    }
}
