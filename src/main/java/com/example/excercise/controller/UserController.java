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

	@GetMapping("/agree")
	public String Agree() {
		return "agree";
	}
	
	@GetMapping("/signup")
	public String SignUpForm(Model model) {
	    model.addAttribute("userDTO", new UserDTO()); // UserDTO 인스턴스를 생성하여 모델에 추가
	    return "signup";
	}
	
	@PostMapping("/signup")
	public String MakeSignUp(@ModelAttribute UserDTO userDTO) {
	    userService.save(userDTO);
	    return "login";
	}
	
	@GetMapping("/login")
	public String SignUpComplete() {
		return "login";
	}
	
	@GetMapping("/main")
	public String LoginSuccess(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if(principal instanceof CustomUserDetails) {
			CustomUserDetails userDetails = (CustomUserDetails) principal;
		
		model.addAttribute("username", userDetails.getName());
		}
		return "main";
	}
	
	@PostMapping("/user/usernameCheck")
	public @ResponseBody String idCheck(@RequestParam(name = "username") String username) {
		String checkResult = userService.idcheck(username);
		return checkResult;
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
	 
//	 @GetMapping("/mypage")
//	 public String detail(HttpSession session, Model model) {
//		 UserDTO userDTO = (UserDTO)session.getAttribute("loginInformation");
//		 if (userDTO != null) {
//			 model.addAttribute("user", userDTO);
//			 return "detail";
//		 }else {
//			 return "redirect:/login";
//		 }
//	}
	 
//	 @GetMapping("/editProfil")
//	 public String editProfil() {
//		 return "editPrifil";
//	 }
	 
	 
}
