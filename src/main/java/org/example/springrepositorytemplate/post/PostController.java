package org.example.springrepositorytemplate.post;

import org.example.springrepositorytemplate.comment.CreateCommentRequest;
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
	public String createPost(CreatePostRequest request) {
		return "post_submit";
	}

	@PostMapping
	public String createPost(@Valid CreatePostRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "post_submit";
		}

		Post post = new Post();
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		postService.savePost(post);

		return "redirect:/";
	}

	@GetMapping("{id}")
	public String getPostDetail(@PathVariable("id") Long id,
		Model model,
		CreateCommentRequest request) {

		Post post = postService.findById(id);
		model.addAttribute("post", post);
		return "post_detail";
	}

	@GetMapping("/update")
	public String updatePost() {
		return "post_submit";
	}

	@GetMapping("/update/{id}")
	public String updatePost(@PathVariable("id") Long id, CreatePostRequest request) {
		Post post = postService.findById(id);

		request.setTitle(post.getTitle());
		request.setContent(post.getContent());

		return "post_submit";
	}

	@PostMapping("/update/{id}")
	public String updatePost(@PathVariable("id") Long id, CreatePostRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "post_submit";
		}

		Post post = postService.findById(id);
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		postService.savePost(post);

		return String.format("redirect:/post/%s", id);
	}

	@GetMapping("/delete/{id}")
	public String deletePost(@PathVariable("id") Long id) {
		Post post = postService.findById(id);
		postService.deletePost(post);

		return "redirect:/";
	}
}
