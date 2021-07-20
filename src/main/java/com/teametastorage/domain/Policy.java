package com.teametastorage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Policy extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long policySeq;
	
	@Column(length = 500, nullable = false)
	private String title;

	@Column(columnDefinition = "text", nullable = false)
	private String content;
	
	@Builder
	public Policy(Long policySeq, String title, String content) {
		this.policySeq = policySeq;
		this.title = title;
		this.content = content;
	}

}
