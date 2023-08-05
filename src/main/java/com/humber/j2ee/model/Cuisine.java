package com.humber.j2ee.model;

import jakarta.persistence.Embeddable;
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

//this cuisine is part of dish entity so embeddable
@Embeddable
public class Cuisine {
	private String cuisineName;
	private String country;
}
