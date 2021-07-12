package com.teametastorage.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teametastorage.domain.Board;
import com.teametastorage.domain.Member;
import com.teametastorage.service.BoardService;

@RestController
public class BoardController {

	@Autowired
	BoardService boardService;

	@RequestMapping(value = "/listBoards", method = RequestMethod.GET)
	public ModelAndView listBoard(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, HttpServletRequest request) {
		System.out.println("BoardController - listBoard : " + page + " : " + size + " : " + request);
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Member sessionMember = (Member) request.getSession().getAttribute("member");
		String team = sessionMember.getTeam();

		ModelAndView mav = new ModelAndView();

		Page<Board> boardPage = boardService.findPaginated(PageRequest.of(currentPage - 1, pageSize), team);
		System.out.println("boardPage : " + boardPage);
		mav.addObject("boardPage", boardPage);

		int totalPages = boardPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			mav.addObject("pageNumbers", pageNumbers);
			System.out.println("pageNumbers : " + pageNumbers);
		}
		mav.addObject("pageNumberForward",currentPage-1);
		mav.addObject("pageNumberNext",currentPage+1);
		mav.addObject("pageNumberCurrent",currentPage);
		mav.addObject("pageNumberEnd",totalPages);
		mav.setViewName("board/listBoards.html");
		return mav;
	}
}
