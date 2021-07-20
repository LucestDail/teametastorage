package com.teametastorage.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
public class Event {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eventSeq;
	
	@Column(length = 500, nullable = false)
	private String title; 
	
	@Column(columnDefinition = "text", nullable = false)
	private String description;
	
	@Column(nullable = false)
	private LocalDateTime start; 
	
	@Column(nullable = false)
	private LocalDateTime finish;
	
	@Builder
	public Event(Long eventSeq, String title, String description, LocalDateTime start, LocalDateTime finish) {
		this.eventSeq = eventSeq;
		this.title = title;
		this.description = description;
		this.start = start;
		this.finish = finish;
	}

}
