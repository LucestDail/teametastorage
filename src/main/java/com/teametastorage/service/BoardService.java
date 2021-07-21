package com.teametastorage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		for (Board targetBoard : currentBoardList) {
			if (targetBoard.getSaveTeam().equals(team)) {
				targetBoardList.add(targetBoard);
			}
		}
		return targetBoardList;
	}

	public List<Board> getAllBoardReverse(String team) {
		List<Board> currentBoardList = boardRepository.findAll();
		List<Board> targetBoardList = new ArrayList<>();
		for (Board targetBoard : currentBoardList) {
			if (targetBoard.getSaveTeam().equals(team)) {
				targetBoardList.add(targetBoard);
			}
		}
		Collections.reverse(targetBoardList);
		return targetBoardList;
	}

	public List<Board> searchBoard(String team, String keyword) {
		System.out.println("BoardService - searchBoard : " + team + " : " + keyword);
		List<Board> currentBoardList = getAllBoardReverse(team);
		List<Board> targetBoardList = new ArrayList<>();
		String targetKeyword = keyword;
		for (Board targetBoard : currentBoardList) {
			if (targetBoard.getTitle().contains(targetKeyword) || targetBoard.getContent().contains(targetKeyword)
					|| targetBoard.getSaveName().contains(targetKeyword)) {
				targetBoardList.add(targetBoard);
			}
		}
		return targetBoardList;
	}

	public boolean updateBoard(BoardUpdateRequestDto dto, Member sessionMember) {
		boardRepository.update(dto.getBoardSeq(), dto.getTitle(), dto.getContent(), sessionMember.getTeam(),
				sessionMember.getName(), sessionMember.getId());
		return true;
	}


	public boolean insertBoard(BoardCreateRequestDto dto, Member sessionMember) {
		dto.setSaveName(sessionMember.getName());
		dto.setSaveTeam(sessionMember.getTeam());
		dto.setSaveId(sessionMember.getId());
		if (Objects.isNull(boardRepository.save(dto.toEntity()))) {
			return false;
		}
		return true;
	}

	public Page<Board> findPaginated(Pageable pageable, String team, Optional<String> keyword) {
		List<Board> boards = getAllBoardReverse(team);
		if (keyword.isPresent()) {
			boards = searchBoard(team, keyword.get());
		} else {
			boards = getAllBoardReverse(team);
		}
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Board> list;

		if (boards.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, boards.size());
			list = boards.subList(startItem, toIndex);
		}

		Page<Board> boardPage = new PageImpl<Board>(list, PageRequest.of(currentPage, pageSize), boards.size());

		return boardPage;
	}

	public void addCount(Long boardSeq) {
		boardRepository.addCount(boardSeq);
	}

	public void addGood(String id) {
		boardRepository.addGood(Long.parseLong(id));

	}

	public void minusGood(String id) {
		boardRepository.minusGood(Long.parseLong(id));

	}

	public Page<Board> findPaginated(Pageable pageable, String team, Optional<String> keyword, String category) {
		List<Board> boards = getAllBoardReverse(team);
		if (keyword.isPresent()) {
			boards = searchBoard(category, team, keyword.get());
		} else {
			boards = getAllBoardReverse(category, team);
		}
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Board> list;

		if (boards.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, boards.size());
			list = boards.subList(startItem, toIndex);
		}

		Page<Board> boardPage = new PageImpl<Board>(list, PageRequest.of(currentPage, pageSize), boards.size());

		return boardPage;
	}

	private List<Board> getAllBoardReverse(String category, String team) {
		List<Board> currentBoardList = boardRepository.findAll();
		List<Board> targetBoardList = new ArrayList<>();
		for (Board targetBoard : currentBoardList) {
			if (targetBoard.getSaveTeam().equals(team) && targetBoard.getCategory().equals(category)) {
				targetBoardList.add(targetBoard);
			}
		}
		Collections.reverse(targetBoardList);
		return targetBoardList;
	}

	private List<Board> searchBoard(String category, String team, String keyword) {
		List<Board> currentBoardList = getAllBoardReverse(team);
		List<Board> targetBoardList = new ArrayList<>();
		String targetKeyword = keyword;
		for (Board targetBoard : currentBoardList) {
			if ((targetBoard.getTitle().contains(targetKeyword) || targetBoard.getContent().contains(targetKeyword)
					|| targetBoard.getSaveName().contains(targetKeyword))
					&& targetBoard.getCategory().equals(category)) {
				targetBoardList.add(targetBoard);
			}
		}
		return targetBoardList;
	}

	public Board getBoardDetail(long seq) {
		return boardRepository.getById(seq);
	}

	public boolean insertBoard(BoardCreateRequestDto dto, Member sessionMember, String category) {
		dto.setSaveName(sessionMember.getName());
		dto.setSaveTeam(sessionMember.getTeam());
		dto.setSaveId(sessionMember.getId());

		switch (category) {
		case "team":
			if (Objects.isNull(boardRepository.save(dto.toEntityTeam()))) {
				return false;
			}
			break;
		case "unname":
			if (Objects.isNull(boardRepository.save(dto.toEntityUnname()))) {
				return false;
			}
			break;
		case "personal":
			if (Objects.isNull(boardRepository.save(dto.toEntityPersonal()))) {
				return false;
			}
			break;
		case "work":
			if (Objects.isNull(boardRepository.save(dto.toEntityWork()))) {
				return false;
			}
			break;
		default:
			return false;
		}
		return true;
	}
	
	public boolean updateBoard(BoardUpdateRequestDto dto, Member sessionMember, String category) {
		dto.setSaveName(sessionMember.getName());
		dto.setSaveTeam(sessionMember.getTeam());
		dto.setSaveId(sessionMember.getId());
		dto.setCategory(category);
		Board currentBoard = boardRepository.getById(dto.getBoardSeq());
		dto.setGood(currentBoard.getGood());
		dto.setCount(currentBoard.getCount());
		if(Objects.isNull(boardRepository.saveAndFlush(dto.toEntity()))){
			return false;
		}
		return true;
	}
	
	public boolean deleteBoardBySeq(long seq) {
		boardRepository.deleteById(seq);
		if(Objects.isNull(boardRepository.findById(seq))) {
			return true;
		}
		return false;
		
	}

}
