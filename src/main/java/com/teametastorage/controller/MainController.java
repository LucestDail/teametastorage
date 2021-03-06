package com.teametastorage.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class MainController {
	
	@GetMapping("/test")
	public ModelAndView test(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/test");
		return mav;
	}
	
	@GetMapping("/testa")
	public ModelAndView testa(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/testa");
		return mav;
	}
	
	@GetMapping("/testb")
	public ModelAndView testb(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/testb");
		return mav;
	}

	@GetMapping("/")
	public ModelAndView login(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login");
		return mav;
	}

	@GetMapping("/main")
	public ModelAndView loginMain(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/main");
		return mav;
	}
	
	@GetMapping("/filebrowserBrowse")
	public ModelAndView filebrowserBrowse(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/filebrowserBrowse");
		return mav;
	}
	
	@GetMapping("/filebrowserUpload")
	public ModelAndView filebrowserUpload(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/filebrowserUpload");
		return mav;
	}

}
