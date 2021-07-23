package com.teametastorage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teametastorage.domain.Board;
import com.teametastorage.domain.Comment;
import com.teametastorage.domain.Good;
import com.teametastorage.domain.Member;
import com.teametastorage.dto.BoardCreateRequestDto;
import com.teametastorage.dto.BoardUpdateRequestDto;
import com.teametastorage.dto.CommentCreateRequestDto;
import com.teametastorage.dto.GoodCreateRequestDto;
import com.teametastorage.service.BoardService;
import com.teametastorage.service.CommentService;
import com.teametastorage.service.GoodService;

@RestController
@RequestMapping("/board")
public class BoardRestController {

	@Autowired
	BoardService boardService;

	@Autowired
	CommentService commentService;

	@Autowired
	GoodService goodService;

	/*
	 * Get all board information with pagination
	 * 
	 */
	@RequestMapping(value = "/{category}", method = RequestMethod.GET)
	public ModelAndView listBoard(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("keyword") Optional<String> keyword,
			HttpServletRequest request, @PathVariable("category") String category) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		String currentKeyword = keyword.orElse(null);
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		String team = sessionMember.getTeam();
		ModelAndView mav = new ModelAndView();
		Page<Board> boardPage = boardService.findPaginated(PageRequest.of(currentPage - 1, pageSize), team, keyword,
				category);
		mav.addObject("boardPage", boardPage);
		int totalPages = boardPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			mav.addObject("pageNumbers", pageNumbers);
		}
		mav.addObject("category", category);
		mav.addObject("pageNumberForward", currentPage - 1);
		mav.addObject("pageNumberNext", currentPage + 1);
		mav.addObject("pageNumberCurrent", currentPage);
		mav.addObject("pageNumberEnd", totalPages);
		mav.addObject("keyword", currentKeyword);
		mav.setViewName("board/listBoards.html");
		return mav;
	}

	/*
	 * Get one board information
	 */
	@RequestMapping(value = "/{category}/{seq}", method = RequestMethod.GET)
	public ModelAndView infoBoard(HttpServletRequest request,
			@PathVariable("category") String category, @PathVariable("seq") String seq) {
		ModelAndView mav = new ModelAndView();
		boardService.addCount(Long.parseLong(seq));
		Board board = boardService.getBoardDetail(Long.parseLong(seq));
		List<Comment> commentlist = new ArrayList<>();
		commentlist = commentService.getAllComment(board);
		mav.addObject("seq", seq);
		mav.addObject("category", category);
		mav.addObject("board", board);
		mav.addObject("commentlist", commentlist);
		mav.setViewName("board/infoboard.html");
		return mav;
	}

	/*
	 * Write board with modal from listBoard
	 */
	@RequestMapping(value = "/{category}", method = RequestMethod.PUT)
	public boolean insertBoard(@RequestBody BoardCreateRequestDto dto, HttpServletRequest request,
			@PathVariable("category") String category) throws Exception {
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		if (boardService.insertBoard(dto, sessionMember,category)) {
			return true;
		}
		return false;
	}

	/*
	 * update board with modal from infoBoard
	 */
	@RequestMapping(value = "/{category}/{seq}", method = RequestMethod.POST)
	public boolean updateBoard(@RequestBody BoardUpdateRequestDto dto, HttpServletRequest request,
			@PathVariable("category") String category, @PathVariable("seq") String seq) {
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		if (boardService.updateBoard(dto, sessionMember, category)) {
			return true;
		}
		return false;
	}

	/*
	 * delete board with modal from infoBoard
	 */
	@RequestMapping(value = "/{category}/{seq}", method = RequestMethod.DELETE)
	public boolean deleteBoard(HttpServletRequest request,
			@PathVariable("category") String category, @PathVariable("seq") String seq) {
		boardService.deleteBoardBySeq(Long.parseLong(seq));
		return false;
	}

	/*
	 * to good board from infoBoard
	 */
	@RequestMapping(value = "/{category}/{seq}/good", method = RequestMethod.GET)
	public ModelAndView goodBoard(Model model, HttpServletRequest request,
			@PathVariable("category") String category, @PathVariable("seq") String seq) {
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		if (goodService.checkGood(sessionMember.getId(), Long.parseLong(seq))) {
			boardService.minusGood(seq);
			Good targetGood = goodService.getTargetGood(sessionMember.getId(), Long.parseLong(seq));
			goodService.deleteGood(targetGood.getGoodSeq());
		} else {
			boardService.addGood(seq);
			GoodCreateRequestDto dto = new GoodCreateRequestDto();
			dto.setBoardId(seq);
			dto.setGoodId(sessionMember.getId());
			dto.setGoodName(sessionMember.getName());
			goodService.createGood(dto);
		}
		return infoBoard(request,category,seq);
	}
}
