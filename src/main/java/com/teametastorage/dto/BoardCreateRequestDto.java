package com.teametastorage.dto;

import com.teametastorage.domain.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardCreateRequestDto {
	
	private String title;
	private String content;
	private String saveTeam;
	private String saveName;

	@Builder
	public BoardCreateRequestDto(String title, String content, String saveTeam, String saveName) {
		this.title = title;
		this.content = content;
		this.saveTeam = saveTeam;
		this.saveName = saveName;
	}

	public Board toEntity() {
		return Board.builder().title(title).content(content).saveTeam(saveTeam).saveName(saveName).build();

	}

	@Override
	public String toString() {
		return "BoardCreateRequestDto [title=" + title + ", content=" + content + ", saveTeam=" + saveTeam
				+ ", saveName=" + saveName + "]";
	}

}
