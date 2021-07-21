package com.teametastorage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.teametastorage.domain.Member;
import com.teametastorage.dto.CommentCreateRequestDto;
import com.teametastorage.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;

	/*
	 * insert comment
	 */
	@RequestMapping(value = "/{category}/{seq}", method = RequestMethod.PUT)
	public String insertComment(@RequestBody CommentCreateRequestDto dto, HttpServletRequest request,
			@PathVariable("category") String category, @PathVariable("seq") String seq) {
		Member sessionMember = (Member) request.getSession().getAttribute("member");
		if (commentService.createComment(dto, sessionMember)) {
			return "success";
		}
		return "fail";
	}

	@RequestMapping(value = "/{category}/{seq}", method = RequestMethod.DELETE)
	public ModelAndView deleteComment(@RequestParam String id, HttpServletRequest request,
			@PathVariable("category") String category, @PathVariable("seq") String seq) {
		String boardId = commentService.getCommentDetail(id);
		commentService.deleteComment(id);
		return null;
	}

}
