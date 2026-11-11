package dk.aau.sw3.p32operate.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutingController {

    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/battery")
    public String battery() { return "battery"; }

    @GetMapping("/notifications")
    public String notifications() { return "notifications"; }

    @GetMapping("/roomOverview")
    public String roomOverview() { return "roomOverview"; }
}
