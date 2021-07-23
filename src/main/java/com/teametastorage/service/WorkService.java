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

import com.teametastorage.domain.Member;
import com.teametastorage.domain.Meta;
import com.teametastorage.domain.Work;
import com.teametastorage.dto.WorkCreateRequestDto;
import com.teametastorage.dto.WorkUpdateRequestDto;
import com.teametastorage.repository.MemberRepository;
import com.teametastorage.repository.WorkRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class WorkService {
	
	private WorkRepository workRepository;
	
	public Work getWork(long workSeq) {
		return workRepository.getById(workSeq);
	}
	
	public List<Work> getWorkAll(String team){
		List<Work> allWork = workRepository.findAll();
		List<Work> targetWork = new ArrayList<>();
		for(Work target : allWork) {
			if(target.getSaveTeam().equals(team)) {
				targetWork.add(target);
			}
		}
		return targetWork;
	}

	public boolean putWork(WorkCreateRequestDto dto, String team, Member member) {
		System.out.println("putWork : " + dto);
		return Objects.isNull(workRepository.save(dto.toEntity(member))) ? false : true;
	}

	public boolean postWork(WorkUpdateRequestDto dto, String team, long workseq, Member member) {
		dto.setWorkSeq(workseq);
		return Objects.isNull(workRepository.saveAndFlush(dto.toEntity(member))) ? false : true;
	}

	public boolean deleteWork(long workSeq) {
		workRepository.deleteById(workSeq);
		return Objects.isNull(workRepository.getById(workSeq)) ? true : false;
	}

	public Page<Work> findPaginated(Pageable pageable, String team, Optional<String> keyword) {
		List<Work> boards = new ArrayList<>();
		if (keyword.isPresent()) {
			boards = getWorkAll(team, keyword.get());
		} else {
			boards = getWorkAll(team);
		}
		Collections.reverse(boards);
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Work> list;

		if (boards.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, boards.size());
			list = boards.subList(startItem, toIndex);
		}

		Page<Work> boardPage = new PageImpl<Work>(list, PageRequest.of(currentPage, pageSize), boards.size());

		return boardPage;
	}

	private List<Work> getWorkAll(String team, String keyword) {
		List<Work> allWork = workRepository.findAll();
		List<Work> targetWork = new ArrayList<>();
		for(Work target : allWork) {
			if(target.getSaveTeam().equals(team) && (
					target.getTitle().contains(keyword)||
					target.getSaveName().contains(keyword)||
					target.getDescription().contains(keyword))) {
				targetWork.add(target);
			}
		}
		return targetWork;
	}
	

	
}
