package com.teametastorage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.teametastorage.service.MemberService;

@RestController
public class LoginController {
	
	@Autowired
	MemberService memberService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


}
