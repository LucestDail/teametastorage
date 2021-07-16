package com.teametastorage.dto;

import com.teametastorage.domain.Team;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TeamCreateRequestDto {

	private String memberId;
	private String team;
	private String name;
	private String rank;

	@Builder
	public TeamCreateRequestDto(String memberId, String team, String name, String rank) {
		this.memberId = memberId;
		this.team = team;
		this.name = name;
		this.rank = rank;
	}

	public Team toEntityNone() {
		return Team.builder().memberId(memberId).team(team).name(name).rank("none").build();
	}

	public Team toEntityNormal() {
		return Team.builder().memberId(memberId).team(team).name(name).rank("normal").build();
	}

	public Team toEntityAdmin() {
		return Team.builder().memberId(memberId).team(team).name(name).rank("admin").build();
	}
}
