package com.application.ev_platform.api.provider;

import com.application.ev_platform.shared.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @GetMapping("/api/provider/health")
    public ApiResponse<String> health() {
        return ApiResponse.ok("Provider API is running");
    }
}
