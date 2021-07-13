package com.teametastorage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	

	@GetMapping("/updateMember")
	public ModelAndView logingetUpdate() {
		return new ModelAndView("member/update.html");
	}

	@PostMapping("/updateMember")
	public boolean loginUpdateMember(@RequestBody MemberUpdateRequestDto dto, HttpServletRequest request) {
		System.out.println("RestController - loginUpdateMember : " + dto);
		if (memberService.updateMember(dto)) {
			HttpSession session = request.getSession();
			session.invalidate();
			return true;
		}
		return false;
	}

	@GetMapping("/deleteMember")
	public ModelAndView loginDeleteMember(@RequestParam String id, HttpServletRequest request) throws Exception {
		System.out.println("RestController - loginDeleteMember : " + id);
		String rank = (String) request.getSession().getAttribute("rank");
		Member targetMember = memberService.getMemberById(id);
		Team targetTeam = teamService.getTeamObject(targetMember.getTeam(), targetMember.getId());
		System.out.println("targetTeam : " + targetTeam);
		ModelAndView mav = new ModelAndView();
		if (teamService.deleteTeamMember(targetTeam)) {
			if (memberService.deleteMember(id)) {
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
		System.out.println("RestController - getRegister");
		return new ModelAndView("member/register.html");
	}

	@GetMapping("/login")
	public ModelAndView getLoginform() {
		System.out.println("RestController - getLoginform");
		return new ModelAndView("main/login.html");
	}

	@PostMapping("/loginMember")
	public boolean postMemberLogin(@RequestBody MemberReadRequestDto dto, HttpServletRequest request) {
		System.out.println("RestController - postMemberLogin : " + dto + " : " + request);
		if (memberService.loginMember(dto)) {
			HttpSession session = request.getSession();
			Member currentMember = memberService.getMemberById(dto.getId());
			Team currentTeam = teamService.getTeamObject(currentMember.getTeam(), currentMember.getId());

			if (!currentTeam.getRank().equals("none")) {
				System.out.println("Auth user login : " + dto);
				session.setAttribute("member", currentMember);
				session.setAttribute("rank", teamService.getRank(currentMember.getTeam(), currentMember.getId()));
				return true;
			} else {
				System.out.println("unAuth user access : " + dto);
				return false;
			}
		} else {
			System.out.println("login failed : " + dto);
			return false;
		}
	}

	@PostMapping("/createMember")
	public Long createMember(@RequestBody MemberCreateRequestDto dto) throws Exception {
		System.out.println("RestController - createMember : " + dto);
		return memberService.createMember(dto);
	}
	


	@PostMapping("/validateId")
	public boolean validateId(@RequestBody MemberCreateRequestDto dto) throws Exception {
		System.out.println("RestController - validateId : " + dto);
		if (memberService.validateMember(dto)) {
			return true;
		} else {
			return false;
		}
	}

	@PostMapping("/validateTeam")
	public boolean validateTeam(@RequestBody TeamCreateRequestDto dto) throws Exception {
		System.out.println("RestController - validateTeam : " + dto);
		if (teamService.validateTeam(dto)) {
			return true;
		} else {
			return false;
		}
	}

	@GetMapping("/getMemberDetail")
	public ModelAndView getMemberDetail(@RequestParam String id, HttpServletRequest request) throws Exception {
		System.out.println("RestController - getMemberDetail : " + id);
		ModelAndView mav = new ModelAndView();
		Member member = memberService.getMemberById(id);
		mav.addObject(member);
		mav.setViewName("member/mypage.html");
		return mav;
	}

	@GetMapping("/mypage")
	public ModelAndView loginMypage(HttpSession session) {
		System.out.println("RestController - loginMyPage : " + session);
		return new ModelAndView("member/mypage.html");
	}

	@GetMapping("/getTeamDetail")
	public ModelAndView loginTeampage(@RequestParam String team) {
		System.out.println("RestController - loginTeampage : " + team);
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
