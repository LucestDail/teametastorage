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
import org.springframework.transaction.annotation.Transactional;

import com.teametastorage.domain.Board;
import com.teametastorage.domain.Member;
import com.teametastorage.domain.Meta;
import com.teametastorage.dto.MetaCreateRequestDto;
import com.teametastorage.dto.MetaUpdateRequestDto;
import com.teametastorage.repository.MetaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MetaService {

	private MetaRepository metaRepository;

	public List<Meta> getMetaListByName(String name, String team) {
		System.out.println("MetaService - getMetaListByName");
		List<Meta> findMetaList = new ArrayList<>();
		List<Meta> currentMetaList = metaRepository.findAll();
		
		return findMetaList;
	}

	public List<Meta> getAllMetaByTeam(String team) {
		System.out.println("MetaService - getAllMetaName : " + team);
		List<Meta> findMetaList = new ArrayList<>();
		List<Meta> currentMetaList = metaRepository.findAll();
		for (Meta targetMeta : currentMetaList) {
			if (targetMeta.getSaveTeam().equals(team)) {
				findMetaList.add(targetMeta);
			}
		}
		return findMetaList;
	}

	public List<Meta> getAllMetaByName(String name) {
		System.out.println("MetaService - getAllMetaByName : " + name);
		return null;
	}

	public Meta getMetaDetail(Long metaSeq) {
		System.out.println("MetaService - getMetaDetail : " + metaSeq);
		return metaRepository.getById(metaSeq);
	}

	public boolean updateMeta(Meta inputMeta, Member sessionMember) {
		System.out.println("MetaService - updateMeta : " + inputMeta + " : " + sessionMember);
		MetaUpdateRequestDto dto = new MetaUpdateRequestDto();
		dto.setSaveName(sessionMember.getName());
		dto.setSaveTeam(sessionMember.getTeam());
		return true;
	}

	public boolean deleteMeta(long id) {
		metaRepository.deleteById(id);
		if(Objects.isNull(metaRepository.findById(id))) {
			return true;
		}
		return false;
	}

	public Meta getMeta(long seq) {
		return metaRepository.getById(seq);
	}

	public List<Meta> getMetaAll(String team) {
		List<Meta> listMeta = metaRepository.findAll();
		List<Meta> targetMeta = new ArrayList<>();
		for(Meta target : listMeta) {
			if(target.getSaveTeam().equals(team)) {
				targetMeta.add(target);
			}
		}
		return targetMeta;
	}

	private List<Meta> getMetaAll(String team, String keyword) {
		List<Meta> listMeta = metaRepository.findAll();
		List<Meta> targetMeta = new ArrayList<>();
		for(Meta target : listMeta) {
			if(target.getSaveTeam().equals(team) && (
					target.getTitle().contains(keyword)
					|| target.getDescription().contains(keyword)
					|| target.getSaveName().contains(keyword)
					)) {
				targetMeta.add(target);
			}
		}
		return targetMeta;
	}
	
	public boolean putMeta(MetaCreateRequestDto dto, Member member) {
		return Objects.isNull(metaRepository.save(dto.toEntity(member))) ? false : true;
	}

	public boolean postMeta(MetaUpdateRequestDto dto, String seq, Member member) {
		dto.setMetaSeq(Long.parseLong(seq));
		return Objects.isNull(metaRepository.saveAndFlush(dto.toEntity(member))) ? false : true;
	}

	public Page<Meta> findPaginated(Pageable pageable, String team, Optional<String> keyword) {
		List<Meta> boards = new ArrayList<>();
		if (keyword.isPresent()) {
			boards = getMetaAll(team, keyword.get());
		} else {
			boards = getMetaAll(team);
		}
		Collections.reverse(boards);
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Meta> list;

		if (boards.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, boards.size());
			list = boards.subList(startItem, toIndex);
		}

		Page<Meta> boardPage = new PageImpl<Meta>(list, PageRequest.of(currentPage, pageSize), boards.size());

		return boardPage;
	}

	public String createMeta(MetaCreateRequestDto dto, Member sessionMember) {
		// TODO Auto-generated method stub
		return null;
	}


}
