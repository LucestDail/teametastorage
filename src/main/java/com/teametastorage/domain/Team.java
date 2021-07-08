package com.teametastorage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;
import com.teametastorage.util.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Team extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teamSeq;

	@Column(length = 500)
	@NotNull
	private String memberId;

	@Column(length = 500)
	@NotNull
	private String team;

	@Column(length = 500)
	@NotNull
	private String name;

	@Column(length = 500)
	@NotNull
	private String rank;

	@Builder
	public Team(Long teamSeq, String memberId, String team, String name, String rank) {
		this.teamSeq = teamSeq;
		this.memberId = memberId;
		this.team = team;
		this.name = name;
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Team [teamSeq=" + teamSeq + ", memberId=" + memberId + ", team=" + team + ", name=" + name + ", rank="
				+ rank + "]";
	}

}