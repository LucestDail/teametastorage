package com.teametastorage.dto;

import com.teametastorage.domain.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardUpdateRequestDto {
	private Long boardSeq;
	private String title;
	private String content;
	private String saveTeam;
	private String saveName;

	@Builder
	public BoardUpdateRequestDto(Long boardSeq, String title, String content, String saveTeam, String saveName) {
		this.boardSeq = boardSeq;
		this.title = title;
		this.content = content;
		this.saveTeam = saveTeam;
		this.saveName = saveName;
	}

	public Board toEntity() {
		return Board.builder().boardSeq(boardSeq).title(title).content(content).saveTeam(saveTeam).saveName(saveName)
				.build();

	}

	@Override
	public String toString() {
		return "BoardUpdateRequestDto [boardSeq=" + boardSeq + ", title=" + title + ", content=" + content
				+ ", saveTeam=" + saveTeam + ", saveName=" + saveName + "]";
	}

}
