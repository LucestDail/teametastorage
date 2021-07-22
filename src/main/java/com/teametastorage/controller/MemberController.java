package com.teametastorage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RestController;

import com.teametastorage.domain.Member;
import com.teametastorage.domain.Team;
import com.teametastorage.dto.MemberCreateRequestDto;
import com.teametastorage.dto.MemberReadRequestDto;
import com.teametastorage.dto.MemberUpdateRequestDto;
import com.teametastorage.dto.TeamCreateRequestDto;
import com.teametastorage.service.MemberService;
import com.teametastorage.service.TeamService;

@RestController
public class MemberController {

	@Autowired
	MemberService memberService;
	

	@Autowired
	TeamService teamService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@GetMapping("/updateMember")
	public ModelAndView logingetUpdate() {
		return new ModelAndView("member/update.html");
	}

	@PostMapping("/updateMember")
	public boolean loginUpdateMember(@RequestBody MemberUpdateRequestDto dto, HttpServletRequest request) {
		if (memberService.updateMember(dto)) {
			HttpSession session = request.getSession();
			//session.invalidate();
			return true;
		}
		return false;
	}

	@GetMapping("/deleteMember")
	public ModelAndView loginDeleteMember(@RequestParam String id, HttpServletRequest request) throws Exception {
		String rank = (String) request.getSession().getAttribute("rank");
		Member targetMember = memberService.getMemberById(id);
		Team targetTeam = teamService.getTeamObject(targetMember.getTeam(), targetMember.getId());
		ModelAndView mav = new ModelAndView();
		if (teamService.deleteTeamMember(targetTeam)) {
			if (memberService.deleteMember(targetMember.getMemberSeq())) {
				if (!rank.equals("admin")) {
					HttpSession session = request.getSession();
					session.invalidate();
					mav.setViewName("member/login.html");
					return mav;
				} else {
					List<Team> teamlist = new ArrayList<>();
					teamlist = teamService.getAllMember(targetMember.getTeam());
					mav.addObject("teamlist", teamlist);
					mav.setViewName("member/listMembers.html");
					return mav;
				}
			}
		}
		mav.setViewName("member/mypage.html");
		return mav;
	}

	@GetMapping("/register")
	public ModelAndView getRegister() {
		return new ModelAndView("member/register.html");
	}

	@GetMapping("/login")
	public ModelAndView getLoginform() {
		return new ModelAndView("main/login.html");
	}

	@PostMapping("/loginMember")
	public boolean postMemberLogin(@RequestBody MemberReadRequestDto dto, HttpServletRequest request) {
		if (memberService.loginMember(dto)) {
			HttpSession session = request.getSession();
			Member currentMember = memberService.getMemberById(dto.getId());
			Team currentTeam = teamService.getTeamObject(currentMember.getTeam(), currentMember.getId());
			if (!currentTeam.getRank().equals("none")) {
				session.setAttribute("member", currentMember);
				session.setAttribute("rank", teamService.getRank(currentMember.getTeam(), currentMember.getId()));
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@PostMapping("/createMember")
	public Long createMember(@RequestBody MemberCreateRequestDto dto) throws Exception {
		return memberService.createMember(dto);
	}
	


	@PostMapping("/validateId")
	public boolean validateId(@RequestBody MemberCreateRequestDto dto) throws Exception {
		if (memberService.validateMember(dto)) {
			return true;
		} else {
			return false;
		}
	}

	@PostMapping("/validateTeam")
	public boolean validateTeam(@RequestBody TeamCreateRequestDto dto) throws Exception {
		if (teamService.validateTeam(dto)) {
			return true;
		} else {
			return false;
		}
	}

	@GetMapping("/getMemberDetail")
	public ModelAndView getMemberDetail(@RequestParam String id, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Member member = memberService.getMemberById(id);
		mav.addObject(member);
		mav.setViewName("member/mypage.html");
		return mav;
	}

	@GetMapping("/mypage")
	public ModelAndView loginMypage(HttpSession session) {
		return new ModelAndView("member/mypage.html");
	}

	@GetMapping("/getTeamDetail")
	public ModelAndView loginTeampage(@RequestParam String team) {
		ModelAndView mav = new ModelAndView();
		List<Team> teamlist = new ArrayList<>();
		teamlist = teamService.getAllMember(team);
		mav.addObject("teamlist", teamlist);
		mav.setViewName("member/teampage.html");
		return mav;
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return new ModelAndView("member/login.html");
	}
	
	@RequestMapping(value = "/listMembers", method = RequestMethod.GET)
	public ModelAndView listMember(Model model, HttpServletRequest request) {
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		String team = sessionMember.getTeam();
		ModelAndView mav = new ModelAndView();
		List<Team> teamlist = new ArrayList<>();
		teamlist = teamService.getAllMember(team);
		mav.addObject("teamlist", teamlist);
		mav.setViewName("member/listMembers.html");
		return mav;
	}

	@RequestMapping(value = "/noneToNormal", method = RequestMethod.GET)
	public ModelAndView noneToNormal(Model model, @RequestParam("id") Optional<String> id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Team targetTeam = teamService.getTeamBySeq(Long.parseLong(id.get()));
		teamService.noneToNormal(targetTeam);
		mav.setViewName("main/main.html");
		return mav;
	}
}
