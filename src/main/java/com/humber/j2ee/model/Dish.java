package com.humber.j2ee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
//builder design pattern
@Builder
// Entity means that Dish will have a table associated with it in the db
@Entity
public class Dish {
	
//	primary key for the table Dish
	@Id
//	to make the id  auto generated
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String cuisine;
	private String description;
	private String category;
}
