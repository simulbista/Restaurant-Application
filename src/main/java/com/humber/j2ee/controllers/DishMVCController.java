package com.humber.j2ee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.humber.j2ee.services.DishService;

//difference between @RestController and @Controller - 
//when you use @RestController, it returns json data
//but when you use @Controller, it redirects to (say) index.html inside resources/templates
@Controller
@RequestMapping("/restaurant")
public class DishMVCController {
	
//		get restaurant name from application.properties file
		@Value("${restaurant.name}")
		public String name;
		
		@Autowired
		private DishService dishService;
		
		@GetMapping("/")
//		model is passing values to the html file where it is read by thymeleaf
		public String home(Model model) {
			model.addAttribute("restaurantName",name);
			return "home";
		}
		
		@GetMapping("/menu")
//		model is passing values to the html file where it is read by thymeleaf
		public String menu(Model model) {
			model.addAttribute("restaurantName",name);
			model.addAttribute("dishes",dishService.getDishes());
			return "menu";
		}
		
}
