package com.example.est_spring.day0715to0716;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/index")
    public String index(Model model) {
        User max = new User("max", "max@gmail.com", true);

        model.addAttribute("user",max);
        return "index";
    }

    @PostMapping("/users")
    public String users(@ModelAttribute User user) {

        System.out.println("유저의 이름은" + user.getUsername() + "입니다.");
        System.out.println("유저의 이메일은" + user.getEmail() + "입니다.");

        return "index2";
    }


}