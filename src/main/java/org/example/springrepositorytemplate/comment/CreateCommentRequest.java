package org.example.springrepositorytemplate.comment;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
	@NotEmpty
	@Length(max = 2048)
	private String content;
}
