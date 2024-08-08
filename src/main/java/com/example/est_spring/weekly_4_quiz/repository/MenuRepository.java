package com.example.est_spring.weekly_4_quiz.repository;

import com.example.est_spring.weekly_4_quiz.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findById(Long id);

    List<Menu> findByStoreId(Long storeId);

}
