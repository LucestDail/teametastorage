package com.teametastorage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teametastorage.domain.Event;
import com.teametastorage.service.EventService;


@RestController
public class EventController {
	
	@Autowired
	EventService eventService;
	
	@RequestMapping(value="/calendar", method=RequestMethod.GET) 
	public ModelAndView calendar() {
		return new ModelAndView("common/calendar");
	}
	
	@RequestMapping(value="/jsoncalendar", method=RequestMethod.GET) 
	public ModelAndView jsoncalendar() {
		return new ModelAndView("common/jsoncalendar");
	}
	
	@RequestMapping(value="/allevents", method=RequestMethod.GET)
	public List<Event> allEvents() {
		return eventService.allEvents();
	}
	
	@RequestMapping(value="/event", method=RequestMethod.POST)
	public Event addEvent(@RequestBody Event event) {
		return eventService.addEvent(event);
	}
	
	@RequestMapping(value="/event", method=RequestMethod.PATCH)
	public Event updateEvent(@RequestBody Event event) {
		return eventService.updateEvent(event);
	}
	
	@RequestMapping(value="/event", method=RequestMethod.DELETE)
	public void removeEvent(@RequestBody Event event) {
		eventService.removeEvent(event);
	}
	
	@RequestMapping(value="/events", method=RequestMethod.GET)
	public List<Event> getEventsInRange(@RequestParam(value = "start", required = true) String start, 
			@RequestParam(value = "end", required = true) String end) {
		return eventService.getEventsInRange(start, end);
	}

}
