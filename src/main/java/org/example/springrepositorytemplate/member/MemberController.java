package org.example.springrepositorytemplate.member;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	private final MemberService memberService;

	@GetMapping("/register")
	public String createMember(CreateMemberRequest request) {
		return "register";
	}

	@PostMapping("/register")
	public String createMember(@Valid CreateMemberRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
		if (!request.getPassword1().equals(request.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");
			return "register";
		}

		try {
			memberService.create(request.getUsername(), request.getPassword1(), request.getEmail());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("registerFailed", "이미 등록된 사용자입니다.");
			return "register";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("registerFailed", "알 수 없는 이유로 실패했습니다.");
			return "register";
		}

		return "redirect:/";
	}

	@GetMapping("/login")
	public String login(LoginRequest request) {
		return "login";
	}
}
