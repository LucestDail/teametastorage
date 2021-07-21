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
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
public class TeamDetail extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teamDetailSeq;
	
	@Column(length = 500)
	@NotNull
	private String team;
	
	@Column(length = 500)
	@NotNull
	private String description;
	
	@Builder
	public TeamDetail(Long teamDetailSeq, String team, String description) {
		this.teamDetailSeq = teamDetailSeq;
		this.team = team;
		this.description = description;
	}
	

}
