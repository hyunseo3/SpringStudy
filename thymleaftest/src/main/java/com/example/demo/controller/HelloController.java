package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	@GetMapping("/hello")
	public void hello(Model model) {
		model.addAttribute("title", "쌍용미디어");
	}
}
