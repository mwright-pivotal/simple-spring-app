package com.pivotal.demo.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "aidans_list")
public class Toy {
	@Id
	private String toy_name;
	
	private String description;

	public String getToy_name() {
		return toy_name;
	}

	public void setToy_name(String toy_name) {
		this.toy_name = toy_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
