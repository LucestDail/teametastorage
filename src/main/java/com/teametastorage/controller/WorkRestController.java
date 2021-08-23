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

import com.teametastorage.domain.Event;
import com.teametastorage.domain.Member;
import com.teametastorage.domain.Meta;
import com.teametastorage.domain.Work;
import com.teametastorage.dto.WorkCreateRequestDto;
import com.teametastorage.dto.WorkUpdateRequestDto;
import com.teametastorage.service.EventService;
import com.teametastorage.service.MetaService;
import com.teametastorage.service.WorkService;

@RestController
@RequestMapping("/work")
public class WorkRestController {
	
	@Autowired
	WorkService workService;
	
	@Autowired
	EventService eventService;
	
	@Autowired
	MetaService metaService;
	
	@RequestMapping(value = "/works", method = RequestMethod.GET)
	public List<Work> allWorks(HttpServletRequest request){
		Member currentMember = (Member) request.getSession().getAttribute("member");
		return workService.getWorkAll(currentMember.getTeam());
	}
	
	
	/*
	 * read one
	 */
	@RequestMapping(value = "/{team}/{seq}", method = RequestMethod.GET)
	public ModelAndView getWork(@PathVariable("team") String team, @PathVariable("seq") String seq) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("work", workService.getWork(Long.parseLong(seq)));
		String metalist = workService.getWork(Long.parseLong(seq)).getMetalist();
		String[] metalistArray = metalist.split(",");
		List<Meta> metalistIndex = new ArrayList<>();
		for(int i = 0; i < metalistArray.length; i++) {
			if(i == 0) {
				continue;
			}else {
				Meta meta = metaService.getMeta(Long.parseLong(metalistArray[i]));
				metalistIndex.add(meta);
			}
		}
		System.out.println(metalistIndex);
		mav.addObject("metalistIndex", metalistIndex);
		mav.addObject("team", team);
		mav.addObject("seq", seq);
		mav.setViewName("work/workinfo");
		return mav;
	}
	
	/*
	 * read all
	 * 
	 */
	@RequestMapping(value = "/{team}", method = RequestMethod.GET)
	public ModelAndView getWorkAll(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("keyword") Optional<String> keyword,
			HttpServletRequest request, @PathVariable("team") String team) {
		System.out.println("meta all invoke");
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		String currentKeyword = keyword.orElse(null);
		ModelAndView mav = new ModelAndView();
		Page<Work> boardPage = workService.findPaginated(PageRequest.of(currentPage - 1, pageSize), team, keyword);
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
		mav.addObject("keyword", currentKeyword);
		mav.setViewName("work/worklist");
		return mav;
	}
	
	/*
	 * create
	 * 
	 */
	@RequestMapping(value = "/{team}", method = RequestMethod.PUT)
	public boolean putWork(@RequestBody WorkCreateRequestDto dto, @PathVariable("team") String team, HttpServletRequest request) {
		return workService.putWork(dto,team,(Member)request.getSession().getAttribute("member"));
	}
	
	/*
	 * update
	 * 
	 */
	@RequestMapping(value = "/{team}/{seq}", method = RequestMethod.POST)
	public boolean postWork(@RequestBody WorkUpdateRequestDto dto, @PathVariable("team") String team, @PathVariable("seq") String seq,HttpServletRequest request) {
		return workService.postWork(dto,team,Long.parseLong(seq),(Member)request.getSession().getAttribute("member"));
	}
	
	/*
	 * 
	 * delete
	 */
	@RequestMapping(value = "/{team}/{seq}", method = RequestMethod.DELETE)
	public boolean deleteWork(@PathVariable("team") String team, @PathVariable("seq") String seq) {
		return workService.deleteWork(Long.parseLong(seq));
	}
}
