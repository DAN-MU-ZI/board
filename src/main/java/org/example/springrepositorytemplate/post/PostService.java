package org.example.springrepositorytemplate.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@Transactional(readOnly = true)
	public Page<Post> getPosts(int page, String kw) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("id"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return postRepository.findAllByKeyword(kw, pageable);
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
