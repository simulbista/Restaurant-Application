package com.humber.j2ee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;import com.humber.j2ee.model.Category;
import com.humber.j2ee.model.Cuisine;
import com.humber.j2ee.model.Dish;
import com.humber.j2ee.repository.CategoryRepository;
import com.humber.j2ee.repository.DishRepository;

@SpringBootApplication
public class Week12SpringSecurity  implements CommandLineRunner{

	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private DishRepository dishRepository;
	
	public static void main(String[] args){
		SpringApplication.run(Week12SpringSecurity.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		inserting categories on app start
		categoryRepository.save(Category.builder()
				.name("Vegan")
				.color("pink")
				.build());
		
		categoryRepository.save(Category.builder()
				.name("Dairy")
				.color("blue")
				.build());
		
//		categoryRepository.save(Category.builder()
//				.name("Nonveg")
//				.color("green")
//				.build());
		
//		inserting dishes on app start
		
		dishRepository.save(Dish.builder()
				.name("Poutine")
				.cuisine(Cuisine.builder().cuisineName("Canadian").country("Canada").build())
				.description("Fries with gravy")
				.category(Category.builder().name("Veg").color("green").build())
				.price(21.0)
				.build());
		dishRepository.save(Dish.builder()
				.name("Biryani")
				.cuisine(Cuisine.builder().cuisineName("South Indian").country("India").build())
				.description("Rice with meat")
				.category(Category.builder().name("Nonveg").color("purple").build())
				.price(17.0)
				.build());
		
	}

}
