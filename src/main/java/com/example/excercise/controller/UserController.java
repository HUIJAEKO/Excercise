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
import com.example.excercise.DTO.UserDTO;
import com.example.excercise.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/user/agree")
	public String Agree() {
		return "user/agree";
	}
	
	@GetMapping("/user/signup")
	public String SignUpForm(Model model) {
	    model.addAttribute("userDTO", new UserDTO()); 
	    return "user/signup";
	}
	
	@PostMapping("/user/signup")
	public String MakeSignUp(@ModelAttribute UserDTO userDTO) {
	    userService.save(userDTO);
	    return "user/login";
	}
	
//	@GetMapping("/login")
//	public String SignUpComplete() {
//		return "login";
//	}
	
	@GetMapping("/user/main")
	public String LoginSuccess(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if(principal instanceof CustomUserDetails) {
			CustomUserDetails userDetails = (CustomUserDetails) principal;
			model.addAttribute("username", userDetails.getName());
		}
		return "user/main";
	}
	
	@PostMapping("/user/usernameCheck")
	public @ResponseBody String idCheck(@RequestParam(name = "username") String username) {
		String checkResult = userService.idcheck(username);
		return checkResult;
	}
	
	
	@GetMapping("/user/login")
	public String login(@RequestParam(value = "error", required = false) Boolean error,
									HttpSession session, Model model) {
		if(Boolean.TRUE.equals(error)) {
			String errorCode = (String) session.getAttribute("loginMessage");
			String errorMessage = switch (errorCode) {
				case "badCredentials" -> "아이디 또는 비밀번호가 일치하지 않습니다";
				default -> "알 수 없는 오류가 발생했습니다. 관리자에게 문의해주세요";
			};
			model.addAttribute("loginMessage", errorMessage);
		}
		
		return "user/login";
	}
	
	
//	 @PostMapping("/login")
//	    public String login(@ModelAttribute UserDTO userDTO,HttpSession session, Model model) {
//	        UserDTO loginResult = userService.login(userDTO);  
//	        if (loginResult != null) {
//	            session.setAttribute("loginInformation", loginResult);
//	            model.addAttribute("loginName", loginResult.getName());
//	            return "main";
//	        } else {
//	        	model.addAttribute("loginError", "아이디 또는 비밀번호가 일치하지 않습니다.");
//	            return "login";
//	            
//	        }
//	    }

	
	
//	 @GetMapping("/logout")
//	 public String logout(HttpSession session) {
//		 session.invalidate();
//		 return "login";
//	 }
	 
	 
	 
}
