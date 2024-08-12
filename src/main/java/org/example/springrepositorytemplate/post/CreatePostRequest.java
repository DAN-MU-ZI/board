package org.example.springrepositorytemplate.post;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;

public record CreatePostRequest(
	@NotEmpty(message = "제목은 필수입니다.")
	@Length(max = 20)
	String title,
	@NotEmpty(message = "내용은 필수입니다.")
	@Length(max = 2048)
	String content
) {
}
