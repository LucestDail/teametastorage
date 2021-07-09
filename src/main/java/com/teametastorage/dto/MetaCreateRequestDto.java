package com.teametastorage.dto;

import com.teametastorage.domain.Member;
import com.teametastorage.domain.Meta;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MetaCreateRequestDto {
	
	private String nameKor;
	private String nameEng;
	private String explanation;
	private String saveTeam;
	private String saveName;
	private String type;
	
	@Builder
	public MetaCreateRequestDto(String nameKor, String nameEng, String explanation, String saveTeam, String saveName,
			String type) {
		this.nameKor = nameKor;
		this.nameEng = nameEng;
		this.explanation = explanation;
		this.saveTeam = saveTeam;
		this.saveName = saveName;
		this.type = type;
	}
	
	public Meta toEntity(Member sessionMember) {
		return Meta.builder().nameKor(nameKor).nameEng(nameEng).explanation(explanation).saveTeam(sessionMember.getTeam()).saveName(sessionMember.getName()).type(type).build();
	}

	@Override
	public String toString() {
		return "MetaCreateRequestDto [nameKor=" + nameKor + ", nameEng=" + nameEng + ", explanation=" + explanation
				+ ", saveTeam=" + saveTeam + ", saveName=" + saveName + ", type=" + type + "]";
	}
	

}
