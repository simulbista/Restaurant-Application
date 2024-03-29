package com.humber.j2ee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.humber.j2ee.model.Category;
import com.humber.j2ee.model.Dish;
import com.humber.j2ee.services.CategoryService;
import com.humber.j2ee.services.DishService;

@Controller
@RequestMapping(value = "/restaurant/admin")
public class AdminController {

//	get restaurant name from application.properties file
	@Value("${restaurant.name}")
	public String name;

	@Autowired
	private DishService dishService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public String admin() {
		return "admin/admin";
	}

	@GetMapping("/add")
	public String addPage(Model model) {
		model.addAttribute("restaurantName", name);
		model.addAttribute("item", new Dish());
		model.addAttribute("categories", categoryService.getAllCategories());
		return "admin/add-item";
	}

	@PostMapping("/add")
	public String addItem(Model model, @ModelAttribute Dish dish) {
//		since category.id is being stored in the Dish object from the select option in add-item.html view
		// so we get this id using dish.getCategory().getId()
		// then we pass this id to the service method to retrive the entire category
		// which then goes into the dish
		Category category = categoryService.getCategoryById(dish.getCategory().getId()).orElse(null);
		dish.setCategory(category);
		// add dish to db
		dishService.addDish(dish);
//		using redirect api instead of return "menu" page as need to change the url (from /restaurant/add-item to /restaurant/menu
//		plus need to do some operations in the get api in the restaurant controller before displaying the menu page
		return "redirect:/restaurant/menu?success=Item Added Successfully";
	}

	@GetMapping("/delete/{id}")
	public String deleteItem(Model model, @PathVariable int id) {
		System.out.println("im here!");
		dishService.deleteDish(id);
		return "redirect:/restaurant/menu?success=Item Deleted Successfully";
	}
}
