package com.humber.j2ee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humber.j2ee.model.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
}
