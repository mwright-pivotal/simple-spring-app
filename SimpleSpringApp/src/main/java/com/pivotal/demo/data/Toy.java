package com.pivotal.demo.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aidans_list")
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
