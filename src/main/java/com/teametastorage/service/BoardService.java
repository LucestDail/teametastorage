package com.teametastorage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.teametastorage.domain.Board;
import com.teametastorage.domain.Member;
import com.teametastorage.dto.BoardCreateRequestDto;
import com.teametastorage.dto.BoardUpdateRequestDto;
import com.teametastorage.repository.BoardRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardService {

	private BoardRepository boardRepository;

	public Board getBoardDetail(Long boardSeq, String team) {
		return boardRepository.getById(boardSeq);
	}

	public void deleteBoard(String id) {
		boardRepository.deleteById(Long.parseLong(id));
	}

	public List<Board> getAllBoard(String team) {
		List<Board> currentBoardList = boardRepository.findAll();
		List<Board> targetBoardList = new ArrayList<>();
		for(Board targetBoard : currentBoardList) {
			if(targetBoard.getSaveTeam().equals(team)) {
				targetBoardList.add(targetBoard);
			}
		}
		return targetBoardList;
	}

	public boolean updateBoard(BoardUpdateRequestDto dto, Member sessionMember) {
		boardRepository.update(dto.getBoardSeq(), dto.getTitle(), dto.getContent(), sessionMember.getTeam(), sessionMember.getName());
		return true;
	}

	public boolean insertBoard(BoardCreateRequestDto dto, Member sessionMember) {
		dto.setSaveName(sessionMember.getName());
		dto.setSaveTeam(sessionMember.getTeam());
		if(Objects.isNull(boardRepository.save(dto.toEntity()))) {
			return false;
		}
		return true;
	}

}
