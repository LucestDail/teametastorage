package com.teametastorage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.teametastorage.domain.Board;
import com.teametastorage.domain.Comment;
import com.teametastorage.domain.Member;
import com.teametastorage.dto.CommentCreateRequestDto;
import com.teametastorage.repository.CommentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommentService {
	
	private CommentRepository commentRepository;

	public void deleteComment(String id) {
		System.out.println("CommentRepository - deleteComment : " + id);
		// TODO Auto-generated method stub
		commentRepository.deleteById(Long.parseLong(id));
		commentRepository.flush();
	}

	public boolean createComment(CommentCreateRequestDto dto, Member sessionMember) {
		System.out.println("RestController - createcomment : " + dto + " : " + sessionMember);
		dto.setSaveName(sessionMember.getName());
		dto.setSaveTeam(sessionMember.getTeam());
		dto.setSaveId(sessionMember.getId());
		if(Objects.isNull(commentRepository.save(dto.toEntity()))){
			commentRepository.flush();
			return false;
		}
		return true;
	}

	public List<Comment> getAllComment(Board board) {
		List<Comment> currentCommentList = commentRepository.findAll();
		List<Comment> targetCommentList = new ArrayList<>();
		for(Comment targetComment : currentCommentList) {
			if(targetComment.getBoardId().equals(String.valueOf(board.getBoardSeq()))) {
				targetCommentList.add(targetComment);
			}
		}
		return targetCommentList;
	}

	public String getCommentDetail(String id) {
		System.out.println("CommentService - getCommentDetail : " + id);
		List<Comment> currentCommentList = commentRepository.findAll();
		String boardSeq = null;
		for(Comment targetComment : currentCommentList) {
			if(targetComment.getCommentSeq().equals(Long.parseLong(id))){
				boardSeq = targetComment.getBoardId();
			}
		}
		return boardSeq;
	}
}
