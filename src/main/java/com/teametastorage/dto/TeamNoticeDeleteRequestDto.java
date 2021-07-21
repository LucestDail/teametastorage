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
public class TeamNoticeDeleteRequestDto {
	private Long teamNoticeSeq;

	@Builder
	public TeamNoticeDeleteRequestDto(Long teamNoticeSeq) {
		this.teamNoticeSeq = teamNoticeSeq;
	}

	public TeamNotice toEntity() {
		return TeamNotice.builder().teamNoticeSeq(teamNoticeSeq).build();

	}

}
