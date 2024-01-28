package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDAO dao;
	
	@GetMapping("/")
	public String index() {
		return "index.jsp";
	}

	@RequestMapping("/listMember")
	public void list(Model model) {
		model.addAttribute("list", dao.findAll());
	}
	
	@GetMapping("/insert")
	public void insertForm() {
		
	}

	@PostMapping("/insert")
	public ModelAndView insertSend(MemberVO m) {
		ModelAndView mav=new ModelAndView("redirect:/listMember");
		dao.insert(m);
		return mav;
	}
}
