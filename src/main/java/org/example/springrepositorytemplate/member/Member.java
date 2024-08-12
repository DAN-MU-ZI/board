package org.example.springrepositorytemplate.member;

import org.example.springrepositorytemplate.BaseEntity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member extends BaseEntity {
	private String name;
}
