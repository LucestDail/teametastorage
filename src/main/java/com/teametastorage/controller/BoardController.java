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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teametastorage.domain.Board;
import com.teametastorage.domain.Comment;
import com.teametastorage.domain.Member;
import com.teametastorage.dto.BoardCreateRequestDto;
import com.teametastorage.dto.BoardUpdateRequestDto;
import com.teametastorage.dto.CommentCreateRequestDto;
import com.teametastorage.service.BoardService;
import com.teametastorage.service.CommentService;

@RestController
public class BoardController {

	@Autowired
	BoardService boardService;

	@Autowired
	CommentService commentService;

	@GetMapping("/insertBoard")
	public ModelAndView insertBoard() {
		System.out.println("RestController - insertBoard");
		return new ModelAndView("board/insertboard.html");
	}

	@PostMapping("/insertBoard")
	public boolean insertBoard(@RequestBody BoardCreateRequestDto dto, HttpServletRequest request) throws Exception {
		System.out.println("RestController - insertBoard : " + dto + " : " + request);
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		if (boardService.insertBoard(dto, sessionMember)) {
			return true;
		}
		return false;
	}

	@GetMapping("/updateBoard")
	public ModelAndView updateBoard(@RequestParam String id, HttpServletRequest request) {
		System.out.println("RestController - updateBoard : ");
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		String team = sessionMember.getTeam();
		Board board = boardService.getBoardDetail(Long.parseLong(id), team);
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("board/updateboard.html");
		return mav;
	}

	@PostMapping("/updateBoard")
	public String updateBoard(@RequestBody BoardUpdateRequestDto dto, HttpServletRequest request) {
		System.out.println("RestController - updateboard : " + dto + " : " + request);
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		if (boardService.updateBoard(dto, sessionMember)) {
			return "success";
		}
		return "fail";
	}

	@GetMapping("/boardlist")
	public ModelAndView boardlist(HttpServletRequest request) {
		System.out.println("RestController - boardlist : " + request);
		ModelAndView mav = new ModelAndView();
		List<Board> boardlist = new ArrayList<>();
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		String team = sessionMember.getTeam();
		boardlist = boardService.getAllBoard(team);
		mav.addObject("boardlist", boardlist);
		mav.setViewName("board/boardlist.html");
		return mav;
	}

	@GetMapping("/infoBoard")
	public ModelAndView infoBoard(@RequestParam String id, HttpServletRequest request) {
		System.out.println("RestController - infoBoard : " + id + " : " + request);
		ModelAndView mav = new ModelAndView();
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		String team = sessionMember.getTeam();
		Board board = boardService.getBoardDetail(Long.parseLong(id), team);
		List<Comment> commentlist = new ArrayList<>();
		commentlist = commentService.getAllComment(board, sessionMember);
		mav.addObject("board", board);
		mav.addObject("commentlist", commentlist);
		System.out.println("commentlist : " + commentlist);
		mav.setViewName("board/infoboard.html");
		return mav;
	}

	@GetMapping("/deleteBoard")
	public ModelAndView deleteBoard(@RequestParam String id, HttpServletRequest request) {
		System.out.println("RestController - deleteBoard : " + id);
		boardService.deleteBoard(id);
		return boardlist(request);
	}

	@PostMapping("/insertComment")
	public String insertComment(@RequestBody CommentCreateRequestDto dto, HttpServletRequest request) {
		System.out.println("Restcontroller - insertComment : " + dto + " : " + request);
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		if (commentService.createComment(dto, sessionMember)) {
			return "success";
		}
		return "fail";
	}

	@GetMapping("/deleteComment")
	public ModelAndView deleteComment(@RequestParam String id, HttpServletRequest request) {
		System.out.println("RestController - deleteComment : " + id);
		String boardId = commentService.getCommentDetail(id);
		commentService.deleteComment(id);
		return infoBoard(boardId, request);
	}

	@RequestMapping(value = "/listBoards", method = RequestMethod.GET)
	public ModelAndView listBoard(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("keyword") Optional<String> keyword,
			HttpServletRequest request) {
		System.out.println("BoardController - listBoard : " + page + " : " + size + " : " + keyword + " : " + request);
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		String currentKeyword = keyword.orElse(null);
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		String team = sessionMember.getTeam();
		ModelAndView mav = new ModelAndView();
		Page<Board> boardPage = boardService.findPaginated(PageRequest.of(currentPage - 1, pageSize), team, keyword);
		mav.addObject("boardPage", boardPage);
		int totalPages = boardPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			mav.addObject("pageNumbers", pageNumbers);
		}
		mav.addObject("pageNumberForward", currentPage - 1);
		mav.addObject("pageNumberNext", currentPage + 1);
		mav.addObject("pageNumberCurrent", currentPage);
		mav.addObject("pageNumberEnd", totalPages);
		mav.addObject("keyword", currentKeyword);
		mav.setViewName("board/listBoards.html");
		return mav;
	}
}
