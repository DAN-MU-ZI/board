package org.example.springrepositorytemplate.post;

import java.security.Principal;

import org.example.springrepositorytemplate.comment.CreateCommentRequest;
import org.example.springrepositorytemplate.member.Member;
import org.example.springrepositorytemplate.member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
	private final PostService postService;
	private final MemberService memberService;

	@PreAuthorize("isAuthenticated()")
	@GetMapping
	public String createPost(CreatePostRequest request) {
		return "post_submit";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping
	public String createPost(@Valid CreatePostRequest request, BindingResult bindingResult, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "post_submit";
		}
		String username = principal.getName();
		Member member = memberService.findByUserName(username);

		Post post = new Post();
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		post.setMember(member);
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

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/update")
	public String updatePost() {
		return "post_submit";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/update/{id}")
	public String updatePost(@PathVariable("id") Long id, CreatePostRequest request, Principal principal) {
		String username = principal.getName();
		Member member = memberService.findByUserName(username);

		Post post = postService.findById(id);

		checkOwnerDifferent(post, member);
		request.setTitle(post.getTitle());
		request.setContent(post.getContent());

		return "post_submit";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/update/{id}")
	public String updatePost(@PathVariable("id") Long id, CreatePostRequest request, BindingResult bindingResult,
		Principal principal) {

		if (bindingResult.hasErrors()) {
			return "post_submit";
		}

		String username = principal.getName();
		Member member = memberService.findByUserName(username);

		Post post = postService.findById(id);

		checkOwnerDifferent(post, member);

		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		postService.savePost(post);

		return String.format("redirect:/post/%s", id);
	}

	private void checkOwnerDifferent(Post post, Member member) {
		if (!post.getMember().getId().equals(member.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String deletePost(@PathVariable("id") Long id, Principal principal) {
		String username = principal.getName();
		Member member = memberService.findByUserName(username);

		Post post = postService.findById(id);

		checkOwnerDifferent(post, member);

		postService.deletePost(post);

		return "redirect:/";
	}
}
