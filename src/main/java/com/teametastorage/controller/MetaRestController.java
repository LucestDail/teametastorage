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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teametastorage.domain.Member;
import com.teametastorage.domain.Meta;
import com.teametastorage.dto.MetaCreateRequestDto;
import com.teametastorage.dto.MetaUpdateRequestDto;
import com.teametastorage.service.MetaService;

@RestController
@RequestMapping("/meta")
public class MetaRestController {

	@Autowired
	MetaService metaService;

	/*
	 * meta read one view + object
	 */
	@RequestMapping(value = "/{team}/{seq}", method = RequestMethod.GET)
	public ModelAndView detailMeta(@PathVariable("seq") String seq, @PathVariable("team") String team,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("meta", metaService.getMeta(Long.parseLong(seq)));
		mav.addObject("team", team);
		mav.addObject("seq", seq);
		mav.setViewName("meta/metainfo");
		return mav;
	}
	@RequestMapping(value = "/{team}/{seq}/data", method = RequestMethod.GET)
	public Meta meta(@PathVariable("seq") String seq, @PathVariable("team") String team) {
		return metaService.getMeta(Long.parseLong(seq));
	}

	/*
	 * meta read all view + list
	 */
	@RequestMapping(value = "/{team}", method = RequestMethod.GET)
	public ModelAndView detailMetaAll(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam("keyword") Optional<String> keyword,
			HttpServletRequest request, @PathVariable("team") String team) {
		System.out.println("meta all invoke");
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		String currentKeyword = keyword.orElse(null);
		ModelAndView mav = new ModelAndView();
		Page<Meta> boardPage = metaService.findPaginated(PageRequest.of(currentPage - 1, pageSize), team, keyword);
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
		mav.setViewName("meta/metalist");
		return mav;
	}
	
	@RequestMapping(value = "/{team}/data", method = RequestMethod.GET)
	public List<Meta> metaAll(@PathVariable("team") String team) {
		return metaService.getAllMetaByTeam(team);
	}
	
	@RequestMapping(value = "/{team}/data/search", method = RequestMethod.GET)
	public List<Meta> metaAllKeyword(@PathVariable("team") String team, @RequestParam("keyword") String keyword){
		return metaService.getAllMetaByTitleToList(team,keyword);
	}

	/*
	 * meta create
	 */
	@RequestMapping(value = "/{team}", method = RequestMethod.PUT)
	public boolean putMeta(@PathVariable("team") String team, @RequestBody MetaCreateRequestDto dto,
			HttpServletRequest request) {
		Member member = (Member) request.getSession().getAttribute("member");
		return metaService.putMeta(dto, member);
	}

	/*
	 * meta update
	 */

	@RequestMapping(value = "/{team}/{seq}", method = RequestMethod.POST)
	public boolean postMeta(@PathVariable("team") String team, @PathVariable("seq") String seq,
			@RequestBody MetaUpdateRequestDto dto, HttpServletRequest request) {
		Member member = (Member) request.getSession().getAttribute("member");
		return metaService.postMeta(dto, seq, member);
	}

	/*
	 * meta delete
	 */

	@RequestMapping(value = "/{team}/{seq}", method = RequestMethod.DELETE)
	public boolean deleteMeta(@PathVariable("team") String team, @PathVariable("seq") String seq,
			HttpServletRequest request) {
		return metaService.deleteMeta(Long.parseLong(seq));
	}
}
