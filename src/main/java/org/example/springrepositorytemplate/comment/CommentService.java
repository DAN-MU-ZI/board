package org.example.springrepositorytemplate.comment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;

	public void save(Comment comment) {
		commentRepository.save(comment);
	}

	public Comment findById(Long id) {
		return commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	public void delete(Comment comment) {
		commentRepository.delete(comment);
	}
}
