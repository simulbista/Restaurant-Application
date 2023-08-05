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
@Entity
public class Category {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String color;
}
