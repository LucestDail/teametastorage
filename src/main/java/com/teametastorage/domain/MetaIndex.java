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
public class MetaIndex extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long metaIndexSeq;
	
	@Column
	private Long workSeq;
	
	@Column
	private Long metaSeq;

	@Builder
	public MetaIndex(Long metaIndexSeq, Long workSeq, Long metaSeq) {
		this.metaIndexSeq = metaIndexSeq;
		this.workSeq = workSeq;
		this.metaSeq = metaSeq;
	}
}
