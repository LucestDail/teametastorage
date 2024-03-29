package com.teametastorage.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.teametastorage.util.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Event extends BaseTimeEntity{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eventSeq;
	
	@Column(length = 500)
	private String title; 
	
	@Column(length = 500, nullable = false)
	private String saveTeam;
	
	@Column(length = 500, nullable = false)
	private String saveId; 
	
	@Column(length = 500, nullable = false)
	private String saveName; 
	
	@Column(columnDefinition = "LONGTEXT")
	private String description;
	
	@Column
	private LocalDateTime start; 
	
	@Column
	private LocalDateTime finish;
	
	@Builder
	public Event(Long eventSeq, String title, String saveTeam, String saveId, String saveName, String description, LocalDateTime start, LocalDateTime finish) {
		this.eventSeq = eventSeq;
		this.title = title;
		this.saveTeam = saveTeam;
		this.saveId = saveId;
		this.saveName = saveName;
		this.description = description;
		this.start = start;
		this.finish = finish;
	}

}
