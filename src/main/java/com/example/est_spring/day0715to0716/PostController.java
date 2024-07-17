package com.example.est_spring.day0715to0716;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private List<Post> posts = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", posts);
        return "post/list";
    }

    @GetMapping("/new")
    public String newPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/form";
    }

    @PostMapping
    public String savePost(@ModelAttribute Post post) {
        post.setId(nextId++); //id를 증가시키는 이유? pk
        post.setCreateAt(LocalDateTime.now()); // 생성시 시간
        posts.add(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Post post = posts.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());

        model.addAttribute("post", post);
        return "post/detail";

    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id")Long id) {
        posts.removeIf(post -> post.getId() == id);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Post post = posts.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());

        model.addAttribute("post", post);
        return "post/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, @ModelAttribute Post updatePost) {
        Post post = posts.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
        post.setTitle(updatePost.getTitle()); // set 메서드 업데이트 객체의 title 값으로 변경
        post.setContent(updatePost.getContent()); // set 메서드로 업데이트 객체의 content 값으로 변경
        return "redirect:/posts/{id}";
    }


}
