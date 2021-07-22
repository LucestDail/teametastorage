package com.teametastorage.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.teametastorage.domain.Event;
import com.teametastorage.domain.Member;
import com.teametastorage.repository.EventRepository;
import com.teametastorage.util.*;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EventService {

	private EventRepository eventRepository;

	public List<Event> allEvents(Member currentMember) {
		List<Event> listEvent = eventRepository.findAll();
		List<Event> targetEvent = new ArrayList<>();
		for (Event target : listEvent) {
			if (target.getSaveTeam().equals(currentMember.getTeam())) {
				targetEvent.add(target);
			}
		}
		return targetEvent;
	}

	public Event addEvent(@RequestBody Event event, Member currentMember) {
		event.setSaveName(currentMember.getName());
		event.setSaveId(currentMember.getId());
		event.setSaveTeam(currentMember.getTeam());
		return eventRepository.save(event);
	}

	public Event updateEvent(@RequestBody Event event, Member currentMember) {
		event.setSaveName(currentMember.getName());
		event.setSaveId(currentMember.getId());
		event.setSaveTeam(currentMember.getTeam());
		return eventRepository.saveAndFlush(event);
	}

	public void removeEvent(@RequestBody Event event) {
		eventRepository.delete(event);
	}

	public List<Event> getEventsInRange(@RequestParam(value = "start", required = true) String start,
			@RequestParam(value = "end", required = true) String end, Member currentMember) {
		Date startDate = null;
		Date endDate = null;
		SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			startDate = inputDateFormat.parse(start);
		} catch (ParseException e) {
			throw new BadDateFormatException("bad start date: " + start);
		}

		try {
			endDate = inputDateFormat.parse(end);
		} catch (ParseException e) {
			throw new BadDateFormatException("bad end date: " + end);
		}
		LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
		LocalDateTime endDateTime = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
		
		List<Event> targetDateEvent = eventRepository.findByDateBetween(startDateTime, endDateTime);
		List<Event> targetEvent = new ArrayList<>();
		for(Event target : targetDateEvent) {
			if(target.getSaveTeam().equals(currentMember.getTeam())) {
				targetEvent.add(target);
			}
		}
		return targetEvent;
	}

}
