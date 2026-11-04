package dk.aau.sw3.p32operate.presentation.controllers;

import dk.aau.sw3.p32operate.business.models.MyModel;
import dk.aau.sw3.p32operate.business.services.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MyController {
    private final MyService _myService;

    public MyController(MyService myService) {
        _myService = myService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/2operate")
    public String message() {
        return "Mikkel's YNDLINGS firma er 2operate";
    }

    @GetMapping("/GetUsername")
    public String username(@RequestParam(defaultValue = "2") int n ) {
        int id = (int)n;
        MyModel user = _myService.getUserData(id);
        return user.name;
    }
}
