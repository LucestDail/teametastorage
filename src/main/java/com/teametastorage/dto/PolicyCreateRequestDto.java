package com.teametastorage.dto;

import javax.persistence.Column;

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
public class PolicyCreateRequestDto {

	private String title;
	private String content;
	
	@Builder
	public PolicyCreateRequestDto(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public Policy toEntity() {
		return Policy.builder().title(title).content(content).build();
	}

}
