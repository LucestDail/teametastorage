package com.teametastorage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teametastorage.domain.Member;
import com.teametastorage.domain.Meta;
import com.teametastorage.dto.MetaCreateRequestDto;
import com.teametastorage.service.MetaService;

@RestController
public class MetaController {

	@Autowired
	MetaService metaService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@GetMapping("/metasearch")
	public ModelAndView loginIndex(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/index.html");
		return mav;
	}
	
	@GetMapping("/getAllMeta")
	public ModelAndView loginGetAllMeta(HttpServletRequest request) throws Exception {
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
		ModelAndView mav = new ModelAndView();
		Meta meta = metaService.getMetaDetail(Long.parseLong(id));
		mav.addObject("meta", meta);
		mav.setViewName("meta/metainfo.html");
		return mav;
	}

	@GetMapping("/failurl")
	public ModelAndView loginGetFailUrl(@RequestParam String id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("id", id);
		mav.setViewName("meta/failurl.html");
		return mav;
	}

	@GetMapping("/insertMeta")
	public ModelAndView loginInsertMetaForm() {
		return new ModelAndView("meta/insertmeta.html");
	}

	@PostMapping("/insertMeta")
	public String loginInsertMeta(@RequestBody MetaCreateRequestDto dto, HttpServletRequest request) throws Exception {
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		return metaService.createMeta(dto, sessionMember);
	}

	@GetMapping("/metaUpdate")
	public ModelAndView loginUpdateMetaForm(@RequestParam String id, HttpServletRequest request) {
		Meta meta = metaService.getMetaDetail(Long.parseLong(id));
		ModelAndView mav = new ModelAndView();
		System.out.println(meta);
		mav.addObject("meta", meta);
		mav.setViewName("meta/metaupdate.html");
		return mav;
	}

	@PostMapping("/metaUpdate")
	public String loginUpdateMeta(@RequestBody Meta inputMeta, HttpServletRequest request) {
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		if (metaService.updateMeta(inputMeta, sessionMember)) {
			return "success";
		}
		return "fail";
	}

	@GetMapping("/metaDelete")
	public ModelAndView loginDeleteMeta(@RequestParam String id) {
		metaService.deleteMeta(Long.parseLong(id));
		return new ModelAndView("main/index.html");
	}

}
