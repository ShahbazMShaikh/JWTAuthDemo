package jwtDemoApp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @RequestMapping("/welcome")
    public String welcome() {
        return "this is a private page";
    }
}
