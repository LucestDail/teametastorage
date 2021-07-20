package com.teametastorage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teametastorage.dto.MemberUpdateRequestDto;
import com.teametastorage.service.MemberService;
import com.teametastorage.service.TeamService;

@RestController
@RequestMapping("/member")
public class MemberRestController {
	
	@Autowired
	MemberService memberService;

	@Autowired
	TeamService teamService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * Create(Put)
	 */
	
	
	/*
	 * Read(Get)
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView getUpdateMember() {
		return new ModelAndView("member/update.html");
	}
	
	/*
	 * Update(Post)
	 */
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public boolean postUpdatMember(@RequestBody MemberUpdateRequestDto dto, HttpServletRequest request) {
		if (memberService.updateMember(dto)) {
			HttpSession session = request.getSession();
			return true;
		}
		return false;
	}
	
	
	/*
	 * Delete(Delete)
	 */
	
	
	

	

	

}
