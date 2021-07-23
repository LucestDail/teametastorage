package com.teametastorage.dto;

import com.teametastorage.domain.Member;
import com.teametastorage.domain.Meta;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MetaUpdateRequestDto {

	private Long metaSeq;
	private String title;
	private String description;
	private String saveTeam;
	private String saveName;
	private String saveId;

	@Builder
	public MetaUpdateRequestDto(Long metaSeq, String title, String description, String saveTeam, String saveName,
			String saveId) {
		this.metaSeq = metaSeq;
		this.title = title;
		this.description = description;
		this.saveTeam = saveTeam;
		this.saveName = saveName;
		this.saveId = saveId;
	}

	public Meta toEntity(Member member) {
		return Meta.builder().metaSeq(metaSeq).title(title).description(description).saveTeam(member.getTeam())
				.saveName(member.getName()).saveId(member.getId()).build();
	}
}