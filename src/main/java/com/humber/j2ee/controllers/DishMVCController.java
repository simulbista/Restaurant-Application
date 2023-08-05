package com.humber.j2ee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.humber.j2ee.services.DishService;

//difference between @RestController and @Controller - 
//when you use @RestController, it returns json data
//but when you use @Controller, it redirects to (say) index.html inside resources/templates
@Controller
//@RequestMapping("/restaurant")
public class DishMVCController {

//		get restaurant name from application.properties file
	@Value("${restaurant.name}")
	public String name;

	@Autowired
	private DishService dishService;

//		redirect to login on the landing page url
	@GetMapping("/")
	public String login() {
		return "redirect:/login";
	}

	@GetMapping("/restaurant/")
//		model is passing values to the html file where it is read by thymeleaf
	public String home(Model model) {
		model.addAttribute("restaurantName", name);
		return "home";
	}

	@GetMapping("/restaurant/menu")
//		model is passing values to the html file where it is read by thymeleaf
	// the request param might be there (if it is coming from adding a dish by the
	// admin) - so requried=false
	// as it might not be there if it is coming from somewhere else
	public String menu(Model model, @RequestParam(required = false) String success) {
		model.addAttribute("restaurantName", name);
		model.addAttribute("success", success);
		model.addAttribute("dishes", dishService.getDishes());
		return "menu";
	}

}
