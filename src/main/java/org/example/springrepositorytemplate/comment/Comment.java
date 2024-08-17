package org.example.springrepositorytemplate.comment;

import org.example.springrepositorytemplate.BaseEntity;
import org.example.springrepositorytemplate.post.Post;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment extends BaseEntity {
	@ManyToOne
	private Post post;
	private String content;
}
