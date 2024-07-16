package com.example.est_spring.day0716;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public List<User> getAllUsers() {
        // 전체 사용자 목록 조회
        return null;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return null;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {}

}
