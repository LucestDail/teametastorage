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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teametastorage.domain.Board;
import com.teametastorage.domain.Policy;
import com.teametastorage.dto.PolicyCreateRequestDto;
import com.teametastorage.service.QnaService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnaController {
	
	@Autowired
	QnaService qnaService;

	@GetMapping("/getPolicy")
	public ModelAndView getPolicyList(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		String category = "Policy";
		Page<?> boardPage = qnaService.findPaginated(PageRequest.of(currentPage - 1, pageSize), category);
		int totalPages = boardPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			mav.addObject("pageNumbers", pageNumbers);
		}
		mav.addObject("pageNumberForward", currentPage - 1);
		mav.addObject("pageNumberNext", currentPage + 1);
		mav.addObject("pageNumberCurrent", currentPage);
		mav.addObject("pageNumberEnd", totalPages);
		mav.setViewName("qna/policy");
		return mav;
	}
	
	@GetMapping("/putPolicy")
	public ModelAndView putPolicy() {
		return new ModelAndView("policy/putPolicy.html");
	}
	
	@PostMapping("/putPolicy")
	public boolean postPutPolicy(@RequestBody PolicyCreateRequestDto dto) {
		if(qnaService.insertPolicy(dto)) {
			return true;
		}
		return false;
	}

	@GetMapping("/getService")
	public ModelAndView service(Model mode) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("qna/service");
		return mav;
	}

	@GetMapping("/getTech")
	public ModelAndView tech(Model mode) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("qna/tech");
		return mav;
	}

	@GetMapping("/getUsually")
	public ModelAndView usually(Model mode) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("qna/usually");
		return mav;
	}

	@GetMapping("/contact")
	public ModelAndView contact(Model mode) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("qna/contact");
		return mav;
	}

}
