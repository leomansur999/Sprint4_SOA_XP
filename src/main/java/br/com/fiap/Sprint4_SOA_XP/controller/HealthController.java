package br.com.fiap.Sprint4_SOA_XP.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
public class HealthController {
    @GetMapping
    public String health() { return "API OK"; }
}
