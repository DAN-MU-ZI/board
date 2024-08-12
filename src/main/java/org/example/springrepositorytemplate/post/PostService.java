package org.example.springrepositorytemplate.post;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

	public List<Post> getPosts() {
		return postRepository.findAll();
	}

	public void savePost(CreatePostRequest request) {
		Post post = new Post();
		post.setTitle(request.title());
		post.setContent(request.content());

		postRepository.save(post);
	}

	public Post findById(Long id) {
		return postRepository.findById(id)
			.orElseThrow(EntityNotFoundException::new);
	}
}
