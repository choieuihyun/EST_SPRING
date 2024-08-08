package com.example.est_spring.weekly_4_quiz.service;

import com.example.est_spring.weekly_4_quiz.dto.MenuDto;
import com.example.est_spring.weekly_4_quiz.entity.Menu;
import com.example.est_spring.weekly_4_quiz.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

//    @Transactional
//    public MenuDto createMenu(MenuDto menuDto) {
//        //Menu menu = menuDto.toEntity();
//        //Menu createdMenu = menuRepository.save(menu);
//        return MenuDto.fromEntity(createdMenu);
//    }

    @Transactional(readOnly = true)
    public List<MenuDto> getAllMenu() {
        return menuRepository.findAll().stream()
                .map(MenuDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<MenuDto> getMenu(Long id) {
        return menuRepository.findById(id)
                .map(MenuDto::fromEntity);
    }

    @Transactional
    public Optional<MenuDto> updateMenu(Long id, MenuDto menuDto) {
        return menuRepository.findById(id)
                .map(existingMenu -> {
                    // 더티 체킹
                    existingMenu.update(
                            menuDto.getName(),
                            menuDto.getPrice(),
                            menuDto.getDescription()
                            );
                    return MenuDto.fromEntity(existingMenu);
                });

    }

    @Transactional
    public boolean deleteMenu(Long id) {
        return menuRepository.findById(id)
                .map(menu -> {
                    menuRepository.delete(menu);
                    return true;
                })
                .orElse(false);
    }


}
