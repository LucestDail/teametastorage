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
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberSeq;

	@Column(length = 500)
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(length = 500)
	@NotNull
	private String password;

	@Column(length = 500)
	@NotNull
	private String name;

	@Column(length = 500)
	@NotNull
	private String team;
	
	@Column(length = 500)
	@NotNull
	private String info;

	@Builder
	public Member(Long memberSeq, String id, String password, String name, String team, String info) {
		this.memberSeq = memberSeq;
		this.id = id;
		this.password = password;
		this.name = name;
		this.team = team;
		this.info = info;
	}
}