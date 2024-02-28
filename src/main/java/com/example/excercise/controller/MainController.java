package com.example.excercise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	//localhost:8081접속 시 이동
	@GetMapping("/")
	public String Main() {
		return "user/login";
	}
	
}
