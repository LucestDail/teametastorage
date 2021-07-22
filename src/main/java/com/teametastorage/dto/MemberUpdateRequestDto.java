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
public class MemberUpdateRequestDto {

	private Long memberSeq;
	private String id;
	private String password;
	private String name;
	private String team;
	private String info;

	@Builder
	public MemberUpdateRequestDto(Long memberSeq, String id, String password, String name, String team, String info) {
		this.memberSeq = memberSeq;
		this.id = id;
		this.password = password;
		this.name = name;
		this.team = team;
		this.info = info;
	}

	public Member toEntity() {
		return Member.builder().id(id).password(password).name(name).team(team).info(info).build();
	}
}
