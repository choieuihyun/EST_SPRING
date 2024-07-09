package com.example.est_spring.day0709;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("name", "euihyun");
        model.addAttribute("age", "27");
        model.addAttribute("gender", "man");
        model.addAttribute("language", "java&kotlin");
        model.addAttribute("money", "0");
        return "index";
    }

}
