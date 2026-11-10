package dk.aau.sw3.p32operate.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BatteryController {

    @GetMapping("/battery")
    public String index() { return "battery"; }
}
