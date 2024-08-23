package org.example.springrepositorytemplate.post;

import java.util.ArrayList;
import java.util.List;

import org.example.springrepositorytemplate.SearchCategory;
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

	@Transactional(readOnly = true)
	public Page<Post> getPosts(int page, String kw, SearchCategory category) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("id"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

		if (category == null) {
			return postRepository.findAllByKeyword(kw, pageable);
		}

		return switch (category) {
			case TITLE -> postRepository.findAllByTitleContainingIgnoreCase(kw, pageable);
			case CONTENT -> postRepository.findAllByContentContainingIgnoreCase(kw, pageable);
			case TITLE_OR_CONTENT -> postRepository.findByTitleContainingOrContentContaining(kw, pageable);
			case COMMENT -> postRepository.findAllByCommentsContainingKeyword(kw, pageable);
			case AUTHOR -> postRepository.findAllByMember_Username(kw, pageable);
			default -> throw new RuntimeException("처리되지 못한 카테고리입니다.");
		};
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
