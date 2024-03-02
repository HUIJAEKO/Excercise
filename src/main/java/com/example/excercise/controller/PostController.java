package com.example.excercise.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.excercise.DTO.CustomUserDetails;
import com.example.excercise.DTO.PostDTO;
import com.example.excercise.service.PostService;


@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping("/post/newPost")
	public String newPost() {
		return "post/newPost";
	}
	
	@PostMapping("/post/newPost")
	public String postSave(@ModelAttribute PostDTO postDTO, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//authentication을 통해 정보 가져오기
		Object principal = authentication.getPrincipal();
		if(principal instanceof CustomUserDetails) {
			CustomUserDetails userDetails = (CustomUserDetails) principal;
			model.addAttribute("username", userDetails.getName());
			postService.save(postDTO, userDetails.getId());		
		}
		return "redirect:/user/main";
	}	
}
