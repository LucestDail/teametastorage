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
public class MetaIndexUpdateRequestDto {
	
	private Long metaIndexSeq;
	private Long workSeq;
	private Long metaSeq;
	
	@Builder
	public MetaIndexUpdateRequestDto(Long metaIndexSeq, Long workSeq, Long metaSeq) {
		this.metaIndexSeq = metaIndexSeq;
		this.workSeq = workSeq;
		this.metaSeq = metaSeq;
	}
	
	public MetaIndex toEntity() {
		return MetaIndex.builder().metaIndexSeq(metaIndexSeq).workSeq(workSeq).metaSeq(metaSeq).build();
	}

}
