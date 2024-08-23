package org.example.springrepositorytemplate;

import lombok.Getter;

@Getter
public enum SearchCategory {
	TITLE("title", "제목"),
	CONTENT("content", "내용"),
	TITLE_OR_CONTENT("titleOrContent", "제목 또는 내용"),
	COMMENT("comment", "댓글"),
	AUTHOR("author", "작성자");

	private final String value;
	private final String displayName;

	SearchCategory(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

}
