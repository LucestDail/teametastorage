package com.teametastorage.dto;

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
public class MetaReadRequestDto {

	private String nameKor;
	private String nameEng;
	private String explanation;
	private String saveTeam;
	private String saveName;
	private String type;

	@Builder
	public MetaReadRequestDto(String nameKor, String nameEng, String explanation, String saveTeam, String saveName,
			String type) {
		this.nameEng = nameEng;
		this.nameKor = nameKor;
		this.explanation = explanation;
		this.saveTeam = saveTeam;
		this.saveName = saveName;
		this.type = type;
	}

	public Meta toEntity() {
		return Meta.builder().nameEng(nameEng).nameKor(nameKor).explanation(explanation).saveTeam(saveTeam)
				.saveName(saveName).build();
	}
}
