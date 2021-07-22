package com.teametastorage.controller;

import java.util.List;
import java.util.Objects;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teametastorage.domain.Qna;
import com.teametastorage.dto.QnaCreateRequestDto;
import com.teametastorage.dto.QnaUpdateRequestDto;
import com.teametastorage.service.QnaService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnaController {

	@Autowired
	QnaService qnaService;
	
	@RequestMapping(value = "/{category}", method = RequestMethod.GET)
	public ModelAndView getCategoryList(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @PathVariable("category") String category,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		String targetCategory = category;
		Page<?> boardPage = qnaService.findPaginated(PageRequest.of(currentPage - 1, pageSize), targetCategory);
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
		mav.addObject("category", targetCategory);
		String viewName = "qna/qna";
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/{category}/{seq}", method = RequestMethod.GET)
	public ModelAndView getCategoryOne(@PathVariable("category") String category, @PathVariable("seq") String seq) {
		ModelAndView mav = new ModelAndView();
		Qna targetQna = qnaService.getOne(Long.parseLong(seq));
		String viewName = "qna/qnainfo";
		mav.addObject("category", category);
		mav.addObject("seq", seq);
		mav.addObject("item", targetQna);
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/put{category}", method = RequestMethod.GET)
	public ModelAndView putForm(@PathVariable("category") String category) {
		ModelAndView mav = new ModelAndView();
		String viewName = "qna/qnaput";
		mav.addObject("category", category);
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/{category}", method = RequestMethod.PUT)
	public boolean putPolicy(@RequestBody QnaCreateRequestDto dto, HttpServletRequest request,
			@PathVariable("category") String category) {
		if (qnaService.insertQna(category,dto)) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/{category}/{seq}", method = RequestMethod.POST)
	public boolean postQna(@RequestBody QnaUpdateRequestDto dto, @PathVariable("category") String category, @PathVariable("seq") String seq, HttpServletRequest request) {
		if(qnaService.updateQna(category, seq, dto)) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/{category}/{seq}", method = RequestMethod.DELETE)
	public boolean deleteQna(@PathVariable("category") String category, @PathVariable("seq") String seq, HttpServletRequest request) {
		qnaService.deleteQna(seq);
		if(Objects.isNull(qnaService.getOne(Long.parseLong(seq)))){
			return true;
		}
		return false;
	}
}
