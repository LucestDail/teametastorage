package com.teametastorage.dto;

import com.teametastorage.domain.MetaIndex;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MetaIndexCreateRequestDto {
	
	private Long workSeq;
	private Long metaSeq;
	
	@Builder
	public MetaIndexCreateRequestDto(Long workSeq, Long metaSeq) {
		this.workSeq = workSeq;
		this.metaSeq = metaSeq;
	}
	
	public MetaIndex toEntity() {
		return MetaIndex.builder().workSeq(workSeq).metaSeq(metaSeq).build();
	}

}
