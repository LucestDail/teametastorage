package com.teametastorage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Good {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long goodSeq;
	
	@Column(length = 500, nullable = false)
	private String boardId;
	
	@Column(length = 500, nullable = false)
	private String goodName;
	
	@Column(length = 500, nullable = false)
	private String goodId;
	
	@Builder
	public Good(Long goodSeq, String boardId, String goodName, String goodId) {
		this.goodSeq = goodSeq;
		this.boardId = boardId;
		this.goodName = goodName;
		this.goodId = goodId;
	}

	@Override
	public String toString() {
		return "Good [goodSeq=" + goodSeq + ", boardId=" + boardId + ", goodName=" + goodName + ", goodId=" + goodId
				+ "]";
	}
	
}
