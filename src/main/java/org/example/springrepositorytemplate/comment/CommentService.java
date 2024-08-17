package org.example.springrepositorytemplate.comment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;

	public void save(Comment comment) {
		commentRepository.save(comment);
	}
}
