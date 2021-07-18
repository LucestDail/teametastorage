package com.teametastorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@CrossOrigin
@RequiredArgsConstructor
public class RestController {
	
	@GetMapping("/test")
	public String test() {
		return "main/test.html";
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
	
	@GetMapping("/policy")
	public ModelAndView policy(Model mode) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("qna/policy.html");
		return mav;
	}
	
	@GetMapping("/service")
	public ModelAndView service(Model mode) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("qna/service.html");
		return mav;
	}
	
	@GetMapping("/tech")
	public ModelAndView tech(Model mode) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("qna/tech.html");
		return mav;
	}
	
	@GetMapping("/usually")
	public ModelAndView usually(Model mode) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("qna/usually.html");
		return mav;
	}
}
