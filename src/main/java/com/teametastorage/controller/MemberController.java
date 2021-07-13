package com.teametastorage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RestController;

import com.teametastorage.domain.Member;
import com.teametastorage.domain.Team;
import com.teametastorage.service.MemberService;
import com.teametastorage.service.TeamService;

@RestController
public class MemberController {

	@Autowired
	MemberService memberService;

	@Autowired
	TeamService teamService;

	@RequestMapping(value = "/listMembers", method = RequestMethod.GET)
	public ModelAndView listMember(Model model, HttpServletRequest request) {
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		String team = sessionMember.getTeam();
		System.out.println("MemberController - listMember : ");
		ModelAndView mav = new ModelAndView();
		List<Team> teamlist = new ArrayList<>();
		teamlist = teamService.getAllMember(team);
		mav.addObject("teamlist", teamlist);
		mav.setViewName("member/listMembers.html");
		return mav;
	}

	@RequestMapping(value = "/noneToNormal", method = RequestMethod.GET)
	public ModelAndView noneToNormal(Model model, @RequestParam("id") Optional<String> id, HttpServletRequest request) {
		System.out.println("MemberController - noneToNormal : " + id);
		ModelAndView mav = new ModelAndView();
		Team targetTeam = teamService.getTeamBySeq(Long.parseLong(id.get()));
		if (teamService.noneToNormal(targetTeam)) {
			System.out.println("noneToNormal Success");
		} else {
			System.out.println("noneToNormal Fail : " + targetTeam);
		}
		mav.setViewName("main/main.html");
		return mav;
	}
}
