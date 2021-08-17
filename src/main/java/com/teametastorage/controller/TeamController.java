package com.teametastorage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teametastorage.domain.Member;
import com.teametastorage.domain.Team;
import com.teametastorage.domain.TeamDetail;
import com.teametastorage.domain.TeamNotice;
import com.teametastorage.dto.TeamDetailUpdateRequestDto;
import com.teametastorage.service.MemberService;
import com.teametastorage.service.TeamService;
import com.teametastorage.dto.*;

@RestController
@RequestMapping("/team")
public class TeamController {

	@Autowired
	TeamService teamService;
	
	@Autowired
	MemberService memberService;

	/**
	 * 팀 상세정보
	 * 
	 * @param model
	 * @param team
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/teamdetail/{team}", method = RequestMethod.GET)
	public ModelAndView teamDetail(Model model, @PathVariable("team") String team, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TeamDetail teamDetail = teamService.getTeamDetail(team);
		mav.addObject("team", teamDetail);
		mav.setViewName("team/teamdetail.html");
		return mav;
	}

	/**
	 * 팀 정보 수정
	 * 
	 * @param model
	 * @param team
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/teamdetail/{team}", method = RequestMethod.POST)
	public String updateTeamDetail(Model model, @PathVariable("team") String team,
			@RequestBody TeamDetailUpdateRequestDto dto) {
		System.out.println(team + " -> " + dto);
		if (teamService.updateTeamDetail(dto)) {
			return "success";
		}
		return "fail";
	}

	@RequestMapping(value = "/teamboard/{team}", method = RequestMethod.GET)
	public ModelAndView teamBoard(Model model, @PathVariable("team") String team, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<Team> teamlist = new ArrayList<>();
		teamlist = teamService.getAllMember(team);
		mav.addObject("teamlist", teamlist);
		mav.setViewName("member/listMembers.html");
		return mav;
	}

	@RequestMapping(value = "/teamlist/{team}", method = RequestMethod.GET)
	public ModelAndView teamList(Model model, @PathVariable("team") String team, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<Team> teamlist = new ArrayList<>();
		teamlist = teamService.getAllMember(team);
		mav.addObject("teamlist", teamlist);
		mav.setViewName("member/teampage.html");
		return mav;
	}

	/**
	 * 팀 공지사항 게시판
	 * 읽기 -> 팀 소속 전체
	 * 쓰기 -> rank("admin")
	 * 수정 -> rank("admin")
	 * 삭제 -> rank("admin")
	 * @param model
	 * @param page
	 * @param size
	 * @param team
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/teamnotice/{team}", method = RequestMethod.GET)
	public ModelAndView teamNotice(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @PathVariable("team") String team,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		Page<?> boardPage = teamService.findPaginated(PageRequest.of(currentPage - 1, pageSize), team);
		mav.addObject("boardPage", boardPage);
		int totalPages = boardPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			mav.addObject("pageNumbers", pageNumbers);
		}
		mav.addObject("team", team);
		mav.addObject("pageNumberForward", currentPage - 1);
		mav.addObject("pageNumberNext", currentPage + 1);
		mav.addObject("pageNumberCurrent", currentPage);
		mav.addObject("pageNumberEnd", totalPages);
		String viewName = "team/teamnotice";
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/teamnotice/{team}", method = RequestMethod.PUT)
	public boolean putTeamNotice(Model model, @PathVariable("team") String team,
			@RequestBody TeamNoticeCreateRequestDto dto, HttpServletRequest request) {
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		dto.setSaveId(sessionMember.getId());
		dto.setSaveName(sessionMember.getName());
		dto.setSaveTeam(sessionMember.getTeam());
		if (teamService.createTeamNotice(dto)) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/teamnotice/{team}/{id}", method = RequestMethod.GET)
	public ModelAndView getTeamNotice(Model model, @PathVariable("team") String team,@PathVariable("id") String id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		TeamNotice teamNotice = teamService.getTeamNotice(Long.parseLong(id));
		mav.addObject("teamnotice", teamNotice);
		String viewName = "team/getteamnotice";
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/teamnotice/{team}/{id}", method = RequestMethod.POST)
	public boolean postTeamNotice(Model model, @PathVariable("team") String team,@PathVariable("id") String id,
			@RequestBody TeamNoticeUpdateRequestDto dto, HttpServletRequest request) {
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		dto.setTeamNoticeSeq(Long.parseLong(id));
		dto.setSaveId(sessionMember.getId());
		dto.setSaveName(sessionMember.getName());
		dto.setSaveTeam(sessionMember.getTeam());
		if (teamService.updateTeamNotice(dto)) {
			return true;
		}
		return false;
	}
	

	@RequestMapping(value = "/teamnotice/{team}/{id}", method = RequestMethod.DELETE)
	public boolean deleteTeamNotice(Model model, @PathVariable("team") String team,@PathVariable("id") String id,
			HttpServletRequest request) {
		if (teamService.deleteTeamNotice(Long.parseLong(id))) {
			return true;
		}
		return false;
	}
	
	
	
	

	@RequestMapping(value = "/teamunname/{team}", method = RequestMethod.GET)
	public ModelAndView teamUnname(Model model, @PathVariable("team") String team, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<Team> teamlist = new ArrayList<>();
		teamlist = teamService.getAllMember(team);
		mav.addObject("teamlist", teamlist);
		mav.setViewName("member/listMembers.html");
		return mav;
	}
	
	
	@RequestMapping(value = "/teammanage/{team}", method = RequestMethod.GET)
	public ModelAndView teamManage(Model model, @PathVariable("team") String team, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<Team> teamlist = new ArrayList<>();
		teamlist = teamService.getAllMember(team);
		mav.addObject("teamlist", teamlist);
		mav.setViewName("member/listMembers.html");
		return mav;
	}
	
	
	
	
	
	
	
	

}
