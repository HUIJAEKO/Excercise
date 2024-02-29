package com.example.excercise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

	@GetMapping("/post/newPost")
	public String newPost() {
		return "post/newPost";
	}
}
