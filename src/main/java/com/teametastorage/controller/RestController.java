package com.teametastorage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
@RequiredArgsConstructor
public class RestController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/hello")
	public String hello() {
		return "testing page";
	}

	@GetMapping("/")
	public ModelAndView login(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login.html");
		return mav;
	}

	@GetMapping("/main")
	public ModelAndView loginMain(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/main.html");
		return mav;
	}
}
