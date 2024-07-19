package com.example.demo.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test/list")
    public String list() {
        return "test/list";
    }

    @GetMapping("/test/insertForm")
    public String insertForm() {
        return "test/insert";
    }

}