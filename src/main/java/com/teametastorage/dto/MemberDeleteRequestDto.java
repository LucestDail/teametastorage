package com.teametastorage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDeleteRequestDto {
	
	private Long memberSeq;
	
	@Builder
	public MemberDeleteRequestDto(Long memberSeq) {
		this.memberSeq = memberSeq;
	}

}
