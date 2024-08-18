package org.example.springrepositorytemplate.comment;

import java.security.Principal;

import org.example.springrepositorytemplate.member.Member;
import org.example.springrepositorytemplate.member.MemberService;
import org.example.springrepositorytemplate.post.Post;
import org.example.springrepositorytemplate.post.PostService;
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
@RequestMapping("/comment")
public class CommentController {
	private final PostService postService;
	private final CommentService commentService;
	private final MemberService memberService;

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/{postId}")
	public String createComment(@PathVariable("postId") Long postId, Model model, @Valid CreateCommentRequest request,
		BindingResult bindingResult, Principal principal) {

		Post post = postService.findById(postId);

		if (bindingResult.hasErrors()) {
			model.addAttribute("post", post);
			return "post_detail";
		}

		String username = principal.getName();
		Member member = memberService.findByUserName(username);

		Comment comment = new Comment();
		comment.setContent(request.getContent());
		comment.setPost(post);
		comment.setMember(member);
		commentService.save(comment);

		return String.format("redirect:/post/%s", postId);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/update/{id}")
	public String updateComment(@PathVariable("id") Long id, CreateCommentRequest request, Principal principal) {
		String username = principal.getName();
		Member member = memberService.findByUserName(username);

		Comment comment = commentService.findById(id);

		checkOwnerDifferent(comment, member);
		request.setContent(comment.getContent());

		return "comment_submit";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/update/{id}")
	public String updateComment(@PathVariable("id") Long id, CreateCommentRequest request,
		BindingResult bindingResult, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "comment_submit";
		}

		String username = principal.getName();
		Member member = memberService.findByUserName(username);

		Comment comment = commentService.findById(id);

		checkOwnerDifferent(comment, member);

		comment.setContent(request.getContent());
		comment.setMember(member);
		commentService.save(comment);

		return String.format("redirect:/post/%s", comment.getPost().getId());
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String deleteComment(@PathVariable("id") Long id, Principal principal) {

		String username = principal.getName();
		Member member = memberService.findByUserName(username);

		Comment comment = commentService.findById(id);

		checkOwnerDifferent(comment, member);

		commentService.delete(comment);

		return String.format("redirect:/post/%s", comment.getPost().getId());
	}

	private void checkOwnerDifferent(Comment comment, Member member) {
		if (!comment.getMember().getId().equals(member.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
	}
}
