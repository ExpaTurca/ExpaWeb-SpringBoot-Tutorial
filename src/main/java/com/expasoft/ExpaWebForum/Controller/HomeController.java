package com.expasoft.ExpaWebForum.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v3")
public class HomeController {
    @GetMapping({"", "{welcome}"})
    public String index(){
        return "Hoşgeldiniz!";
    }
}
