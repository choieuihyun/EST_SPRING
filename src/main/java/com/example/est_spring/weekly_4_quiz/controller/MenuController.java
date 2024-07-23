package com.example.est_spring.weekly_4_quiz.controller;

import com.example.est_spring.weekly_4_quiz.dto.MenuDto;
import com.example.est_spring.weekly_4_quiz.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    // 생성자
    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<MenuDto>> getMenuList() {
        List<MenuDto> menu = menuService.getAllMenu();
        return ResponseEntity.ok(menu);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getMenuById(@PathVariable Long id) {
        return menuService.getMenuById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto) {
        MenuDto createMenu = menuService.createMenu(menuDto);
        return new ResponseEntity<>(createMenu, HttpStatus.CREATED);
    }

    // name으로 찍어주는게 맞지않나?
    @PutMapping("/{id}")
    public ResponseEntity<MenuDto> updateMenu(@PathVariable Long id, @RequestBody MenuDto menuDto) {
        return menuService.updateMenu(id, menuDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        boolean deletedMenu = menuService.deleteMenu(id);
        return deletedMenu ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


}
