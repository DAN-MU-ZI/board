package org.example.springrepositorytemplate.main;

import org.example.springrepositorytemplate.SearchCategory;
import org.example.springrepositorytemplate.post.Post;
import org.example.springrepositorytemplate.post.PostService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final PostService postService;

	@GetMapping("/")
	public String redirect(
		Model model,
		@RequestParam(value = "page", defaultValue = "0") int page,
		@RequestParam(value = "kw", defaultValue = "") String kw,
		@RequestParam(value = "category", required = false) SearchCategory category
	) {
		Page<Post> paging = postService.getPosts(page, kw, category);
		model.addAttribute("paging", paging);
		model.addAttribute("kw", kw);
		model.addAttribute("category", category);
		return "/home";
	}

	@GetMapping("/home")
	public String home() {
		return "redirect:/home";
	}
}
