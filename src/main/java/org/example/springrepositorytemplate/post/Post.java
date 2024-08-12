package org.example.springrepositorytemplate.post;

import org.example.springrepositorytemplate.BaseEntity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Post extends BaseEntity {
	private String title;
	private String content;
}
