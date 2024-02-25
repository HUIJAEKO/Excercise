package com.example.excercise.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.excercise.DTO.CustomUserDetails;
import com.example.excercise.DTO.PasswordChangeRequest;
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
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

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
	
	@GetMapping("/user/mypage")
	public String Detail(@AuthenticationPrincipal UserDetails currentUser, Model model) {
		model.addAttribute("username", currentUser.getUsername());
		model.addAttribute("birthdate", ((CustomUserDetails) currentUser).getBirthdate());
		model.addAttribute("phone", ((CustomUserDetails) currentUser).getPhone());
		model.addAttribute("name", ((CustomUserDetails) currentUser).getName());
		model.addAttribute("password", currentUser.getPassword());
		model.addAttribute("gender", ((CustomUserDetails) currentUser).getGender());
		
		return "user/detail";
	}
	
	@DeleteMapping("/user/delete")
	public String DeleteUser(@AuthenticationPrincipal CustomUserDetails user) {
	userService.deleteUser(user.getId());		
		SecurityContextHolder.clearContext();
		return "user/login";
	}
	
	@GetMapping("/user/edit")
	public String editProfil(@AuthenticationPrincipal UserDetails currentUser2, Model model) {
		model.addAttribute("username", currentUser2.getUsername());
		model.addAttribute("birthdate", ((CustomUserDetails) currentUser2).getBirthdate());
		model.addAttribute("phone", ((CustomUserDetails) currentUser2).getPhone());
		model.addAttribute("name", ((CustomUserDetails) currentUser2).getName());
		model.addAttribute("password", currentUser2.getPassword());
		model.addAttribute("gender", ((CustomUserDetails) currentUser2).getGender());
		
		return "user/edit";
	}
	
	
	@GetMapping("/user/editPassword")
	public String editPassword() {
		return "user/editPassword";
	}
	
	public UserController(UserService userService,  BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
	
	
    @PatchMapping("/user/editPassword")
    public ResponseEntity<?> bCryptPasswordEncoder(@RequestBody PasswordChangeRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        CustomUserDetails currentUser = (CustomUserDetails) userDetails;

        // 현재 비밀번호 확인
        if (!bCryptPasswordEncoder.matches(request.getCurrentPw(), currentUser.getPassword())) {
            return ResponseEntity.badRequest().body(Map.of("error", "현재 비밀번호가 일치하지 않습니다."));
        }

        // 새 비밀번호와 새 비밀번호 확인 일치 검사
        if (!request.getNewPw().equals(request.getNewPwConfirm())) {
            return ResponseEntity.badRequest().body(Map.of("error", "새 비밀번호와 비밀번호 확인이 일치하지 않습니다."));
        }else {
        	// 비밀번호 변경 로직 실행
            userService.changePassword(currentUser.getUsername(), request.getNewPw());
        }

        return ResponseEntity.ok().body(Map.of("message", "비밀번호가 성공적으로 변경되었습니다."));
    }
	
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
	 
	 
	 

