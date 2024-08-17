package org.example.springrepositorytemplate.comment;

import org.example.springrepositorytemplate.post.Post;
import org.example.springrepositorytemplate.post.PostService;
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
@RequestMapping("/comment")
public class CommentController {
	private final PostService postService;
	private final CommentService commentService;

	@PostMapping("/{postId}")
	public String createComment(@PathVariable("postId") Long postId, Model model, @Valid CreateCommentRequest request,
		BindingResult bindingResult) {

		Post post = postService.findById(postId);

		if (bindingResult.hasErrors()) {
			model.addAttribute("post", post);
			return "post_detail";
		}

		Comment comment = new Comment();
		comment.setContent(request.getContent());
		comment.setPost(post);
		commentService.save(comment);

		return String.format("redirect:/post/%s", postId);
	}

	@GetMapping("/update/{id}")
	public String updateComment(@PathVariable("id") Long id, CreateCommentRequest request) {
		Comment comment = commentService.findById(id);
		request.setContent(comment.getContent());

		return "comment_submit";
	}

	@PostMapping("/update/{id}")
	public String updateComment(@PathVariable("id") Long id, CreateCommentRequest request,
		BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "comment_submit";
		}
		Comment comment = commentService.findById(id);
		comment.setContent(request.getContent());
		commentService.save(comment);

		return String.format("redirect:/post/%s", comment.getPost().getId());
	}

	@GetMapping("/delete/{id}")
	public String deleteComment(@PathVariable("id") Long id) {
		Comment comment = commentService.findById(id);
		commentService.delete(comment);

		return String.format("redirect:/post/%s", comment.getPost().getId());
	}
}
