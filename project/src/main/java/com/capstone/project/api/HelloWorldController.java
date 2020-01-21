package com.capstone.project.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/hello")
@RestController
public class HelloWorldController {

    // GET, PUT, POST, DELETE
    @GetMapping
    public String getHelloWorld() {
        return "Hello World";
    }

//    public String getString()
//    {
//        return JSONObject.quote("Hello World");
//    }
}
