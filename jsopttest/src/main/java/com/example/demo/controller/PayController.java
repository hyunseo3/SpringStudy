package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {

//	<form action="/payok" method="post" id="pay">
//	<input type="hidden" name="imp_uid" id="imp_uid" />
//	<input type="hidden" name="merchant_uid" id="merchant_uid" />
//	<input type="hidden" name="paid_amount" id="paid_amount" />
//	<input type="hidden" name="apply_num" id="apply_num" />
//</form>
	
	@PostMapping("/payok")
	public String payok(String imp_uid, String merchant_uid, int paid_amount, String apply_num) {
		
		return "ok";
	} 
}
