package com.teametastorage.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
@RequiredArgsConstructor
public class RestController {

	@GetMapping("/hello")
	public String hello() {
		System.out.println("Restcontroller - hello : ");
		return "testing page";
	}

	@GetMapping("/")
	public ModelAndView login(Model model) {
		System.out.println("RestController - login : " + model);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login.html");
		return mav;
	}

	@GetMapping("/main")
	public ModelAndView loginMain(Model model) {
		System.out.println("RestController - loginMain : " + model);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/main.html");
		return mav;
	}
}
