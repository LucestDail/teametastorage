package com.teametastorage.dto;

import com.teametastorage.domain.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardCreateRequestDto {
	
	private String title;
	private String content;
	private String saveTeam;
	private String saveName;
	private String saveId;
	private Long count;
	private Long good;
	private String category;

	@Builder
	public BoardCreateRequestDto(String title, String content, String saveTeam, String saveName, String saveId, Long count, Long good, String category) {
		this.title = title;
		this.content = content;
		this.saveTeam = saveTeam;
		this.saveName = saveName;
		this.saveId = saveId;
		this.count = count;
		this.good = good;
		this.category = category;
		}

	public Board toEntity() {
		return Board.builder().title(title).content(content).saveTeam(saveTeam).saveName(saveName).saveId(saveId).count(0L).good(0L).category(category).build();

	}

}
