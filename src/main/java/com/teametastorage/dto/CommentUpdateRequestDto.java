package com.teametastorage.dto;

import com.teametastorage.domain.Comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentUpdateRequestDto {

	private Long commentSeq;
	private String boardId;
	private String content;
	private String saveTeam;
	private String saveName;

	@Builder
	public CommentUpdateRequestDto(Long commentSeq, String boardId, String content, String saveTeam, String saveName) {
		this.commentSeq = commentSeq;
		this.boardId = boardId;
		this.content = content;
		this.saveTeam = saveTeam;
		this.saveName = saveName;
	}

	public Comment toEntity() {
		return Comment.builder().commentSeq(commentSeq).boardId(boardId).content(content).saveTeam(saveTeam)
				.saveName(saveName).build();
	}

	@Override
	public String toString() {
		return "CommentUpdateRequestDto [commentSeq=" + commentSeq + ", boardId=" + boardId + ", content=" + content
				+ ", saveTeam=" + saveTeam + ", saveName=" + saveName + "]";
	}

}
