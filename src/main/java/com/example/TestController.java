package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class TestController {

    @GetMapping("/")
    public String index(Model model) {
        // This simulates a dynamic variable injected by the server
        model.addAttribute("backendToken", "SECURE_XYZ_123");
        return "index";
    }

    @PostMapping("/api/test")
    @ResponseBody
    public Map<String, String> handleAjax(@RequestBody Map<String, String> payload) {
        Map<String, String> response = new HashMap<>();
        response.put("result", "Received: " + payload.get("data"));
        response.put("tokenUsed", payload.get("token"));
        return response;
    }

    @GetMapping("/api/test-get")
    @ResponseBody
    public Map<String, String> handleGet(@RequestParam(name = "id", defaultValue = "0") String id) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Data retrieved for ID: " + id);
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return response;
    }

    @GetMapping("/api/verifs")
    @ResponseBody
    public Map<String, Object> handleDatatables(
            @RequestParam Map<String, String> allParams) {
        // Log headers to see what Codesealer is doing
        System.out.println("Request Params: " + allParams);

        String drawSentByFrontend = allParams.getOrDefault("draw", "1");
        int draw = Integer.parseInt(drawSentByFrontend);

        List<Map<String, String>> mockData = new ArrayList<>();
        mockData.add(Map.of("id", "1", "nama", "Monkey D. Luffy", "status", "Active"));
        mockData.add(Map.of("id", "2", "nama", "Nami", "status", "Active"));
        mockData.add(Map.of("id", "3", "nama", "Roronoa Zoro", "status", "Active"));
        mockData.add(Map.of("id", "4", "nama", "Sanji", "status", "Active"));
        mockData.add(Map.of("id", "5", "nama", "Usopp", "status", "Active"));
        mockData.add(Map.of("id", "6", "nama", "Tony Tony Chopper", "status", "Pending"));

        Map<String, Object> response = new HashMap<>();
        response.put("draw", draw);
        response.put("recordsTotal", 6);
        response.put("recordsFiltered", 6);
        response.put("data", mockData);

        return response;
    }
}