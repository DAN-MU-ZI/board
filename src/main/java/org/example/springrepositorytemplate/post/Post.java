package org.example.springrepositorytemplate.post;

import java.util.List;

import org.example.springrepositorytemplate.BaseEntity;
import org.example.springrepositorytemplate.comment.Comment;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Post extends BaseEntity {
	private String title;
	private String content;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments;
}
