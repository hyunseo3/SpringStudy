package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Book {
	private String title;
	private String link;
	public Book(String title, String link) {
		super();
		this.title = title;
		this.link = link;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
