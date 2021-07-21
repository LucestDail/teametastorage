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
public class TeamDetailCreateRequestDto {
	
	private String team;
	private String description;
	
	@Builder
	public TeamDetailCreateRequestDto(String team, String description){
		this.team = team;
		this.description = description;
	}
	
	public TeamDetail toEntity() {
		return TeamDetail.builder().team(team).description("안녕하세요. " + team + "팀 입니다").build();
	}

}
