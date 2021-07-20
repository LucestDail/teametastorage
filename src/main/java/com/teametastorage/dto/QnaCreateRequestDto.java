package com.teametastorage.dto;

import com.teametastorage.domain.Qna;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class QnaCreateRequestDto {

	private String title;
	private String content;
	private String category;
	
	@Builder
	public QnaCreateRequestDto(String title, String content, String category) {
		this.title = title;
		this.content = content;
		this.category = category;
	}
	
	public Qna toEntityService() {
		return Qna.builder().title(title).content(content).category("service").build();
	}
	
	public Qna toEntityTech() {
		return Qna.builder().title(title).content(content).category("tech").build();
	}
	
	public Qna toEntityUsually() {
		return Qna.builder().title(title).content(content).category("usually").build();
	}
	
	public Qna toEntityPolicy() {
		return Qna.builder().title(title).content(content).category("policy").build();
	}
	
	public Qna toEntityNotice() {
		return Qna.builder().title(title).content(content).category("notice").build();
	}
	
	

}
