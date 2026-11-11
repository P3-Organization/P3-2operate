package dk.aau.sw3.p32operate.presentation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {



    @GetMapping("/")
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/2operate")
    public String message() {
        return "Mikkel's YNDLINGS firma er 2operate";
    }
}
