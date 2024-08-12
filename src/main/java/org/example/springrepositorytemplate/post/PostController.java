package org.example.springrepositorytemplate.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
	private final PostService postService;

	@GetMapping
	public String createPost() {
		return "post_new";
	}

	@PostMapping
	public String createPost(@Valid CreatePostRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "post_new";
		}
		postService.savePost(request);
		return "redirect:/";
	}

	@GetMapping("{id}")
	public String getPostDetail(@PathVariable("id") Long id,
		Model model) {
		Post post = postService.findById(id);
		model.addAttribute("post", post);
		return "post_detail";
	}
}
