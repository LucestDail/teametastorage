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
public class QnaUpdateRequestDto {

	private Long qnaSeq;
	private String title;
	private String content;
	private String category;
	
	@Builder
	public QnaUpdateRequestDto(Long policySeq, String title, String content, String category) {
		this.title = title;
		this.content = content;
		this.category = category;
	}
	
	public Qna toEntity() {
		return Qna.builder().qnaSeq(qnaSeq).title(title).content(content).category(category).build();
	}

}

