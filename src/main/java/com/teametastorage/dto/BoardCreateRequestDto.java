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
	private Long count;
	private Long good;

	@Builder
	public BoardCreateRequestDto(String title, String content, String saveTeam, String saveName, Long count, Long good) {
		this.title = title;
		this.content = content;
		this.saveTeam = saveTeam;
		this.saveName = saveName;
		this.count = count;
		this.good = good;
	}

	public Board toEntity() {
		return Board.builder().title(title).content(content).saveTeam(saveTeam).saveName(saveName).count(0L).good(0L).build();

	}

	@Override
	public String toString() {
		return "BoardCreateRequestDto [title=" + title + ", content=" + content + ", saveTeam=" + saveTeam
				+ ", saveName=" + saveName + ", count=" + count + ", good=" + good + "]";
	}


}
