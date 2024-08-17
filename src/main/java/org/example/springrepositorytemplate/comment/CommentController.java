package org.example.springrepositorytemplate.comment;

import org.example.springrepositorytemplate.post.Post;
import org.example.springrepositorytemplate.post.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
}
