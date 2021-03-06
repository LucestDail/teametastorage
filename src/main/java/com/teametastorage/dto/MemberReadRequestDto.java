package com.teametastorage.dto;

import com.teametastorage.domain.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberReadRequestDto {

	private String id;
	private String password;
	private String name;
	private String team;
	
	@Builder
	public MemberReadRequestDto(String id, String password, String name, String team) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.team = team;
	}
	
	public Member toEntity() {
		return Member.builder().id(id).password(password).name(name).team(team).build();
	}

}
