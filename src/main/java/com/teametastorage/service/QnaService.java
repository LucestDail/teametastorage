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
import com.teametastorage.domain.Qna;
import com.teametastorage.dto.QnaCreateRequestDto;
import com.teametastorage.dto.QnaUpdateRequestDto;
import com.teametastorage.repository.QnaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class QnaService {

	private QnaRepository qnaRepository;

	public Optional<Qna> getPolicy(Long policySeq) {
		return qnaRepository.findById(policySeq);
	}

	public Page<Qna> findPaginated(Pageable pageable, String category) {
		List<Qna> listAll = qnaRepository.findAll();
		List<Qna> listQna = new ArrayList<>();
		for (Qna target : listAll) {
			if (target.getCategory().equals(category)) {
				listQna.add(target);
			}
		}
		Collections.reverse(listQna);
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Qna> list;

		if (listQna.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, listQna.size());
			list = listQna.subList(startItem, toIndex);
		}

		Page<Qna> boardPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), listQna.size());

		return boardPage;
	}

	public Qna getPolicy(long seq) {
		return qnaRepository.getById(seq);
	}

	public boolean insertQna(String category, QnaCreateRequestDto dto) {
		switch (category) {
		case "policy":
			if (Objects.isNull(qnaRepository.save(dto.toEntityPolicy()))) {
				return false;
			}
			break;
		case "notice":
			if (Objects.isNull(qnaRepository.save(dto.toEntityNotice()))) {
				return false;
			}
			break;
		case "usually":
			if (Objects.isNull(qnaRepository.save(dto.toEntityUsually()))) {
				return false;
			}
			break;
		case "tech":
			if (Objects.isNull(qnaRepository.save(dto.toEntityTech()))) {
				return false;
			}
			break;
		case "service":
			if (Objects.isNull(qnaRepository.save(dto.toEntityService()))) {
				return false;
			}
			break;
		default:
			return false;
		}
		return true;
	}

	public Qna getOne(Long seq) {
		return qnaRepository.getById(seq);
	}

	public boolean updateQna(String category, String seq, QnaUpdateRequestDto dto) {
		// TODO Auto-generated method stub
		dto.setQnaSeq(Long.parseLong(seq));
		dto.setCategory(category);
		if (Objects.isNull(qnaRepository.saveAndFlush(dto.toEntity()))) {
			return false;
		}
		return true;
	}

	public void deleteQna(String seq) {
		qnaRepository.deleteById(Long.parseLong(seq));

	}

}
