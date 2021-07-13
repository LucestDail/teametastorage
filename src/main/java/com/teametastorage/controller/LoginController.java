package com.teametastorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.teametastorage.service.MemberService;

@RestController
public class LoginController {
	
	@Autowired
	MemberService memberService;

}
