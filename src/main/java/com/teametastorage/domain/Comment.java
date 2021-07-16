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
public class Comment extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentSeq;

	@Column(length = 500, nullable = false)
	private String boardId;

	@Column(columnDefinition = "text", nullable = false)
	private String content;

	@Column(length = 500, nullable = false)
	private String saveTeam;

	@Column(length = 500, nullable = false)
	private String saveName;
	
	@Column(length = 500, nullable = false)
	private String saveId;

	@Builder
	public Comment(Long commentSeq, String boardId, String content, String saveTeam, String saveName, String saveId) {
		this.commentSeq = commentSeq;
		this.boardId = boardId;
		this.content = content;
		this.saveTeam = saveTeam;
		this.saveName = saveName;
		this.saveId = saveId;
	}
}