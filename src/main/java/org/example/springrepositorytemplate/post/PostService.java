package org.example.springrepositorytemplate.post;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

	@Transactional(readOnly = true)
	public List<Post> getPosts() {
		return postRepository.findAll();
	}

	public void savePost(Post post) {
		postRepository.save(post);
	}

	@Transactional(readOnly = true)
	public Post findById(Long id) {
		return postRepository.findById(id)
			.orElseThrow(EntityNotFoundException::new);
	}

	public void deletePost(Post post) {
		postRepository.delete(post);
	}
}
