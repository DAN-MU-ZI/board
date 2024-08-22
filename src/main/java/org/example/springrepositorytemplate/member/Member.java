package org.example.springrepositorytemplate.member;

import java.util.List;

import org.example.springrepositorytemplate.BaseEntity;
import org.example.springrepositorytemplate.comment.Comment;
import org.example.springrepositorytemplate.post.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member extends BaseEntity {
	@Column(unique = true)
	private String username;
	private String password;

	@Column(unique = true)
	private String email;

	@Enumerated(EnumType.STRING)
	private MemberRole role;

	@OneToMany(mappedBy = "member")
	private List<Post> posts;

	@OneToMany(mappedBy = "member")
	private List<Comment> comments;
}