package com.teametastorage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teametastorage.domain.Board;
import com.teametastorage.domain.Policy;
import com.teametastorage.dto.PolicyCreateRequestDto;
import com.teametastorage.repository.PolicyRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class QnaService {

	private PolicyRepository policyRepository;

	public List<Policy> getAllPolicy() {
		return policyRepository.findAll();
	}

	public Page<?> findPaginated(Pageable pageable, String category) {
		List<?> listQna = new ArrayList<>();
		switch (category) {
		case "Policy":
			listQna = getAllPolicy();
			break;
		default:
			break;
		}
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<?> list;

		if (listQna.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, listQna.size());
			list = listQna.subList(startItem, toIndex);
		}

		Page<?> boardPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), listQna.size());

		return boardPage;
	}

	public boolean insertPolicy(PolicyCreateRequestDto dto) {
		// TODO Auto-generated method stub
		return false;
	}

}
