package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;

@Controller
public class KaKaoLoginController {
	@GetMapping("/KaKaoLoginOK/{name}/{email}")
	public String KaKaoLoginOK(@PathVariable String name, @PathVariable String email, HttpSession session) {
		session.setAttribute("name", name);
		session.setAttribute("email", email);
		return "loginOK";
	}
}
