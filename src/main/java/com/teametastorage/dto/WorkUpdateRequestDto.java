package com.teametastorage.dto;

import java.time.LocalDateTime;

import com.teametastorage.domain.Member;
import com.teametastorage.domain.Work;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class WorkUpdateRequestDto {

	private Long workSeq;
	private String title; 
	private String saveTeam;
	private String saveId; 
	private String saveName; 
	private String description;
	private String metalist;
	private LocalDateTime start; 
	private LocalDateTime end;

	@Builder
	public WorkUpdateRequestDto(Long workSeq, String title, String saveTeam, String saveId, String saveName, String description, LocalDateTime start, LocalDateTime end, String metalist) {
		this.workSeq = workSeq;
		this.title = title;
		this.saveTeam = saveTeam;
		this.saveId = saveId;
		this.saveName = saveName;
		this.description = description;
		this.start = start;
		this.end = end;
		this.metalist = metalist;
	}

	public Work toEntity(Member member) {
		return Work.builder().workSeq(workSeq).title(title).saveTeam(member.getTeam()).saveId(member.getId()).saveName(member.getName()).description(description).start(start).end(end).metalist(metalist).build();
	}
}
