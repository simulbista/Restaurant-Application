package com.humber.j2ee.model;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	//embedding cuisine from its own model - cuisine
	@Embedded
	private Cuisine cuisine;
	// many to one relation (multiple dish can have the same category)
	@ManyToOne
	//naming the joining column i.e. the category id
	@JoinColumn(name = "DISH_CATEGORY_ID")
	//since Dish table gets category data from the category table
	//creating a new dish with new category throws an error since that category doesn't exist in the db i.e. it exists in the object only so transient state
	//so we use Cascade - persist which converts this transient state of category to persistent state
	//making the insert of dish with new category successful
	@Cascade(CascadeType.PERSIST)
	private Category category;
	
	private String description;
	@Column(name="DISH_COST")
	private double price;
}
