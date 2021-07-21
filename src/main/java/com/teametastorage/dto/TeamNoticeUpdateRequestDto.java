package com.teametastorage.dto;

import com.teametastorage.domain.TeamNotice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TeamNoticeUpdateRequestDto {
	private Long teamNoticeSeq;
	private String title;
	private String content;
	private String saveTeam;
	private String saveName;
	private String saveId;

	@Builder
	public TeamNoticeUpdateRequestDto(Long teamNoticeSeq, String title, String content, String saveTeam, String saveName, String saveId) {
		this.teamNoticeSeq = teamNoticeSeq;
		this.title = title;
		this.content = content;
		this.saveTeam = saveTeam;
		this.saveName = saveName;
		this.saveId = saveId;
	}

	public TeamNotice toEntity() {
		return TeamNotice.builder().teamNoticeSeq(teamNoticeSeq).title(title).content(content).saveTeam(saveTeam).saveName(saveName).saveId(saveId)
				.build();

	}

}
