package com.teametastorage.dto;

import com.teametastorage.domain.Comment;
import com.teametastorage.domain.Good;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoodCreateRequestDto {
	
	private String boardId;
	private String goodName;
	private String goodId;
	
	@Builder
	public GoodCreateRequestDto(String boardId, String goodName, String goodId) {
		this.boardId = boardId;
		this.goodName = goodName;
		this.goodId = goodId;
	}
	

	public Good toEntity() {
		return Good.builder().boardId(boardId).goodName(goodName).goodId(goodId).build();
	}


	@Override
	public String toString() {
		return "GoodCreateRequestDto [boardId=" + boardId + ", goodName=" + goodName + ", goodId=" + goodId + "]";
	}
	
	
}
