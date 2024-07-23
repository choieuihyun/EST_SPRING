package com.example.est_spring.weekly_4_quiz.repository;

import com.example.est_spring.weekly_4_quiz.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
