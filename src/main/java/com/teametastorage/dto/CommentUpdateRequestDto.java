package com.teametastorage.dto;

import com.teametastorage.domain.Comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentUpdateRequestDto {

	private Long commentSeq;
	private String boardId;
	private String content;
	private String saveTeam;
	private String saveName;
	private String saveId;

	@Builder
	public CommentUpdateRequestDto(Long commentSeq, String boardId, String content, String saveTeam, String saveName, String saveId) {
		this.commentSeq = commentSeq;
		this.boardId = boardId;
		this.content = content;
		this.saveTeam = saveTeam;
		this.saveName = saveName;
		this.saveId = saveId;
	}

	public Comment toEntity() {
		return Comment.builder().commentSeq(commentSeq).boardId(boardId).content(content).saveTeam(saveTeam)
				.saveName(saveName).saveId(saveId).build();
	}

}
