package com.hilmatrix.fresh_goodies.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;


@RestController
public class HomeController implements ErrorController {
    @GetMapping("/")
    public String helloWorld() {
        return "<h1>Ini home page</h1>";
    }

    @RequestMapping("/error")
    public String handleError() {
        // You can customize the error response here
        return "<h1>Nyasar yah mas ?</h1>";
    }
}


