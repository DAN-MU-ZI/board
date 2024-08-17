package org.example.springrepositorytemplate.main;

import java.util.List;

import org.example.springrepositorytemplate.post.Post;
import org.example.springrepositorytemplate.post.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final PostService postService;

	@GetMapping("/")
	public String redirect(Model model) {
		List<Post> posts = postService.getPosts();
		model.addAttribute("posts", posts);
		return "/home";
	}

	@GetMapping("/home")
	public String home() {
		return "redirect:/home";
	}
}
