package com.teametastorage.dto;

import com.teametastorage.domain.Comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentCreateRequestDto {
	private String boardId;
	private String content;
	private String saveTeam;
	private String saveName;
	
	@Builder
	public CommentCreateRequestDto(String boardId, String content, String saveTeam, String saveName) {
		this.boardId = boardId;
		this.content = content;
		this.saveTeam = saveTeam;
		this.saveName = saveName;
	}
	
	public Comment toEntity() {
		return Comment.builder().boardId(boardId).content(content).saveTeam(saveTeam).saveName(saveName).build();
	}

	@Override
	public String toString() {
		return "CommentCreateRequestDto [boardId=" + boardId + ", content=" + content + ", saveTeam=" + saveTeam
				+ ", saveName=" + saveName + "]";
	}
	
	
}
