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

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Meta extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long metaSeq;

	@Column(columnDefinition = "text", nullable = false)
	private String nameKor;

	@Column(columnDefinition = "text", nullable = false)
	private String nameEng;

	@Column(columnDefinition = "text", nullable = false)
	private String explanation;

	@Column(columnDefinition = "text", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String saveTeam;

	@Column(columnDefinition = "text", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String saveName;

	@Column(columnDefinition = "text", nullable = false)
	private String type;

	@Builder
	public Meta(Long metaSeq, String nameKor, String nameEng, String explanation, String saveTeam, String saveName,
			String type) {
		this.metaSeq = metaSeq;
		this.nameKor = nameKor;
		this.nameEng = nameEng;
		this.explanation = explanation;
		this.saveTeam = saveTeam;
		this.saveName = saveName;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Meta [metaSeq=" + metaSeq + ", nameKor=" + nameKor + ", nameEng=" + nameEng + ", explanation="
				+ explanation + ", saveTeam=" + saveTeam + ", saveName=" + saveName + ", type=" + type + "]";
	}

}
