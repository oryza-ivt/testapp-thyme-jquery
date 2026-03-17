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
}