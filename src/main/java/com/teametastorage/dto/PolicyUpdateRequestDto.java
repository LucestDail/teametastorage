package com.teametastorage.dto;

import com.teametastorage.domain.Policy;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PolicyUpdateRequestDto {

	private Long policySeq;
	private String title;
	private String content;
	
	@Builder
	public PolicyUpdateRequestDto(Long policySeq, String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public Policy toEntity() {
		return Policy.builder().policySeq(policySeq).title(title).content(content).build();
	}

}

