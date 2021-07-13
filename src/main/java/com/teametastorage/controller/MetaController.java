package com.teametastorage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	@GetMapping("/metasearch")
	public ModelAndView loginIndex(Model model) {
		System.out.println("RestController - metasearch : " + model);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/index.html");
		return mav;
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
