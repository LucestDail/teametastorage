package com.teametastorage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.teametastorage.domain.Board;
import com.teametastorage.domain.Comment;
import com.teametastorage.domain.Member;
import com.teametastorage.domain.Meta;
import com.teametastorage.domain.Team;
import com.teametastorage.dto.MemberCreateRequestDto;
import com.teametastorage.dto.MemberReadRequestDto;
import com.teametastorage.dto.MemberUpdateRequestDto;
import com.teametastorage.dto.MetaCreateRequestDto;
import com.teametastorage.dto.MetaReadRequestDto;
import com.teametastorage.dto.TeamCreateRequestDto;
import com.teametastorage.service.MemberService;
import com.teametastorage.service.MetaService;
import com.teametastorage.service.TeamService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class RestController {

	@Autowired
	MemberService memberService;

	@Autowired
	TeamService teamService;

	@Autowired
	MetaService metaService;

	@GetMapping("/hello")
	public String hello() {
		System.out.println("Restcontroller - hello : ");
		return "testing page";
	}

	@GetMapping("/")
	public ModelAndView login(Model model) {
		System.out.println("RestController - login : " + model);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login.html");
		return mav;
	}

	@GetMapping("/main")
	public ModelAndView loginMain(Model model) {
		System.out.println("RestController - loginMain : " + model);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/main.html");
		return mav;
	}

	@GetMapping("/metasearch")
	public ModelAndView loginIndex(Model model) {
		System.out.println("RestController - metasearch : " + model);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/index.html");
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
			session.setAttribute("member", memberService.getMemberById(dto.getId()));
			return true;
		} else {
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
		if (memberService.deleteMember(id)) {
			HttpSession session = request.getSession();
			session.invalidate();
			return new ModelAndView("member/login.html");
		}
		return new ModelAndView("member/mypage.html");
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return new ModelAndView("member/login.html");
	}

	@GetMapping("/getAllMeta")
	public ModelAndView loginGetAllMeta(HttpServletRequest request) throws Exception {
		System.out.println("RestController - loginGetAllMeta : " + request);
		ModelAndView mav = new ModelAndView();
		List<Meta> metalist = new ArrayList<>();
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		String team = sessionMember.getTeam();
		metalist = metaService.getAllMetaByTeam(team);
		mav.addObject("metalist", metalist);
		mav.setViewName("meta/metaSearchList.html");
		return mav;
	}

	@GetMapping("/metaSearchList")
	public ModelAndView getSearchMetaList(@RequestParam String id, HttpServletRequest request) {
		System.out.println("RestController - getSearchMetaList : " + id + " : " + request);
		ModelAndView mav = new ModelAndView();
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		String team = sessionMember.getTeam();
		List<Meta> metalist = metaService.getMetaListByName(id, team);
		System.out.println(metalist);
		mav.addObject("metalist", metalist);
		mav.setViewName("meta/metaSearchList.html");
		return mav;
	}

	@GetMapping("/getMetaInfo")
	public ModelAndView loginGetMetaInfo(@RequestParam String id, HttpServletRequest request) throws Exception {
		System.out.println("RestController - loginGetMetaInfo : " + id + " : " + request);
		ModelAndView mav = new ModelAndView();
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		String team = sessionMember.getTeam();
		Meta meta = metaService.getMetaDetail(Long.parseLong(id));
		mav.addObject("meta", meta);
		mav.setViewName("meta/metainfo.html");
		return mav;
	}

	@GetMapping("/failurl")
	public ModelAndView loginGetFailUrl(@RequestParam String id) {
		System.out.println("RestController - loginGetFailUrl : " + id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("id", id);
		mav.setViewName("meta/failurl.html");
		return mav;
	}

	@GetMapping("/insertMeta")
	public ModelAndView loginInsertMetaForm() {
		System.out.println("RestController - loginInsertMetaForm");
		return new ModelAndView("meta/insertmeta.html");
	}

	@PostMapping("/insertMeta")
	public String loginInsertMeta(@RequestBody MetaCreateRequestDto dto, HttpServletRequest request) throws Exception {
		System.out.println("RestController - loginInsertMeta : " + dto + " : " + request);
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		return metaService.createMeta(dto, sessionMember);
	}

	@GetMapping("/metaUpdate")
	public ModelAndView loginUpdateMetaForm(@RequestParam String id, HttpServletRequest request) {
		System.out.println("RestController - loginUpdateMetaForm : " + id + " : " + request);
		Meta meta = metaService.getMetaDetail(Long.parseLong(id));
		ModelAndView mav = new ModelAndView();
		System.out.println(meta);
		mav.addObject("meta", meta);
		mav.setViewName("meta/metaupdate.html");
		return mav;
	}

	@PostMapping("/metaUpdate")
	public String loginUpdateMeta(@RequestBody Meta inputMeta, HttpServletRequest request) {
		System.out.println("RestController - loginUpdateMeta : " + inputMeta + " : " + request);
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		if(metaService.updateMeta(inputMeta, sessionMember)) {
			return "success";
		}
		return "fail";
	}

	@GetMapping("/metaDelete")
	public ModelAndView loginDeleteMeta(@RequestParam String id) {
		metaService.deleteMeta(Long.parseLong(id));
		return new ModelAndView("main/index.html");
	}

	@GetMapping("/insertBoard")
	public ModelAndView insertBoard() {
		System.out.println("RestController - insertBoard");
		return new ModelAndView("board/insertboard.html");
	}

	/*
	 * @PostMapping("/insertBoard") public String insertBoard(@RequestBody Board
	 * board, HttpServletRequest request) throws Exception{ Member sessionMember =
	 * (Member) request.getSession().getAttribute("member"); return
	 * firebaseServiceBoard.insertBoard(board,sessionMember); }
	 * 
	 * @GetMapping("/updateBoard") public ModelAndView updateBoard(@RequestParam
	 * String id, HttpServletRequest request) { Member sessionMember = (Member)
	 * request.getSession().getAttribute("member"); String team =
	 * sessionMember.getTeam(); String searchid = id; Board board = null; try {
	 * board = firebaseServiceBoard.getBoardDetail(searchid,team); } catch
	 * (Exception e) { e.printStackTrace(); } ModelAndView mav = new ModelAndView();
	 * mav.addObject("board",board); mav.setViewName("board/updateboard.html");
	 * return mav; }
	 * 
	 * @PostMapping("/updateBoard") public String updateBoard(@RequestBody Board
	 * inputBoard, HttpServletRequest request) { System.out.println("update : " +
	 * inputBoard); Member sessionMember = (Member)
	 * request.getSession().getAttribute("member"); String team =
	 * sessionMember.getTeam(); String searchid = inputBoard.getId(); Board board =
	 * null; try { board = firebaseServiceBoard.getBoardDetail(searchid,team);
	 * if(firebaseServiceBoard.updateBoard(inputBoard, sessionMember) != null) {
	 * return "success"; } } catch (Exception e) { e.printStackTrace(); } return
	 * "fail"; }
	 * 
	 * @GetMapping("/boardlist") public ModelAndView boardlist(HttpServletRequest
	 * request) throws Exception{ ModelAndView mav = new ModelAndView(); List<Board>
	 * boardlist = new ArrayList<>(); Member sessionMember = (Member)
	 * request.getSession().getAttribute("member"); String team =
	 * sessionMember.getTeam(); boardlist = firebaseServiceBoard.getAllBoard(team);
	 * mav.addObject("boardlist",boardlist);
	 * mav.setViewName("board/boardlist.html"); return mav; }
	 * 
	 * @GetMapping("/infoBoard") public ModelAndView infoBoard(@RequestParam String
	 * id, HttpServletRequest request) throws Exception{ ModelAndView mav = new
	 * ModelAndView(); Member sessionMember = (Member)
	 * request.getSession().getAttribute("member"); String team =
	 * sessionMember.getTeam(); String searchid = id; Board board =
	 * firebaseServiceBoard.getBoardDetail(searchid,team); List<Comment> commentlist
	 * = new ArrayList<>(); commentlist =
	 * firebaseServiceComment.getAllComment(board,sessionMember);
	 * System.out.println(board); mav.addObject("board",board);
	 * mav.addObject("commentlist",commentlist);
	 * mav.setViewName("board/infoboard.html"); return mav; }
	 * 
	 * @GetMapping("/deleteBoard") public ModelAndView deleteBoard(@RequestParam
	 * String id) { try { firebaseServiceBoard.deleteBoard(id); } catch (Exception
	 * e) { e.printStackTrace(); } return new ModelAndView("main/main.html"); }
	 * 
	 * @PostMapping("/insertComment") public String insertComment(@RequestBody
	 * Comment comment, HttpServletRequest request) { System.out.println(comment);
	 * Member sessionMember = (Member) request.getSession().getAttribute("member");
	 * String team = sessionMember.getTeam(); String id = comment.getBoardId();
	 * Board board = null; try { board =
	 * firebaseServiceBoard.getBoardDetail(id,team); return
	 * firebaseServiceComment.insertComment(comment,board,sessionMember); } catch
	 * (Exception e) { e.printStackTrace(); } return null; }
	 * 
	 * @PostMapping("/deleteComment") public void deleteComment(@RequestBody Comment
	 * comment) { System.out.println("delete Comment : " + comment); try {
	 * firebaseServiceComment.deleteComment(comment.getId()); }catch (Exception e) {
	 * e.printStackTrace(); } }
	 */
	@PostMapping("/updateComment")
	public String updateComment(@RequestBody Comment comment, HttpServletRequest request) {
		System.out.println("RestController - updatecomment : " + comment);
		return null;
	}
}
