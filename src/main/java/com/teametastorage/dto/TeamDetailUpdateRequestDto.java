package com.teametastorage.dto;

import com.teametastorage.domain.TeamDetail;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TeamDetailUpdateRequestDto {
	
	private Long teamDetailSeq;
	private String team;
	private String description;
	
	@Builder
	public TeamDetailUpdateRequestDto(Long teamDetailSeq, String team, String description) {
		this.teamDetailSeq = teamDetailSeq;
		this.team = team;
		this.description = description;
	}
	
	public TeamDetail toEntity() {
		return TeamDetail.builder().teamDetailSeq(teamDetailSeq).team(team).description(description).build();
	}
	

}
