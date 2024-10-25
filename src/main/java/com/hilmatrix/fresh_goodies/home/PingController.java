package com.hilmatrix.fresh_goodies.home;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/ping")
public class PingController {

    @GetMapping
    public String getPing() throws IOException {
        return "<h1 style=\"color:red\"> This is Ping-uin </h1>";
    }
}
