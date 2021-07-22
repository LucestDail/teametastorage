package com.teametastorage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teametastorage.domain.Member;
import com.teametastorage.domain.Team;
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
	
	/*
	 * member detail utility 
	 */
	@RequestMapping(value = "/detail/{seq}", method = RequestMethod.GET)
	public ModelAndView detailMember(@PathVariable("seq") String seq) {
		ModelAndView mav = new ModelAndView();
		Member currentMember = memberService.getMemberBySeq(Long.parseLong(seq));
		mav.addObject("member",currentMember);
		mav.addObject("seq", seq);
		mav.setViewName("member/detail");
		return mav;
	}
	
	@RequestMapping(value = "/detail/{seq}", method = RequestMethod.POST)
	public boolean updateMember(@RequestBody MemberUpdateRequestDto dto, @PathVariable("seq") String seq, HttpServletRequest request) {
		System.out.println("dto >>>>>>>>>>>> " + dto);
		if (memberService.updateMember(dto)) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/detail/{seq}", method=RequestMethod.DELETE)
	public boolean deleteMember(@PathVariable("seq") String seq) {
		Member targetMember = memberService.getMemberBySeq(Long.parseLong(seq));
		Team targetTeam = teamService.getTeamObject(targetMember.getTeam(), targetMember.getId());
		if(teamService.deleteTeamMember(targetTeam)) {
			if(memberService.deleteMember(Long.parseLong(seq))) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	/*
	 * diary utility
	 */
	@RequestMapping(value = "/diary/{seq}", method = RequestMethod.GET)
	public ModelAndView getDiary(@PathVariable("seq") String seq) {
		ModelAndView mav = new ModelAndView();
		Member currentMember = memberService.getMemberBySeq(Long.parseLong(seq));
		mav.addObject("member",currentMember);
		mav.addObject("seq", seq);
		mav.setViewName("member/diary");
		return mav;
	}

	/*
	 * Note utility
	 */
	@RequestMapping(value = "/note/{seq}", method = RequestMethod.GET)
	public ModelAndView getNote(@PathVariable("seq") String seq) {
		ModelAndView mav = new ModelAndView();
		Member currentMember = memberService.getMemberBySeq(Long.parseLong(seq));
		mav.addObject("member",currentMember);
		mav.addObject("seq", seq);
		mav.setViewName("member/note");
		return mav;
	}
}
