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
public class Meta extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long metaSeq;

	@Column(columnDefinition = "text", nullable = false)
	private String title;

	@Column(columnDefinition = "text", nullable = false)
	private String description;

	@Column(columnDefinition = "text", nullable = false)
	private String saveTeam;

	@Column(columnDefinition = "text", nullable = false)
	private String saveName;

	@Column(columnDefinition = "text", nullable = false)
	private String saveId;

	@Builder
	public Meta(Long metaSeq, String title, String description, String saveTeam, String saveName, String saveId) {
		this.metaSeq = metaSeq;
		this.title = title;
		this.description = description;
		this.saveTeam = saveTeam;
		this.saveName = saveName;
		this.saveId = saveId;
	}
}
