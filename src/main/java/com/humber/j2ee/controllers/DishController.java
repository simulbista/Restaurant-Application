package com.humber.j2ee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humber.j2ee.model.Dish;
import com.humber.j2ee.services.DishService;

@RestController
@RequestMapping("/api")
public class DishController {
	
	@Autowired
	private DishService dishService;
	
	@GetMapping("/dish")
	public List<Dish> getDishes(){
		return this.dishService.getDishes();
	}
}
